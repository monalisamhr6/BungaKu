package com.example.bloomy3

import java.io.Serializable

class Ulasan2 : Serializable {
    var review:String
    var rating:Int

    constructor(review:String,rating:Int){
        this.review = review
        this.rating = rating
    }
}