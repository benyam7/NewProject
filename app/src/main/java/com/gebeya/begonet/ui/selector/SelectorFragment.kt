package com.gebeya.begonet.ui.selector

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.gebeya.begonet.R
import com.gebeya.begonet.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.fragment_selector.*

class SelectorFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_selector

    private var adapter: ArrayAdapter<String>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binds()
    }

    private fun binds() {

        val items = arrayOf(
            getString(R.string.selectorFragmentVolunteer),
            getString(R.string.selectorFragmentNGO)
        )

        adapter = ArrayAdapter(requireContext(), R.layout.item_role, items)
        (roleSelector.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        actionToHome.setOnClickListener {
            selectorProgress.visibility = View.VISIBLE
            save(object : SaveUserDataListener {
                override fun onUserDataSaved() {
                    selectorProgress.visibility = View.INVISIBLE
                    findNavController().navigate(R.id.acitonSelectorToMain)
                }

                override fun onFailure() {
                    Toast.makeText(requireContext(),
                        getString(R.string.selectorFragmentTryAgain),
                        Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun save(saveUserDataListener: SaveUserDataListener) {
        val db = FirebaseFirestore.getInstance()


        val name = FirebaseAuth.getInstance().currentUser?.displayName ?: "N/A"
        val email = FirebaseAuth.getInstance().currentUser?.email ?: "N/A"

        val phoneNum = phoneNumber.text.toString()
        val role = roleSelector.editText?.text.toString()

        db.collection("users")
            .document("${FirebaseAuth.getInstance().currentUser?.uid}")
            .set(
                mapOf("name" to name,
                    "email" to email,
                    "phoneNumber" to "+251$phoneNum",
                    "userId" to "${FirebaseAuth.getInstance().currentUser?.uid}",
                    "role" to role), SetOptions.merge()
            )
            .addOnSuccessListener {
                saveUserDataListener.onUserDataSaved()
            }
            .addOnFailureListener {
                saveUserDataListener.onFailure()
            }
    }
}

interface SaveUserDataListener {
    fun onUserDataSaved()
    fun onFailure()
}
