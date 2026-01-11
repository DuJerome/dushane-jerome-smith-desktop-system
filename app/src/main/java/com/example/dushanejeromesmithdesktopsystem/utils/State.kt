package com.example.dushanejeromesmithdesktopsystem.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.util.concurrent.CopyOnWriteArrayList

class State<T>(@Volatile private var rawString: T) : IBitState<T>, LifecycleObserver {

    private val originalValue: T = rawString
    private val observers = CopyOnWriteArrayList<(T) -> Unit>()

    override fun getValue(): T = rawString

    override fun setValue(newValue: T) {
        if (newValue != rawString) {
            rawString = newValue
            observers.forEach { it(rawString) }
        }
    }

    override fun deleteState() {
        setValue(originalValue)
    }

    override fun observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
        observers.add(observer)
        lifecycleOwner.lifecycle.addObserver(this)
        observer(rawString)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.removeObserver(this)
    }
}
