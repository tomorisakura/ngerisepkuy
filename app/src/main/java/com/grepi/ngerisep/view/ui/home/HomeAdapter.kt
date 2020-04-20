package com.grepi.ngerisep.view.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepi.ngerisep.R
import com.grepi.ngerisep.model.Dessert
import kotlinx.android.synthetic.main.list_item_banner.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var food : ArrayList<Dessert> = arrayListOf()
    private var onItemClickCallback : OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(food : Dessert)
    }

    fun setOnClickCallback(onItemClick : OnItemClickCallback) {
        this.onItemClickCallback = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_banner, parent,false)
        return HomeViewHolder(mView)
    }

    fun addItems(f : ArrayList<Dessert>) {
        notifyDataSetChanged()
        food.clear()
        food.addAll(f)
    }

    override fun getItemCount(): Int {
        return food.size
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        holder.bind(food[position])
    }

    inner class HomeViewHolder(mView : View) : RecyclerView.ViewHolder(mView) {
        fun bind(f : Dessert) {
            with(itemView) {
                Glide.with(itemView.context).load(f.strMealThumb).placeholder(R.drawable.tomato_placeholder).override(200,200).into(food_img)
                name_food.text = f.strMeal
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(f) }
            }
        }
    }
}