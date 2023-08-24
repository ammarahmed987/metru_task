package com.example.metru.fragment.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.metru.R
import com.example.metru.activity.DockActivity
import com.example.metru.base.BaseDialogFragment
import com.example.metru.base.ClickListener
import com.example.metru.constant.Constants
import com.example.metru.databinding.FragmentRecordingCompletedDialogBinding

class RecordingCompletedDialogFragment(private val listener: ClickListener, private val redoLeft: Int, private val newDockActivity: DockActivity) : BaseDialogFragment() {

    private lateinit var binding: FragmentRecordingCompletedDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        initView()

        countdownTimerRedoAnswer.start()

        return binding.root
    }

    private fun initView() {
        binding = FragmentRecordingCompletedDialogBinding.inflate(layoutInflater)
        setOnClickListener()
        binding.btnRedo.text = getString(R.string.nav_rec_completed_redo, redoLeft)
    }

    private fun setOnClickListener() {
        binding.let {
            it.btnRedo.setOnClickListener {
                if (redoLeft != 0) {
                    countdownTimerRedoAnswer.cancel()
                    listener.onClick(data = Constants.REC_COMPLETED_REDO)
                    dialog?.dismiss()
                } else {
                    newDockActivity.showErrorSnackBar(requireView(), requireContext(), "Sorry! You don't have any redos left")
                }
            }
            it.btnContinue.setOnClickListener {
                countdownTimerRedoAnswer.cancel()
                dialog?.dismiss()
                navigateToFragment(R.id.action_nav_camera_fragment_to_nav_play_recording_fragment)
            }
        }
    }

    // AMMAR - Countdown object to give answer
    private val countdownTimerRedoAnswer = object : CountDownTimer(11000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            newDockActivity.countDownFormatting(requireContext(), millisUntilFinished, binding.tvRedoAnswer)
        }

        override fun onFinish() {
            navigateToFragment(R.id.action_nav_camera_fragment_to_nav_play_recording_fragment)
        }
    }

    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
//        params?.height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}