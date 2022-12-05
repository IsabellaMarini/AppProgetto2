package com.example.appprogetto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class MyAdapterUtente(private val Users: ArrayList<Users>): RecyclerView.Adapter<MyAdapterUtente.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTipe: Int): MyAdapterUtente.MyViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemutente, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder:MyAdapterUtente.MyViewHolder, position: Int) {
        val utente : Users = Users[position]
        holder.nome.text = utente.nome
        holder.cognome.text = utente.cognome
        holder.username.text = utente.username
        holder.email.text = utente.email
        holder.cardView.setBackgroundResource(R.color.viola)
        holder.data.text = utente.data
    }
    override fun getItemCount(): Int {
        return Users.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nome: TextView = itemView.findViewById(R.id.nome3)
        val cognome : TextView = itemView.findViewById(R.id.cognome2)
        val username: TextView = itemView.findViewById(R.id.username2)
        val cardView: CardView = itemView.findViewById(R.id.Utenti)
        val email: TextView = itemView.findViewById(R.id.email3)
        val data: TextView = itemView.findViewById(R.id.data_nascita)
    }
}