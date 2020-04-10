package com.grepi.ngerisep.view.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepi.ngerisep.R
import com.grepi.ngerisep.model.Seafod
import kotlinx.android.synthetic.main.list_item_footer.view.*

class FooterAdapter(var seafood : ArrayList<Seafod>) : RecyclerView.Adapter<FooterAdapter.FooterViewHolder>() {

    internal var onItemClickCallBack : OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(seafood : Seafod)
    }

    fun setOnItemClickCallback(onItemClick: OnItemClickCallback) {
        this.onItemClickCallBack = onItemClick
    }

    fun addItem(p : ArrayList<Seafod>) {
        seafood.clear()
        seafood.addAll(p)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FooterAdapter.FooterViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_footer, parent, false)
        return FooterViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return seafood.size
    }

    override fun onBindViewHolder(holder: FooterAdapter.FooterViewHolder, position: Int) {
        holder.bind(seafood[position])
    }

    inner class FooterViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(mFood : Seafod) {
            with(itemView) {
                Glide.with(itemView.context).load(mFood.strMealThumb).placeholder(R.drawable.ic_tomat_placeholder_xx).into(food_img_f1)
                itemView.name_food_f1.text = mFood.strMeal
                itemView.setOnClickListener { onItemClickCallBack?.onItemClicked(mFood) }
            }
        }
    }
}