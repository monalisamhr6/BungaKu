package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HomeScreenPembeli : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_home_pembeli)

        var accountButton: ImageButton
        var chatsButton: ImageButton
        var listButton: ImageButton
        var imageViewCart : ImageView

        accountButton = findViewById(R.id.accountButton)
        chatsButton = findViewById(R.id.chatsButton)
        listButton = findViewById(R.id.listButton)
        imageViewCart = findViewById(R.id.imageViewCart)


        accountButton.setOnClickListener {
            startActivity(Intent(applicationContext, HomePembeli::class.java))
        }

        chatsButton.setOnClickListener {
            startActivity(Intent(applicationContext, chat::class.java))
        }

        listButton.setOnClickListener {
            startActivity(Intent(applicationContext, itemAdapter2::class.java))
        }

        imageViewCart.setOnClickListener {
            startActivity(Intent(applicationContext, CartActivity::class.java))
        }

    }
}