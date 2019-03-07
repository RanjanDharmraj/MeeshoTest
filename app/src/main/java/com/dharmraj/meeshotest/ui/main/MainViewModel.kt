package com.dharmraj.meeshotest.ui.main

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.dharmraj.meeshotest.R
import com.dharmraj.meeshotest.exceptions.GithubException
import com.dharmraj.meeshotest.exceptions.NoInternetException
import com.dharmraj.meeshotest.network.repository.PullRequestRepository
import com.dharmraj.meeshotest.ui.base.BaseViewModel
import com.dharmraj.meeshotest.utils.SingleLiveEvent
import com.dharmraj.meeshotest.utils.extensions.applySchedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val context: Application,
    private val repository: PullRequestRepository) : BaseViewModel(context) {


    val githubOwnerName = MutableLiveData<String>()

    val githubRepoName = MutableLiveData<String>()

    var isLoading = MutableLiveData<Boolean>()

    val navigator = SingleLiveEvent<Boolean>()

    val snackbarText = SingleLiveEvent<String>()

    init {
        title.value = "Meesho Test"
    }


    fun onSubmitOnClick() {
        val ownerName = githubOwnerName.value
        val repoName = githubRepoName.value

        login(ownerName,repoName)
            .applySchedulers()
            .subscribe ({
                when (it) {
                    State.Loading -> isLoading.value = true
                    is State.Success -> {
                        navigator.value = true
                        isLoading.value = false
                    }
                    is State.ServerError -> {
                        snackbarText.value = it.message
                        isLoading.value = false
                    }
                    is State.NetworkConnectionError -> {
                        snackbarText.value = it.message
                        isLoading.value = false
                    }
                    is State.ValidationError -> displayValidationError(it.validationErrorType)
                }
            }, {

            }).untilCleared()

    }

    @SuppressLint("CheckResult")
    fun login(ownerName: String?, repoName: String?): Observable<State> {

        return if (!ownerName.isNullOrEmpty() && !repoName.isNullOrEmpty()) {

            repository.getPullRequests(ownerName,repoName)
                .map {
                    State.Success() as State
                }
                .startWith(State.Loading)
                .onErrorResumeNext { exception: Throwable ->
                    when (exception) {
                        is GithubException -> Observable.just(State.ServerError(exception.message!!))
                        is NoInternetException -> Observable.just(State.NetworkConnectionError(exception.message!!))
                        else -> Observable.error(exception)
                    }
                }
        } else {
            val State: State =
                if (ownerName.isNullOrBlank() && repoName.isNullOrBlank())
                    State.ValidationError(ValidationErrorType.ALL_FIELDS_BLANK)
                else if (ownerName.isNullOrBlank())
                    State.ValidationError(ValidationErrorType.OWNER_NAME_BLANK)
                else
                    State.ValidationError(ValidationErrorType.REPO_NAME_BLANK)

            return Observable.just(State)
        }
    }

    private fun displayValidationError(loginError: ValidationErrorType) = with(context) {
        snackbarText.value = when (loginError) {
            ValidationErrorType.ALL_FIELDS_BLANK -> getString(R.string.error_all_fields_blank)
            ValidationErrorType.OWNER_NAME_BLANK -> getString(R.string.error_owner_name_blank)
            ValidationErrorType.REPO_NAME_BLANK -> getString(R.string.error_repo_name_blank)
        }
    }

}