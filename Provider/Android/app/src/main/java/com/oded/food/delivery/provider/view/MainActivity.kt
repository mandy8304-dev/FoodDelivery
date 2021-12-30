package com.oded.food.delivery.provider.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.oded.food.delivery.provider.R
import com.oded.food.delivery.provider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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
                R.id.nav_home, R.id.nav_product, R.id.nav_dispatch, R.id.nav_inventory
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
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
                R.id.nav_setting_app -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_setting_app, Bundle())
                    true
                }
                R.id.nav_product -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_product, Bundle())
                    true
                }
                R.id.nav_inventory -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_inventory, Bundle())
                    true
                }
                R.id.nav_dispatch -> {
                    binding.drawerLayout.closeDrawers()
                    navController.navigate(R.id.nav_dispatch, Bundle())
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

    }
}