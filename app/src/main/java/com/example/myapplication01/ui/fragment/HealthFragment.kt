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
import com.example.myapplication01.vo.HealthItem
import kotlinx.android.synthetic.main.fragment_health.*

class HealthFragment : Fragment() {
    private var myItems = mutableListOf<HealthItem>()
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
            add(HealthItem(healthImage = R.drawable.ic_muscle_chest_64, healthName = "벤치프레스", healthDescription = "가슴운동"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_quadriceps_64, healthName = "스쿼트", healthDescription = "다리운동"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_back_64, healthName = "데드리프트", healthDescription = "등운동"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_back_64, healthName = "렛풀다운", healthDescription = "등운동"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_biceps_64, healthName = "아령들기", healthDescription = "이두근 발달"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_biceps_64, healthName = "아령들기", healthDescription = "이두근 발달"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_biceps_64, healthName = "아령들기", healthDescription = "이두근 발달"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_biceps_64, healthName = "아령들기", healthDescription = "이두근 발달"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_biceps_64, healthName = "아령들기", healthDescription = "이두근 발달"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_biceps_64, healthName = "아령들기", healthDescription = "이두근 발달"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_biceps_64, healthName = "아령들기", healthDescription = "이두근 발달"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_biceps_64, healthName = "아령들기", healthDescription = "이두근 발달"))
            add(HealthItem(healthImage = R.drawable.ic_muscle_biceps_64, healthName = "아령들기", healthDescription = "이두근 발달"))

            healthAdapter = HealthAdapter(myItems)
            recycler_view.adapter = healthAdapter
            healthAdapter.notifyDataSetChanged()
        }
    }
}