package com.nirajan.anchorplayer.base

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * [MapKey] annotation to key bindings by a type of a [ViewModel].
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out BaseViewModel<*>>)
