package com.example.appprogetto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appprogetto.databinding.ActivityPaginaHomeBinding
import com.google.firebase.auth.FirebaseAuth


class pagina_home : AppCompatActivity() {

    private lateinit var binding: ActivityPaginaHomeBinding
    private lateinit var user:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaginaHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        if(user.currentUser != null) {
            user.currentUser?.let {

                binding.UserEmail.text = it.email

            }
        }


        binding.disconnetti.setOnClickListener{
            user.signOut()
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()


        }




    }
}