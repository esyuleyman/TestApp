package com.outdoorsy.testapp.modules

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class ApplicationContext
