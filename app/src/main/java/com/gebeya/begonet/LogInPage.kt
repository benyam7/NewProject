package com.gebeya.begonet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gebeya.begonet.data.AppDatabase
import com.gebeya.begonet.data.AppDatabase2
import com.gebeya.begonet.data.model.LogIn
import com.gebeya.begonet.framework.base.BaseActivity
import com.gebeya.begonet.framework.util.DATABASE_NAME
import kotlinx.android.synthetic.main.activity_log_in_page.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class LogInPage : BaseActivity() {
    val url = "https://reqres.in/api/login"
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var  password: EditText
    private val MIN_PASSWORD_LENGTH = 6
    private lateinit var begonet_db: AppDatabase2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_page)

        logInButton.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        skipButton.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
        /* val jsonobj = JSONObject()

         logInButton.setOnClickListener {
             jsonobj.put("email", userName.text)
             jsonobj.put("password", passwordLogin.text)

             val que = Volley.newRequestQueue(this)
             val req = JsonObjectRequest(
                 Request.Method.POST, url, null,
                 { response ->
                     Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
                 },
                 {response ->
                     Toast.makeText(this,"something went wrong",Toast.LENGTH_LONG).show()
                 })
             que.add(req)
         }*/
     /*   logInButton.setOnClickListener {


            val que = Volley.newRequestQueue(this)
            val req = StringRequest(
                Request.Method.GET, url, Response.Listener
                { response ->
                    signInBar.text = response.toString()
                },
                {response ->
                    Toast.makeText(this,"something went wrong",Toast.LENGTH_LONG).show()
                })
            que.add(req)
        }*/

        val ENDPOINT = "https://reqres.in/api/login"

        fun getItems(activity: Activity, queue: RequestQueue, listView: ListView) {
            val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, ENDPOINT, null,
                { response ->
                    val list = ArrayList<String>()

                    (0 until response.length()).mapTo(list) {
                        response[it].toString()
                    }

                    val adapter = ArrayAdapter(activity,
                        android.R.layout.simple_list_item_1, list)
                    listView.adapter = adapter
                },
                { error ->
                    Toast.makeText(
                        activity.applicationContext,
                        error.toString(),
                        Toast.LENGTH_SHORT).show()
                }
            )
            //add getItems to queue
            queue.add(jsonArrayRequest)
        }
/*

        begonet_db = Room.databaseBuilder(
            this,
            AppDatabase2::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries()
            .build()

        val logIn = begonet_db.signUpDao().getAll()
        for (logInInfo in logIn) {
            d(logInInfo.toString())
        }

        val login = LogIn(
            phone = +251926388219,
            email = "betheltessema88@gmail.com",
            password = "chu",
        )
        begonet_db.LoginDao().readInformation(login)

        logInButton.setOnClickListener {
            finish()
        }
*/
        fun viewInitializations() {
            email = findViewById(R.id.email)
            phone = findViewById(R.id.phone)
            password = findViewById(R.id.password)

        }

        fun isEmailValid(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        fun validateInput(): Boolean {
            if (email.text.toString() == "") {
                email.error = "Please Enter Email"

                return false
            }
            if (password.text.toString() == "") {
                password.error = "Please Enter Password"
                return false
            }

            if (!isEmailValid(email.text.toString())) {
                email.error = "Please Enter Valid Email"
                return false
            }

            if (password.text.length < MIN_PASSWORD_LENGTH) {
                password.error = "Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters"
                return false
            }
            return true
        }


        fun performSignUp(v: View) {
            if (validateInput()) {

                // Input is valid, here send data to your server
                val email = email.text.toString()
                val password = password.text.toString()
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                // Here you can call you API
            }
        }
        signUpButton2.setOnClickListener {
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
        }

    }
}
