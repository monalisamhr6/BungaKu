package com.example.bloomy3

class items {
    var id_produk:Int
    var namaProduk:String
    var harga:Double
    var deskripsi:String
    var photo:String
    var stok:Int

    constructor(id_produk:Int,namaProduk:String,harga:Double,deskripsi:String,photo:String,stok:Int){
        this.id_produk = id_produk
        this.namaProduk = namaProduk
        this.harga = harga
        this.deskripsi = deskripsi
        this.photo = photo
        this.stok = stok
    }

}