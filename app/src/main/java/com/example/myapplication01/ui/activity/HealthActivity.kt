package com.example.myapplication01.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication01.R
import com.example.myapplication01.databinding.ActivityHealthBinding
import com.example.myapplication01.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HealthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHealthBinding
    private val tabTitles = arrayOf(
        "health",
        "diet"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        val intent: Intent = getIntent()
        val name = intent.getStringExtra("name")

        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }
}