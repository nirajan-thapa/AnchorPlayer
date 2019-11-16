package com.nirajan.anchorplayer

import android.app.Application
import com.nirajan.anchorplayer.base.di.AppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AnchorPlayerApp : Application(), HasAndroidInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private val component: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
    }
}
