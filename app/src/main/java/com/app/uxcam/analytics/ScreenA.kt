package com.app.uxcam.analytics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScreenA(
    modifier: Modifier = Modifier
) {

    Scaffold { padding ->
        val viewModel = koinViewModel<DemoViewModel>()
        Column(
            modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    viewModel.startSession()
                }
            ) {
                Text("Start session")
            }
            Button(
                onClick = {
                    viewModel.trackEvent("button_click")
                }
            ) {
                Text("Track event")
            }
            Button(
                onClick = {
                    viewModel.trackEvent(
                        "login",
                        data = mapOf(
                            "email" to "test@mail.com",
                            "password" to "12345"
                        )
                    )
                }
            ) {
                Text("User Auth - Login")
            }
            Button(
                onClick = {
                    viewModel.endSession()
                }
            ) {
                Text("End session")
            }
        }
    }
}