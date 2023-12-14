package com.example.bloomy3

import android.app.Activity
import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class itemAdapter(var context:Context,var list: ArrayList<items>) : RecyclerView.Adapter<RecyclerView.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return ItemHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(
            list[position].namaProduk,
            list[position].harga,
            list[position].deskripsi,
            list[position].photo,
            list[position].stok,
            list[position].id_produk)
    }

    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemHarga: TextView = itemView.findViewById(R.id.item_harga)
        val itemDeskripsi: TextView = itemView.findViewById(R.id.item_deskripsi)
        val itemPhoto: ImageView = itemView.findViewById(R.id.item_photo)
        val itemStok: TextView = itemView.findViewById(R.id.item_stok)
        val itemAdd: ImageView = itemView.findViewById(R.id.item_add_photo)

        fun bind(n:String, h:Double, d:String, p:String, s:Int, produk_Id:Int){
            itemName.text = n
            itemHarga.text = h.toString()
            itemDeskripsi.text = d
            var web:String="http://192.168.100.246/bloomy/uploads/"+p
            web=web.replace(" ","%20")
            Picasso.get().load("http://192.168.100.246/bloomy/uploads/"+p).into(itemPhoto)
            itemStok.text = s.toString()

            itemAdd.setOnClickListener{
                UserInfo.produkId=produk_Id
                var obj=QtyFragment()
                var manager=(itemView.context as Activity).fragmentManager
//                obj.show(manager,"Qty")

            }
        }
    }

}