package com.example.adlschool.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.adlschool.R
import com.squareup.picasso.Picasso

class CustomAdapterImage(private val dataSet: Array<CharSequence>, val activity: Activity) :
    RecyclerView.Adapter<CustomAdapterImage.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
          var img: ImageView


        init {
            // Define click listener for the ViewHolder's View
            img = view.findViewById(R.id.item_image)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_image_restoran, viewGroup, false)


        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        Picasso.get().load(dataSet[position].toString()).into(viewHolder.img)

        viewHolder.img.setOnClickListener {
            var img = ImageView(activity)
            img.translationZ = 90f
            img.setImageDrawable(viewHolder.img.drawable)
            AlertDialog.Builder(activity)
                .setView(img)
                .create()
                .show()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}