package com.grepi.ngerisep.view.ui.home

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
import com.grepi.ngerisep.model.Dessert
import com.grepi.ngerisep.model.Seafod
import com.grepi.ngerisep.view.ui.activity.PopularActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter : HomeAdapter
    private lateinit var footerAdapter: FooterAdapter
    private var seafood : ArrayList<Seafod> = arrayListOf()
    private var dessert : ArrayList<Dessert> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createRecyclerView()
        createRecyclerFooter()
    }

    private fun createRecyclerView() {
        rv_item_banner.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        homeAdapter = HomeAdapter(dessert)
        rv_item_banner.adapter = homeAdapter
        homeViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)
        homeViewModel.fetchData()
        homeViewModel.getMeals().observe(this.viewLifecycleOwner, Observer { p->
            homeAdapter.addItems(p)
            shimmer_banner.stopShimmerAnimation()
            shimmer_banner.visibility = View.GONE
        })

        homeAdapter.setOnClickCallback(object : HomeAdapter.OnItemClickCallback {
            override fun onItemClicked(food: Dessert) {
                preparePopularFood(food)
            }

        })
    }

    private fun createRecyclerFooter() {
        rv_footer.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        footerAdapter = FooterAdapter(seafood)
        rv_footer.adapter = footerAdapter
        homeViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)
        homeViewModel.fetchSeafood()
        homeViewModel.getSeafood().observe(this.viewLifecycleOwner, Observer { item ->
            footerAdapter.addItem(item)
            shimmer_banner_footer.stopShimmerAnimation()
            shimmer_banner_footer.visibility = View.GONE
        })

        footerAdapter.setOnItemClickCallback(object : FooterAdapter.OnItemClickCallback{
            override fun onItemClicked(seafood: Seafod) {
                prepareFooterMenu(seafood)
            }

        })
    }

    private fun preparePopularFood(m : Dessert) {
        val mIntent = Intent(activity, PopularActivity::class.java)
        mIntent.putExtra(PopularActivity.mOBJECT, m)
        startActivity(mIntent)
    }

    private fun prepareFooterMenu(sea : Seafod) {
        val mIntent = Intent(activity, PopularActivity::class.java)
        mIntent.putExtra(PopularActivity.mOBJECT_SEA, sea)
        startActivity(mIntent)
    }

    override fun onResume() {
        super.onResume()
        shimmer_banner.startShimmerAnimation()
        shimmer_banner_footer.startShimmerAnimation()
    }

    override fun onPause() {
        shimmer_banner.stopShimmerAnimation()
        shimmer_banner_footer.stopShimmerAnimation()
        super.onPause()
    }
}
