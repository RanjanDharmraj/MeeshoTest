package com.dharmraj.meeshotest.ui

import android.app.Application
import com.dharmraj.meeshotest.network.repository.PullRequestRepository
import com.dharmraj.meeshotest.ui.main.MainViewModel
import com.dharmraj.meeshotest.ui.main.State
import com.dharmraj.meeshotest.ui.main.ValidationErrorType
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*

class MainViewModelTest {

    lateinit var context: Application
    lateinit var mainViewModel: MainViewModel
    lateinit var pullRequestRepository: PullRequestRepository

    companion object {

        @BeforeClass
        @JvmStatic
        fun setUp() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        }

        @AfterClass
        @JvmStatic
        fun tearDown() {
            RxAndroidPlugins.reset()
            RxJavaPlugins.reset()
        }
    }
    lateinit var validationError: State.ValidationError

    lateinit var success: State.Success

    @Before
    fun beforeEachTest() {
        pullRequestRepository = mock()
        context = mock()
        mainViewModel = MainViewModel(context, pullRequestRepository)
        whenever(context.getString(any())).thenReturn("SomeText")
    }

    @Test
    fun getPullRequestWithAllFieldEmpty_ShouldShowError() {

        mainViewModel.getPullRequests("", "")
            .subscribe({
                validationError = it as State.ValidationError
            }, {})

        Assert.assertTrue(validationError.validationErrorType == ValidationErrorType.ALL_FIELDS_BLANK)
    }


    @Test
    fun getPullRequestWithOwnerNameEmpty_ShouldShowError() {

        mainViewModel.getPullRequests("", "go-octokit")
            .subscribe({
                validationError = it as State.ValidationError
            }, {})

        Assert.assertTrue(validationError.validationErrorType == ValidationErrorType.OWNER_NAME_BLANK)
    }

    @Test
    fun getPullRequestWithRepoNameEmpty_ShouldShowError() {

        mainViewModel.getPullRequests("octokit", "")
            .subscribe({
                validationError = it as State.ValidationError
            }, {})

        Assert.assertTrue(validationError.validationErrorType == ValidationErrorType.REPO_NAME_BLANK)
    }

    @Test
    fun getPullRequestWithAllField_ShouldShowError() {

        mainViewModel.getPullRequests("octokit", "go-octokit")
            .subscribe({
                success = it as State.Success
            }, {})

        Assert.assertTrue(success == State.Success())
    }

}