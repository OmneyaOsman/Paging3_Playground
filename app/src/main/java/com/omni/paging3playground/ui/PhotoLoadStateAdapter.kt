package com.omni.paging3playground.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omni.paging3playground.databinding.ItemLoadStateBinding

class PhotoLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PhotoLoadStateAdapter.LoadStateVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadStateVH(
            ItemLoadStateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: LoadStateVH, loadState: LoadState) =
        holder.bind(loadState)

    inner class LoadStateVH(
        private val b: ItemLoadStateBinding
    ) : RecyclerView.ViewHolder(b.root) {

        fun bind(state: LoadState) = with(b) {
            progressBar.isVisible = state is LoadState.Loading
            buttonRetry.isVisible = state is LoadState.Error
            textError.isVisible   = state is LoadState.Error

            if (state is LoadState.Error) {
                textError.text = state.error.localizedMessage
                buttonRetry.setOnClickListener { retry() }
            }
        }
    }
}
