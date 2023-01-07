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

class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messagelist: ArrayList<Message>
    private lateinit var db: FirebaseFirestore
    private lateinit var user: FirebaseAuth
    lateinit var chat: ArrayList<String>
    var receiverRoom: String? = null
    var senderRoom: String? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val receiver = intent.getStringExtra("email2")
        user = FirebaseAuth.getInstance()
        val sender = user.currentUser?.email
        db= Firebase.firestore

        senderRoom = receiver + sender
        receiverRoom = sender+ receiver

        chatRecyclerView = findViewById(R.id.chatReciclerView)
        messageBox = findViewById(R.id.messageBox)
        sendButton = findViewById(R.id.sentButton)
        messagelist = ArrayList()
        messageAdapter = MessageAdapter(this, messagelist)

        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter
        val currentTimestamp = System.currentTimeMillis()
        chat= ArrayList()
        chat.add( receiver.toString())
        chat.add(sender.toString())
        sendButton.setOnClickListener(){
           var chat= hashMapOf("mittente" to  sender,
               "destinatario" to receiver)
            var messaggio =  hashMapOf(
                "messaggio" to messageBox.text.toString(),
                "time" to currentTimestamp
               )

            var db = db.collection("Chat")
            db.add(chat as HashMap<String, Any>).addOnSuccessListener{ documentReference->
                Log.d(ContentValues.TAG, "I dati sono stati salvati correttamente: ${documentReference.id}")
                Toast.makeText(this,
                    "Dati salvati correttamente",
                    Toast.LENGTH_SHORT).show()
                documentReference.collection("Messaggi").add(messaggio as HashMap<String, Any>).addOnSuccessListener{ documentReference->
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

            }.addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Errore", e)
                Toast.makeText(this,
                    "Errore",
                    Toast.LENGTH_SHORT).show()
            }

        }
        db.collection("Chat").whereIn( "mittente", chat)
            .get()
            .addOnSuccessListener { documents->
                for(document in documents){

                            messagelist.add(document.toObject(Message::class.java))

                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                }

       messageAdapter.notifyDataSetChanged()

    }
    }}

