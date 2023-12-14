package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MetodePembayaranActivity : AppCompatActivity() {

    private lateinit var tipePembayaranRadioGroup: RadioGroup
    private lateinit var aturPengirimanRadioGroup: RadioGroup
    private lateinit var requestQueue: RequestQueue
    private lateinit var waktuEditText: EditText
    private lateinit var cartItems: MutableList<CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metode_pembayaran)

        tipePembayaranRadioGroup = findViewById(R.id.tipePembayaranRadioGroup)
        aturPengirimanRadioGroup = findViewById(R.id.aturPengirimanRadioGroup)
        waktuEditText = findViewById(R.id.waktuEditText)
        requestQueue = Volley.newRequestQueue(this)

        cartItems = mutableListOf()
        getCartItemsFromServer()

        findViewById<Button>(R.id.tambahMetodePembayaranButton).setOnClickListener() {

            val selectedTipeRadioButtonId: Int = tipePembayaranRadioGroup.checkedRadioButtonId
            val selectedAturPengirimanRadioButtonId: Int =
                aturPengirimanRadioGroup.checkedRadioButtonId

            if (selectedTipeRadioButtonId != -1 && selectedAturPengirimanRadioButtonId != -1) {
                val tipePembayaranRadioButton: RadioButton = findViewById(selectedTipeRadioButtonId)
                val aturPengirimanRadioButton: RadioButton = findViewById(selectedAturPengirimanRadioButtonId)

                val tipePembayaran = tipePembayaranRadioButton.text.toString()
                val aturPengiriman = aturPengirimanRadioButton.text.toString()
                val waktu = waktuEditText.text.toString()

                tambahMetodePembayaran(tipePembayaran, aturPengiriman, waktu)
                startActivity(Intent(applicationContext, History::class.java))
            } else {
                showToast("Semua field harus diisi.")
            }
        }
    }

    private fun tambahMetodePembayaran(
        tipePembayaran: String,
        aturPengiriman: String,
        waktu: String
    ) {

        // Mengambil data produk dari keranjang
        val cartProducts = JSONArray()
        for (item in cartItems) {
            val product = JSONObject()
            product.put("namaProduk", item.namaProduk)
            product.put("harga", item.harga)
            product.put("quantity", item.quantity)
            cartProducts.put(product)
        }

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            Db_Contract.urlBayar,
            Response.Listener { response ->
                showToast("Berhasil Checkout")
            },
            Response.ErrorListener { error ->
                showToast("Berhasil Checkout")
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["tipePembayaran"] = tipePembayaran
                params["aturPengiriman"] = aturPengiriman
                params["waktu"] = waktu
                params["cartProducts"] = cartProducts.toString()
                return params
            }
        }

        requestQueue.add(stringRequest)
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showCartItems() {
        // Tampilkan data produk dalam keranjang menggunakan ListView
        val listView: ListView = findViewById(R.id.rv_cart_list_items)
        val cartAdapter = CartAdapter(this, cartItems)
        listView.adapter = cartAdapter

        // Hitung total harga dan tampilkan
        var totalHarga = 0.0
        for (item in cartItems) {
            totalHarga += item.harga
        }

        var subtotal = totalHarga + 3000

        val totalTextView: TextView = findViewById(R.id.subtotal)
        totalTextView.text = "Rp $totalHarga"

        val SubtotalTextView: TextView = findViewById(R.id.grand_total)
        SubtotalTextView.text = "Rp $subtotal"
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
