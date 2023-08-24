package com.example.metru.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.metru.R
import com.example.metru.databinding.ActivityLoginBinding

class LoginActivity : DockActivity() {

    lateinit var binding: ActivityLoginBinding

    companion object {
        lateinit var navController : NavController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_login_fragment)
    }

    override fun getDockFrameLayoutId(): Int {
        return R.id.container
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_login_fragment)
        return super.onSupportNavigateUp()
    }



}