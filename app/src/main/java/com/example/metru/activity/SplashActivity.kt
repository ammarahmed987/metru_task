package com.example.metru.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.metru.R
import com.example.metru.databinding.ActivitySplashBinding
import com.example.metru.utils.SharedPrefManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val TIME_OUT: Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) { /* ... */
                    logoAnimation()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) { /* ... */
                }
            }).check()

    }

    private fun logoAnimation() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            nextView()
        }, TIME_OUT)
    }

    private fun nextView() {
        val sharedPrefManager = SharedPrefManager(this)

        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        finish()
    }
}