package com.example.dushanejeromesmithdesktopsystem.utils

import androidx.lifecycle.LifecycleOwner

interface IBitState<T> {
    fun getValue(): T
    fun setValue(newValue: T)
    fun observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit)
    fun deleteState()
}

enum class State2BitValue {
    ZERO, ONE, TWO, THREE
}

class State2Bit(
    private val state: IBitState<State2BitValue>
) : IBitState<State2BitValue> by state {
    constructor(initialValue: State2BitValue = State2BitValue.ZERO) : this(State(initialValue))
}
