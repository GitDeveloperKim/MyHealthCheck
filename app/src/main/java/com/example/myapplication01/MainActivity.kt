package com.example.myapplication01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.example.myapplication01.vo.Sex
import com.example.myapplication01.vo.User
import com.example.myapplication01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var data1: String // lateinit + var // Long, Short, Double, Floate, Boolean, Byte 타입 불가
    var data2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 획득
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // 액티비티 화면 출력
        setContentView(binding.root)

        var kimyoungseok = User("kimyougnseok", 80, 1.8, Sex.MALE)
        kimyoungseok.calculateBMI()
        Toast.makeText(this, "test world: " + kimyoungseok.bmi, Toast.LENGTH_SHORT).show()

        // 뷰 객체 이용
        binding.trainingButton.setOnClickListener{
            Toast.makeText(this, "training btn ", Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_view, MainFragment())
                .commit()
        }

        binding.calorieButton.setOnClickListener{
            Toast.makeText(this, "calorie btn", Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_view, HealthFragment())
                .commit()
        }

        binding.timerButton.setOnClickListener{
            Toast.makeText(this, "timer btn", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> Log.d("kkang", "back button click")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("kkang", "volume up button click")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("kkang", "volume down button click")
        }
        return super.onKeyDown(keyCode, event)
    }
}