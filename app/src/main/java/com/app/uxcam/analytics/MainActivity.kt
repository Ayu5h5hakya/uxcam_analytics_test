package com.app.uxcam.analytics

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import com.app.uxcam.analytics.core.theme.MyAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold { padding ->
                    val viewModel : DemoViewModel by viewModel()
                    DemoApp(Modifier.padding(padding), viewModel)
                }
            }
        }
    }
}

@Composable
fun DemoApp(modifier: Modifier = Modifier, viewModel: DemoViewModel) {
    Column(
        modifier.fillMaxSize(),
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
                viewModel.trackEvent()
            }
        ) {
            Text("Track event")
        }
        Button(
            onClick = {
                viewModel.trackEvent()
            }
        ) {
            Text("End session")
        }
    }
}