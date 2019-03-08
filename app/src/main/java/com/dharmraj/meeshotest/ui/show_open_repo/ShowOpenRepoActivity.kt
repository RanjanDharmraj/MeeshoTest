package com.dharmraj.meeshotest.ui.show_open_repo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dharmraj.meeshotest.R
import com.dharmraj.meeshotest.databinding.ActivityShowOpenRepoBinding
import com.dharmraj.meeshotest.ui.show_open_repo.adapter.PullRequestAdapter
import com.dharmraj.meeshotest.ui.show_open_repo.adapter.PullRequestViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class ShowOpenRepoActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var showOpenRepoViewModel: ShowOpenRepoViewModel

    private lateinit var pullRequestAdapter: PullRequestAdapter

    lateinit var pullRequestViewModel: PullRequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_open_repo)
        AndroidInjection.inject(this)
        showOpenRepoViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShowOpenRepoViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityShowOpenRepoBinding>(this, R.layout.activity_show_open_repo)
        binding.viewmodel = showOpenRepoViewModel
        binding.lifecycleOwner = this
        pullRequestViewModel = PullRequestViewModel.create(this, viewModelFactory)
        pullRequestAdapter = PullRequestAdapter(emptyList(), pullRequestViewModel)
        binding.recyclerView.adapter = pullRequestAdapter
        showOpenRepoViewModel.fetchPullRequestData()
        showOpenRepoViewModel.pullRequestLists.observe(this, Observer {
            it?.run {
                pullRequestAdapter.setItems(it)
            }
        })

    }
}
