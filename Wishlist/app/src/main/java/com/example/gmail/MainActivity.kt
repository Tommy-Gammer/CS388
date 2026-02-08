package com.example.gmail

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WishlistAdapter
    private val wishlist = mutableListOf<WishlistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.emailsRv)
        val addBtn = findViewById<Button>(R.id.addBtn)

        val addItem = findViewById<EditText>(R.id.addItem)
        val addPrice = findViewById<EditText>(R.id.addPrice)
        val addLink = findViewById<EditText>(R.id.addLink)

        adapter = WishlistAdapter(wishlist)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addBtn.setOnClickListener {
            val itemText = addItem.text.toString().trim()
            val priceText = addPrice.text.toString().trim()
            val linkText = addLink.text.toString().trim()

            if (itemText.isBlank()) {
                addItem.error = "Item required"
                return@setOnClickListener
            }

            val wishlistItem = WishlistItem(
                name = itemText,
                price = if (priceText.isBlank()) "No price" else "$" + priceText,
                link = if (linkText.isBlank()) "No link" else linkText
            )

            wishlist.add(wishlistItem)
            adapter.notifyItemInserted(wishlist.size - 1)
            recyclerView.scrollToPosition(wishlist.size - 1)

            // clear inputs
            addItem.text?.clear()
            addPrice.text?.clear()
            addLink.text?.clear()
        }
    }
}

