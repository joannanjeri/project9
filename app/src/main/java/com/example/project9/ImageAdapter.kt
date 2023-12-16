package com.example.project9

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * adapter for displaying images in a recyclerview
 *
 * @param context the context of the application
 * @param imageUrls the list of image urls to display
 */
class ImageAdapter(private val context: Context, private val imageUrls: List<String>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    /**
     * creates a new viewholder
     *
     * @param parent the parent viewgroup
     * @param viewType the type of view to create
     * @return a new viewholder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(view as ImageView)
    }

    /**
     * binds the data to the viewholder
     *
     * @param holder the viewholder to bind data to
     * @param position the position of the item
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(imageUrls[position]).into(holder.imageView)
    }

    /**
     * returns the number of items in the adapter
     *
     * @return the number of items in the adapter
     */
    override fun getItemCount(): Int = imageUrls.size

    /**
     * viewholder class
     */
    class ViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}
