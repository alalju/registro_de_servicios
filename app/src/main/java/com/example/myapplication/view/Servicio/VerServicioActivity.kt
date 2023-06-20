package com.example.myapplication.view.Servicio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityVerServicioBinding
import com.example.myapplication.interfaces.view.VerServicioView
import com.example.myapplication.models.Presenters.VerServicioPresenter
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VerServicioActivity : AppCompatActivity(), VerServicioView {
    private lateinit var binding: ActivityVerServicioBinding
    private lateinit var presenter: VerServicioPresenter
    private var id_servicio: Int= 0
    private var id_cliente: Int= 0
    private lateinit var servicioSelect: Servicio
    private lateinit var clienteSelect: Cliente
    private lateinit var estadoSelect: Estado
    private lateinit var subsSelect: Subscripcion


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerServicioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter= VerServicioPresenter(this, this.applicationContext)

        val bundle = intent.extras
        id_cliente = bundle?.getInt("id_cliente")!!
        intent.putExtra("el id_cliente pasado es",id_cliente)
        id_servicio = bundle?.getInt("id_servicio")!!
        intent.putExtra("el id_ser pasado es",id_servicio)
        lifecycleScope.launch(Dispatchers.Main){
            obtenetValores(id_servicio, id_cliente)
        }

        binding.editar.setOnClickListener {
            var intent= Intent(this, EditarServicioActivity::class.java)
            intent.putExtra("id_cliente",id_cliente)
            intent.putExtra("id_servicio",id_servicio)
            startActivity(intent)
            finish()
        }

        binding.eliminar.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main){
                presenter.deleteServici0(servicioSelect)
            }
            var intent= Intent(this, ServicioActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    suspend fun obtenetValores(id_ser: Int, id_cliente: Int){
        presenter.consultarClienteById(id_cliente)
        presenter.consultarServicioById(id_ser)
    }

    private fun llenarCamposCliente(){
        runOnUiThread {
            binding.cliente.setText(clienteSelect.nombre.toString() + " " + clienteSelect.apellidoM + " " + clienteSelect.apellidoP)
        }
    }

    private fun llenarCamposServicio(){
        runOnUiThread {
            binding.servicio.setText(servicioSelect.nombre)
            binding.concepto.setText(servicioSelect.concepto_pago)
            binding.fin.setText(servicioSelect.fin)
            binding.inicio.setText(servicioSelect.inicio)
            binding.monto.setText(servicioSelect.monto_pago)
        }
    }
    private fun llenarEst(){
        runOnUiThread {
            binding.estado.setText(estadoSelect.nombre)
        }
    }
    private fun llenarSub(){
        runOnUiThread {
            binding.subscripcion.setText(subsSelect.nombre)
        }
    }
    override fun verClienteById(cliente: Cliente) {
        clienteSelect=cliente
        llenarCamposCliente()

    }

    override fun verServicioById(servicio: Servicio) {
        servicioSelect=servicio
        llenarCamposServicio()
        lifecycleScope.launch(Dispatchers.IO) {
            presenter.consultarEstadoById(servicio.id_estado)
            presenter.consultarSubsById(servicio.id_subscripcion)
        }
    }

    override fun verSubsById(subscripcion: Subscripcion) {
        subsSelect=subscripcion
        llenarSub()

    }

    override fun verEstadoById(estado: Estado) {
        estadoSelect=estado
        llenarEst()
    }
}