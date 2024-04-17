package com.myblog.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.myblog.OnBlogItemClick
import com.myblog.R
import com.myblog.adapter.BlogAdapter
import com.myblog.databinding.ActivityBlogListBinding
import com.myblog.model.BlogListItem
import com.myblog.viewmodel.BlogViewModelFactory
import com.myblog.viewmodel.BlogsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MyBlogList : AppCompatActivity(), OnBlogItemClick {

    private lateinit var adapter: BlogAdapter
    private lateinit var binding: ActivityBlogListBinding
    private val viewModel by viewModels<BlogsViewModel> { BlogViewModelFactory(application) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_blog_list)

        init()
    }

    /**
     * method used for initialization
     */
    private fun init() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getBlogsFromServer()

        adapter = BlogAdapter(this)
        setAdapter()

        lifecycleScope.launch {
            binding.progressBar.visibility = View.GONE
            viewModel.allBlogs.collectLatest { adapter.submitData(it) }
        }

        viewModel.commentData.observe(this) {
            if (it != null) {
                startActivity(Intent(this, BlogComments::class.java).putExtra("data", Gson().toJson(it)))
            }
        }
    }

    /**
     * method used to set adapter
     */
    private fun setAdapter() {
        binding.blogList.layoutManager = LinearLayoutManager(this)
        binding.blogList.itemAnimator = DefaultItemAnimator()
        binding.blogList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh_icon -> {
                binding.progressBar.visibility=View.VISIBLE
                viewModel.refreshData()
            }
        }
        return super.onOptionsItemSelected(item);
    }

    override fun onBlogClick(post: BlogListItem) {
        viewModel.onItemClick(post)
    }

}