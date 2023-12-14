package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class CartActivity : AppCompatActivity() {

    private lateinit var cartItems: MutableList<CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        var btnNext: Button

        btnNext = findViewById(R.id.nextbutton)

        btnNext.setOnClickListener {
            startActivity(Intent(applicationContext, MetodePembayaranActivity::class.java))
        }

        cartItems = mutableListOf()
        getCartItemsFromServer()
    }

    private fun showCartItems() {
        // Tampilkan data produk dalam keranjang menggunakan ListView
        val listView: ListView = findViewById(R.id.listView)
        val cartAdapter = CartAdapter(this, cartItems)
        listView.adapter = cartAdapter

        // Hitung total harga dan tampilkan
        var totalHarga = 0.0
        for (item in cartItems) {
            totalHarga += item.harga
        }
        val totalTextView: TextView = findViewById(R.id.totaltxt)
        totalTextView.text = "Rp $totalHarga"
    }

    private fun getCartItemsFromServer() {
        // Ganti URL_API_CART_ITEMS dengan URL yang sesuai di server Anda
        val url = Db_Contract.urlGetCart

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                // Handle respons dari server
                val jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val namaProduk = jsonObject.getString("namaProduk")
                    val harga = jsonObject.getDouble("harga")
                    val quantity = jsonObject.getInt("quantity")

                    // Tambahkan item ke dalam daftar cartItems
                    cartItems.add(CartItem(namaProduk, harga, quantity))
                }



                // Panggil fungsi untuk menampilkan data dan menghitung total harga
                showCartItems()
            },
            { error ->
                // Handle error dari server jika diperlukan
                Toast.makeText(this, "Gagal mengambil data keranjang", Toast.LENGTH_SHORT).show()
            })

        // Tambahkan request ke queue
        Volley.newRequestQueue(this).add(stringRequest)
    }
}
