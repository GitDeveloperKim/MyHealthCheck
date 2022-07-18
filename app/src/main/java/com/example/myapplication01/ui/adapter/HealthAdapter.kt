package com.example.myapplication01.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication01.R

import kotlinx.android.synthetic.main.health_item.view.*

class HealthAdapter (val healthItems: MutableList<String>):
    RecyclerView.Adapter<HealthAdapter.ViewHolder>() {

    //var healthItems = mutableListOf<String>() // todo image link, string, string 으로 구성된 클래스로 변경 필요

    override fun getItemCount(): Int = healthItems.size

    // 항목의 뷰를 가지는 뷰 홀더를 준비하려고 자동으로 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context)
            .inflate(R.layout.health_item, parent, false)
        return HealthAdapter.ViewHolder(inflateView)
    }
    // = MyViewHolder(HealthItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    // 뷰 홀더의 뷰에 데이터를 출력하려고 자동으로 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(healthItems[position])
    }

//    fun replaceData (updatedList : MutableList<String>) {
//        healthItems = updatedList.toMutableList()
//        notifyDataSetChanged()
//    }

    // 항목 개수를 판단하려고 자동으로 호출
    class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        fun bind(item:String) {
            view.health_name.text = item
        }
    }
}

