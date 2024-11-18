package com.app.uxcam.spector_analytics

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class SpectorTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, SpectorTestApp::class.java.name, context)
    }
}