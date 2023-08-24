package com.example.metru.fragment.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.metru.base.BaseDialogFragment
import com.example.metru.base.ClickListener
import com.example.metru.constant.Constants
import com.example.metru.databinding.FragmentQuestionDialogBinding

class QuestionDialogFragment(private val listener: ClickListener) : BaseDialogFragment() {

    private lateinit var binding: FragmentQuestionDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        initView()

        return binding.root
    }

    private fun initView() {
        binding = FragmentQuestionDialogBinding.inflate(layoutInflater)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.let {
            it.btnRecord.setOnClickListener {
                listener.onClick(data = Constants.QUES_START_RECORDING)
                dialog?.dismiss()
            }
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