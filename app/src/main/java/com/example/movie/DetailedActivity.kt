package com.example.movie

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.core.view.isInvisible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detailed.*

class DetailedActivity : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)



        // prvi
        var web : WebView = findViewById(R.id.webview)
        var name: TextView = findViewById(R.id.tvdetailed)
        var image: ImageView = findViewById(R.id.ivdetailed)
        //var desc: TextView = findViewById(R.id.tvdesc)
        //var desc2: TextView = findViewById(R.id.tvdesc2)
       //var desc3: TextView = findViewById(R.id.tvdesc3)


        // drugi
        val bundle: Bundle? = intent.extras

        //treci
        val ime = bundle!!.getString("title")
        val slika = bundle.getString("image")
        val url = bundle.getString("url")

        //cetvrti
        name.text = ime
        //desc.text = opis
       // desc2.text = opis
       // desc3.text = opis2
        Glide.with(this).load(slika).into(image)


        //        setSupportActionBar(toooolbar)
        //toooolbar.title = ime

        web.settings.javaScriptEnabled = true
        web.webViewClient = WebViewClient()
        web.loadUrl(url.toString())

    }



}