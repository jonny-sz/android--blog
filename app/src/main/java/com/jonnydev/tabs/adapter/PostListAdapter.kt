package com.jonnydev.tabs.adapter

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jonnydev.tabs.PostActivity
import com.jonnydev.tabs.databinding.PostListItemBinding
import com.jonnydev.tabs.model.Post
import kotlin.properties.Delegates.observable

const val POST_DETAIL = "com.jonnydev.tabs.adapter.POST_DETAILS"

class PostListAdapter(
        private val mContext: Context?
) : RecyclerView.Adapter<PostListAdapter.ViewHolder>(), AutoUpdatableAdapter {

    var posts by observable(mutableListOf<Post>()) { _, oldPosts, newPosts ->
        autoNotify(oldPosts, newPosts) { oldPost, newPost -> oldPost.id == newPost.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PostListItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    inner class ViewHolder(
            private val binding: PostListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val intent = Intent(mContext, PostActivity::class.java).apply {
                    putExtra(POST_DETAIL, posts[adapterPosition])
                }

                mContext?.startActivity(intent)
            }
        }

        fun bind(post: Post) {
            binding.post = post
        }
    }
}
