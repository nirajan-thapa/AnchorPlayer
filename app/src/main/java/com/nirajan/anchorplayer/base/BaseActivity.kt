package com.nirajan.anchorplayer.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject lateinit var factory: ViewModelProvider.Factory

    @LayoutRes protected abstract fun getLayoutId(): Int

    /** Inject your Activity here. */
    protected abstract fun injectDependencies()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }
}
