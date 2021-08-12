package com.example.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private lateinit var context: Context


    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        lateinit var constraintLayout: ConstraintLayout
        lateinit var loader: ProgressBar

        init {
          constraintLayout = itemView.findViewById(R.id.main_layout)
            loader = itemView.findViewById(R.id.loader)
        }

        fun bindMovie(movie : Movie){
            loader.isVisible = true
            itemView.movie_title.text = movie.id
            Glide.with(itemView).load(movie.poster).into(itemView.movie_poster)
            loader.isVisible = false

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        return MovieViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))

        holder.constraintLayout.setOnClickListener {
           val intent = Intent(context, DetailedActivity::class.java)

            val bundle = Bundle()
            bundle.putString("title", movies[position].id)
            bundle.putString("description", movies[position].title)
            bundle.putString("image", movies[position].poster)
            bundle.putString("url", movies[position].url)

            intent.putExtras(bundle)
            context.startActivity(intent)
        }


    }


}