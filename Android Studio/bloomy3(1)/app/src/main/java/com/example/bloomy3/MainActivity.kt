package com.example.bloomy3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var getStartedButton: Button

        getStartedButton = findViewById(R.id.getStartedButton)


        getStartedButton.setOnClickListener {
            startActivity(Intent(applicationContext, Login::class.java))
        }


    }
}