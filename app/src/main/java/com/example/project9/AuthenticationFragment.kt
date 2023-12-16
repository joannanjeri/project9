//package com.example.project9
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.google.firebase.auth.FirebaseAuth
//
//class AuthenticationFragment : Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_authen, container, false)
//
//        // Initialize Firebase Authentication
//        val auth = FirebaseAuth.getInstance()
//
//        // Use FirebaseUI for authentication
//        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
//        val signInIntent = AuthUI.getInstance()
//            .createSignInIntentBuilder()
//            .setAvailableProviders(providers)
//            .build()
//
//        startActivityForResult(signInIntent, RC_SIGN_IN)
//
//        return view
//    }
//
//    companion object {
//        const val RC_SIGN_IN = 123
//    }
//}
