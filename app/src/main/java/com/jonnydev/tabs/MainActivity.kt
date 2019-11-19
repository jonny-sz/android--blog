package com.jonnydev.tabs

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Gravity.CENTER
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.jonnydev.tabs.adapter.SectionsPagerAdapter
import com.jonnydev.tabs.model.Category
import com.jonnydev.tabs.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

private const val TAB_POSITION = "position"

class MainActivity : AppCompatActivity() {
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private val mViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if ( isConnected() ) {
            mViewModel.categories.observe(this, Observer { categories ->
                updateCategories(categories)
            })

            if (savedInstanceState != null) {
                view_pager.currentItem = savedInstanceState.getInt(TAB_POSITION)
            }

            view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
            tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))
        } else {
            showNoConnectionView()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAB_POSITION, tabs.selectedTabPosition)
    }

    private fun showNoConnectionView() {
        val v = TextView(this).apply {
            text = getText(R.string.no_connection)
            textSize = 20f
            gravity = CENTER
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }

        tabs.visibility = View.GONE
        view_pager.visibility = View.GONE

        main_content.addView(v)
    }

    private fun updateCategories(categories: List<Category>?) {
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, categories)
        view_pager.adapter = mSectionsPagerAdapter
        initTabs(categories)
    }

    private fun initTabs(categories: List<Category>?) {
        with(tabs) {
            addTab(newTab().setText(getString(R.string.all)))

            if ( categories != null ) {
                if ( categories.size > 3 )
                    tabMode = TabLayout.MODE_SCROLLABLE

                for (category in categories)
                    addTab(newTab().setText(category.title))
            }
        }
    }

    private fun isConnected(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }
}
