package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

//import kotlinx.android.synthetic.main.activity_kategori.*

class DetailProdukActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Dapatkan data produk dari intent
        val product = intent.getSerializableExtra("product") as items2
//        val productPenjual = intent.getSerializableExtra("product") as itemsPenjual

        // Tampilkan data produk di tampilan detail
        val namaProdukTextView: TextView = findViewById(R.id.tvnamaProduk)
        val hargaTextView: TextView = findViewById(R.id.tvharga)
        val deskripsiTextView: TextView = findViewById(R.id.tvdeskripsi)
        val stokTextView: TextView = findViewById(R.id.tvstok)
        val txtSeeAllReviews: TextView = findViewById(R.id.txtSeeAllReviews)


        namaProdukTextView.text = "${product.namaProduk}"
        hargaTextView.text = "Rp: ${product.harga}"
        deskripsiTextView.text = "${product.deskripsi}"
        stokTextView.text = "Stok: ${product.stok}"

        val addToCartButton: Button = findViewById(R.id.keranjang)
        addToCartButton.setOnClickListener {
            // Panggil fungsi untuk menambahkan produk ke keranjang
            addToCart(product)
        }

        val BuyButton: Button = findViewById(R.id.buy)
        BuyButton.setOnClickListener {
            // Panggil fungsi untuk menambahkan produk ke keranjang
            startActivity(Intent(applicationContext, MetodePembayaranActivity::class.java))
        }

        txtSeeAllReviews.setOnClickListener {
            val intent = Intent(this, UlasanAdapter2::class.java)
            startActivity(intent)
        }

    }

    // Fungsi untuk menambahkan produk ke keranjang
    private fun addToCart(product: items2) {
        // Implementasi logika penambahan produk ke keranjang
        // Anda dapat menyimpan data ke database di sini
        // Misalnya, menggunakan Volley untuk mengirim permintaan HTTP ke PHP
        // atau menggunakan Room Database untuk penyimpanan lokal

        // Contoh penggunaan Volley (Harus menyesuaikan dengan implementasi server Anda)
        val addToCartUrl = Db_Contract.urlAddToCart
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)


        val stringRequest = object : StringRequest(
            Request.Method.POST, addToCartUrl,
            Response.Listener { response ->
                // Handle respons dari server jika diperlukan
                // Misalnya, tampilkan pesan sukses atau update tampilan keranjang
                Toast.makeText(this, "Produk ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
            },
            { error ->
                // Handle error dari server jika diperlukan
                Toast.makeText(this, "Gagal menambahkan produk ke keranjang", Toast.LENGTH_SHORT)
                    .show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                // Mengirim data ke server
                val params = java.util.HashMap<String, String>()
                params["namaProduk"] = product.namaProduk
                params["harga"] = product.harga.toString()
                params["quantity"] = "1" // Default quantity is 1

                return params
            }

        }

        requestQueue.add(stringRequest)
    }

//    private fun buy(productPenjual: itemsPenjual) {
//        // Implementasi logika penambahan produk ke keranjang
//        // Anda dapat menyimpan data ke database di sini
//        // Misalnya, menggunakan Volley untuk mengirim permintaan HTTP ke PHP
//        // atau menggunakan Room Database untuk penyimpanan lokal
//
//        // Contoh penggunaan Volley (Harus menyesuaikan dengan implementasi server Anda)
//        val addToCartUrl = Db_Contract.urlAddToCart
//        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//
//
//        val stringRequest = object : StringRequest(
//            Request.Method.POST, addToCartUrl,
//            Response.Listener { response ->
//                // Handle respons dari server jika diperlukan
//                // Misalnya, tampilkan pesan sukses atau update tampilan keranjang
//                Toast.makeText(this, "Produk ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, MetodePembayaranActivity::class.java)
//                startActivity(intent)
//            },
//            { error ->
//                // Handle error dari server jika diperlukan
//                Toast.makeText(this, "Gagal menambahkan produk ke keranjang", Toast.LENGTH_SHORT)
//                    .show()
//            }) {
//            override fun getParams(): MutableMap<String, String> {
//                // Mengirim data ke server
//                val params = java.util.HashMap<String, String>()
//                params["namaProduk"] = productPenjual.namaProduk
//                params["harga"] = productPenjual.harga.toString()
//                params["quantity"] = "1" // Default quantity is 1
//
//                return params
//            }
//
//        }
//
//        requestQueue.add(stringRequest)
//    }
}
