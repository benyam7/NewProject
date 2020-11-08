package com.gebeya.begonet.ui.decider

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.gebeya.begonet.base.BaseFragment
import com.gebeya.begonet.R
import com.gebeya.begonet.ui.auth.AuthFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DeciderFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_decider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binds()
    }

    private fun binds() {
        if(FirebaseAuth.getInstance().currentUser == null){
            findNavController().navigate(R.id.actionDeciderFragmentToAuthFragment)
        } else {
            checkIfAlreadySelectedRole(object : AuthFragment.UserExistListener {
                override fun onUserDoesntExist() {
                    Toast.makeText(requireContext(),"No user", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.actionDeciderFragmentToSelectorFragment)
                }

                override fun onUserExists() {
                    Toast.makeText(requireContext(),"User exists", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_deciderFragment_to_mainFragment)
                }
            })

        }
    }


    private fun checkIfAlreadySelectedRole(userExistListener: AuthFragment.UserExistListener) {
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


}