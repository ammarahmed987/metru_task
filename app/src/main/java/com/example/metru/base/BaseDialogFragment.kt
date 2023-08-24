package com.example.metru.base

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.metru.progress.ProgressIndicator

open class BaseDialogFragment : DialogFragment(),BaseView, ProgressIndicator {
    override fun closeDrawer() {

    }

    override fun showBanner(text: String, type: String) {

    }

    override fun navigateToFragment(id: Int, args: Bundle?) {

    }


    override fun setTitle(text: String) {

    }


    override fun <T> initiateListArrayAdapter(list: List<T>): ArrayAdapter<T> {
        TODO("Not yet implemented")
    }



    override fun showProgressIndicator() {

    }

    override fun hideProgressIndicator() {

    }
}