package com.dndapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dndapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_email_password.*
import org.koin.android.ext.android.inject


class EmailPasswordActivity : AppCompatActivity(),  View.OnClickListener {

    private val auth by inject<FirebaseAuth>()

    private var etEmail: EditText? = null
    private var etPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_password)
        etEmail =  findViewById(R.id.et_email)
        etPassword =  findViewById(R.id.et_password)

        // Buttons
        emailSignInButton.setOnClickListener(this)
        emailCreateAccountButton.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        auth.currentUser
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.emailCreateAccountButton -> createAccount(et_email.text.toString(), et_password.text.toString())
            R.id.emailSignInButton -> signIn(et_email.text.toString(), et_password.text.toString())
        }
    }

    private fun signIn(email: String, password: String) {
        try {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    intent = Intent(this@EmailPasswordActivity, DndAppActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    handleException(this@EmailPasswordActivity, "The username or password is incorrect")
                }
            }
        } catch (e : Exception)  {
            handleException(this@EmailPasswordActivity, e.localizedMessage)
        }
    }

    private fun createAccount(email: String, password: String) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    this
                ) {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this@EmailPasswordActivity, "createUserWithEmail:success.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //todo navigate to create account page
                    } else {
                        handleException(this@EmailPasswordActivity, "The username or password is incorrect")
                    }
                }
        } catch (e : Exception)  {
            handleException(this@EmailPasswordActivity, e.localizedMessage)
        }
    }

    private fun handleException(context: Context, exceptionMessage : String ){
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Exception occurred")

        alertDialogBuilder
            .setMessage(exceptionMessage)
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
            }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }
}
