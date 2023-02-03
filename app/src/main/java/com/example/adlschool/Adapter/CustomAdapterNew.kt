package com.example.adlschool.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.adlschool.Model.New
import com.example.adlschool.Model.Teacher
import com.example.adlschool.R
import com.squareup.picasso.Picasso

class CustomAdapterNew(private val dataSet: ArrayList<New>, val activity: Activity) :
    RecyclerView.Adapter<CustomAdapterNew.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
          var img: ImageView
          var tv: TextView

        init {
            // Define click listener for the ViewHolder's View
            img = view.findViewById(R.id.img_new)
            tv = view.findViewById(R.id.tv_new)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_new, viewGroup, false)


        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        Picasso.get().load(dataSet[position].image).into(viewHolder.img)
        viewHolder.tv.text = dataSet[position].text
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