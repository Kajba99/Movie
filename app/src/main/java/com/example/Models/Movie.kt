package com.example.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("title")
    val id : String ?,

    @SerializedName("description")
    val title : String?,

    @SerializedName("urlToImage")
    val poster : String?,

    @SerializedName("url")
    val url : String?

) : Parcelable{
    constructor() : this("", "", "","")
}