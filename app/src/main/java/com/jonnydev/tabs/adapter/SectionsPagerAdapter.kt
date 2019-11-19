package com.jonnydev.tabs.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jonnydev.tabs.fragment.CategoryFragment
import com.jonnydev.tabs.model.Category

private const val DEFAULT_TABS = 1

class SectionsPagerAdapter(
        fm: FragmentManager,
        private val mCategories: List<Category>?
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = when {
        (mCategories == null || position == 0) -> CategoryFragment.newInstance(-1)
        else -> CategoryFragment.newInstance(mCategories[position-1].id)
    }

    override fun getCount() = when(mCategories) {
        null -> DEFAULT_TABS
        else -> mCategories.size + DEFAULT_TABS
    }
}
