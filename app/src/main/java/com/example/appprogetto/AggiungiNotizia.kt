package com.example.appprogetto

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appprogetto.databinding.AggiungiNotiziaBinding
import com.example.appprogetto.databinding.RegistratiBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AggiungiNotizia: AppCompatActivity() {
    lateinit var binding: AggiungiNotiziaBinding

    lateinit var database : FirebaseFirestore
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        database = Firebase.firestore
        binding = AggiungiNotiziaBinding.inflate(layoutInflater)
    setContentView(binding.root)

        binding.calcioAmbito.setOnClickListener(){
            binding.ambito.text = (binding.calcioAmbito.text)
        }
        binding.basketAmbito.setOnClickListener(){
            binding.ambito.text = (binding.basketAmbito.text)
        }
        binding.formulaunoAmbito.setOnClickListener(){
            binding.ambito.text = (binding.formulaunoAmbito.text)
        }
        binding.tennisAmbito.setOnClickListener(){
            binding.ambito.text = (binding.tennisAmbito.text)
        }
        binding.pallavoloAmbito.setOnClickListener(){
            binding.ambito.text = (binding.pallavoloAmbito.text)
        }
        binding.nuotoAmbito.setOnClickListener(){
            binding.ambito.text = (binding.nuotoAmbito.text)
        }

        binding.conferma.setOnClickListener() {
            val notizia =  hashMapOf(
                "titolo" to binding.titolo.text.toString(),
                "testo" to  binding.articolo.text.toString(),
                "ambito" to binding.ambito.text.toString(),
            )
            if (binding.titolo.text.isEmpty() || binding.articolo.text.isEmpty() || binding.ambito.text.isEmpty()) {
                Toast.makeText(this,
                    "Dati mancanti",
                    Toast.LENGTH_SHORT).show()}

            else{
                var db = database.collection("Notizie")
                db.add(notizia as Map<String, Any>).addOnSuccessListener{ documentReference->
                    Log.d(ContentValues.TAG, "I dati sono stati salvati correttamente: ${documentReference.id}")
                    Toast.makeText(this,
                        "Dati salvati correttamente",
                        Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Errore", e)
                    Toast.makeText(this,
                        "Errore",
                        Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, pagina_home::class.java)
                startActivity(intent)}
        }

}}