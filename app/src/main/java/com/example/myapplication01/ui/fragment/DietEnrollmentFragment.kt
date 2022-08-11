package com.example.myapplication01.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication01.R
import com.example.myapplication01.databinding.FragmentDietEnrollmentBinding

class DietEnrollmentFragment : Fragment() {
    lateinit var binding: FragmentDietEnrollmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDietEnrollmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}