package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactionhistory)

        val historyListView: ListView = findViewById(R.id.allReviewsList)
        val title : TextView = findViewById(R.id.namehISTORY)
        var historyList = ArrayList<HistoryClass>()


        title.text = "Transaction History"
        var url = Db_Contract.urlGetItem
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var jar = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0 until response.length()) {
                val jsonObject: JSONObject = response.getJSONObject(x)
                val product = HistoryClass(
                    jsonObject.getString("namaProduk"),
                    jsonObject.getDouble("harga"),
                    jsonObject.getString("deskripsi"),
                    jsonObject.getInt("stok")
//                    jsonObject.getString("imageUrl")
                )
                historyList.add(product)
            }

            var adp = HistoryAdapter(this, historyList)
            historyListView.adapter = adp


        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show()
        })
        rq.add(jar)
    }




    }

