@file:Suppress("DEPRECATION")

package com.gebeya.begonet

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gebeya.begonet.data.AppDatabase
import com.gebeya.begonet.data.model.SignUp
import com.gebeya.begonet.framework.base.BaseActivity
import com.gebeya.begonet.framework.util.DATABASE_NAME
import kotlinx.android.synthetic.main.activity_log_in_page.*
import kotlinx.android.synthetic.main.activity_sign_up_page.*
import org.json.JSONObject
import java.util.*

class SignUpPage : BaseActivity() {

    private lateinit var begonet_db: AppDatabase
    lateinit var name: EditText
    lateinit var phone: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText
    val MIN_PASSWORD_LENGTH = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        signUpButton.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

   /*    begonet_db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries()
            .build()

        val signUp = begonet_db.signUpDao().getAll()
        for (signUPInfo in signUp) {
            d(signUPInfo.toString())
        }

        val register = SignUp(
            name = "Bethel Tesema",
            phone = +251926388219,
            email = "betheltessema88@gmail.com",
            password = "chu",
            confirmPassword = "chu",
            createdAt = Date().toString()
        )
        begonet_db.signUpDao().addInformation(register)

        signUpButton.setOnClickListener {
            finish()
        }
*/
        fun viewInitializations() {
            name = findViewById(R.id.fullName)
            phone = findViewById(R.id.phone)
            email = findViewById(R.id.email)
            password = findViewById(R.id.password)
            confirmPassword = findViewById(R.id.confirmPassword)

            // To show back button in actionbar
            //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        fun isEmailValid(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun validateInput(): Boolean {
            if (name.text.toString() == "") {
                name.setError("Please Enter Your Full Name")
                return false
            }
            if (phone.text.toString() == "") {
                name.error = "Please Enter Your Phone Number"
                return false
            }
            if (email.text.toString() == "") {
                email.error = "Please Enter Your Email"
                return false
            }
            if (password.text.toString() == "") {
                password.error = "Please Enter Your Password"
                return false
            }
            if (confirmPassword.text.toString() == "") {
                confirmPassword.error = "Please Confirm Your Password"
                return false
            }

            // checking the proper email format
            if (!isEmailValid(email.text.toString())) {
                email.error = "Please Enter Valid Email"
                return false
            }

            // checking minimum password Length
            if (password.text.length < MIN_PASSWORD_LENGTH) {
                password.error =
                    "Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters"
                return false
            }

            // Checking if confirm password is the same
            if (password.text.toString() != confirmPassword.text.toString()) {
                confirmPassword.setError("Password does not match")
                return false
            }
            return true
        }


        fun performSignUp(view: View) {
            if (validateInput()) {

                // Input is valid, here send data to your server

                val Name = name.text.toString()
                val Phone = phone.text.toString()
                val Email = email.text.toString()
                val Password = password.text.toString()
                val ConfirmPassword = confirmPassword.text.toString()

                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                // Here you can call you API

            }

        }
/*
        var volleyRequestQueue: RequestQueue? = null
        var dialog: ProgressDialog? = null
        val serverAPIURL: String = "https://..."
        val TAG = "Request Queue"

        fun SendSignUpDataToServer(firstName: String, lastName: String, email: String, password: String) {
            volleyRequestQueue = Volley.newRequestQueue(this)
            dialog = ProgressDialog.show(this, "", "Please wait...", true);
            val parameters: MutableMap<String, String> = HashMap()
            // Add your parameters in HashMap
            parameters.put("firstName",firstName);
            parameters.put("lastName",lastName);
            parameters.put("email",email);
            parameters.put("password",password);

            val strReq: StringRequest = object : StringRequest(
                Method.POST,serverAPIURL,
                Response.Listener { response ->
                    Log.e(TAG, "response: " + response)
                    dialog?.dismiss()

                    // Handle Server response here
                    try {
                        val responseObj = JSONObject(response)
                        val isSuccess = responseObj.getBoolean("isSuccess")
                        val code = responseObj.getInt("code")
                        val message = responseObj.getString("message")
                        if (responseObj.has("data")) {
                            val data = responseObj.getJSONObject("data")
                            // Handle your server response data here
                        }
                        Toast.makeText(this,message,Toast.LENGTH_LONG).show()

                    } catch (e: Exception) { // caught while parsing the response
                        Log.e(TAG, "problem occurred")
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { volleyError -> // error occurred
                    Log.e(TAG, "problem occurred, volley error: " + volleyError.message)
                }) {

                override fun getParams(): MutableMap<String, String> {
                    return parameters;
                }

                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {

                    val headers: MutableMap<String, String> = HashMap()
                    // Add your Header paramters here
                    return headers
                }
            }
            // Adding request to request queue
            volleyRequestQueue?.add(strReq)
        }

        */

    }
}

