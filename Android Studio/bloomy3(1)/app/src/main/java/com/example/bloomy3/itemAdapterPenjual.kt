package com.example.bloomy3


import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class itemAdapterPenjual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items2_penjual)

        var url = Db_Contract.urlGetItemPenjual
        var productListPenjual = ArrayList<itemsPenjual>()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        // Menggunakan findViewById untuk mendapatkan referensi ListView
        val homeCatListView: ListView = findViewById(R.id.home_item)
        var jar = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0 until response.length()) {
                val jsonObject: JSONObject = response.getJSONObject(x)
                val productPenjual = itemsPenjual(
                    jsonObject.getInt("id_produk"),
                    jsonObject.getString("namaProduk"),
                    jsonObject.getDouble("harga"),
                    jsonObject.getString("deskripsi"),
                    jsonObject.getInt("stok")
//                    jsonObject.getString("imageUrl")
                )
                productListPenjual.add(productPenjual)
            }

            var adp = adapterProduk2Penjual(this, productListPenjual)
            homeCatListView.adapter = adp

            homeCatListView.setOnItemClickListener { _, _, position, _ ->
                val selectedProduct = productListPenjual[position]
                showProductDetail(selectedProduct)
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show()
        })
        rq.add(jar)
    }
    private fun showProductDetail(product: itemsPenjual) {
        val intent = Intent(this, DetailProdukPenjual::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }
}