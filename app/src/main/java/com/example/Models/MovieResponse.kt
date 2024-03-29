package com.example.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(

    @SerializedName("articles")
    val movies : List<Movie>


) : Parcelable {
    constructor() : this(mutableListOf())
}
