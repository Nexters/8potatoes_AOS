package com.eight_potato.rest.detail.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eight_potato.rest.detail.ui.extra.RestStopFuelFragment
import com.eight_potato.rest.detail.ui.info.RestStopInfoFragment
import com.eight_potato.rest.detail.ui.menu.RestStopMenuFragment

class RestStopDetailViewPagerAdapter(
    private val factory: FragmentFactory,
    private val fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int = RestStopDetailPage.values().size

    override fun createFragment(position: Int): Fragment {
        val classLoader = fragmentActivity.classLoader
        return when (RestStopDetailPage.values()[position]) {
            RestStopDetailPage.MENU -> factory.instantiate(classLoader, RestStopMenuFragment::class.java.name)
            RestStopDetailPage.EXTRA -> factory.instantiate(classLoader, RestStopFuelFragment::class.java.name)
            RestStopDetailPage.INFO -> factory.instantiate(classLoader, RestStopInfoFragment::class.java.name)
        }
    }

    enum class RestStopDetailPage {
        MENU, EXTRA, INFO
    }
}