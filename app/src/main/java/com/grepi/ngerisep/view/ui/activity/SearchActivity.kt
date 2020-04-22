package com.grepi.ngerisep.view.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.grepi.ngerisep.R
import com.grepi.ngerisep.model.Meal
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    companion object {
        const val mObject ="object"
    }

    private lateinit var searchViewModel : SearchViewModel
    private var searchAdapter: SearchAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val mData = intent.getSerializableExtra(mObject)
        setSupportActionBar(toolbar_search)
        supportActionBar?.title = "Searching '${mData}'"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupTheme()
        setObservableSearch()
    }

    private fun setObservableSearch() {
        val mData = intent.getStringExtra(mObject)
        rv_search.layoutManager = GridLayoutManager(this, 2)
        rv_search.adapter = searchAdapter
        searchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)
        searchViewModel.fetchSearchFood(mData!!, this)
        searchViewModel.getSearchFood().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                img_notfound.visibility = View.GONE
                searchAdapter.addItem(it)
            }else {
                img_notfound.visibility = View.VISIBLE
            }
        })
        searchAdapter.setOnItemClickCallback(object : SearchAdapter.OnItemClickCallback{
            override fun onItemClicked(meal: Meal) {
                foodDetails(meal)
            }

        })
    }

    private fun foodDetails(meal : Meal) {
        val mIntent = Intent(this, DetailsActivity::class.java)
        mIntent.putExtra(DetailsActivity.mObject_search, meal)
        startActivity(mIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}
