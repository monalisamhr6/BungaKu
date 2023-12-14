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

class home_kategori : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        var url = "http://192.168.100.246/bloomy/get_cat.php"
        var list = ArrayList<String>()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        // Menggunakan findViewById untuk mendapatkan referensi ListView
        val homeCatListView: ListView = findViewById(R.id.home_cat)

        var jar = JsonArrayRequest(Request.Method.GET,url,null, Response.Listener { response ->
            for (x in 0..response.length()-1)
                list.add(response.getJSONObject(x).getString("kategori"))

            var adp = ArrayAdapter(this,R.layout.my_textview,list)
            homeCatListView.adapter = adp
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show()
        })
        rq.add(jar)

        homeCatListView.setOnItemClickListener { adapterView, view, i, id ->
            var cat: String = list[i]
            var obj = Intent(this,Produk::class.java)
            obj.putExtra("kategori",cat)
            startActivity(obj)
        }

//        var cat: String= intent.getStringExtra("kategori")
//        var url="http://192.168.100.246/bloomy/get_items.php?kategori="+cat
 }
}