package com.gebeya.begonet.ui.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragmentViewModel : ViewModel() {

    private val _photoUri = MutableLiveData<String>()
    val photoUri: LiveData<String>
        get() = _photoUri

    fun uploadImageFromGallery(
        storageReference: StorageReference,
        data: Intent?,
        context: Context,
        status: TextView
    ) {
        status.text = "Uploading ..."
        val uploadTask = data?.data?.let { storageReference.putFile(it) }
        uploadTask?.continueWithTask { task ->
            if (!task.isSuccessful) {
                Toast.makeText(
                    context,
                   "failed to upload please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
            storageReference!!.downloadUrl
        }?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
//                photoUrl = getDownloadUriWithoutToken(downloadUri)
                _photoUri.postValue(getDownloadUriWithoutToken(downloadUri))
                status.text = "Success!"
                Toast.makeText(
                    context,
                    "Image uploaded Success!",
                    Toast.LENGTH_SHORT
                )
                    .show()

            }
        }
    }


    fun getDownloadUriWithoutToken(uri: Uri?): String {
        return uri.toString()
            .substring(0, uri.toString().indexOf("&token"))
    }

}