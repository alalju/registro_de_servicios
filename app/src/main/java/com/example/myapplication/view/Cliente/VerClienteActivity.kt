package com.example.myapplication.view.Cliente

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityVerClienteBinding
import com.example.myapplication.interfaces.view.VerClienteView
import com.example.myapplication.models.Presenters.VerClientePresenter
import com.example.myapplication.view.Servicio.EditarServicioActivity
import com.example.myapplication.view.Servicio.VerServicioActivity
import com.example.myapplication.view.ViewModel.EditarClienteViewModel
import com.example.myapplication.view.ViewModel.VerClienteviewModel
import com.example.myapplication.view.ViewModelFactory.EditarClienteFactory
import com.example.myapplication.view.ViewModelFactory.VerClienteViewModelFactory
import com.example.registrodeservicios.db.entidades.Cliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VerClienteActivity : AppCompatActivity(), VerClienteView {
    private lateinit var binding: ActivityVerClienteBinding
    private lateinit var presenter: VerClientePresenter
    private var clienteById= Cliente()
    private var id: Int=0

    private lateinit var viewModel: VerClienteviewModel
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVerClienteBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        presenter= VerClientePresenter(this, this.applicationContext)
        val bundle= intent.extras
        id= bundle?.getInt("id")!!
        System.out.println("El id es: "+ id)
        lifecycleScope.launch {
            if (id != null) {
                presenter.consultarClienteById(id)
            }
        }

        sp = getPreferences(Context.MODE_PRIVATE)
        val id_ = sp.getInt("id_cliente",0)
        val nombre= sp.getString("nombre", " ")
        val apellidoM= sp.getString("apellidoM", " ")
        val apellidoP= sp.getString("apellidoMP", " ")
        val edad= sp.getString("edad", " ")
        val telefono= sp.getString("telefono", " ")
        val corre= sp.getString("correo", " ")


        //viewModel = ViewModelProvider(this, VerClienteViewModelFactory(clienteById))[VerClienteviewModel::class.java]


        binding.editar.setOnClickListener {
            val intent= Intent(this, EditarClienteActivity:: class.java)
            intent.putExtra("id",id)
            System.out.println("El id a pasar para edira es: "+ id)
            startActivity(intent)
        }

        binding.eliminar.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                presenter.deleteUsuario(clienteById)
                presenter.deleteServico(clienteById.id_cliente)
            }
            val intent= Intent(this, ClientesActivity::class.java)
            startActivity(intent)
            finish()
        }



    }


    override fun onPause() {
        super.onPause()
        sp.edit{
            putInt("id_cliente",viewModel.id_cliente)
            putString("nombre", viewModel.nombre)
            putString("apellidoM", viewModel.apellidoM)
            putString("apellidoP", viewModel.apellidoP)
            putString("edad", viewModel.edad)
            putString("telefono", viewModel.telefono)
            putString("correo", viewModel.corre)
        }
    }

    override fun mostrarClienteById(cliente: Cliente) {
        clienteById= cliente
        if(clienteById==null){
            System.out.println("El objeto cliente esta vacio")
        }else{
            System.out.println("El nombre es:"+ clienteById.nombre)
            binding.nombre.setText( clienteById.nombre)
            binding.apellidoM.setText( clienteById.apellidoM)
            binding.apellidoP.setText(clienteById.apellidoP)
            binding.edad.setText(clienteById.edad)
            binding.telefono.setText(clienteById.telefono)
            binding.correo.setText(clienteById.correo)
            viewModel = ViewModelProvider(this, VerClienteViewModelFactory(clienteById))[VerClienteviewModel::class.java]
            System.out.println("El valor de viewModel nombre es: "+viewModel.nombre)
        }
    }
}