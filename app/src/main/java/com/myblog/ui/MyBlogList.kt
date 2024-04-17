package com.myblog.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.myblog.R
import com.myblog.adapter.BlogAdapter
import com.myblog.databinding.ActivityBlogListBinding
import com.myblog.viewmodel.BlogViewModelFactory
import com.myblog.viewmodel.BlogsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MyBlogList : AppCompatActivity() {

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

        adapter = BlogAdapter()
        setAdapter()

        lifecycleScope.launch {
            binding.progressBar.visibility = View.GONE
            viewModel.allBlogs.collectLatest { adapter.submitData(it) }
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

}