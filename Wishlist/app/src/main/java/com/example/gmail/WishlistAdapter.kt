package com.example.gmail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(
    private val items: MutableList<WishlistItem>
) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTV: TextView = itemView.findViewById(R.id.itemTV)
        val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
        val linkTV: TextView = itemView.findViewById(R.id.linkTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wishlistItem = items[position]
        holder.itemTV.text = wishlistItem.name
        holder.itemPrice.text = wishlistItem.price
        holder.linkTV.text = wishlistItem.link
    }

    override fun getItemCount(): Int = items.size
}
