package com.dharmraj.meeshotest.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(context : Application) : AndroidViewModel(context){

    val title = MutableLiveData<String>()

    val githubOwnerName  = MutableLiveData<String>()

    val githubRepoName  = MutableLiveData<String>()

    var isLoading = MutableLiveData<Boolean>()


    fun onSubmitOnClick() {

    }

}