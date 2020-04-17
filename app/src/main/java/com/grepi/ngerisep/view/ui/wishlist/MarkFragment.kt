package com.grepi.ngerisep.view.ui.wishlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.grepi.ngerisep.R
import com.grepi.ngerisep.db.MealsDatabase
import com.grepi.ngerisep.entity.MealsMark
import kotlinx.android.synthetic.main.fragment_mark.*

class MarkFragment : Fragment() {

    private lateinit var markViewModel: MarkViewModel

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
    }
}
