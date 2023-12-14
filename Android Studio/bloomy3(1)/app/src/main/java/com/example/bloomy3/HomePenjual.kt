package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_kategori.*

class HomePenjual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accountpenjual)

        var btnTambah: Button
        var btnLihatProduk: Button
        var btntambahUlasan: Button

        var name: TextView = findViewById(R.id.nameOfUser)
        var telp: TextView = findViewById(R.id.emailOfUser)

        name.text = "Monalisa Maharani"
        telp.text = "+62 000 888 777"

        btnTambah = findViewById(R.id.btnTambah)
        btnLihatProduk = findViewById(R.id.btnLihatProduk)
        btntambahUlasan = findViewById(R.id.btnTambahUlasan)

        btnTambah.setOnClickListener {
            startActivity(Intent(applicationContext, addproduk::class.java))
        }

        btnLihatProduk.setOnClickListener {
            startActivity(Intent(applicationContext, itemAdapterPenjual::class.java))
        }

        btntambahUlasan.setOnClickListener {
            startActivity(Intent(applicationContext, tambahUlasan::class.java))
        }


    }
}