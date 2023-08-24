package com.example.metru.activity

import android.annotation.SuppressLint
import android.content.*
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.tapadoo.alerter.Alerter
import com.example.metru.R
import com.example.metru.base.BaseDockFragment
import com.example.metru.progress.ProgressDialog
import com.example.metru.progress.ProgressIndicator
import com.example.metru.room.RoomHelper
import com.example.metru.utils.InternetHelper
import com.example.metru.utils.SharedPrefManager
import com.example.metru.viewModel.UserViewModel
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject

/**
 * @author Abdullah Nagori
 */

abstract class DockActivity : DaggerAppCompatActivity(), ProgressIndicator {

    abstract fun getDockFrameLayoutId(): Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPrefManager: SharedPrefManager

    @Inject
    lateinit var roomHelper: RoomHelper

    @Inject
    lateinit var internetHelper: InternetHelper

    var location: Location = Location(LocationManager.GPS_PROVIDER)
    var latitude: String? = ""
    var longitude: String? = ""
    var locationManager: LocationManager? = null
    private lateinit var progressBarDialog: ProgressDialog
    private lateinit var userViewModel: UserViewModel
    private var mBound = false


    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModels()
  //      getLocation()

    }

    private fun initViewModels() {
        userViewModel = ViewModelProvider(viewModelStore as androidx.lifecycle.ViewModelStore, viewModelFactory).get(UserViewModel::class.java)
    }

    fun hideKeyboard(view: View) {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            view.applicationWindowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun replaceDockableFragmentWithoutBackStack(frag: BaseDockFragment?) {
        val transaction = supportFragmentManager
            .beginTransaction()
        transaction.replace(getDockFrameLayoutId(), frag!!)
        transaction.commit()
    }

    fun getUserViewModel(): UserViewModel {
        return userViewModel
    }

    open fun showErrorMessage(message: String) {
        Alerter.create(this)
            .setTitle(getString(R.string.error))
            .setText(message)
            .setDuration(5000)
            .setIcon(R.drawable.ic_close)
            .setBackgroundColorRes(R.color.error_color)
            .enableSwipeToDismiss()
            .show()
    }

    open fun showSuccessMessage(message: String) {
        Alerter.create(this)
            .setTitle(getString(R.string.success))
            .setText(message)
            .setDuration(5000)
            .setIcon(R.drawable.ic_close)
            .setBackgroundColorRes(R.color.banner_green_color)
            .enableSwipeToDismiss()
            .show()
    }

    open fun onSuccessResponse(liveData: LiveData<String>, tag: String) {
        this.hideProgressIndicator()
    }

    open fun onFailureResponse(message: String, tag: String) {
        this.hideProgressIndicator()
        this.showErrorMessage(message)
    }

    fun <T : Iterable<*>?> nullGuard(item: T?): T {
        return (item ?: Collections.EMPTY_LIST) as T
    }

    override fun showProgressIndicator() {
        progressBarDialog = ProgressDialog()
        progressBarDialog.showDialog(
            supportFragmentManager,
            DockActivity::class.java.simpleName
        )
    }

    override fun hideProgressIndicator() {
        if (this::progressBarDialog.isInitialized && progressBarDialog.isAdded) {
            progressBarDialog.dismiss()
        }
    }

    private fun logResultsToScreen(output: String) {
        Log.i("Foreground", output)
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

//    @SuppressLint("MissingPermission")
//    private fun getLocation() {
//        LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnSuccessListener {
//            try {
//                if (it == null) {
//                    getLocation()
//                }
//                latitude = it.latitude.toString()
//                longitude = it.longitude.toString()
//                Log.i(
//                    "CurrentLocation",
//                    "Your Location: \nLatitude: $latitude\nLongitude: $longitude"
//                )
//            } catch (e: java.lang.Exception) {
//            }
//        }
//    }

    fun visibleWithAnimation(view: View) {
        view.visibility = View.VISIBLE
        view.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.slide_in_right
            )
        )
    }

    fun goneWithAnimation(view: View) {
        view.visibility = View.GONE
        view.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.slide_out_right
            )
        )
    }

//    fun closeDrawer() {
//        drawer_layout.closeDrawer(GravityCompat.START)
//    }

}