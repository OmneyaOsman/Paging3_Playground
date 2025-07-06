package com.omni.paging3playground.collapsing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.omni.paging3playground.Injection
import com.omni.paging3playground.R
import com.omni.paging3playground.databinding.PageLayoutBinding
import com.omni.paging3playground.ui.PhotoLoadStateAdapter
import com.omni.paging3playground.ui.PhotosAdapter
import com.omni.paging3playground.ui.SearchPhotosViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class UpcomingFragment :Fragment(R.layout.page_layout) {
    private lateinit var binding: PageLayoutBinding
    private lateinit var viewModel: SearchPhotosViewModel
    private val adapter = PhotosAdapter()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PageLayoutBinding.bind(view)

        // get the view model
        viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        )[SearchPhotosViewModel::class.java]

        initAdapter()
        collectState()
    }

    private fun initAdapter() {
        binding.list.adapter = adapter

        binding.list.adapter = adapter.withLoadStateFooter(
            footer = PhotoLoadStateAdapter { adapter.retry() }
        )
    }

    private fun collectState() {

        lifecycleScope.launch {
            viewModel.pagingResult.collect {
                adapter.submitData(it)
            }
        }
        // Scroll to top when the list is refreshed from network.
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.list.scrollToPosition(0) }
        }
    }
}