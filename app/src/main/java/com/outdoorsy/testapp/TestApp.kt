package com.outdoorsy.testapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.outdoorsy.testapp.modules.ApplicationComponent
import com.outdoorsy.testapp.modules.ApplicationModule
import com.outdoorsy.testapp.modules.DaggerApplicationComponent

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        sApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent?) {
        sApplicationComponent = applicationComponent
    }

    companion object {
        private var sApplicationComponent: ApplicationComponent? = null
        val component: ApplicationComponent?
            get() = sApplicationComponent
    }
}
