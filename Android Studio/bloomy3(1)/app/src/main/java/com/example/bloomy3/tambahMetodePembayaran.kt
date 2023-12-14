package com.example.bloomy3

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class tambahMetodePembayaran : AppCompatActivity() {

    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_metode_pembayaran)

        requestQueue = Volley.newRequestQueue(this)

        val tambahMetodeButton: Button = findViewById(R.id.tambahMetodeButton)
        tambahMetodeButton.setOnClickListener {
            // Panggil fungsi untuk melakukan permintaan GET ke PHP
            tambahMetodePembayaran()
        }
    }

    private fun tambahMetodePembayaran() {
        val url = Db_Contract.urlPembayaran

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                // Proses respons dari server jika diperlukan
                showToast(response)
            },
            Response.ErrorListener { error ->
                showToast("Error: ${error.message}")
            }
        )

        // Menambahkan request ke antrian
        requestQueue.add(stringRequest)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
