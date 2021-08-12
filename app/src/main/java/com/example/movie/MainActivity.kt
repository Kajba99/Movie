package com.example.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.Models.Movie
import com.example.Models.MovieResponse
import com.example.Services.MovieApiInterface
import com.example.Services.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {

    lateinit var swipeLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeLayout = findViewById(R.id.swipelayout)
        rv_movies_list.layoutManager = LinearLayoutManager(this)
        rv_movies_list.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
           rv_movies_list.adapter = MovieAdapter(movies)
        }

        swipeLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            getMovieData { movies : List<Movie> ->
                rv_movies_list.adapter = MovieAdapter(movies)
            }

        })
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit){

        swipeLayout.isRefreshing = true

        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

             swipeLayout.isRefreshing=false

                displayDialog()

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                swipeLayout.isRefreshing=false
                return callback(response.body()!!.movies)
            }

        })
    }

    private fun displayDialog( ) {
        val builder = AlertDialog.Builder( this@MainActivity )
        builder.setTitle( "No Internet Connection" )
        builder.setMessage( "Make sure your device is connected to the internet." )
        builder.setPositiveButton( "Settings" ) { dialog, which ->
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }
/*
        builder.setNegativeButton( "No" ) { dialog, which ->
            Toast.makeText( applicationContext, "Declining action", Toast.LENGTH_SHORT).show()
        }
*/
        builder.setNeutralButton( "Cancel" ) { dialog, which ->
            Toast.makeText( applicationContext, "You cancelled the dialog.", Toast.LENGTH_SHORT ).show( )
        }

        val dialog: AlertDialog = builder.create( )
        dialog.show( )
    }


}