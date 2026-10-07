package com.example.myapplication01.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication01.databinding.HealthItemBinding
import com.example.myapplication01.ui.activity.HealthActivity
import com.example.myapplication01.vo.HealthItem

class HealthAdapter (val healthItems: MutableList<HealthItem>):
    RecyclerView.Adapter<HealthAdapter.ViewHolder>()
{
    //var healthItems = mutableListOf<String>() // todo image link, string, string 으로 구성된 클래스로 변경 필요
    override fun getItemCount(): Int = healthItems.size

    // 항목의 뷰를 가지는 뷰 홀더를 준비하려고 자동으로 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HealthItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    // 뷰 홀더의 뷰에 데이터를 출력하려고 자동으로 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listener = View.OnClickListener {
           // Toast.makeText(it.context, healthItems[position].healthName, Toast.LENGTH_SHORT).show()

            val intent: Intent = Intent(it.context, HealthActivity::class.java)
            intent.putExtra("name", healthItems[position].healthName)
            it.context.startActivity(intent)
        }
        holder.bind(listener, healthItems[position])
    }

    // 항목 개수를 판단하려고 자동으로 호출
    class ViewHolder(private val binding: HealthItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: HealthItem) {
            binding.healthImage.setImageResource(item.healthImage)
            binding.healthName.text = item.healthName
            binding.healthDescription.text = item.healthDescription
            binding.root.setOnClickListener(listener)
        }
    }
}

