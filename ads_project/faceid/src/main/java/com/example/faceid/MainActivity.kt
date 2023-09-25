package com.example.faceid


import FaceDetectornew
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity()  {

    private lateinit var imageView: ImageView
    private lateinit var captureButton: Button
    private var photo : Bitmap? = null

    private lateinit var faceDetector: FaceDetectornew // Tạo class FaceDetector riêng

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 100
        private const val REQUEST_IMAGE_CAPTURE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        captureButton = findViewById(R.id.captureButton)

        captureButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                captureImage()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION
                )
            }
        }

        // Khởi tạo FaceDetector
        faceDetector = FaceDetectornew(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            photo = imageBitmap
            imageView.setImageBitmap(photo)
        }
    }

    private fun captureImage() {
        // Code để chụp ảnh từ camera và nhận được bitmap
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
        var bitmap = photo

        // Sau khi nhận được bitmap, gửi nó tới FaceDetector
        val result = bitmap?.let { faceDetector.detectFace(it) }

        if (result == true) {
            // Khuôn mặt được tìm thấy, hiển thị kết quả
            Toast.makeText(this, "Khuôn mặt được tìm thấy!", Toast.LENGTH_SHORT).show()
        } else {
            // Không tìm thấy khuôn mặt
            Toast.makeText(this, "Không tìm thấy khuôn mặt.", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                captureImage()
            } else {
                Toast.makeText(
                    this,
                    "Không thể truy cập vào camera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        faceDetector.close() // Đóng FaceDetector
    }

}

