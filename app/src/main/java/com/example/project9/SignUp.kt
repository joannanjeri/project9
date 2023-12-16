package com.example.project9

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * activity for user sign-up using Firebase Authentication
 *
 * @property editTextEmail the EditText for entering email
 * @property editTextPassword the EditText for entering password
 * @property buttonSignUp the Button for signing up
 * @property auth the FirebaseAuth instance
 */
class SignUp : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignUp: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonSignUp = findViewById(R.id.buttonSignUp)

        auth = FirebaseAuth.getInstance()

        buttonSignUp.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            if (validateForm(email, password)) {
                createAccount(email, password)
            }
        }
    }

    /**
     * creates a new user account with the provided email and password
     *
     * @param email the user's email
     * @param password the user's password
     */
    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Sign Up Successful.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    /**
     * validates the user input for email and password
     *
     * @param email the user's email
     * @param password the user's password
     * @return true if the input is valid, false otherwise
     */
    private fun validateForm(email: String, password: String): Boolean {
        var valid = true

        if (email.isEmpty()) {
            editTextEmail.error = "Required."
            valid = false
        } else {
            editTextEmail.error = null
        }

        if (password.isEmpty()) {
            editTextPassword.error = "Required."
            valid = false
        } else {
            editTextPassword.error = null
        }

        return valid
    }
}
