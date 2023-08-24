package com.example.metru.activity

import android.os.Bundle
import com.example.metru.R
import com.example.metru.databinding.ActivityWelcomeLoginBinding
import com.example.metru.fragment.WelcomeLoginFragment

class WelcomeLoginActivity : DockActivity() {

    lateinit var binding: ActivityWelcomeLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment()
    }

    override fun getDockFrameLayoutId(): Int {
        return R.id.container
    }

    private fun initFragment() {
        replaceDockableFragmentWithoutBackStack(WelcomeLoginFragment())
    }
}