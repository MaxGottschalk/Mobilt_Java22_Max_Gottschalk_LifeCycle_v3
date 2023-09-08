package com.example.miniprojekt2

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageButton: ImageButton
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val email = intent.getStringExtra("email")
        val username : String = "User: " + email.toString()
        val text1 = findViewById<TextView>(R.id.textView2)
        text1.text = username

        val btnForm: Button = findViewById(R.id.button3)
        val btnLogOut : Button = findViewById(R.id.button4)

        imageButton = findViewById(R.id.imageButton)
        imageView = findViewById(R.id.imageView)


        imageButton.setOnClickListener {
            openImagePicker()
        }

        btnForm.setOnClickListener {
            val i = Intent(this@MainActivity2, MainActivity4::class.java)
            i.putExtra("email", email)
            startActivity(i)
        }

        btnLogOut.setOnClickListener {
            val i = Intent(this@MainActivity2, MainActivity::class.java)
            startActivity(i)
        }

    }
    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data

            if (selectedImageUri != null) {
                val inputStream = contentResolver.openInputStream(selectedImageUri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageView.setImageBitmap(bitmap)
                imageView.visibility = ImageView.VISIBLE

                // Now, you have the selected image in 'bitmap' and can proceed with uploading it.
                Log.d("bitmap", bitmap.toString())
            }
        }
    }

}