package com.example.metru.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.metru.activity.*
import com.example.metru.common.LoadingListener
import com.example.metru.network.ApiListener
import com.example.metru.room.RoomHelper
import com.example.metru.utils.DateTimeFormatter
import com.example.metru.utils.SharedPrefManager
import com.example.metru.utils.UtilHelper
import com.example.metru.utils.ValidationHelper
import dagger.android.support.DaggerFragment

import javax.inject.Inject

/**
 * @author Abdullah Nagori
 */


abstract class BaseDockFragment : DaggerFragment(), ApiListener {

    var myDockActivity: DockActivity? = null
    private var isLoading = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPrefManager: SharedPrefManager

    @Inject
    lateinit var roomHelper: RoomHelper

    @Inject
    lateinit var validationhelper: ValidationHelper

    @Inject
    lateinit var dateTimeFormatter: DateTimeFormatter

    @Inject
    lateinit var utilHelper: UtilHelper

    private lateinit var apiListener: ApiListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myDockActivity = getDockActivity()
        apiListener = this
    }

    private fun getDockActivity(): DockActivity? {
        return myDockActivity
    }

    protected fun loadingStarted() {
        if (parentFragment != null) (parentFragment as LoadingListener?)?.onLoadingStarted() else getDockActivity()!!
        isLoading = true
    }

    protected fun loadingFinished() {
        if (parentFragment != null) (parentFragment as LoadingListener?)?.onLoadingFinished() else if (getDockActivity() != null) getDockActivity()!!
        isLoading = false
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myDockActivity = context as DockActivity
    }

    override fun onStarted() {
       // myDockActivity?.onLoadingStarted()
        //myDockActivity?.showProgressIndicator()
    }

    override fun onSuccess(liveData: LiveData<String>, tag: String) {
        myDockActivity?.onSuccessResponse(liveData, tag)
    }

    override fun onFailure(message: String, tag: String) {
        myDockActivity?.onFailureResponse(message, tag)
    }

    override fun onFailureWithResponseCode(code: Int, message: String, tag: String) {
        myDockActivity?.hideProgressIndicator()
//        if (code == 551) {
//            sharedPrefManager.clear()
//            startActivity(Intent(requireContext(), LoginActivity::class.java))
//        } else if (code == 552) {
//            startActivity(Intent(requireContext(), ChangePasswordActivity::class.java))
//        }
    }

     fun showBanner(text: String, type: String) {
        if (activity != null) (activity as DockActivity)
    }

    fun navigateToFragment(@IdRes id: Int, args: Bundle? = null) {
        if (args != null) {
            MainActivity.navController.navigate(id, args)
            return
        }
        MainActivity.navController.navigate(id)
    }

//    override fun <T> initiateListArrayAdapter(list: List<T>): ArrayAdapter<T> {
//        val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, list)
//        adapter.setDropDownViewResource(R.layout.item_spinner)
//        return adapter
//    }
}