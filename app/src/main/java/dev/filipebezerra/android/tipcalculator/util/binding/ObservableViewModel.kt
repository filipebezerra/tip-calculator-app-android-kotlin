package dev.filipebezerra.android.tipcalculator.util.binding

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import dev.filipebezerra.android.tipcalculator.BR

abstract class ObservableViewModel(application: Application) : AndroidViewModel(application), Observable {
    @delegate:Transient
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    fun notifyChange() {
        callbacks.notifyChange(this, BR._all)
    }
}