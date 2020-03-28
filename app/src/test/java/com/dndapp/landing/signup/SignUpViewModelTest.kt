package com.dndapp.landing.signup

import com.dndapp.R
import com.dndapp.landing.BaseDndAppTest
import com.dndapp.mainModule
import com.dndapp.matchers.*
import com.dndapp.thenCallListenerInstantly
import com.dndapp.viewmodel.NavigationViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.get
import org.mockito.Mockito.*

class SignUpViewModelTest : BaseDndAppTest() {

    private lateinit var signUpViewModel: SignUpViewModel

    private lateinit var navigationViewModel: NavigationViewModel
    private lateinit var firebaseAuth: FirebaseAuth

    private val testEmail = "test@test.test"
    private val testPassword = "123456"

    @Before
    fun setUp() {
        navigationViewModel = mock()
        firebaseAuth = mock()
        startKoin {
            modules(mainModule, module(override = true) {
                single { navigationViewModel }
                single { firebaseAuth }
            })
        }
        signUpViewModel = get()
        signUpViewModel.state.value?.run {
            email = testEmail
            password = testPassword
            confirmPassword = testPassword
        }
    }

    @Test
    fun signUpViewModel_loading_on_submit() {
        //given
        `when`(firebaseAuth.createUserWithEmailAndPassword(anyString(), anyString())).thenReturn(mock())
        //when
        signUpViewModel.onSubmit()
        //then
        assertThat(signUpViewModel.state.value?.loading, `is`(true))
    }

    @Test
    fun signUpViewModel_loading_finish_on_result() {
        //given
        val taskMock = mock<Task<AuthResult>>()
        `when`(firebaseAuth.createUserWithEmailAndPassword(anyString(), anyString())).thenReturn(taskMock)
        `when`(taskMock.addOnSuccessListener(any())).thenCallListenerInstantly()
        //when
        signUpViewModel.onSubmit()
        //then
        assertThat(signUpViewModel.state.value?.loading, `is`(false))
    }

    @Test
    fun signUpViewModel_credentials_on_submit() {
        //given
        `when`(firebaseAuth.createUserWithEmailAndPassword(anyString(), anyString())).thenReturn(mock())
        //when
        signUpViewModel.onSubmit()
        //then
        verify(firebaseAuth).createUserWithEmailAndPassword(eq(testEmail), eq(testPassword))
    }

    @Test
    fun signUpViewModel_navigate_on_success() {
        //given
        val taskMock = mock<Task<AuthResult>>()
        `when`(firebaseAuth.createUserWithEmailAndPassword(anyString(), anyString())).thenReturn(taskMock)
        `when`(taskMock.addOnSuccessListener(any())).thenCallListenerInstantly()
        //when
        signUpViewModel.onSubmit()
        //then
        verify(navigationViewModel).navigate(
            withArg(
                withDestination(
                    withId(R.id.fragment_character_list),
                    withPopUpTo(withId(R.id.fragment_sign_in), inclusive())
                )
            )
        )
    }
}