package com.dndapp.landing.signin

import com.dndapp.landing.BaseDndAppTest
import com.dndapp.mainModule
import com.google.firebase.auth.FirebaseAuth
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.get
import org.mockito.Mockito.*

@RunWith(Parameterized::class)
class SignInViewModelInvalidStateTest(
    private val testEmail: String,
    private val testPassword: String
) : BaseDndAppTest() {

    @Suppress("UNCHECKED_CAST")
    companion object {
        @Parameters
        @JvmStatic
        fun credentials(): Collection<Array<Any>> = listOf(
            arrayOf("", "123456"), //empty email
            arrayOf("not_a_email", "123456"), //not email
            arrayOf("test@test.test", "") // empty password
        ) as Collection<Array<Any>>
    }

    private lateinit var signInViewModel: SignInViewModel

    private lateinit var firebaseAuth: FirebaseAuth

    @Before
    fun setUp() {
        firebaseAuth = mock()
        startKoin {
            modules(mainModule, module(override = true) {
                single { firebaseAuth }
            })
        }
        signInViewModel = get()
    }


    @Test
    fun signInViewModel_not_submitting_with_invalid_data() {
        //given
        `when`(firebaseAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mock())
        //when empty email
        signInViewModel.state.value?.run {
            email = testEmail
            password = testPassword
        }
        signInViewModel.onSubmit()
        //then
        verify(firebaseAuth, never()).signInWithEmailAndPassword(anyString(), anyString())
    }
}