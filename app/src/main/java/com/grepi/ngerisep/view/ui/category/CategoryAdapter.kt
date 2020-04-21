package com.grepi.ngerisep.view.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepi.ngerisep.R
import com.grepi.ngerisep.model.CategoryFood
import kotlinx.android.synthetic.main.list_item_category.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var meals : ArrayList<CategoryFood> = arrayListOf()
    private var onClickItemCallBack : OnItemClickCallBack? = null

    interface OnItemClickCallBack {
        fun onItemClicked(mFood : CategoryFood)
    }

    fun setOnClickItem(onItemClicked : OnItemClickCallBack) {
        this.onClickItemCallBack = onItemClicked
    }

    internal fun addItems(category : ArrayList<CategoryFood>) {
        meals.clear()
        meals.addAll(category)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_category, parent, false)
        return CategoryViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(mFood : CategoryFood) {
            with(itemView) {
                Glide.with(context).load(mFood.strMealThumb).placeholder(R.drawable.tomato_placeholder).into(food_img_category)
                cat_food.text = mFood.strMeal
                setOnClickListener { onClickItemCallBack?.onItemClicked(mFood) }
            }
        }
    }
}