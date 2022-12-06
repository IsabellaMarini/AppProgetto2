package com.example.appprogetto


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText

class MyAdapterModifica(private val Users: ArrayList<Users>): RecyclerView.Adapter<MyAdapterModifica.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTipe: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_modifica, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder:MyAdapterModifica.MyViewHolder, position: Int) {
        val utente : Users = Users[position]
        holder.nome.setText(utente.nome)
        holder.cognome.setText(utente.cognome)
        holder.username.setText(utente.username)
        holder.email.setText(utente.email)
        holder.cardView.setBackgroundResource(R.color.viola)
        holder.data.setText(utente.data)
    }
    override fun getItemCount(): Int {
        return Users.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nome: EditText = itemView.findViewById(R.id.nome4)
        val cognome :EditText= itemView.findViewById(R.id.cognome4)
        val username: EditText = itemView.findViewById(R.id.username4)
        val cardView: CardView = itemView.findViewById(R.id.Utenti2)
        val email: EditText = itemView.findViewById(R.id.email4)
        val data: EditText= itemView.findViewById(R.id.data4)
    }
}