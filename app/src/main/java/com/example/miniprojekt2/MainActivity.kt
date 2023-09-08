package com.example.miniprojekt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin: Button = findViewById(R.id.loginButton)
        val btnCreateUser: Button = findViewById(R.id.regButton)
        val db = Firebase.firestore

        val enterEmail: EditText = findViewById(R.id.createEmail)
        val enterPassword: EditText = findViewById(R.id.passwordId)

        btnCreateUser.setOnClickListener {
            val i = Intent(this@MainActivity, MainActivity3::class.java)
            startActivity(i)
        }
        btnLogin.setOnClickListener {

            val email = enterEmail.text.toString()
            val password = enterPassword.text.toString()

            Log.d("email", email);
            Log.d("password", password);

            // Perform a Firestore query to check if the email and password exist
            db.collection("users")
                .whereEqualTo("email", email)
                .whereEqualTo("password", password)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        // User with the provided email and password exists in Firestore
                        val i = Intent(this@MainActivity, MainActivity4::class.java)
                        i.putExtra("email", email)
                        i.putExtra("password", password)
                        startActivity(i)
                    } else {
                        // User does not exist or the password is incorrect
                        Log.d("Authentication", "User not found or incorrect password")
                        // You can display an error message to the user
                        Toast.makeText(this@MainActivity,"L USER", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }
}