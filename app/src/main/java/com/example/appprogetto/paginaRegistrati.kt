package com.example.appprogetto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class paginaRegistrati : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_registrati)
        val intent : Intent = getIntent()
    }


}