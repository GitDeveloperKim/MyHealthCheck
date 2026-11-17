package com.example.myapplication01.ui.activity

import android.content.Context
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
 * 나이/키/몸무게/성별 입력 화면.
 * - 첫 로그인 시: 한 번 입력 후 메인으로 이동
 * - 수정 모드([editIntent]): 저장된 값을 채워서 보여주고, 저장하면 이전 화면(프로필)으로 돌아감
 * BMI 는 입력받지 않고 키/몸무게로 계산한다.
 */
class ProfileSetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileSetupBinding
    private val isEditMode by lazy { intent.getBooleanExtra(EXTRA_EDIT_MODE, false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PreferenceManager.getName(this)?.let {
            binding.greetingText.text = getString(R.string.profile_greeting, it)
        }

        if (isEditMode) {
            setTitle(R.string.profile_edit_title)
            binding.descriptionText.setText(R.string.profile_edit_description)
            binding.saveButton.setText(R.string.profile_edit_save)
            // 화면 회전 등으로 재생성될 때는 사용자가 입력 중이던 값을 유지
            if (savedInstanceState == null) fillSavedProfile()
        }

        binding.saveButton.setOnClickListener { save() }
    }

    /** 저장된 신체 정보를 입력칸에 채운다. */
    private fun fillSavedProfile() {
        if (!PreferenceManager.isProfileCompleted(this)) return
        binding.ageInput.setText(PreferenceManager.getAge(this).toString())
        binding.heightInput.setText(formatNumber(PreferenceManager.getHeightCm(this)))
        binding.weightInput.setText(formatNumber(PreferenceManager.getWeightKg(this)))
        when (PreferenceManager.getSex(this)) {
            Sex.MALE -> binding.maleRadio.isChecked = true
            Sex.FEMALE -> binding.femaleRadio.isChecked = true
            null -> Unit
        }
    }

    /** 175.0 -> "175", 175.5 -> "175.5" */
    private fun formatNumber(value: Float): String =
        if (value % 1f == 0f) value.toInt().toString() else value.toString()

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

        if (isEditMode) {
            Toast.makeText(this, R.string.profile_edit_saved, Toast.LENGTH_SHORT).show()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
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

    companion object {
        private const val EXTRA_EDIT_MODE = "extra_edit_mode"

        /** 프로필 화면에서 정보 수정용으로 열 때 사용 */
        fun editIntent(context: Context): Intent =
            Intent(context, ProfileSetupActivity::class.java).putExtra(EXTRA_EDIT_MODE, true)
    }
}
