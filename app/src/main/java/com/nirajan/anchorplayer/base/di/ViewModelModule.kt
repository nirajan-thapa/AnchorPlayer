package com.nirajan.anchorplayer.base.di

import androidx.lifecycle.ViewModelProvider
import com.nirajan.anchorplayer.base.AnchorPlayerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: AnchorPlayerViewModelFactory): ViewModelProvider.Factory
}
