package com.example.myapplication.view.Cliente

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityRegistrarClienteBinding
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.interfaces.IPresenters.IRegistrarClientePresente
import com.example.registrodeservicios.interfaces.view.RegistrarClienteView
import com.example.registrodeservicios.models.Presenters.RegistrarClientePresenter
import kotlinx.coroutines.launch


class RegistrarClienteActivity : AppCompatActivity(), RegistrarClienteView {
    private lateinit var binding: ActivityRegistrarClienteBinding
    private lateinit var presenter: IRegistrarClientePresente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrarClienteBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
        presenter= RegistrarClientePresenter(this,this.applicationContext)

        binding.rsBtnGuardar.setOnClickListener {
            if(validarCampos()) {
                lifecycleScope.launch {
                    val nombre = binding.nombre.text.toString()
                    val apellidoM = binding.apellidoM.text.toString()
                    val apellidoP = binding.apellidoP.text.toString()
                    val edad = binding.edad.text.toString()
                    val telefono = binding.edad.text.toString()
                    val correo = binding.correo.text.toString()

                    val cliente = Cliente(nombre, apellidoM, apellidoP, edad, telefono, correo)
                    guardarCliente(cliente)
                }
                val intent = Intent(this, ClientesActivity::class.java)
                startActivity(intent)
            }
        }
    }

    suspend fun guardarCliente(cliente: Cliente){
        System.out.println("Esta es la funcion de registrar cliente")
        presenter.insertarCliente(cliente)
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
}