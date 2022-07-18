package com.example.myapplication01

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication01.ui.adapter.HealthAdapter
import com.example.myapplication01.vo.HealthItem
import kotlinx.android.synthetic.main.fragment_health.*

class HealthFragment : Fragment() {
    private var myItems = mutableListOf<String>()
    lateinit var healthActivity:MainActivity
    lateinit var healthAdapter: HealthAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        healthActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        myItems.apply {
            add("벤치프레스")
            add("벤치프레스")
            add("벤치프레스")
            add("벤치프레스")
            add("벤치프레스")
            add("벤치프레스")

            healthAdapter = HealthAdapter(myItems)
            //recycler_view.layoutManager = LinearLayoutManager(healthActivity)
            recycler_view.adapter = healthAdapter
            healthAdapter.notifyDataSetChanged()
            //healthAdapter.replaceData(myItems)
        }



    }
}