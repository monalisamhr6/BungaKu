package com.example.bloomy3

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Produk : AppCompatActivity() {

    private lateinit var productListLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produk)

        var cat: String? = intent.getStringExtra("kategori")
        cat = cat ?: "kategori"
        Log.d("KategoriDebug", "Kategori dari intent: $cat")
        var url="http://192.168.100.246/bloomy/get_items.php?kategori="+cat
        var list=ArrayList<items>()
        val item: RecyclerView = findViewById(R.id.item_rv)

        var rq: RequestQueue = Volley.newRequestQueue(this)
        var jar = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0..response.length() - 1) {
                val jsonObject = response.getJSONObject(x)
                if (jsonObject.has("photo")) {
                    val photo = jsonObject.getString("photo")
                    list.add(
                        items(
                            jsonObject.getInt("id_produk"),
                            jsonObject.getString("namaProduk"),
                            jsonObject.getDouble("harga"),
                            jsonObject.getString("deskripsi"),
                            photo,
                            jsonObject.getInt("stok")
                        )
                    )
                } else {
                    Log.e("JSONParsing", "Objek JSON tanpa kunci 'photo' ditemukan.")
                }
            }

            var adp = itemAdapter(this, list)
            item.layoutManager = LinearLayoutManager(this)
            item.adapter = adp



        },Response.ErrorListener {error ->
            Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()

        })

        rq.add(jar)


    }
}
