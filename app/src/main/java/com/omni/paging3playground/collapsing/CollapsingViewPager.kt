package com.omni.paging3playground.collapsing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CollapsingViewPager(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    sealed class RecurringTransferStatusPage(val title: String) {
        object Complete : RecurringTransferStatusPage("Complete")
        object Cancelled : RecurringTransferStatusPage("Cancelled ")
        object Upcoming : RecurringTransferStatusPage("Upcoming")
    }

    private val pages = listOf(
        RecurringTransferStatusPage.Complete,
        RecurringTransferStatusPage.Cancelled,
        RecurringTransferStatusPage.Upcoming
    )

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment = when (pages[position]) {
        RecurringTransferStatusPage.Complete -> CompletedFragment()
        RecurringTransferStatusPage.Cancelled -> CancelledFragment()
        RecurringTransferStatusPage.Upcoming -> UpcomingFragment()
    }

    fun title(position: Int) = pages[position].title
}
val pageOffscreenLimit = 2