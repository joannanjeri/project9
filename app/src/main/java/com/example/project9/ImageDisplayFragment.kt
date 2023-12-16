package com.example.project9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage

class ImageDisplayFragment : Fragment() {
    private val storageReference = FirebaseStorage.getInstance().reference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_display, container, false)
        // Load and display images from Firebase Storage using Glide
        loadImagesIntoRecyclerView(view.findViewById(R.id.imageRecyclerView))

        return view
    }

    private fun loadImagesIntoRecyclerView(recyclerView: RecyclerView) {
        // Query Firebase Storage for image URLs
        storageReference.child("images").listAll().addOnSuccessListener { result ->
            val imageUrls = result.items.map { it.downloadUrl.toString() }

            // Set up RecyclerView with a simple adapter
            val adapter = ImageAdapter(imageUrls)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }.addOnFailureListener { exception ->
            // Handle failed image retrieval
            // TODO: Add error handling logic here
        }
    }

}