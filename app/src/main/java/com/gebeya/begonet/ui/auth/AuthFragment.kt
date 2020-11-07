package com.gebeya.begonet.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.gebeya.begonet.base.BaseFragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.gebeya.begonet.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_auth

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binds()
    }

    private fun binds() {
        if (auth.currentUser != null) {
            if (auth.currentUser!!.isEmailVerified) {
                findNavController().navigate(R.id.actionAuthFragmentToSelectorFragment)
            }
        } else {
            authenticate()
        }
    }

    private fun authenticate() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(getAuthProvidersList())
                .setLogo(R.drawable.logoblue)
                .setIsSmartLockEnabled(false)
                .setTheme(R.style.LoginTheme)
                .build(), REQUEST_CODE
        )
    }

    private fun getAuthProvidersList(): MutableList<AuthUI.IdpConfig> {
        return arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )
    }

    private fun logOut() {
        AuthUI.getInstance()
            .signOut(requireContext())
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)


            if (resultCode == Activity.RESULT_OK) {

                checkIfAlreadySelectedRole(object : UserExistListener {
                    override fun onUserExists() {
                        findNavController().navigate(R.id.actionAuthToMain)
                    }

                    override fun onUserDoesntExist() {
                        // sign in success
                        // if the user is using email, send verification link if not verified yet
                        if (response?.providerType == getString(R.string.passwordProviderType)) {
                            if (auth.currentUser!!.isEmailVerified) {
                                findNavController().navigate(R.id.actionAuthFragmentToSelectorFragment)
                            } else {
                                // send verification email
                                auth.currentUser!!.sendEmailVerification()
                                    .addOnSuccessListener {
                                        Toast.makeText(requireContext(), getString(R.string.authFragmentEmailSent), Toast.LENGTH_LONG).show()
                                        logOut()
                                        authenticate()
                                    }
                                    .addOnFailureListener{
                                        Toast.makeText(requireContext(), getString(R.string.authFragmentFailedToSendEmail), Toast.LENGTH_LONG).show()
                                    }

                            }
                        } else {
                            findNavController().navigate(R.id.actionAuthFragmentToSelectorFragment)
                        }
                    }
                })



                return
            } else {
                when {
                    response == null -> {
                        Toast.makeText(requireContext(), getString(R.string.authFragmentAuthCanceled), Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                    response.error?.errorCode == ErrorCodes.NO_NETWORK -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.authFragmentCheckYourConnection),
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                    response.error?.errorCode == ErrorCodes.UNKNOWN_ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.authFragmentUnknownError),
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                }
            }
        }
    }

    private fun checkIfAlreadySelectedRole(userExistListener: UserExistListener) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .document("$uid")
            .get()
            .addOnSuccessListener {
                if(it.exists()){
                    userExistListener.onUserExists()
                } else {
                    userExistListener.onUserDoesntExist()
                }
            }

            .addOnFailureListener{

            }
    }

    interface UserExistListener {
        fun onUserExists()
        fun onUserDoesntExist()
    }
    companion object {
        private const val REQUEST_CODE = 116
    }
}
