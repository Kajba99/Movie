package com.example.Services

import com.example.Models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    companion object {
        const val BASE_URL = "https://newsapi.org"
    }

    @GET("/v1/articles?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398")
     fun getMovieList(): Call<MovieResponse>
}