package com.example.myapplication01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication01.dao.User

class MainActivity : AppCompatActivity() {
    lateinit var data1: String // lateinit + var // Long, Short, Double, Floate, Boolean, Byte 타입 불가
    var data2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var kimyoungseok = User("kimyougnseok", 80, 1.8)
        kimyoungseok.calculateBMI()
        Toast.makeText(this, "test world: " + kimyoungseok.bmi, Toast.LENGTH_SHORT).show()
    }
}