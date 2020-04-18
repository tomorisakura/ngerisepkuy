package com.grepi.ngerisep.view.ui.category

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.grepi.ngerisep.R
import com.grepi.ngerisep.model.CategoryFood
import com.grepi.ngerisep.model.Seafod
import com.grepi.ngerisep.view.ui.activity.DetailsActivity
import com.grepi.ngerisep.view.ui.activity.SearchViewModel
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    companion object {
        const val mObject_meat = "object_meat"
        const val mObject_vegetarian = "object_vegetarian"
        const val mObject_breakfast = "string_breakfast"
        const val mObject_chicken = "string_chicken"
        const val mObject_side = "string_chicken"
        const val mObject_starter = "string_starter"
        const val mObject_lamb = "string_lamb"
        const val mObject_vegan = "string_vegan"
        const val mObject_pasta = "string_pasta"
    }

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupTheme()
        prepareParcelable()
    }

    private fun prepareParcelable() {
        val mBeef = intent.getStringExtra(mObject_meat)
        val mVege = intent.getStringExtra(mObject_vegetarian)
        val mBreakfast = intent.getStringExtra(mObject_breakfast)
        val mChicken = intent.getStringExtra(mObject_chicken)
        val mSide = intent.getStringExtra(mObject_side)
        val mStarter = intent.getStringExtra(mObject_starter)
        val mLamb = intent.getStringExtra(mObject_lamb)
        val mVegan = intent.getStringExtra(mObject_vegan)
        val mPasta = intent.getStringExtra(mObject_pasta)
        when {
            mBeef is String -> {
                supportActionBar?.title = mBeef
                rv_category.layoutManager = GridLayoutManager(this, 2)
                categoryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)
                categoryViewModel.fetchCategoryFood(mBeef)
                categoryViewModel.getCategoryFood().observe(this, Observer {
                    progress_category.visibility = View.GONE
                    categoryAdapter = CategoryAdapter(it)
                    rv_category.adapter = categoryAdapter

                    categoryAdapter.setOnClickItem(object : CategoryAdapter.OnItemClickCallBack {
                        override fun onItemClicked(mFood: CategoryFood) {
                            prepareDetailsFood(mFood)
                        }
                    })
                })
            }
            mVege is String -> {
                supportActionBar?.title = mVege
                rv_category.layoutManager = GridLayoutManager(this, 2)
                categoryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)
                categoryViewModel.fetchCategoryFood(mVege)
                categoryViewModel.getCategoryFood().observe(this, Observer {
                    progress_category.visibility = View.GONE
                    categoryAdapter = CategoryAdapter(it)
                    rv_category.adapter = categoryAdapter

                    categoryAdapter.setOnClickItem(object : CategoryAdapter.OnItemClickCallBack {
                        override fun onItemClicked(mFood: CategoryFood) {
                            prepareDetailsFood(mFood)
                        }
                    })
                })
            }
            mBreakfast is String -> {
                supportActionBar?.title = mBreakfast
                rv_category.layoutManager = GridLayoutManager(this, 2)
                categoryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)
                categoryViewModel.fetchCategoryFood(mBreakfast)
                categoryViewModel.getCategoryFood().observe(this, Observer {
                    progress_category.visibility = View.GONE
                    categoryAdapter = CategoryAdapter(it)
                    rv_category.adapter = categoryAdapter

                    categoryAdapter.setOnClickItem(object : CategoryAdapter.OnItemClickCallBack {
                        override fun onItemClicked(mFood: CategoryFood) {
                            prepareDetailsFood(mFood)
                        }
                    })
                })
            }
            mChicken is String -> {
                supportActionBar?.title = mChicken
                rv_category.layoutManager = GridLayoutManager(this, 2)
                categoryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)
                categoryViewModel.fetchCategoryFood(mChicken)
                categoryViewModel.getCategoryFood().observe(this, Observer {
                    progress_category.visibility = View.GONE
                    categoryAdapter = CategoryAdapter(it)
                    rv_category.adapter = categoryAdapter

                    categoryAdapter.setOnClickItem(object : CategoryAdapter.OnItemClickCallBack {
                        override fun onItemClicked(mFood: CategoryFood) {
                            prepareDetailsFood(mFood)
                        }
                    })
                })
            }
            mSide is String -> {
                supportActionBar?.title = mSide
                rv_category.layoutManager = GridLayoutManager(this, 2)
                categoryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)
                categoryViewModel.fetchCategoryFood(mSide)
                categoryViewModel.getCategoryFood().observe(this, Observer {
                    progress_category.visibility = View.GONE
                    categoryAdapter = CategoryAdapter(it)
                    rv_category.adapter = categoryAdapter

                    categoryAdapter.setOnClickItem(object : CategoryAdapter.OnItemClickCallBack {
                        override fun onItemClicked(mFood: CategoryFood) {
                            prepareDetailsFood(mFood)
                        }
                    })
                })
            }
            mStarter is String -> {
                supportActionBar?.title = mStarter
                rv_category.layoutManager = GridLayoutManager(this, 2)
                categoryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)
                categoryViewModel.fetchCategoryFood(mStarter)
                categoryViewModel.getCategoryFood().observe(this, Observer {
                    progress_category.visibility = View.GONE
                    categoryAdapter = CategoryAdapter(it)
                    rv_category.adapter = categoryAdapter

                    categoryAdapter.setOnClickItem(object : CategoryAdapter.OnItemClickCallBack {
                        override fun onItemClicked(mFood: CategoryFood) {
                            prepareDetailsFood(mFood)
                        }
                    })
                })
            }
            mLamb is String -> {
                supportActionBar?.title = mLamb
                rv_category.layoutManager = GridLayoutManager(this, 2)
                categoryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)
                categoryViewModel.fetchCategoryFood(mLamb)
                categoryViewModel.getCategoryFood().observe(this, Observer {
                    progress_category.visibility = View.GONE
                    categoryAdapter = CategoryAdapter(it)
                    rv_category.adapter = categoryAdapter

                    categoryAdapter.setOnClickItem(object : CategoryAdapter.OnItemClickCallBack {
                        override fun onItemClicked(mFood: CategoryFood) {
                            prepareDetailsFood(mFood)
                        }
                    })
                })
            }
            mVegan is String -> {
                supportActionBar?.title = mVegan
                rv_category.layoutManager = GridLayoutManager(this, 2)
                categoryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)
                categoryViewModel.fetchCategoryFood(mVegan)
                categoryViewModel.getCategoryFood().observe(this, Observer {
                    progress_category.visibility = View.GONE
                    categoryAdapter = CategoryAdapter(it)
                    rv_category.adapter = categoryAdapter

                    categoryAdapter.setOnClickItem(object : CategoryAdapter.OnItemClickCallBack {
                        override fun onItemClicked(mFood: CategoryFood) {
                            prepareDetailsFood(mFood)
                        }
                    })
                })
            }
            mPasta is String -> {
                supportActionBar?.title = mPasta
                rv_category.layoutManager = GridLayoutManager(this, 2)
                categoryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)
                categoryViewModel.fetchCategoryFood(mPasta)
                categoryViewModel.getCategoryFood().observe(this, Observer {
                    progress_category.visibility = View.GONE
                    categoryAdapter = CategoryAdapter(it)
                    rv_category.adapter = categoryAdapter

                    categoryAdapter.setOnClickItem(object : CategoryAdapter.OnItemClickCallBack {
                        override fun onItemClicked(mFood: CategoryFood) {
                            prepareDetailsFood(mFood)
                        }
                    })
                })
            }
        }
    }

    private fun prepareDetailsFood(mFood : CategoryFood) {
        val mIntent = Intent(this, DetailsActivity::class.java)
        mIntent.putExtra(DetailsActivity.mObject_category, mFood)
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
