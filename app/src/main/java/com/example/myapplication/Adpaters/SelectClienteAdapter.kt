package com.example.myapplication.Adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemListSeleccionClienteBinding
import com.example.registrodeservicios.db.entidades.Cliente

class SelectClienteAdapter (private val listCliente:List<Cliente>, var listener: ItemSelect): RecyclerView.Adapter<SelectClienteAdapter.ViewHolder>() {
    lateinit var binding: ItemListSeleccionClienteBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= ItemListSeleccionClienteBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    interface ItemSelect{
        fun selectCliente(cliente: Cliente)
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

    class ViewHolder(@NonNull bindig: ItemListSeleccionClienteBinding): RecyclerView.ViewHolder(bindig.root) {
        var binding: ItemListSeleccionClienteBinding = bindig

        fun asignarDatos(cliente: Cliente){
            binding.nombre.text= cliente.nombre +" "+ cliente.apellidoM+" "+cliente.apellidoP
        }
    }

}