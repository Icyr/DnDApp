package com.dndapp.landing.signup

import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.dndapp.BR
import com.dndapp.utils.BaseObservableLiveData

class SignUpViewModel : ViewModel() {

    val state = BaseObservableLiveData(SignUpState())

    fun onSubmit() {
        TODO("Only local database is currently implemented")
    }
}

class SignUpState : BaseObservable() {
    @get:Bindable
    var email: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @get:Bindable
    var confirmPassword: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.confirmPassword)
        }

    @get:Bindable
    var loading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }

    val canSubmit: Boolean
        get() = email.isNotBlank() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.isNotBlank() &&
                confirmPassword == password
}