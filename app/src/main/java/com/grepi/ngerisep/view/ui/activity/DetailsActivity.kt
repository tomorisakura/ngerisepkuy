package com.grepi.ngerisep.view.ui.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.grepi.ngerisep.R
import com.grepi.ngerisep.entity.MealsMark
import com.grepi.ngerisep.model.*
import com.grepi.ngerisep.view.ui.home.HomeViewModel
import com.grepi.ngerisep.view.ui.wishlist.MarkViewModel
import kotlinx.android.synthetic.main.activity_popular.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val mOBJECT = "food_object"
        const val mOBJECT_SEA = "food_sea"
        const val mObject_search = "object_search"
        const val mObject_misce = "object_misce"
        const val mObject_category = "object_category"
        const val mKey_entity = "key"
    }

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var markViewModel: MarkViewModel
    private lateinit var mealsMark: MealsMark

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular)
        setupTheme()
        supportActionBar?.setBackgroundDrawable(getDrawable(R.drawable.toolbar_bg))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setObservableData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setObservableData() {
        val mItem = intent.getParcelableExtra<Dessert>(mOBJECT)
        val mItem2 = intent.getParcelableExtra<Seafod>(mOBJECT_SEA)
        val mSearch = intent.getParcelableExtra<Meal>(mObject_search)
        val mMisce = intent.getParcelableExtra<Miscellaneous>(mObject_misce)
        val mCategory = intent.getParcelableExtra<CategoryFood>(mObject_category)
        val mKey = intent.getParcelableExtra<MealsMark>(mKey_entity)
        homeViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)
        when {
            mItem is Dessert -> {
                supportActionBar?.title = mItem.strMeal
                homeViewModel.fetchMealsbyId(mItem.idMeal)
                homeViewModel.getMealsById().observe(this, Observer {
                    if (it.isEmpty()) {
                        progress_popular.visibility = View.VISIBLE
                    }else {
                        progress_popular.visibility = View.GONE
                        for (i in it.indices) {
                            setDataDetail(it[i])
                            isMarked(it[i])
                        }
                    }
                })
            }
            mItem2 is Seafod -> {
                supportActionBar?.title = mItem2.strMeal
                homeViewModel.fetchMealsbyId(mItem2.idMeal)
                homeViewModel.getMealsById().observe(this, Observer {
                    for (i in it.indices) {
                        if (it.isEmpty()) {
                            progress_popular.visibility = View.VISIBLE
                        } else {
                            progress_popular.visibility = View.GONE
                            setDataDetail(it[i])
                            isMarked(it[i])
                        }
                    }
                })
            }
            mSearch is Meal -> {
                supportActionBar?.title = mSearch.strMeal
                homeViewModel.fetchMealsbyId(mSearch.idMeal)
                homeViewModel.getMealsById().observe(this, Observer {
                    for (i in it.indices) {
                        if (it.isEmpty()) {
                            progress_popular.visibility = View.VISIBLE
                        } else {
                            progress_popular.visibility = View.GONE
                            setDataDetail(it[i])
                            isMarked(it[i])
                        }
                    }
                })
            }

            mMisce is Miscellaneous -> {
                supportActionBar?.title = mMisce.strMeal
                homeViewModel.fetchMealsbyId(mMisce.idMeal)
                homeViewModel.getMealsById().observe(this, Observer {
                    for (i in it.indices) {
                        if (it.isEmpty()) {
                            progress_popular.visibility = View.VISIBLE
                        } else {
                            progress_popular.visibility = View.GONE
                            setDataDetail(it[i])
                            isMarked(it[i])
                        }
                    }
                })
            }

            mCategory is CategoryFood -> {
                supportActionBar?.title = mCategory.strMeal
                homeViewModel.fetchMealsbyId(mCategory.idMeal)
                homeViewModel.getMealsById().observe(this, Observer {
                    for (i in it.indices) {
                        if (it.isEmpty()) {
                            progress_popular.visibility = View.VISIBLE
                        } else {
                            progress_popular.visibility = View.GONE
                            setDataDetail(it[i])
                            isMarked(it[i])
                        }
                    }
                })
            }

            mKey is MealsMark -> {
                supportActionBar?.title = mKey.strMeal
                homeViewModel.fetchMealsbyId(mKey.idMeal.toString())
                homeViewModel.getMealsById().observe(this, Observer {
                    for (i in it.indices) {
                        if (it.isEmpty()) {
                            progress_popular.visibility = View.VISIBLE
                        } else {
                            progress_popular.visibility = View.GONE
                            setDataDetail(it[i])
                            isMarked(it[i])
                        }
                    }
                })
            }
        }
    }

    private fun markViewModel() = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(MarkViewModel::class.java)

    private fun setDataDetail(meal: Meal) {
        Glide.with(this).load(meal.strMealThumb).into(img_dessert)
        det_name.text = meal.strMeal
        area_dessert.text = "\uD83D\uDEA9 ${meal.strArea}"
        category_dessert.text = "\uD83C\uDF72 ${meal.strCategory}"
        val measur16 = meal.strMeasure16 ?: ""
        val measur17 = meal.strMeasure17 ?: ""
        val measur18 = meal.strMeasure18 ?: ""
        val measure19 = meal.strMeasure19 ?: ""
        val measure20 = meal.strMeasure20 ?: ""
        val ingredients16 = meal.strIngredient16 ?: ""
        val ingredients17 = meal.strIngredient17 ?: ""
        val ingredients18 = meal.strIngredient18 ?: ""
        val ingredients19 = meal.strIngredient19 ?: ""
        val ingredients20 = meal.strIngredient20 ?: ""
        bahan_1.text = "- ${meal.strIngredient1} ${meal.strMeasure1}"
        bahan_2.text = "- ${meal.strIngredient2} ${meal.strMeasure2}"
        bahan_3.text = "- ${meal.strIngredient3} ${meal.strMeasure3}"
        bahan_4.text = "- ${meal.strIngredient4} ${meal.strMeasure4}"
        bahan_5.text = "- ${meal.strIngredient5} ${meal.strMeasure5}"
        bahan6.text = "- ${meal.strIngredient6} ${meal.strMeasure6}"
        bahan_7.text = "- ${meal.strIngredient7} ${meal.strMeasure7}"
        bahan_8.text = "- ${meal.strIngredient8} ${meal.strMeasure8}"
        bahan_9.text = "- ${meal.strIngredient9} ${meal.strMeasure9}"
        bahan_10.text = "- ${meal.strIngredient10} ${meal.strMeasure10}"
        bahan_11.text = "- ${meal.strIngredient11} ${meal.strMeasure11}"
        bahan_12.text = "- ${meal.strIngredient12} ${meal.strMeasure12}"
        bahan_13.text = "- ${meal.strIngredient13} ${meal.strMeasure13}"
        bahan_14.text = "- ${meal.strIngredient14} ${meal.strMeasure14}"
        bahan_15.text = "- ${meal.strIngredient15} ${meal.strMeasure15}"
        bahan_16.text = "- ${ingredients16} ${measur16}"
        bahan_17.text = "- ${ingredients17} ${measur17}"
        bahan_18.text = "- ${ingredients18} ${measur18}"
        bahan_19.text = "- ${ingredients19} ${measure19}"
        bahan_20.text = "- ${ingredients20} ${measure20}"
        instruction_text.text = meal.strInstructions

        btn_youtube.setOnClickListener {
            val mIntent = Intent(Intent.ACTION_VIEW)
            mIntent.data = Uri.parse(meal.strYoutube)
            startActivity(mIntent)
        }

        mark_food.setOnClickListener {
            insertMeal(meal)
        }
    }

    private fun insertMeal(meal: Meal) {
        mealsMark = MealsMark(
            idMeal = meal.idMeal!!.toInt(),
            strMeal = meal.strMeal,
            strCategory = meal.strCategory,
            strArea = meal.strArea,
            strMealThumb = meal.strMealThumb
        )

        markViewModel = markViewModel()
        markViewModel.isMarkList(mealsMark, mealsMark.idMeal)
        markViewModel.getExists().observe(this, Observer {
            if (it) {
                mark_food.text = "Unmark"
                setSnackBar("Added ${mealsMark.strMeal}")
            } else {
                mark_food.text = "Mark"
                setSnackBar("Remove ${mealsMark.strMeal}")
            }
        })
    }

    private fun isMarked(meal: Meal) {
        markViewModel = markViewModel()
        markViewModel.checkIdMeals(meal.idMeal!!.toInt())
        markViewModel.getCheck().observe(this, Observer {
            if (it) {
                mark_food.text = "Mark"
            } else {
                mark_food.text = "Unmark"
            }
        })
    }

    private fun setSnackBar(message: String) {
        Snackbar.make(details_cordinator, message, Snackbar.LENGTH_SHORT).setTextColor(Color.WHITE).setAnchorView(bottom_bar_details).show()
    }

    private fun setupTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

}
