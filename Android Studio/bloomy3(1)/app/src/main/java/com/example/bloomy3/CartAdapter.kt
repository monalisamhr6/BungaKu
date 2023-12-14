package com.example.bloomy3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CartAdapter(private val context: Context, private val cartItems: List<CartItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return cartItems.size
    }

    override fun getItem(position: Int): Any {
        return cartItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = convertView ?: inflater.inflate(R.layout.keranjang, null)

        val cartItem = getItem(position) as CartItem

        // Set data ke tampilan
        val namaProdukTextView: TextView = view.findViewById(R.id.nametxt)
        val stokTextView: TextView = view.findViewById(R.id.stok)
        val hargaTextView: TextView = view.findViewById(R.id.pricetxt)
        val quantityTextView: TextView = view.findViewById(R.id.numitemtxt)

        namaProdukTextView.text = cartItem.namaProduk
        hargaTextView.text = "Rp: ${cartItem.harga}"
        quantityTextView.text = cartItem.quantity.toString()

        return view
    }
}
