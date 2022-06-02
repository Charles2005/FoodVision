package com.example.food_vision.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.food_vision.R
import com.google.android.gms.common.wrappers.PackageManagerWrapper
import java.util.jar.Manifest

class NutritionalInquiry : AppCompatActivity() {

    companion object{
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA  = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inquirypage)

        val photoButton = findViewById<ImageButton>(R.id.photoButton)
        val photoBack = findViewById<ImageView>(R.id.photoBack)

        photoBack.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        var resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { results ->
            if (results.resultCode == Activity.RESULT_OK) {
                handleCameraImage(results.data)

            }

        }

        photoButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                resultLauncher.launch(intent)


            }else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA
                )
            }


        }

    }

    private fun handleCameraImage(data: Intent?) {
        val bitmap = data?.extras?.get("data") as Bitmap
        val intent = Intent(this,InformationPage::class.java)
        intent.putExtra("foodImage", bitmap)
        startActivity(intent)
        finish()
    }
}