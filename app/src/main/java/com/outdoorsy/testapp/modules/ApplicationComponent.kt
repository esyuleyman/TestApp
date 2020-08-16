package com.outdoorsy.testapp.modules

import android.content.Context
import com.outdoorsy.testapp.client.OutdoorsySearchClient
import dagger.Component
import javax.inject.Singleton

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context?

    fun outdoorsySearchClient(): OutdoorsySearchClient?
}
