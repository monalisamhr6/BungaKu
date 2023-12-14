package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class tambahUlasan : AppCompatActivity() {

    private lateinit var requestQueue: RequestQueue
    private lateinit var ulasanEditText: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var tambahUlasanButton: Button
    private lateinit var lihatUlasan: Button

//    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_ulasan)

        requestQueue = Volley.newRequestQueue(this)

        ulasanEditText = findViewById(R.id.ulasanEditText)
        ratingBar = findViewById(R.id.ratingBar)
        tambahUlasanButton = findViewById(R.id.tambahUlasanButton)
        lihatUlasan = findViewById(R.id.lihatUlasan)

        val title: TextView = findViewById(R.id.tv_reviewTitle)
        title.text = "Reviews"

        tambahUlasanButton.setOnClickListener {
            // Get the review text and rating from the UI components
            val reviewText = ulasanEditText.text.toString()
            val rating = ratingBar.rating

            // Call the function to add the review
            tambahUlasan(reviewText, rating)
        }
        lihatUlasan.setOnClickListener {
            startActivity(Intent(applicationContext, UlasanAdapter2::class.java))
        }
    }

    private fun tambahUlasan(reviewText: String, rating: Float) {
        // Replace with your server URL
        val url = Db_Contract.urlTambahUlasan

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Response.Listener { response ->
                // Process the response from the server if needed
                showToast(response)
                startActivity(Intent(applicationContext, UlasanAdapter2::class.java))
            },
            Response.ErrorListener { error ->
                showToast("Error: ${error.message}")
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                // Add parameters to the request
                params["review"] = reviewText
                params["rating"] = rating.toString()
                // Add any other parameters you might need
                return params
            }
        }

        // Add the request to the queue
        requestQueue.add(stringRequest)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
