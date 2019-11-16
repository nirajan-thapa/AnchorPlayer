package com.nirajan.anchorplayer.player

import android.os.Bundle
import com.nirajan.anchorplayer.R.layout
import com.nirajan.anchorplayer.base.BaseActivity
import dagger.android.AndroidInjection

class PlayerActivity : BaseActivity() {

    override fun getLayoutId() = layout.activity_player

    override fun injectDependencies() = AndroidInjection.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
