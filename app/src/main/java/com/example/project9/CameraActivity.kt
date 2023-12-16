package com.example.project9

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.UUID

/**
 * activity for capturing and uploading images to Firebase Storage
 */
class CameraActivity : AppCompatActivity() {
    private val storageReference = FirebaseStorage.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)


        // camera x for images

        // handle for image capture
//        captureButton.setOnClickListener {
//            uploadImageToStorage(capturedImageBitmap)
//        }
    }

    /**
     * uploads an image to Firebase Storage
     *
     * @param bitmap the bitmap image to upload
     */
    private fun uploadImageToStorage(bitmap: Bitmap?) {
        val imageRef = storageReference.child("images/${ UUID.randomUUID()}.jpg")
        val baos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnSuccessListener { taskSnapshot ->

        }.addOnFailureListener { exception ->

        }
    }

}