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
import org.json.JSONException
import org.json.JSONObject

//import kotlinx.android.synthetic.main.activity_kategori.*

class DetailProdukPenjual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_penjual)

        // Dapatkan data produk dari intent
        val product = intent.getSerializableExtra("product") as itemsPenjual

        // Tampilkan data produk di tampilan detail
        val namaProdukTextView: TextView = findViewById(R.id.tvnamaProduk)
        val hargaTextView: TextView = findViewById(R.id.tvharga)
        val deskripsiTextView: TextView = findViewById(R.id.tvdeskripsi)
        val stokTextView: TextView = findViewById(R.id.tvstok)

        namaProdukTextView.text = "${product.namaProduk}"
        hargaTextView.text = "Rp: ${product.harga}"
        deskripsiTextView.text = "${product.deskripsi}"
        stokTextView.text = "Stok: ${product.stok}"

        val deleteButton: Button = findViewById(R.id.delete)
        deleteButton.setOnClickListener {
            // Panggil fungsi untuk menghapus produk dari database
            deleteProduct(product)
        }

        val editButton: Button = findViewById(R.id.edit)
        editButton.setOnClickListener {
            editProduct(product)
        }

        val seeAll: TextView = findViewById(R.id.txtSeeAllReviews)
        seeAll.setOnClickListener {
            startActivity(Intent(applicationContext, UlasanAdapter2::class.java))
        }



    }
    private fun deleteProduct(product: itemsPenjual) {
        val deleteProductUrl = Db_Contract.urDelete // Ganti dengan URL skrip PHP di server Anda
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.POST, deleteProductUrl,
            Response.Listener { response ->
                try {
                    // Konversi respons JSON ke objek
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    val message = jsonResponse.getString("message")

                    if (success) {
                        // Jika penghapusan berhasil, tampilkan pesan sukses dan kembali ke aktivitas sebelumnya atau lakukan tindakan lainnya
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        finish() // Anda dapat mengganti ini dengan tindakan lain yang sesuai
                    } else {
                        // Jika penghapusan gagal, tampilkan pesan kesalahan
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                // Handle error dari server jika diperlukan
                Toast.makeText(this, "Gagal menghapus produk", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                // Mengirim data ke server (id_produk untuk dihapus)
                val params = HashMap<String, String>()
                params["id_produk"] = product.id_produk.toString() // Ubah ke String
                return params
            }
        }

        requestQueue.add(stringRequest)
    }

    private fun editProduct(product: itemsPenjual) {
        val intent = Intent(this, EditProduk::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }

}
