package com.example.myapplication.Adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemSevicioBinding
import com.example.registrodeservicios.db.entidades.Servicio

class ServicioAdapter  (private val listCliente: List<Servicio>, var listener: ItemSelect): RecyclerView.Adapter<ServicioAdapter.ViewHolder>() {
    lateinit var binding: ItemSevicioBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= ItemSevicioBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    interface ItemSelect{
        fun selectCliente(cliente: Servicio)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.asignarDatos(listCliente[position])
        holder.itemView.setOnClickListener{
            listener.selectCliente(listCliente[position])
        }
    }

    override fun getItemCount(): Int {
        return listCliente.size
    }

    class ViewHolder(@NonNull bindig: ItemSevicioBinding): RecyclerView.ViewHolder(bindig.root) {
        var binding: ItemSevicioBinding = bindig

        fun asignarDatos(servicio: Servicio){
            binding.servico.text= servicio.nombre
            binding.inicio.text= servicio.inicio
            binding.fin.text= servicio.fin
        }
    }
}