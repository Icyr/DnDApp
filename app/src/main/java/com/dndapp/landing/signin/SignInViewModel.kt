package com.dndapp.landing.signin

import androidx.core.util.PatternsCompat
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.dndapp.BR
import com.dndapp.R
import com.dndapp.fireStoreDBModule
import com.dndapp.roomDBModule
import com.dndapp.utils.BaseObservableLiveData
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel
import com.dndapp.viewmodel.PopUpTo
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.context.loadKoinModules

class SignInViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val navigationViewModel: NavigationViewModel
) : ViewModel() {

    val state = BaseObservableLiveData(SignInState())

    fun onSubmit() {
        state.value?.run {
            if (!canSubmit) {
                return
            }
            state.value = also { loading = true }
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                loadKoinModules(fireStoreDBModule)
                val popUpTo = PopUpTo(R.id.fragment_sign_in, true)
                navigationViewModel.navigate(Destination(R.id.fragment_character_list, popUpTo = popUpTo))
                state.value = also { loading = false }
            }
        }
    }

    fun onSignUp() = navigationViewModel.navigate(Destination(R.id.fragment_sign_up))

    fun onContinueOffline() {
        loadKoinModules(roomDBModule)
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
        get() = email.isNotBlank() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() && password.isNotBlank()
}




