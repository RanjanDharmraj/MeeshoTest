package com.dharmraj.meeshotest.ui.show_open_repo.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dharmraj.meeshotest.BR
import com.dharmraj.meeshotest.R
import com.dharmraj.meeshotest.model.PullRequestItem

class PullRequestAdapter(
    private var pullRequestItems: List<PullRequestItem>,
    private val pullRequestViewModel: PullRequestViewModel
) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PullRequestAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return ViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater,
                R.layout.item_pull_request,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int = pullRequestItems.size

    override fun onBindViewHolder(holder: PullRequestAdapter.ViewHolder, position: Int) =
        holder.bind(pullRequestItems[position], pullRequestViewModel)

    fun setItems(pullRequestItems: List<PullRequestItem>) {
        this.pullRequestItems = pullRequestItems
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PullRequestItem, pullRequestViewModel: PullRequestViewModel) {

            pullRequestViewModel.loadData(item)
            binding.setVariable(BR.viewmodel, pullRequestViewModel)

            if (binding.hasPendingBindings()) {
                binding.executePendingBindings()
            }
        }
    }
}