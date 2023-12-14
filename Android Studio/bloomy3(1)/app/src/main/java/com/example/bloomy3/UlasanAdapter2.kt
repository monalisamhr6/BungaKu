package com.example.bloomy3

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

class UlasanAdapter2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_ulasan)

        var url = Db_Contract.urlUlasan
        var reviewList = ArrayList<Ulasan2>()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        // Menggunakan findViewById untuk mendapatkan referensi ListView
        val Review: ListView = findViewById(R.id.home_item)
        var jar = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0 until response.length()) {
                val jsonObject: JSONObject = response.getJSONObject(x)
                val rev = Ulasan2(
                    jsonObject.getString("review"),
                    jsonObject.getInt("rating")
//                    jsonObject.getString("imageUrl")
                )
                reviewList.add(rev)
            }

            var adp = adapterUlasan2(this, reviewList)
            Review.adapter = adp

//            Review.setOnItemClickListener { _, _, position, _ ->
//                val selectedProduct = reviewList[position]
//                showProductDetail(selectedProduct)
//            }
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show()
        })
        rq.add(jar)
    }
//    private fun showProductDetail(product: items2) {
//        val intent = Intent(this, DetailProdukActivity::class.java)
//        intent.putExtra("product", product)
//        startActivity(intent)
//    }
}