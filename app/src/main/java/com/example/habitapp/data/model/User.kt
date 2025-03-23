package com.example.habitapp.data.model

data class User (
    var firstName: String ? =null,
    var surname: String ? =null,
    var telNo:String ? =null
) {
    var id:String? =null
    override fun toString(): String = "$firstName $surname $telNo"
}
