package com.example.bloomy3

import java.io.Serializable

class HistoryClass : Serializable {
    var namaProduk:String
    var harga:Double
    var deskripsi:String
    var stok:Int
//    var imageUrl: String

    constructor(namaProduk:String,harga:Double,deskripsi:String,stok:Int){
        this.namaProduk = namaProduk
        this.harga = harga
        this.deskripsi = deskripsi
        this.stok = stok
//        this.imageUrl = imageUrl

    }
}