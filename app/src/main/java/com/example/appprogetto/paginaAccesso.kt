package com.example.appprogetto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class paginaAccesso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_accesso)
        val intent : Intent = getIntent()
    }


}