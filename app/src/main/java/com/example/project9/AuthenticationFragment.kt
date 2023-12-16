package com.example.project9

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

/**
 * fragment for user authentication
 */
class AuthenticationFragment : Fragment() {

    /**
     * inflates the layout and handles user authentication
     *
     * @param inflater the layoutinflater object that can be used to inflate
     *                any views in the fragment
     * @param container if non-null, this is the parent view that the fragment's
     *                  ui should be attached to.
     * @param savedInstanceState if non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here
     * @return the root view of the inflated layout
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_authen, container, false)

        val auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            redirectToMainInterface()
        } else {
            startSignInFlow()
        }

        return view
    }

    /**
     * starts the sign-in flow using firebaseui
     */
    private fun startSignInFlow() {
        val providers = listOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    /**
     * redirects to main interface
     */
    private fun redirectToMainInterface() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                redirectToMainInterface()
            } else {
            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 123
    }
}
