package com.app.uxcam.analytics

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.uxcam.analytics.core.theme.MyAppTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object ScreenA

@Serializable
object ScreenB

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                DemoApp()
            }
        }
    }
}

@Composable
fun DemoApp(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val viewModel = koinViewModel<DemoViewModel>()
    val navController = rememberNavController()
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver {_, event ->
            when(event) {
                Lifecycle.Event.ON_CREATE -> {
                    viewModel.startSession()
                }
                else -> {
                    //custom events for other lifecycle events can be created here.
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        return@DisposableEffect onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    NavHost(
        navController = navController,
        startDestination = ScreenA
    ) {
        composable<ScreenA> {
            ScreenA(
                gotoB = {
                    navController.navigate(route = ScreenB)
                }
            )
        }
        composable<ScreenB> {
            ScreenB()
        }
    }
}