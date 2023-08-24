package com.example.metru.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import com.example.metru.R
import com.example.metru.activity.MainActivity
import com.example.metru.base.BaseDockFragment
import com.example.metru.base.ClickListener
import com.example.metru.constant.Constants
import com.example.metru.databinding.FragmentCameraBinding
import com.example.metru.databinding.FragmentPlayRecordingBinding
import com.example.metru.fragment.dialogs.QuestionDialogFragment
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PlayRecordingFragment : BaseDockFragment() {

    private lateinit var binding: FragmentPlayRecordingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        initView()

        return binding.root
    }

    private fun initView() {
        binding = FragmentPlayRecordingBinding.inflate(layoutInflater)
        (activity as MainActivity).supportActionBar?.hide()
//        setOnClickListener()
    }
}