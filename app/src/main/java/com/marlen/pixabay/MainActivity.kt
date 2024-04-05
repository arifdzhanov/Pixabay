package com.marlen.pixabay

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.marlen.pixabay.presentation.MainRoute
import com.marlen.pixabay.ui.theme.PixabayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        registerConnectivityManager()
        setContent {
            PixabayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box( modifier = Modifier.padding(innerPadding)){
                        MainRoute()
                    }
                }
            }
        }
    }

    private fun registerConnectivityManager() {

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                viewModel.setInternetAvailable(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                viewModel.setInternetAvailable(false)
            }
        })
    }
}