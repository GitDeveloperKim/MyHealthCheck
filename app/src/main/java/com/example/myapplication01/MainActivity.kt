package com.example.myapplication01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var data1: String // lateinit + var // Long, Short, Double, Floate, Boolean, Byte 타입 불가
    var data2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "test world", Toast.LENGTH_SHORT).show()
    }
}