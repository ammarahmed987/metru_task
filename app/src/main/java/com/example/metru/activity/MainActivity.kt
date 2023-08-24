package com.example.metru.activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.metru.R
import com.example.metru.databinding.ActivityMainBinding

class MainActivity : DockActivity(){

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var unbinder: Unbinder
        @SuppressLint("StaticFieldLeak")
        lateinit var navController: NavController
    }

    lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var contentView: ConstraintLayout
    private lateinit var actionBarMenu: Menu

    override fun getDockFrameLayoutId(): Int {
        return R.id.container
    }

    // Monitors connection to the while-in-use service.
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        unbinder = ButterKnife.bind(this)
        setContentView(binding.root)
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color))

        initView()
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        actionBarMenu = menu
//        actionBarMenu.findItem(R.id.action_notification).setOnMenuItemClickListener {
//            true
//        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun initView() {
        setSupportActionBar(findViewById(R.id.toolBar))
        contentView = binding.appBarMain.content
        navController = findNavController(R.id.nav_host_main)
        //binding.appBarMain.botttomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.appBarMain.title.text = destination.label
        }
        supportActionBar?.hide()
    }

}