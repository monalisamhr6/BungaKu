package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.HashMap

class Register : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etNama = findViewById(R.id.etNama)
        etEmail = findViewById(R.id.etEmail)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val nama = etNama.text.toString()
            val email = etEmail.text.toString()

            if (!(username.isEmpty() || password.isEmpty() || nama.isEmpty() || email.isEmpty())) {

                val stringRequest = object : StringRequest(
                    Request.Method.POST,
                    Db_Contract.urlRegister,
                    Response.Listener { response ->
                        Toast.makeText(applicationContext, response, Toast.LENGTH_LONG).show()
                        startActivity(Intent(applicationContext, Login::class.java))
                    },
                    Response.ErrorListener { error ->
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_LONG).show()
                    }) {

                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params: MutableMap<String, String> = HashMap()
                        params["username"] = username
                        params["password"] = password
                        params["nama"] = nama
                        params["email"] = email
                        return params
                    }
                }

                val requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
                requestQueue.add(stringRequest)

            } else {
                Toast.makeText(applicationContext, "Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
