package com.example.bloomy3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView

class adapterUlasan2(context: Context, private val reviewList: ArrayList<Ulasan2>) : ArrayAdapter<Ulasan2>(context, R.layout.row_ulasan, reviewList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.row_ulasan, null)

        val rev = getItem(position)

        val Review: TextView = view.findViewById(R.id.userFeedback)
        val Rating: RatingBar = view.findViewById(R.id.rateProduct)
        val username: TextView = view.findViewById(R.id.userName)
        val date: TextView = view.findViewById(R.id.dateOfReview)
        val ratingFloat = rev?.rating?.toFloat() ?: 0.0f


//        val itemDeskripsiTextView: TextView = view.findViewById(R.id.item_deskripsi)

//        val itemImageView: ImageView = view.findViewById(R.id.item_photo)

        Review.text = "${rev?.review}"
        username.text="John Jacob"
        date.text = "30 Nov 2023"
        Rating.rating = ratingFloat

//        itemDeskripsiTextView.text = "Deskripsi: ${product?.deskripsi}"

//        Picasso.get().load(product?.imageUrl).into(itemImageView)
//        val showMoreButton: Button = view.findViewById(R.id.show_more_button)
//
//        showMoreButton.setOnClickListener {
//            val intent = Intent(context, DetailProdukActivity::class.java)
//            intent.putExtra("product", product)
//            context.startActivity(intent)
//        }


        return view
    }
}
