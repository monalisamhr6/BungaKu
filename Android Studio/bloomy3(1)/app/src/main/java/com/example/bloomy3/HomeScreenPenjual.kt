package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeScreenPenjual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)

        var accountButton: ImageButton
        var listButton: ImageButton

        accountButton = findViewById(R.id.accountButton)
        listButton = findViewById(R.id.listButton)


        accountButton.setOnClickListener {
            startActivity(Intent(applicationContext, HomePenjual::class.java))
        }

        listButton.setOnClickListener {
            startActivity(Intent(applicationContext, itemAdapterPenjual::class.java))
        }
    }
}