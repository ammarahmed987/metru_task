package com.example.metru.fragment.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.metru.R
import com.example.metru.activity.DockActivity
import com.example.metru.base.BaseDialogFragment
import com.example.metru.base.ClickListener
import com.example.metru.constant.Constants
import com.example.metru.databinding.FragmentRecordingCompletedDialogBinding

class RecordingCompletedDialogFragment(private val listener: ClickListener, private val redoLeft: Int,
                                       private val newDockActivity: DockActivity, private val recordingUri: Uri) : BaseDialogFragment(), ClickListener {

    private lateinit var binding: FragmentRecordingCompletedDialogBinding
    private val bundle = Bundle()

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

                bundle.putString(Constants.REC_COMPLETED_RECORDING_URI, recordingUri.toString())
                navigateToFragment(R.id.action_nav_camera_fragment_to_nav_play_recording_fragment, bundle)
            }
            it.imgBtnShowQuestion.setOnClickListener {
                val showQuestion = QuestionDialogFragment(this, Constants.DIALOG_FROM_RECORDING_COMPLETED)
                showQuestion.show(childFragmentManager, Constants.SHOW_DIALOG)
            }
        }
    }

    // AMMAR - Countdown object to give answer
    private val countdownTimerRedoAnswer = object : CountDownTimer(Constants.COUNTDOWN_SECONDARY_TIMER_DURATION, Constants.COUNTDOWN_INTERVAL) {

        override fun onTick(millisUntilFinished: Long) {
            try {
                newDockActivity.countDownFormatting(requireContext(), millisUntilFinished, binding.tvRedoAnswer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onFinish() {
            bundle.putString(Constants.REC_COMPLETED_RECORDING_URI, recordingUri.toString())
            navigateToFragment(R.id.action_nav_camera_fragment_to_nav_play_recording_fragment, bundle)
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

    override fun <T> onClick(data: T, type: String, createNested: Boolean) {
        TODO("Not yet implemented")
    }
}