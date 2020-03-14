package com.dndapp.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dndapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_email_password.*


class EmailPasswordActivity : AppCompatActivity(),  View.OnClickListener {

    private lateinit var auth: FirebaseAuth

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

        auth = FirebaseAuth.getInstance();
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        // hideProgressBar()
        if (user != null) {
//          //user is signed in
      } else {
//          //user is signed out
        }
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.emailCreateAccountButton -> createAccount(et_email.text.toString(), et_password.text.toString())
            R.id.emailSignInButton -> signIn(et_email.text.toString(), et_password.text.toString())
        }
    }

    private fun signOut() {
        auth.signOut()
        updateUI(null)
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                   // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        this@EmailPasswordActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                //    updateUI(null)
                }

                // [START_EXCLUDE]
//                if (!task.isSuccessful) {
//                    mStatusTextView.setText(R.string.auth_failed)
//                }
//                hideProgressBar()
                // [END_EXCLUDE]
            }
        // [END sign_in_with_email]
    }

    public fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Toast.makeText(
                        this@EmailPasswordActivity, "Authentication successful.",
                        Toast.LENGTH_SHORT
                    ).show()
                   // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this@EmailPasswordActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                  //  updateUI(null)
                }

                // [START_EXCLUDE]
                // [END_EXCLUDE]
            }
        // [END create_user_with_email]
    }
}
