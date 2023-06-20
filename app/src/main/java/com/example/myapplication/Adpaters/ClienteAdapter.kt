package com.example.myapplication.Adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemClienteBinding
import com.example.registrodeservicios.db.entidades.Cliente

class ClienteAdapter(private var listCliente:List<Cliente>, var listener: ItemSelect): RecyclerView.Adapter<ClienteAdapter.ViewHolder>() {
    lateinit var binding: ItemClienteBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= ItemClienteBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    interface ItemSelect{
        fun itemEdit(cliente: Cliente)
    }

    fun searchcliente(list: List<Cliente>){
        listCliente= list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.asignarDatos(listCliente[position])
        holder.itemView.setOnClickListener{
            listener.itemEdit(listCliente[position])
        }
    }

    override fun getItemCount(): Int {
        return listCliente.size
    }

    class ViewHolder(@NonNull bindig: ItemClienteBinding): RecyclerView.ViewHolder(bindig.root) {
        var binding: ItemClienteBinding= bindig

        fun asignarDatos(cliente: Cliente){
            binding.nombre.text= cliente.nombre +" "+ cliente.apellidoM
            binding.Email.text= cliente.correo
            binding.Telefono.text= cliente.telefono
        }
    }
}