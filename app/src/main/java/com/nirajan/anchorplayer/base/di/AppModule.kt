package com.nirajan.anchorplayer.base.di

import com.nirajan.anchorplayer.player.PlayerActivity
import com.nirajan.anchorplayer.player.PlayerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
interface AppModule {

    @ContributesAndroidInjector fun providePlayerActivity(): PlayerActivity
    @ContributesAndroidInjector fun providePlayerFragment(): PlayerFragment
}
