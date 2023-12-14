package com.example.bloomy3

data class CartItem(
    val namaProduk: String,
    val harga: Double,
    var quantity: Int = 1 // Default quantity is 1
)
