package com.example.project9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

/**
 * fragment for displaying images from Firebase Storage in a RecyclerView
 *
 * @property storageReference the Firebase Storage reference
 */
class ImageDisplayFragment : Fragment() {
    private val storageReference = FirebaseStorage.getInstance().reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_display, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.imageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        loadImagesIntoRecyclerView(recyclerView)

        return view
    }

    /**
     * loads images from Firebase Storage and displays them in a RecyclerView
     *
     * @param recyclerView the RecyclerView to display images in
     */
    private fun loadImagesIntoRecyclerView(recyclerView: RecyclerView) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val imageUrls = mutableListOf<String>()
                val items = storageReference.child("images").listAll().await().items

                items.forEach { ref ->
                    val url = ref.downloadUrl.await().toString()
                    imageUrls.add(url)
                }

                withContext(Dispatchers.Main) {
                    val adapter = ImageAdapter(requireContext(), imageUrls)
                    recyclerView.adapter = adapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
