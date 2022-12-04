package com.example.appprogetto

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val Notizie: ArrayList<Notizie>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
     private lateinit var clickListener: OnClickListener
     interface OnClickListener{
          fun onItemClicked(position: Int)
     }
     fun setOnClickListener(listener: OnClickListener){
          clickListener=listener
     }
     override fun onCreateViewHolder(parent: ViewGroup, viewTipe: Int): MyAdapter.MyViewHolder{
          val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
          return MyViewHolder(itemView, clickListener)
     }
     @SuppressLint("SetTextI18n")
     override fun onBindViewHolder(holder:MyAdapter.MyViewHolder, position: Int) {
          val notizia : Notizie = Notizie[position]
          holder.notizia.text = notizia.notizia
          holder.ambito.text = notizia.ambito
          holder.utente.text = notizia.utente
          holder.cardView.setBackgroundResource(R.color.viola)
     }
     override fun getItemCount(): Int {
          return Notizie.size
     }
     class MyViewHolder(itemView: View, listener: OnClickListener): RecyclerView.ViewHolder(itemView){
          val notizia : TextView = itemView.findViewById(R.id.articolo2)
          val ambito: TextView = itemView.findViewById(R.id.ambito2)
          val cardView: CardView = itemView.findViewById(R.id.cardView)
          val utente: TextView = itemView.findViewById(R.id.Proprietario)
          init {
              utente.setOnClickListener {
                   listener.onItemClicked(adapterPosition)
              }
          }
     }

}