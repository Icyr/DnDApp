package com.dndapp.landing.signin

import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.dndapp.BR
import com.dndapp.R
import com.dndapp.utils.BaseObservableLiveData
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel
import com.dndapp.viewmodel.PopUpTo

class SignInViewModel(
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {

    val state = BaseObservableLiveData(SignInState())

    fun onSubmit() {
        TODO("Only offline mode is supported currently")
    }

    fun onSignUp() = navigationViewModel.navigate(Destination(R.id.fragment_sign_up))

    fun onContinueOffline() {
        val popUpTo = PopUpTo(R.id.fragment_sign_in, true)
        navigationViewModel.navigate(Destination(R.id.fragment_character_list, popUpTo = popUpTo))
    }
}

class SignInState : BaseObservable() {

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
    var loading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }

    val canSubmit: Boolean
        get() = email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isNotBlank()
}




