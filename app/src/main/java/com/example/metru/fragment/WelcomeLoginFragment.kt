package com.example.metru.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.metru.base.BaseDockFragment
import com.example.metru.databinding.WelcomeLoginFragmentBinding




class WelcomeLoginFragment : BaseDockFragment() {

    lateinit var binding: WelcomeLoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initView()

        return binding.root
    }

    private fun initView() {
        binding = WelcomeLoginFragmentBinding.inflate(layoutInflater)
    }


}