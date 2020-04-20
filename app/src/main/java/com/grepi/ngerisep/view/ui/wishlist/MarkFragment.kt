package com.grepi.ngerisep.view.ui.wishlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.grepi.ngerisep.R
import com.grepi.ngerisep.entity.MealsMark
import com.grepi.ngerisep.view.ui.activity.DetailsActivity
import kotlinx.android.synthetic.main.fragment_mark.*

class MarkFragment : Fragment() {

    private lateinit var markViewModel: MarkViewModel
    private var markAdapter: MarkAdapter = MarkAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        markViewModel = ViewModelProviders.of(this).get(MarkViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_mark, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRvMark()
    }

    private fun setRvMark() {
        rv_mark.layoutManager = LinearLayoutManager(activity)
        rv_mark.setHasFixedSize(true)
        markViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MarkViewModel::class.java)
        markViewModel.fetchMeals().observe(this.viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                empty_content.visibility = View.GONE
                markAdapter.addItem(it)
                rv_mark.adapter = markAdapter

                markAdapter.setOnItemClick(object : MarkAdapter.OnItemClickCallBack{
                    override fun itemClicked(meal: MealsMark) {
                        prepareDetails(meal)
                    }

                })
            } else {
                markAdapter.addItem(it)
                empty_content.visibility = View.VISIBLE
            }
        })
    }

    private fun prepareDetails(meal: MealsMark) {
        val mIntent = Intent(activity, DetailsActivity::class.java)
        mIntent.putExtra(DetailsActivity.mKey_entity, meal)
        startActivity(mIntent)
    }
}
