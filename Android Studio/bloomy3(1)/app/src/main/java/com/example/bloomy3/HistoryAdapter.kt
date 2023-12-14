package com.example.bloomy3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HistoryAdapter(context: Context, private val historyList: ArrayList<HistoryClass>) : ArrayAdapter<HistoryClass>(context, R.layout.history_item_list, historyList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.history_item_list, null)

        val history = getItem(position)

        val userName: TextView = view.findViewById(R.id.userName)
        val date: TextView = view.findViewById(R.id.dateOfReview)
        val userFeedback: TextView = view.findViewById(R.id.userFeedback)


        userName.text = "DEWA FLORIST"
        userFeedback.text="Rose Box Bouqet"
        date.text = "2023-11-30 | 12:50:13"


        val beriUlasan: TextView = view.findViewById(R.id.beriUlasan)
        beriUlasan.text = "Beri Ulasan"

        beriUlasan.setOnClickListener {
            val intent = Intent(context, tambahUlasan::class.java)
            intent.putExtra("product", history)
            context.startActivity(intent)
        }

        return view


    }
}