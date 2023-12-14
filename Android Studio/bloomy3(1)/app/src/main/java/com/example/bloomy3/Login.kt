package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Login : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var btnsignup: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

//        btnsignup = findViewById(R.id.signup)
//
//        btnsignup.setOnClickListener {
//            startActivity(Intent(applicationContext, Register::class.java))
//        }

        btnRegister.setOnClickListener {
            startActivity(Intent(applicationContext, Register::class.java))
        }


        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (!(username.isEmpty() || password.isEmpty())) {
                val requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)

                val stringRequest = StringRequest(
                    Request.Method.GET,
                    "${Db_Contract.urlLogin}?username=$username&password=$password",
                    Response.Listener { response ->
                        if (response == "Login Berhasil") {
                            checkUserRole(username)
                            Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, HomePenjual::class.java))
                        } else {
                            Toast.makeText(applicationContext, "Login Gagal : $response", Toast.LENGTH_SHORT).show()
                        }
                    },
                    Response.ErrorListener { error ->
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                    }
                )
                requestQueue.add(stringRequest)
            } else {
                Toast.makeText(applicationContext, "Password Atau Username Salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkUserRole(username: String) {
        val requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)

        val roleCheckRequest = JsonArrayRequest(
            Request.Method.GET,
            "${Db_Contract.urlUserRole}?username=$username",
            null,
            Response.Listener { response ->
                // Parse the role from the JSON response
                val role = response.getJSONObject(0).getString("role")

                // Redirect based on the role
                if (role == "admin") {
                    startActivity(Intent(applicationContext, HomeScreenPenjual::class.java))
                } else {
                    startActivity(Intent(applicationContext, HomeScreenPembeli::class.java))
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            }
        )

        requestQueue.add(roleCheckRequest)
    }
}

