package com.dndapp.landing.signin

import com.dndapp.R
import com.dndapp.landing.BaseDndAppTest
import com.dndapp.mainModule
import com.dndapp.matchers.*
import com.dndapp.viewmodel.NavigationViewModel
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.get
import org.mockito.Mockito.verify

// TODO fix with new authentication
class SignInViewModelTest : BaseDndAppTest() {

    private lateinit var signInViewModel: SignInViewModel

    private lateinit var navigationViewModel: NavigationViewModel
//    private lateinit var firebaseAuth: FirebaseAuth

    private val testEmail = "test@test.test"
    private val testPassword = "123456"

    @Before
    fun setUp() {
        navigationViewModel = mock()
//        firebaseAuth = mock()
        startKoin {
            modules(mainModule, module(override = true) {
                single { navigationViewModel }
//                single { firebaseAuth }
            })
        }
        signInViewModel = get()
        signInViewModel.state.value?.run {
            email = testEmail
            password = testPassword
        }
    }

    @Test
    fun signInViewModel_navigate_on_sign_up() {
        //when
        signInViewModel.onSignUp()
        //then
        verify(navigationViewModel).navigate(
            withArg(withDestination(withId(R.id.fragment_sign_up)))
        )
    }

    @Test
    fun signInViewModel_navigate_on_continue_offline() {
        //when
        signInViewModel.onContinueOffline()
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

    @Test
    @Ignore
    fun signInViewModel_loading_on_submit() {
        //given
//        `when`(firebaseAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mock())
        //when
        signInViewModel.onSubmit()
        //then
        assertThat(signInViewModel.state.value?.loading, `is`(true))
    }

    @Test
    @Ignore
    fun signInViewModel_loading_finish_on_result() {
        //given
//        val taskMock = mock<Task<AuthResult>>()
//        `when`(firebaseAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(taskMock)
//        `when`(taskMock.addOnSuccessListener(any())).thenCallListenerInstantly()
        //when
        signInViewModel.onSubmit()
        //then
        assertThat(signInViewModel.state.value?.loading, `is`(false))
    }

    @Test
    @Ignore
    fun signInViewModel_credentials_on_submit() {
        //given
//        `when`(firebaseAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mock())
        //when
        signInViewModel.onSubmit()
        //then
//        verify(firebaseAuth).signInWithEmailAndPassword(eq(testEmail), eq(testPassword))
    }

    @Test
    @Ignore
    fun signInViewModel_navigate_on_success() {
        //given
//        val taskMock = mock<Task<AuthResult>>()
//        `when`(firebaseAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(taskMock)
//        `when`(taskMock.addOnSuccessListener(any())).thenCallListenerInstantly()
        //when
        signInViewModel.onSubmit()
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