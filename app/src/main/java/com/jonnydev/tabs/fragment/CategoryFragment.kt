package com.jonnydev.tabs.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jonnydev.tabs.R
import com.jonnydev.tabs.adapter.PostListAdapter
import com.jonnydev.tabs.viewmodel.CategoryViewModel
import com.jonnydev.tabs.viewmodel.CategoryViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class CategoryFragment : Fragment() {

    private lateinit var mPostAdapter: PostListAdapter
    private var categoryId: Long? = null
    private val mViewModel by lazy {
        ViewModelProvider(this, CategoryViewModelFactory(categoryId))
                .get(categoryId.toString(), CategoryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = arguments?.getLong(CATEGORY_ID)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        mPostAdapter = PostListAdapter(activity)

        mViewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            posts?.let {
                mPostAdapter.posts = it.toMutableList()
            }
            progressBar.visibility = View.GONE
        })
        initRecyclerView(rootView.postList)

        return rootView
    }

    private fun initRecyclerView(rv: RecyclerView) {
        with(rv) {
            layoutManager = LinearLayoutManager(context)
            adapter = mPostAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }

    companion object {
        private const val CATEGORY_ID = "category_id"

        fun newInstance(categoryId: Long) = CategoryFragment().apply {
            arguments = Bundle().apply {
                putLong(CATEGORY_ID, categoryId)
            }
        }
    }
}
