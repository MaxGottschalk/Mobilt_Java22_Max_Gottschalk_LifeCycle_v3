package com.example.miniprojekt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val btnForm: Button = findViewById(R.id.button)
        val btnLogOut : Button = findViewById(R.id.button2)

        val email = intent.getStringExtra("email")

        btnForm.setOnClickListener {
            val i = Intent(this@MainActivity4, MainActivity2::class.java)
            i.putExtra("email", email)
            startActivity(i)
        }

        btnLogOut.setOnClickListener {
            val i = Intent(this@MainActivity4, MainActivity::class.java)
            startActivity(i)
        }

    }
}