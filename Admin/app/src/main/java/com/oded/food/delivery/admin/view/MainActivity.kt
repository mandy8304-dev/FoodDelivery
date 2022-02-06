package com.oded.food.delivery.admin.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.oded.food.delivery.admin.R
import com.oded.food.delivery.admin.common.Dialog
import com.oded.food.delivery.admin.common.impl.ICallback
import com.oded.food.delivery.admin.databinding.ActivityMainBinding



class MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    companion object {
        fun intent(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        binding.navView.setNavigationItemSelectedListener(navigationItemListener)
        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_provider, R.id.nav_distributor, R.id.nav_client,
                R.id.nav_order, R.id.nav_employee, R.id.nav_product
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private val navigationItemListener =
        NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_home -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_home, Bundle())
                    true
                }
                R.id.nav_provider -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_provider, Bundle())
                    true
                }
                R.id.nav_distributor -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_distributor, Bundle())
                    true
                }
                R.id.nav_client -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_client, Bundle())
                    true
                }
                R.id.nav_order -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_order, Bundle())
                    true
                }
                R.id.nav_setting_app -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_setting_app, Bundle())
                    true
                }
                R.id.nav_employee -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_employee, Bundle())
                    true
                }
                R.id.nav_product -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_product, Bundle())
                    true
                }
                R.id.nav_closed_session -> {
                    binding.drawerLayout.closeDrawers()
                    logout()
                    true
                }


                else -> false
            }
        }

    private fun logout() {

        Dialog.showDialogAsk(this@MainActivity, object : ICallback {
            override fun callback(param: Any?) {
                if (param as String == Dialog.ACTION_YES) {
                    auth?.signOut()
                    startActivity(SplashScreenActivity.intent(this@MainActivity))
                    finish()
                }
            }

        }, getString(R.string.ask_logout), getString(R.string.yes), getString(R.string.no))

    }
}