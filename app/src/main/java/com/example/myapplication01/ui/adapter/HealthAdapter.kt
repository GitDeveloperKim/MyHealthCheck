package com.example.myapplication01.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication01.R
import com.example.myapplication01.vo.HealthItem

import kotlinx.android.synthetic.main.health_item.view.*

class HealthAdapter (val healthItems: MutableList<HealthItem>):
    RecyclerView.Adapter<HealthAdapter.ViewHolder>()
{
    //var healthItems = mutableListOf<String>() // todo image link, string, string 으로 구성된 클래스로 변경 필요
    override fun getItemCount(): Int = healthItems.size

    // 항목의 뷰를 가지는 뷰 홀더를 준비하려고 자동으로 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context)
            .inflate(R.layout.health_item, parent, false)
        return ViewHolder(inflateView)
    }

    // 뷰 홀더의 뷰에 데이터를 출력하려고 자동으로 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listener = View.OnClickListener {
            Toast.makeText(it.context, healthItems[position].healthName, Toast.LENGTH_SHORT).show()
        }
        holder.bind(listener, healthItems[position])
    }

    // 항목 개수를 판단하려고 자동으로 호출
    class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        fun bind(listener: View.OnClickListener, item:HealthItem) {
            view.health_image.setImageResource(item.healthImage)
            view.health_name.text = item.healthName
            view.health_description.text = item.healthDescription
            view.setOnClickListener(listener)
        }
    }
}

