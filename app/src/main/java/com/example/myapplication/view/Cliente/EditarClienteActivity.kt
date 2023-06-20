package com.example.myapplication.view.Cliente

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global.putInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityEdutarClienteBinding
import com.example.myapplication.interfaces.view.EditarClienteView
import com.example.myapplication.models.Presenters.EditarClientePresenter
import com.example.myapplication.view.ViewModel.EditarClienteViewModel
import com.example.myapplication.view.ViewModelFactory.EditarClienteFactory
import com.example.registrodeservicios.db.entidades.Cliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditarClienteActivity : AppCompatActivity(), EditarClienteView {
    private lateinit var binding: ActivityEdutarClienteBinding
    private lateinit var presenter: EditarClientePresenter
    private lateinit var viewModel: EditarClienteViewModel
    private lateinit var sp: SharedPreferences
    private var clienteById= Cliente()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEdutarClienteBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)


        val bundle= intent.extras
        val id= bundle?.getInt("id")
        System.out.println("El id es: "+ id)

        sp = getPreferences(Context.MODE_PRIVATE)
        if(id!=null){
            val almacenarContador = sp.getInt("id_cliente",id)
            viewModel = ViewModelProvider(this,EditarClienteFactory(almacenarContador))[EditarClienteViewModel::class.java]
        }




        presenter= EditarClientePresenter(this, this.applicationContext)
        lifecycleScope.launch {
            if (id != null) {
                presenter.consultarClienteById(id)
            }
        }



        binding.rsBtnGuardar.setOnClickListener {
            if( validarCampos()) {
                val nombre= binding.nombre.text.toString()
                val apellidoM= binding.apellidoM.text.toString()
                val apellidoP= binding.apellidoP.text.toString()
                val edad= binding.edad.text.toString()
                val telefono= binding.telefono.text.toString()
                val corre= binding.correo.text.toString()
                System.out.println("El id del cliente para guardar es: "+ id)
                val cliente= clienteById.id_cliente?.let { it1 -> Cliente(it1,nombre,apellidoM, apellidoP,edad, telefono,corre ) }

                if (cliente != null) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        presenter.updateCliente(cliente)
                    }
                }


                val intent = Intent(this, VerClienteActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            }
        }
    }
    private fun validarCampos(): Boolean {
        var camposValidos = true

        if (binding.nombre.text.toString().isEmpty()) {
            binding.nombre.error = "Este campo es obligatorio"
            camposValidos = false
        }

        if (binding.apellidoM.text.toString().isEmpty()) {
            binding.apellidoM.error = "Este campo es obligatorio"
            camposValidos = false
        }

        if (binding.apellidoP.text.toString().isEmpty()) {
            binding.apellidoP.error = "Este campo es obligatorio"
            camposValidos = false
        }
        if (binding.edad.text.toString().isEmpty()) {
            binding.edad.error = "Este campo es obligatorio"
            camposValidos = false
        }
        if (binding.correo.text.toString().isEmpty()) {
            binding.correo.error = "Este campo es obligatorio"
            camposValidos = false
        }
        if (binding.telefono.text.toString().isEmpty()) {
            binding.telefono.error = "Este campo es obligatorio"
            camposValidos = false
        }

        if (!binding.edad.text.toString().matches(Regex("[0-9]+"))) {
            binding.edad.error = "Este campo debe contener solo números"
            camposValidos = false
        }

        if (!binding.telefono.text.toString().matches(Regex("[0-9]{10}"))) {
            binding.telefono.error = "Este campo debe contener un número de teléfono válido"
            camposValidos = false
        }

        if (!binding.correo.text.toString().matches(Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))) {
            binding.correo.error = "Este campo debe contener un correo electrónico válido"
            camposValidos = false
        }

        return camposValidos
    }

    private fun actualizar(){

    }

    override fun onPause() {
        super.onPause()
        sp.edit{
            putInt("id_cliente",viewModel.id_cliente)
        }
    }
    override fun mostrarUpdate(cantidad: Int) {
        TODO("Not yet implemented")
    }

    override fun mostrarClienteById(cliente: Cliente) {
        clienteById= cliente

        if(clienteById==null){
            System.out.println("El objeto cliente esta vacio")
        }else{
            binding.nombre.setText( clienteById.nombre)
            binding.apellidoM.setText( clienteById.apellidoM)
            binding.apellidoP.setText(clienteById.apellidoP)
            binding.edad.setText(clienteById.edad)
            binding.telefono.setText(clienteById.telefono)
            binding.correo.setText(clienteById.correo)



        }
    }
}
