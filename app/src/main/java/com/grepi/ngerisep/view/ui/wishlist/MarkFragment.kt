package com.grepi.ngerisep.view.ui.wishlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
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
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRefreshLayout()
        setRvMark()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.search_icon)
        val about = menu.findItem(R.id.about_icon)
        item?.isVisible = false
        about?.isVisible = false
    }

    private fun setRvMark() {
        rv_mark.layoutManager = LinearLayoutManager(activity)
        rv_mark.setHasFixedSize(true)
        markViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MarkViewModel::class.java)
        refresh_mark.isRefreshing = true
        markViewModel.fetchMeals().observe(this.viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                empty_content.visibility = View.GONE
                refresh_mark.isRefreshing = false
                markAdapter.addItem(it)
                rv_mark.adapter = markAdapter

                markAdapter.setOnItemClick(object : MarkAdapter.OnItemClickCallBack{
                    override fun itemClicked(meal: MealsMark) {
                        prepareDetails(meal)
                    }

                })
            } else {
                refresh_mark.isRefreshing = false
                markAdapter.addItem(it)
                empty_content.visibility = View.VISIBLE
            }
        })
    }

    private fun onRefreshLayout() {
        refresh_mark.setOnRefreshListener {
            setRvMark()
        }
    }

    private fun prepareDetails(meal: MealsMark) {
        val mIntent = Intent(activity, DetailsActivity::class.java)
        mIntent.putExtra(DetailsActivity.mKey_entity, meal)
        startActivity(mIntent)
    }
}
