package com.grepi.ngerisep.view.ui.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepi.ngerisep.R
import com.grepi.ngerisep.entity.MealsMark
import kotlinx.android.synthetic.main.list_item_mark.view.*

class MarkAdapter(private val foodMark : ArrayList<MealsMark>) : RecyclerView.Adapter<MarkAdapter.MarkViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkAdapter.MarkViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_mark, parent, false)
        return MarkViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return foodMark.size
    }

    override fun onBindViewHolder(holder: MarkAdapter.MarkViewHolder, position: Int) {
        holder.bind(foodMark[position])
    }

    inner class MarkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(meals : MealsMark) {
            with(itemView) {
                Glide.with(this.context).load(meals.strMealThumb).into(mark_img)
                mark_name.text = meals.strMeal
                mark_category.text = meals.strCategory
                mark_area.text = meals.strArea
            }
        }
    }
}