package com.example.appprogetto

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val Notizie: ArrayList<Notizie>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
     override fun onCreateViewHolder(parent: ViewGroup, viewTipe: Int): MyAdapter.MyViewHolder{
          val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
          return MyViewHolder(itemView)
     }
     override fun onBindViewHolder(holder:MyAdapter.MyViewHolder, position: Int) {
          val notizia : Notizie = Notizie[position]
          holder.titolo.text = notizia.titolo
          holder.testo.text = notizia.testo
          holder.ambito.text = notizia.ambito
          holder.utente.text = notizia.utente
          holder.cardView.setBackgroundResource(R.color.gray)

     }
     override fun getItemCount(): Int {
          return Notizie.size
     }
     class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
          val titolo: TextView = itemView.findViewById(R.id.titolo2)
          val testo : TextView = itemView.findViewById(R.id.articolo2)
          val ambito: TextView = itemView.findViewById(R.id.ambito2)
          val cardView: CardView = itemView.findViewById(R.id.cardView)
          val utente: TextView = itemView.findViewById(R.id.Proprietario)
     }
}