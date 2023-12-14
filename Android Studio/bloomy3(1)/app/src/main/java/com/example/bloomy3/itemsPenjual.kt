package com.example.bloomy3

import java.io.Serializable

class itemsPenjual : Serializable {
    var id_produk: Int
    var namaProduk:String
    var harga:Double
    var deskripsi:String
    var stok:Int

//    var imageUrl: String

    constructor(id_produk: Int,namaProduk:String,harga:Double,deskripsi:String,stok:Int){
        this.id_produk = id_produk
        this.namaProduk = namaProduk
        this.harga = harga
        this.deskripsi = deskripsi
        this.stok = stok
//        this.imageUrl = imageUrl

    }}