package com.dharmraj.meeshotest.ui.show_open_repo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dharmraj.meeshotest.R
import com.dharmraj.meeshotest.databinding.ActivityShowOpenRepoBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class ShowOpenRepoActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var showOpenRepoViewModel: ShowOpenRepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_open_repo)
        AndroidInjection.inject(this)
        showOpenRepoViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShowOpenRepoViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityShowOpenRepoBinding>(this, R.layout.activity_show_open_repo)
        binding.viewmodel = showOpenRepoViewModel
        binding.lifecycleOwner = this
        showOpenRepoViewModel.pullRequestLists.observe(this, Observer {
            it?.run {

            }
        })
    }
}
