package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class HomePembeli : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        var btnHistory: Button
        var btnWishlist: Button
        var name: TextView = findViewById(R.id.nameOfUser)
        var telp: TextView = findViewById(R.id.emailOfUser)

        name.text = "John Jacob"
        telp.text = "+62 000 888 777"


        btnHistory = findViewById(R.id.historyButton)
        btnWishlist = findViewById(R.id.myWishList)


        btnHistory.setOnClickListener {
            startActivity(Intent(applicationContext, History::class.java))
        }

        btnWishlist.setOnClickListener {
            startActivity(Intent(applicationContext, CartActivity::class.java))
        }



    }
}