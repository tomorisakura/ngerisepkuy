package com.grepi.ngerisep.view.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepi.ngerisep.R
import com.grepi.ngerisep.model.Meal
import kotlinx.android.synthetic.main.list_item_search.view.*

class SearchAdapter(var meal : ArrayList<Meal>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var onItemClickCallback : OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(meal : Meal)
    }

    fun setOnItemClickCallback(onClickItemClick : OnItemClickCallback) {
        this.onItemClickCallback = onClickItemClick
    }

    fun addItem(meals : ArrayList<Meal>) {
        meal.clear()
        meal.addAll(meals)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_search, parent, false)
        return SearchViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return meal.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        holder.bind(meal[position])
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(m : Meal) {
            with(itemView) {
                Glide.with(itemView.context).load(m.strMealThumb).placeholder(R.drawable.ic_tomat_placeholder_xx).into(food_img_search)
                itemView.name_food_search.text = m.strMeal
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(m) }
            }
        }
    }
}