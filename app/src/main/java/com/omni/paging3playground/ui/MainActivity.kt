package com.omni.paging3playground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import com.omni.paging3playground.R
import com.omni.paging3playground.collapsing.CollapsingViewPager
import com.omni.paging3playground.collapsing.pageOffscreenLimit
import com.omni.paging3playground.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupViewPager()
        supportActionBar?.hide()

    }

    private lateinit var adapter: CollapsingViewPager

    private fun setupViewPager() {
        adapter = CollapsingViewPager(supportFragmentManager, lifecycle)
        binding.manageRecurringTransactionsStatusViewpager.apply {
            adapter = this@MainActivity.adapter
            offscreenPageLimit = pageOffscreenLimit
            isUserInputEnabled = false
        }

        TabLayoutMediator(
            binding.recurringTransactionStatusTabLayout,
            binding.manageRecurringTransactionsStatusViewpager
        ) { tab, position ->
            val custom = layoutInflater.inflate(
                R.layout.recurring_transfer_status_tab_item,
                binding.recurringTransactionStatusTabLayout,
                false
            )
            custom.findViewById<TextView>(R.id.tab_title).text = adapter.title(position)
            tab.customView = custom

        }.attach()
    }
}


