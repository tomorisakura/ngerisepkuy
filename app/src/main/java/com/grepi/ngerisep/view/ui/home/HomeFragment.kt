package com.grepi.ngerisep.view.ui.home

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.grepi.ngerisep.R
import com.grepi.ngerisep.model.Dessert
import com.grepi.ngerisep.model.Miscellaneous
import com.grepi.ngerisep.model.Seafod
import com.grepi.ngerisep.view.ui.activity.DetailsActivity
import com.grepi.ngerisep.view.ui.category.CategoryActivity
import kotlinx.android.synthetic.main.category_item.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var homeAdapter : HomeAdapter = HomeAdapter()
    private var footerAdapter: FooterAdapter = FooterAdapter()
    private var miscellaneousAdapter: MiscellaneousAdapter = MiscellaneousAdapter()
    private lateinit var refreshLayout : SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRefreshLayout(view)
        createRecyclerView()
        createRecyclerFooter()
        createMisceRecyclerView()
        prepareButtonCategory()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.about_icon)
        item?.isVisible = false
    }

    private fun onRefreshLayout(view: View) {
        refreshLayout = view.findViewById(R.id.refresh_home)
        refreshLayout.setOnRefreshListener {
            createRecyclerView()
            createRecyclerFooter()
            createMisceRecyclerView()
            prepareButtonCategory()
        }
    }

    private fun prepareButtonCategory() {
        cat_meat.setOnClickListener(this)
        cat_vegetarian.setOnClickListener(this)
        cat_breakfast.setOnClickListener(this)
        cat_chicken.setOnClickListener(this)
        cat_side.setOnClickListener(this)
        cat_starter.setOnClickListener(this)
        cat_lamb.setOnClickListener(this)
        cat_vegan.setOnClickListener(this)
        cat_pasta.setOnClickListener(this)
    }

    private fun createRecyclerView() {
        rv_item_banner.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        rv_item_banner.adapter = homeAdapter
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        refreshLayout.isRefreshing = true
        homeViewModel.getMeals().observe(this.viewLifecycleOwner, Observer { p->
            homeAdapter.addItems(p)
            shimmer_banner.stopShimmerAnimation()
            shimmer_banner.visibility = View.GONE
            refreshLayout.isRefreshing = false
        })

        homeAdapter.setOnClickCallback(object : HomeAdapter.OnItemClickCallback {
            override fun onItemClicked(food: Dessert) {
                preparePopularFood(food)
            }

        })
    }

    private fun createRecyclerFooter() {
        rv_footer.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        rv_footer.adapter = footerAdapter
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        refreshLayout.isRefreshing = true
        homeViewModel.getSeafood().observe(this.viewLifecycleOwner, Observer { item ->
            footerAdapter.addItem(item)
            shimmer_banner_footer.stopShimmerAnimation()
            shimmer_banner_footer.visibility = View.GONE
            refreshLayout.isRefreshing = false
        })

        footerAdapter.setOnItemClickCallback(object : FooterAdapter.OnItemClickCallback{
            override fun onItemClicked(seafood: Seafod) {
                prepareFooterMenu(seafood)
            }

        })
    }

    private fun createMisceRecyclerView() {
        rv_missce.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        rv_missce.adapter = miscellaneousAdapter
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        refreshLayout.isRefreshing = true
        homeViewModel.fetchMisceFood()
        homeViewModel.getMisceFood().observe(this.viewLifecycleOwner, Observer {
            miscellaneousAdapter.addMisce(it)
            refreshLayout.isRefreshing = false
        })

        miscellaneousAdapter.setOnCLickItem(object : MiscellaneousAdapter.OnItemCLickCallBack {
            override fun onItemClicked(misce: Miscellaneous) {
                prepareMisceFood(misce)
            }

        })
    }

    private fun prepareMisceFood(miscellaneous: Miscellaneous) {
        val mIntent = Intent(activity, DetailsActivity::class.java)
        mIntent.putExtra(DetailsActivity.mObject_misce, miscellaneous)
        startActivity(mIntent)
    }

    private fun preparePopularFood(m : Dessert) {
        val mIntent = Intent(activity, DetailsActivity::class.java)
        mIntent.putExtra(DetailsActivity.mOBJECT, m)
        startActivity(mIntent)
    }

    private fun prepareFooterMenu(sea : Seafod) {
        val mIntent = Intent(activity, DetailsActivity::class.java)
        mIntent.putExtra(DetailsActivity.mOBJECT_SEA, sea)
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

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.cat_meat -> {
                val mIntent = Intent(activity, CategoryActivity::class.java)
                mIntent.putExtra(CategoryActivity.mObject_meat, "Beef")
                startActivity(mIntent)
            }
            R.id.cat_vegetarian -> {
                val mIntent = Intent(activity, CategoryActivity::class.java)
                mIntent.putExtra(CategoryActivity.mObject_vegetarian, "Vegetarian")
                startActivity(mIntent)
            }

            R.id.cat_breakfast -> {
                val mIntent = Intent(activity, CategoryActivity::class.java)
                mIntent.putExtra(CategoryActivity.mObject_breakfast, "Breakfast")
                startActivity(mIntent)
            }

            R.id.cat_chicken -> {
                val mIntent = Intent(activity, CategoryActivity::class.java)
                mIntent.putExtra(CategoryActivity.mObject_chicken, "Chicken")
                startActivity(mIntent)
            }

            R.id.cat_side -> {
                val mIntent = Intent(activity, CategoryActivity::class.java)
                mIntent.putExtra(CategoryActivity.mObject_side, "Side")
                startActivity(mIntent)
            }

            R.id.cat_starter -> {
                val mIntent = Intent(activity, CategoryActivity::class.java)
                mIntent.putExtra(CategoryActivity.mObject_starter, "Starter")
                startActivity(mIntent)
            }

            R.id.cat_lamb -> {
                val mIntent = Intent(activity, CategoryActivity::class.java)
                mIntent.putExtra(CategoryActivity.mObject_lamb, "Lamb")
                startActivity(mIntent)
            }

            R.id.cat_vegan -> {
                val mIntent = Intent(activity, CategoryActivity::class.java)
                mIntent.putExtra(CategoryActivity.mObject_vegan, "Vegan")
                startActivity(mIntent)
            }

            R.id.cat_pasta -> {
                val mIntent = Intent(activity, CategoryActivity::class.java)
                mIntent.putExtra(CategoryActivity.mObject_pasta, "Pasta")
                startActivity(mIntent)
            }
        }
    }
}
