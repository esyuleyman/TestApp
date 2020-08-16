package com.outdoorsy.testapp.modules

import android.app.Application
import android.content.Context
import com.outdoorsy.testapp.client.OutdoorsySearchClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

@Module
class ApplicationModule(protected val mApplication: Application) {
    @Provides
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideOutdoorsySearchClient(): OutdoorsySearchClient? {
        return OutdoorsySearchClient()
    }


}
