package com.example.appprogetto

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Chat : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var messagelist: ArrayList<Message>
    private lateinit var db: FirebaseFirestore
    private lateinit var user: FirebaseAuth
    private lateinit var destinatario: ArrayList<String>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat)
        user = FirebaseAuth.getInstance()
        db= Firebase.firestore
        chatRecyclerView = findViewById(R.id.chat)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.setHasFixedSize(true)
        messagelist= arrayListOf()
        chatAdapter= ChatAdapter( messagelist)
        chatRecyclerView.adapter = chatAdapter
        intent.getStringExtra("username")
        db.collection("Messaggi").whereEqualTo("destinatario", user.currentUser?.email.toString())
            .get()
            .addOnSuccessListener {  documents->
                for(document in documents){
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                 messagelist.add(document.toObject(Message::class.java))
                }

                chatAdapter.notifyDataSetChanged()
            } .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            } }
}
