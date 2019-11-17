package com.nirajan.anchorplayer.base.di

import androidx.lifecycle.ViewModel
import com.nirajan.anchorplayer.base.BaseViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * [MapKey] annotation to key bindings by a type of a [ViewModel].
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out BaseViewModel<*>>)
