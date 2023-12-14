package com.example.bloomy3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.ByteArrayOutputStream

class addproduk : AppCompatActivity() {

        private lateinit var etNamaProduk: EditText
        private lateinit var etDeskripsi: EditText
        private lateinit var etHarga: EditText
        private lateinit var etStok: EditText
        private lateinit var etKategori: EditText
        private lateinit var ivPilihGambar: ImageView
        private lateinit var btnPilihGambar: Button
        private lateinit var btnTambahProduk: Button

        private val REQUEST_CODE_PICK_IMAGE = 1

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_addproduk)

            etNamaProduk = findViewById(R.id.etNamaProduk)
            etDeskripsi = findViewById(R.id.etDeskripsi)
            etHarga = findViewById(R.id.etHarga)
            etStok = findViewById(R.id.etStok)
            etKategori = findViewById(R.id.etKategori)
            ivPilihGambar = findViewById(R.id.ivPilihGambar)
            btnPilihGambar = findViewById(R.id.btnPilihGambar)
            btnTambahProduk = findViewById(R.id.btnTambahProduk)

            btnPilihGambar.setOnClickListener {
                val pickImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(pickImageIntent, REQUEST_CODE_PICK_IMAGE)
            }

            btnTambahProduk.setOnClickListener {
                val namaProduk = etNamaProduk.text.toString()
                val deskripsi = etDeskripsi.text.toString()
                val harga = etHarga.text.toString()
                val stok = etStok.text.toString()
                val kategori = etKategori.text.toString()

                // Mendapatkan bitmap dari ImageView
                val bitmap = (ivPilihGambar.drawable as BitmapDrawable).bitmap

                // Mengubah bitmap menjadi string base64
                val imageString = bitmapToBase64(bitmap)

                // Membuat request queue untuk Volley
                val requestQueue = Volley.newRequestQueue(this)

                // Membuat string request untuk POST
                val stringRequest = object : StringRequest(
                    Method.POST,
                    Db_Contract.urlProduk,
                    Response.Listener { response ->
                        // Handle respons dari server (jika diperlukan)
                        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, itemAdapterPenjual::class.java))

                    },
                    Response.ErrorListener { error ->
                        // Handle error (jika diperlukan)
                        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
                    }) {
                    override fun getParams(): MutableMap<String, String> {
                        // Mengirim data ke server
                        val params = HashMap<String, String>()
                        params["namaProduk"] = namaProduk
                        params["deskripsi"] = deskripsi
                        params["harga"] = harga
                        params["stok"] = stok
                        params["gambar"] = imageString
                        params["kategori"] = kategori
                        return params
                    }
                }

                // Menambahkan request ke queue
                requestQueue.add(stringRequest)
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
                val selectedImageUri: Uri? = data?.data
                // Set gambar yang dipilih ke ImageView
                ivPilihGambar.setImageURI(selectedImageUri)
            }
        }

        private fun bitmapToBase64(bitmap: Bitmap): String {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
    }
