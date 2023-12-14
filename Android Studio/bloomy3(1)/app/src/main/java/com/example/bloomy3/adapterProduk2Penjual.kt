package com.example.bloomy3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView

class adapterProduk2Penjual(context: Context, private val productListPenjual: ArrayList<itemsPenjual>) : ArrayAdapter<itemsPenjual>(context, R.layout.item_row_detail_penjual, productListPenjual) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_row_detail_penjual, null)

        val productPenjual = getItem(position)

        val itemNameTextView: TextView = view.findViewById(R.id.item_name)
        val itemHargaTextView: TextView = view.findViewById(R.id.item_harga)
//        val itemDeskripsiTextView: TextView = view.findViewById(R.id.item_deskripsi)
        val itemStokTextView: TextView = view.findViewById(R.id.item_stok)
//        val itemImageView: ImageView = view.findViewById(R.id.item_photo)

        itemNameTextView.text = "${productPenjual?.namaProduk}"
        itemHargaTextView.text = "${productPenjual?.harga}"
//        itemDeskripsiTextView.text = "Deskripsi: ${product?.deskripsi}"
        itemStokTextView.text = "Stok: ${productPenjual?.stok}"
//        Picasso.get().load(product?.imageUrl).into(itemImageView)
        val showMoreButton: Button = view.findViewById(R.id.show_more_button)

        showMoreButton.setOnClickListener {
            val intent = Intent(context, DetailProdukPenjual::class.java)
            intent.putExtra("product", productPenjual)
            context.startActivity(intent)
        }


        return view
    }
}
