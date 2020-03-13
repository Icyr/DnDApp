package com.dndapp.login

import androidx.fragment.app.Fragment
import com.dndapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val loginViewModel by viewModel<LoginViewModel>()

}