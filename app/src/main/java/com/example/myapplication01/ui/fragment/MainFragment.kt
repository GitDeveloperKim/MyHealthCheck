package com.example.myapplication01.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication01.R
import com.example.myapplication01.databinding.FragmentMainBinding
import com.example.myapplication01.ui.activity.ProfileSetupActivity
import com.example.myapplication01.util.PreferenceManager
import com.example.myapplication01.vo.Sex

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        // 정보 수정: 입력 화면을 수정 모드로 열고, 돌아오면 onResume 에서 다시 표시된다.
        binding.editProfileButton.setOnClickListener {
            startActivity(ProfileSetupActivity.editIntent(requireContext()))
        }


        var view = binding.root
        return view
    }

    override fun onResume() {
        super.onResume()
        showProfile()
    }

    /** 로그인 시 저장한 계정 정보 + 처음 입력한 신체 정보를 화면에 표시한다. */
    private fun showProfile() {
        val ctx = requireContext()

        binding.userName.text = PreferenceManager.getName(ctx) ?: getString(R.string.profile_unknown_name)
        binding.userEmail.text = PreferenceManager.getEmail(ctx).orEmpty()

        if (!PreferenceManager.isProfileCompleted(ctx)) {
            val notSet = getString(R.string.profile_not_set)
            binding.userSexAge.text = notSet
            binding.userHeight.text = notSet
            binding.userWeight.text = notSet
            binding.userBMI.text = notSet
            return
        }

        val heightCm = PreferenceManager.getHeightCm(ctx)
        val weightKg = PreferenceManager.getWeightKg(ctx)
        val sex = PreferenceManager.getSex(ctx)
        val sexLabel = when (sex) {
            Sex.MALE -> getString(R.string.profile_male)
            Sex.FEMALE -> getString(R.string.profile_female)
            null -> getString(R.string.profile_not_set)
        }
        // 성별에 맞는 일러스트 (여성이면 운동복 입은 여성, 그 외는 기본 남성 이미지)
        binding.mainImageView.setImageResource(
            if (sex == Sex.FEMALE) R.drawable.ic_muscles_female else R.drawable.ic_muscles
        )

        binding.userSexAge.text = getString(R.string.profile_sex_age, sexLabel, PreferenceManager.getAge(ctx))
        binding.userHeight.text = getString(R.string.profile_height_value, heightCm)
        binding.userWeight.text = getString(R.string.profile_weight_value, weightKg)

        val heightM = heightCm / 100f
        binding.userBMI.text = if (heightM > 0f) {
            getString(R.string.profile_bmi_value, weightKg / (heightM * heightM))
        } else {
            getString(R.string.profile_not_set)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}