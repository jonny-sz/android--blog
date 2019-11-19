package com.jonnydev.tabs

import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jonnydev.tabs.adapter.POST_DETAIL
import com.jonnydev.tabs.databinding.ActivityPostBinding
import com.jonnydev.tabs.model.Post

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val post = intent.getSerializableExtra(POST_DETAIL) as Post
        val binding: ActivityPostBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_post)

        binding.post = post
    }
}
