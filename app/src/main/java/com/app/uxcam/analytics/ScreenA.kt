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
    modifier: Modifier = Modifier,
    gotoB: () -> Unit = {}
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
                        "screen_view",
                        data = mapOf(
                            "previous_screen" to "ScreenA",
                            "current_screen" to "ScreenB"
                        )
                    )
                    gotoB()
                }
            ) {
                Text("Goto Screen B")
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