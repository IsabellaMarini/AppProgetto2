package com.example.appprogetto


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.widget.TextView

class ChatAdapter(private val Message: ArrayList<Message>): RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTipe: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder:ChatAdapter.MyViewHolder, position: Int) {
        val username : Message = Message[position]
        holder.username.setText(username.mittente)
    }
    override fun getItemCount(): Int {
        return Message.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val username: TextView = itemView.findViewById(R.id.username)
    }

}


