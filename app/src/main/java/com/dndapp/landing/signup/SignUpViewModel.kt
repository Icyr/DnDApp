package com.dndapp.landing.signup

import androidx.core.util.PatternsCompat
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.dndapp.BR
import com.dndapp.R
import com.dndapp.fireStoreDBModule
import com.dndapp.utils.BaseObservableLiveData
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel
import com.dndapp.viewmodel.PopUpTo
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.context.loadKoinModules

class SignUpViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {

    val state = BaseObservableLiveData(SignUpState())

    fun onSubmit() {
        state.value?.run {
            if (!canSubmit) {
                return
            }
            state.value = also { loading = true }
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                loadKoinModules(fireStoreDBModule)
                val popUpTo = PopUpTo(R.id.fragment_sign_in, true)
                navigationViewModel.navigate(Destination(R.id.fragment_character_list, popUpTo = popUpTo))
                state.value = also { loading = false }
            }
        }
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
                PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() &&
                password.isNotBlank() &&
                confirmPassword == password
}