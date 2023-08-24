package com.example.metru.base

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.metru.activity.DockActivity
import com.example.metru.activity.MainActivity
import com.example.metru.common.LoadingListener
import com.example.metru.network.ApiListener
import com.example.metru.progress.ProgressIndicator
import com.example.metru.room.RoomHelper
import com.example.metru.utils.DateTimeFormatter
import com.example.metru.utils.SharedPrefManager
import com.example.metru.utils.UtilHelper
import com.example.metru.utils.ValidationHelper
import javax.inject.Inject

open class BaseDialogFragment : DialogFragment(),BaseView, ProgressIndicator, ApiListener {

    @Inject
    lateinit var sharedPrefManager: SharedPrefManager

    var myDockActivity: DockActivity? = null

    private lateinit var apiListener: ApiListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myDockActivity = getDockActivity()
        apiListener = this
    }

    private fun getDockActivity(): DockActivity? {
        return myDockActivity
    }

    override fun closeDrawer() {

    }

    override fun showBanner(text: String, type: String) {

    }

    override fun navigateToFragment(id: Int, args: Bundle?) {
        if(MainActivity.navController.currentBackStackEntry?.destination?.id == id){
            closeDrawer()
            return
        }
        if (args != null) {
            MainActivity.navController.navigate(id, args)
            return
        }
        MainActivity.navController.navigate(id)
    }


    override fun setTitle(text: String) {

    }


    override fun <T> initiateListArrayAdapter(list: List<T>): ArrayAdapter<T> {
        TODO("Not yet implemented")
    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(liveData: LiveData<String>, tag: String) {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String, tag: String) {
        TODO("Not yet implemented")
    }

    override fun onFailureWithResponseCode(code: Int, message: String, tag: String) {
        TODO("Not yet implemented")
    }

    override fun showProgressIndicator() {
        TODO("Not yet implemented")
    }

    override fun hideProgressIndicator() {
        TODO("Not yet implemented")
    }

}
