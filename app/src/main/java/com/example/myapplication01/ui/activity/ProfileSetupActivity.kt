package com.example.myapplication01.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication01.R
import com.example.myapplication01.databinding.ActivityProfileSetupBinding
import com.example.myapplication01.util.PreferenceManager
import com.example.myapplication01.vo.Sex

/**
 * 첫 로그인 시 한 번만 나이/키/몸무게/성별을 입력받는 화면.
 */
class ProfileSetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileSetupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PreferenceManager.getName(this)?.let {
            binding.greetingText.text = getString(R.string.profile_greeting, it)
        }

        binding.saveButton.setOnClickListener { save() }
    }

    private fun save() {
        val age = readNumber(binding.ageInput, 1f, 120f, "나이")?.toInt() ?: return
        val height = readNumber(binding.heightInput, 50f, 250f, "키") ?: return
        val weight = readNumber(binding.weightInput, 10f, 300f, "몸무게") ?: return

        val sex = when (binding.sexGroup.checkedRadioButtonId) {
            R.id.maleRadio -> Sex.MALE
            R.id.femaleRadio -> Sex.FEMALE
            else -> {
                Toast.makeText(this, "성별을 선택해 주세요", Toast.LENGTH_SHORT).show()
                return
            }
        }

        PreferenceManager.saveProfile(this, age, height, weight, sex)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /** 입력값을 숫자로 읽고 범위를 검사한다. 잘못되면 에러를 표시하고 null 반환. */
    private fun readNumber(input: EditText, min: Float, max: Float, label: String): Float? {
        val value = input.text.toString().trim().toFloatOrNull()
        if (value == null || value < min || value > max) {
            input.error = "$label 을(를) 올바르게 입력해 주세요 (${min.toInt()}~${max.toInt()})"
            input.requestFocus()
            return null
        }
        return value
    }
}
