package com.example.myapplication01.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication01.ui.activity.MainActivity
import com.example.myapplication01.R
import com.example.myapplication01.ui.adapter.HealthAdapter
import kotlinx.android.synthetic.main.fragment_health.*

class HealthFragment : Fragment() {
    private var myItems = mutableListOf<String>()
    lateinit var healthActivity: MainActivity
    lateinit var healthAdapter: HealthAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        healthActivity = context as MainActivity // 자료형 변환 연산자 (from) as (to)
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

        // 이 함수를 호출한 객체를, 이어지는 함수 블록의 리시버로 전달
        myItems.apply {
            add("벤치프레스")
            add("렛풀다운")
            add("스쿼트")
            add("데드리프트")
            add("어깨")
            add("이두근")

            healthAdapter = HealthAdapter(myItems)
            recycler_view.adapter = healthAdapter
            healthAdapter.notifyDataSetChanged()
        }
    }
}