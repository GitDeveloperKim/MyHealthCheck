package com.example.myapplication01.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication01.R

class HealthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health)

        val intent: Intent = getIntent()
        val name = intent.getStringExtra("name")

        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }
}