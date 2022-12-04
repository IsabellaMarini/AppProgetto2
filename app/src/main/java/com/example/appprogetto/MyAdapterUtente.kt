package com.example.appprogetto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class MyAdapterUtente(private val Utente: ArrayList<Users>): RecyclerView.Adapter<MyAdapterUtente.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTipe: Int): MyAdapterUtente.MyViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemutente, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder:MyAdapterUtente.MyViewHolder, position: Int) {
        val utente : Users = Utente[position]
        holder.nome.text = utente.nome
        holder.cognome.text = utente.cognome
        holder.username.text = utente.username
        holder.email.text = utente.email
        holder.cardView.setBackgroundResource(R.color.gray)
        holder.datanascita.text = utente.data_nascita

    }
    override fun getItemCount(): Int {
        return Utente.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nome: TextView = itemView.findViewById(R.id.nome3)
        val cognome : TextView = itemView.findViewById(R.id.cognome2)
        val username: TextView = itemView.findViewById(R.id.username2)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val email: TextView = itemView.findViewById(R.id.email3)
        val datanascita: TextView = itemView.findViewById(R.id.data_nascita)
    }
}