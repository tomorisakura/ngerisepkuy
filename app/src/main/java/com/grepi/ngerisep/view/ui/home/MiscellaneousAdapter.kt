package com.grepi.ngerisep.view.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepi.ngerisep.R
import com.grepi.ngerisep.model.Miscellaneous
import kotlinx.android.synthetic.main.list_item_misscellaneous.view.*

class MiscellaneousAdapter(var missce : ArrayList<Miscellaneous>) : RecyclerView.Adapter<MiscellaneousAdapter.MiscellaneousViewHolder>() {

    private var onItemClickCallBack : OnItemCLickCallBack? = null

    interface OnItemCLickCallBack {
        fun onItemClicked(misce : Miscellaneous)
    }

    fun setOnCLickItem(onItemClicked : OnItemCLickCallBack) {
        this.onItemClickCallBack = onItemClicked
    }

    internal fun addMisce(mis: ArrayList<Miscellaneous>) {
        missce.clear()
        missce.addAll(mis)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MiscellaneousAdapter.MiscellaneousViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_misscellaneous, parent, false)
        return MiscellaneousViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return missce.size
    }

    override fun onBindViewHolder(
        holder: MiscellaneousAdapter.MiscellaneousViewHolder,
        position: Int
    ) {
        holder.bind(missce[position])
    }

    inner class MiscellaneousViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(misce : Miscellaneous) {
            with(itemView) {
                Glide.with(context).load(misce.strMealThumb).placeholder(R.drawable.ic_tomat_placeholder_xx).into(food_img_missce)
                name_food_missce.text = misce.strMeal
                setOnClickListener { onItemClickCallBack?.onItemClicked(misce) }
            }
        }
    }
}