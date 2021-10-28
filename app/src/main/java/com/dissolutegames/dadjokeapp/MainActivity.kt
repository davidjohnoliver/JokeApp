package com.dissolutegames.jokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dissolutegames.jokeapp.databinding.ActivityMainBinding
import com.dissolutegames.jokeapp.services.JokeService
import com.dissolutegames.jokeapp.services.JokeStorageService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_random, R.id.navigation_starred))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        JokeService.initialize()
        JokeStorageService.initialize(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun finish() {
        JokeService.close()
        JokeStorageService.close()
        super.finish()
    }
}