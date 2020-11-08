package com.gebeya.begonet.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.gebeya.begonet.R
import com.gebeya.begonet.base.User
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.*

class ProfileFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    lateinit var storageReference: StorageReference
    private val profileViewModel by viewModels<ProfileFragmentViewModel>()

    private var photoUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        storageReference = FirebaseStorage.getInstance().reference
        binds()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = LatLng(8.991884, 38.729006)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker save the children"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        moveCamera(sydney)
        animateCamera(sydney)
    }

    private fun moveCamera(latLng: LatLng?) {
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }

    private fun animateCamera(latLng: LatLng?) {
        mMap?.animateCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition.Builder().target(
                    latLng
                ).zoom(15.5f).build()
            )
        )
    }

    private fun binds() {

        img_transparent.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        scrollView.requestDisallowInterceptTouchEvent(true)
                        return false
                    }
                    MotionEvent.ACTION_UP -> {
                        scrollView.requestDisallowInterceptTouchEvent(false)
                        return true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        scrollView.requestDisallowInterceptTouchEvent(true)
                        return false
                    }

                    else -> false
                }
            }
        })

        FirebaseFirestore.getInstance()
            .collection("users")
            .document("${FirebaseAuth.getInstance().currentUser?.uid}")
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                user?.let { user ->
                    Glide.with(requireContext())
                        .load(user.profilePicUri)
                        .placeholder(R.drawable.ic_persono)
                        .into(profileImageView)
                    fullName.setText(user.name)
                    email.setText(user.email)
                    phoneNumber.setText(user.phoneNumber)
                }

            }


        saveChanges.setOnClickListener {
            save()
        }

        profileImageView.setOnClickListener {
            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                ).also {
                    it.type = "image/*"
                }, GALLERY_IMAGE_CODE
            )

        }
    }

    private fun save() {
        FirebaseFirestore.getInstance()
            .collection("users")
            .document("${FirebaseAuth.getInstance().currentUser?.uid}")
            .set(
                mapOf(
                    "name" to fullName.text.toString(),
                    "email" to email.text.toString(),
                    "phoneNumber" to phoneNumber.text.toString(),
                ), SetOptions.merge()
            )
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Save succeeded!", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to save try again!", Toast.LENGTH_LONG)
                    .show()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            // get storage  location
            val location =
                "ProfileImages/${profileViewModel}/${Random().nextDouble()}"
            if (requestCode == GALLERY_IMAGE_CODE) {
//              load image into image view
                data?.data?.let {
                    Glide.with(requireContext())
                        .load(it)
                        .into(profileImageView)

                    // upload image to cloud
                    // getStorageReference
                    storageReference = FirebaseStorage.getInstance().getReference(location)

//              uploadImage and assign the download uri to photoUrl var
                    profileViewModel.uploadImageFromGallery(
                        storageReference,
                        data,
                        requireContext(),
                        statusTextView
                    )
//
                    profileViewModel.photoUri.observe(
                        viewLifecycleOwner,
                        androidx.lifecycle.Observer { downloadUri ->
                            if (downloadUri != null) {
                                photoUrl = downloadUri
                                FirebaseFirestore.getInstance()
                                    .collection("users")
                                    .document("${FirebaseAuth.getInstance().currentUser?.uid}")
                                    .set(
                                        mapOf("profilePicUri" to photoUrl.toString()),
                                        SetOptions.merge()
                                    )
                            }
                        })

                }
            }
        }
    }

    companion object {
        const val GALLERY_IMAGE_CODE = 1
    }
}
