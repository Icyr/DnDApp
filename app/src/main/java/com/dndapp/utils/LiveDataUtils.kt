package com.dndapp.utils

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData

class BaseObservableLiveData<T : BaseObservable>(initialValue: T) : MutableLiveData<T>(initialValue) {
    init {
        initialValue.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                value = value
            }
        })
    }
}