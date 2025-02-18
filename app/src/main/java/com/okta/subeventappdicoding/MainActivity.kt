package com.okta.subeventappdicoding

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.okta.subeventappdicoding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_active -> {
                    navController.navigate(R.id.activeEventsFragment)
                    true
                }
                R.id.nav_past -> {
                    navController.navigate(R.id.pastEventsFragment)
                    true
                }
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.favoriteFragment -> {
                    navController.navigate(R.id.favoriteFragment)
                    true
                }
                R.id.settingFragment -> {
                    navController.navigate(R.id.settingFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_active -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.activeEventsFragment)
                true
            }
            R.id.nav_past -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.pastEventsFragment)
                true
            }
            R.id.homeFragment -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                true
            }
            R.id.favoriteFragment -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.favoriteFragment)
                true
            }
            R.id.settingFragment -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.settingFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}