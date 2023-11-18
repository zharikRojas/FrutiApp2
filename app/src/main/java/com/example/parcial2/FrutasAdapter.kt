package com.example.parcial2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil

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
        val startIndex = position *2
        val endIndex = minOf(startIndex + 2, frutasList.size)

        if (startIndex < frutasList.size){
            val frutasEnPosicion = frutasList.subList(startIndex, endIndex)
            holder.bind(frutasEnPosicion)

            holder.itemView.setOnClickListener{
                onItemClick?.invoke(frutasEnPosicion[0])
            }
        }else{
            holder.bind(emptyList())
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
        private val nombreTextView2: TextView = itemView.findViewById(R.id.textViewNombre2)

        fun bind(frutas: List<FrutasModel>){
            if (frutas.size > 0) {
                nombreTextView1.text = frutas[0].nombre
            } else {
                nombreTextView1.text = ""
            }

            if (frutas.size > 1) {
                nombreTextView2.text = frutas[1].nombre
            } else {
                nombreTextView2.text = ""
            }
        }
    }
}