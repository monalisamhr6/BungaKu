package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class itemAdapter2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item2)

        var url = Db_Contract.urlGetItem
        var productList = ArrayList<items2>()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        // Menggunakan findViewById untuk mendapatkan referensi ListView
        val homeCatListView: ListView = findViewById(R.id.home_item)
        var jar = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0 until response.length()) {
                val jsonObject: JSONObject = response.getJSONObject(x)
                val product = items2(
                    jsonObject.getString("namaProduk"),
                    jsonObject.getDouble("harga"),
                    jsonObject.getString("deskripsi"),
                    jsonObject.getInt("stok")
//                    jsonObject.getString("imageUrl")
                )
                productList.add(product)
            }

            var adp = adapterProduk2(this, productList)
            homeCatListView.adapter = adp

            homeCatListView.setOnItemClickListener { _, _, position, _ ->
                val selectedProduct = productList[position]
                showProductDetail(selectedProduct)
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show()
        })
        rq.add(jar)
    }
    private fun showProductDetail(product: items2) {
        val intent = Intent(this, DetailProdukActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }
}