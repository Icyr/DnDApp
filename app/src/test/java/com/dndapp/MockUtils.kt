package com.dndapp

import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.nhaarman.mockitokotlin2.mock
import org.mockito.stubbing.OngoingStubbing

@Suppress("UNCHECKED_CAST")
fun OngoingStubbing<Task<AuthResult>>.thenCallListenerInstantly() {
    then {
        val listener = it.arguments[0] as OnSuccessListener<AuthResult>
        listener.onSuccess(mock())
        it.mock
    }
}