package com.example.dushanejeromesmithdesktopsystem.ui

import androidx.lifecycle.ViewModel
import com.example.dushanejeromesmithdesktopsystem.utils.State

class MainViewModel : ViewModel() {

    val count = State(0)

    fun increment() {
        count.setValue(count.getValue() + 1)
    }
}
