package com.example.parcial2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FrutasAdapter : RecyclerView.Adapter<FrutasAdapter.FrutaViewHolder>() {

    private var frutasList: List<FrutasModel> = emptyList()
    private var onItemClick: ((FrutasModel) -> Unit)?=null

    fun setOnItemClickListener(listener: (FrutasModel)->Unit){
        onItemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrutaViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruta_list_item, parent, false)
        return FrutaViewHolder(view)
    }

    override fun onBindViewHolder(holder: FrutaViewHolder, position: Int) {
        val fruta = frutasList[position]

        holder.bind(fruta)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(fruta)
        }

    }

    override fun getItemCount(): Int {
      return frutasList.size
    }

    fun actualizarDatos(nuevaLista: List<FrutasModel>){
        frutasList = nuevaLista
        notifyDataSetChanged()
    }
    class FrutaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val nombreTextView1: TextView = itemView.findViewById(R.id.textViewNombre1)

        fun bind(fruta: FrutasModel){

                nombreTextView1.text = fruta.nombre

        }
    }
}