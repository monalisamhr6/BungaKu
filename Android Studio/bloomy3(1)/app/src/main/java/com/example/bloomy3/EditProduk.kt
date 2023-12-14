package com.example.bloomy3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class EditProduk : AppCompatActivity() {

    lateinit var product: itemsPenjual
    lateinit var etNamaProduk: EditText
    lateinit var etDeskripsi: EditText
    lateinit var etHarga: EditText
    lateinit var etStok: EditText
    lateinit var etKategori: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_produk)

        // Dapatkan data produk dari intent
        product = intent.getSerializableExtra("product") as itemsPenjual

        // Setel nilai awal di EditText dengan data produk
        etNamaProduk = findViewById(R.id.etNamaProduk)
        etDeskripsi = findViewById(R.id.etDeskripsi)
        etHarga = findViewById(R.id.etHarga)
        etStok = findViewById(R.id.etStok)
        etKategori = findViewById(R.id.etKategori)

        etNamaProduk.setText(product.namaProduk)
        etDeskripsi.setText(product.deskripsi)
        etHarga.setText(product.harga.toString())
        etStok.setText(product.stok.toString())
//        etKategori.setText(product.kategori) // Sesuaikan dengan bagaimana Anda menyimpan kategori

        // ...
        // Lanjutkan dengan menambahkan logika untuk gambar, dll.
        // ...

        val btnSubmit: Button = findViewById(R.id.btnTambahProduk)
        btnSubmit.setOnClickListener {
            // Panggil fungsi untuk mengirim perubahan ke server
            updateProduct()
        }
    }

    fun updateProduct() {


        // Ambil nilai dari EditText sesuai dengan perubahan pengguna

        val updatedNamaProduk = etNamaProduk.text.toString()
        val updatedDeskripsi = etDeskripsi.text.toString()
        val updatedHarga = etHarga.text.toString().toDouble()
        val updatedStok = etStok.text.toString().toInt()
        // val updatedKategori = etKategori.text.toString() // Sesuaikan dengan bagaimana Anda menyimpan kategori

        // ...
        // Lanjutkan dengan menambahkan logika untuk gambar, dll.
        // ...

        // Panggil fungsi update di PHP untuk menyimpan perubahan ke database
        updateProductOnServer(updatedNamaProduk, updatedDeskripsi, updatedHarga, updatedStok)
    }

    private fun updateProductOnServer(updatedNamaProduk: String, updatedDeskripsi: String, updatedHarga: Double, updatedStok: Int) {
        val updateProductUrl = Db_Contract.urlUpdateProduk // Ganti dengan URL skrip PHP di server Anda
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.POST, updateProductUrl,
            Response.Listener { response ->
                try {
                    // Konversi respons JSON ke objek
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    val message = jsonResponse.getString("message")

                    if (success) {
                        // Jika update berhasil, tampilkan pesan sukses dan kembali ke aktivitas sebelumnya atau lakukan tindakan lainnya
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        finish() // Anda dapat mengganti ini dengan tindakan lain yang sesuai
                    } else {
                        // Jika update gagal, tampilkan pesan kesalahan
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                // Handle error dari server jika diperlukan
                Toast.makeText(this, "Gagal memperbarui produk", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                // Mengirim data ke server (sesuaikan dengan parameter yang diperlukan oleh skrip PHP Anda)
                val params = HashMap<String, String>()
                params["id_produk"] = product.id_produk.toString()
                params["namaProduk"] = updatedNamaProduk
                params["harga"] = updatedHarga.toString()
                params["deskripsi"] = updatedDeskripsi
                params["stok"] = updatedStok.toString()
                // params["kategori"] = updatedKategori // Sesuaikan dengan parameter kategori jika diperlukan

                // ...
                // Lanjutkan dengan menambahkan logika untuk gambar, dll.
                // ...

                return params
            }
        }

        requestQueue.add(stringRequest)
}}