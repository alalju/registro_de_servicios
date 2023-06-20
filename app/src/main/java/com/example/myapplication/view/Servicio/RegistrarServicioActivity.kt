package com.example.myapplication.view.Servicio

import ManejoFechas
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityRegistrarServicioBinding
import com.example.myapplication.interfaces.view.IRegistrarServicioPresenter
import com.example.myapplication.interfaces.view.RegistrarServicioView
import com.example.myapplication.models.Presenters.RegistrarServicioPresenter
import com.example.myapplication.view.dialog.SelectClienteFragment
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrarServicioActivity : AppCompatActivity(), RegistrarServicioView, SelectClienteFragment.OnTextViewDataSet {
    private lateinit var binding: ActivityRegistrarServicioBinding
    private lateinit var presenter: IRegistrarServicioPresenter
    private lateinit var clienteSelect: Cliente
    private lateinit var dialogFragment : SelectClienteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrarServicioBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        presenter= RegistrarServicioPresenter(this,this.applicationContext)
        lifecycleScope.launch{
            obtenerValores()
        }

        lanzarDatePicker1()
        lanzarDatePicker2()
        clickCliente()

        clickGuardar()

    }
    private suspend fun obtenerValores(){
            presenter.consultarEstados()
            presenter.consultarSubs()
    }
    private fun llenarSpinnerEstados(estados: List<Estado>) {
        val elementos = mutableListOf<String>()
        elementos.add("Selecciona el estado")
        for (elemento in estados) {
            elementos.add(elemento.nombre)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, elementos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.estados.adapter = adapter
    }

    private fun twoDigits(n: Int): String {
        return if (n <= 9) "0$n" else n.toString()
    }
    private fun showDatePickerDialog1() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val diaSeleccionado = "${twoDigits(dayOfMonth)}-${twoDigits(month + 1)}-$year"
            binding.inicio.setText( diaSeleccionado)
        })
        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun lanzarDatePicker1() {
        binding.inicio.setOnClickListener {
            showDatePickerDialog1()
        }
    }
    private fun showDatePickerDialog2() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val diaSeleccionado = "${twoDigits(dayOfMonth)}-${twoDigits(month + 1)}-$year"
            binding.fin.setText( diaSeleccionado)
        })
        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun lanzarDatePicker2() {
        binding.fin.setOnClickListener {
            showDatePickerDialog2()
        }
    }


    private fun llenarSpinnerSubs(subs: List<Subscripcion>) {
        val elementos = mutableListOf<String>()
        elementos.add("Selecciona la subscripcion")
        for (elemento in subs) {
            elementos.add(elemento.nombre)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, elementos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.subscripcion.adapter = adapter
    }

    private fun clickCliente(){
        binding.cliente.setOnClickListener{
            dialogFragment = SelectClienteFragment()
            dialogFragment.show(supportFragmentManager, "dialog")
        }
    }

    private fun clickGuardar(){
        binding.rsBtnGuardar.setOnClickListener {
            if (!validarCampos()) {
                Toast.makeText(this, "Los campos no son validos", Toast.LENGTH_SHORT).show()

            }else{
                val servicio= obtenerValoresServicio()
                lifecycleScope.launch(Dispatchers.IO){
                    presenter.registrarServicio(servicio)
                }
                val intent = Intent (this, ServicioActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    private fun obtenerValoresServicio(): Servicio{
        val nombre= binding.servicio.text.toString()
        val cliente= clienteSelect.id_cliente
        val concepto= binding.concepto.text.toString()
        val monto= binding.monto.text.toString()
        val inicio= binding.monto.text.toString()
        val fin = binding.fin.text.toString()
        val estado= obtenerIdEstado()
        val subs= obtenerIdSubs()
        return Servicio(nombre,concepto, monto, inicio, fin, cliente,subs, estado )
    }

    private fun obtenerIdEstado(): Int {
        var id=0
        when(binding.estados.selectedItem.toString()){
            "Pagado" ->{
                id= 1
            }
            "Pendiente" ->{
                id= 2
            }
        }
        return id
    }
    private fun obtenerIdSubs(): Int{
        var id=0
        when(binding.subscripcion.selectedItem.toString()){
            "Dia" ->{
                id= 1
            }
            "Semana" ->{
                id= 2
            }
            "Mes" ->{
                id= 3
            }
        }
        return id
    }

    private fun validarCampos(): Boolean {
        val camposValidos = mutableListOf<Boolean>()

        val conceptoValido = binding.concepto.text.toString().isNotEmpty()
        if (!conceptoValido) {
            binding.concepto.error = "Este campo es obligatorio"
        }
        camposValidos.add(conceptoValido)

        val clienteValido = binding.cliente.text.toString().isNotEmpty()
        if (!clienteValido) {
            binding.cliente.error = "Este campo es obligatorio"
        }
        camposValidos.add(clienteValido)

        val montoValido = binding.monto.text.toString().matches(Regex("[0-9]+"))
        if (!montoValido) {
            binding.monto.error = "Este campo debe contener solo números"
        }
        camposValidos.add(montoValido)

        val inicioValido = binding.inicio.text.toString().isNotEmpty()
        if (!inicioValido) {
            binding.inicio.error = "Este campo es obligatorio"
        }
        camposValidos.add(inicioValido)

        val finValido = binding.fin.text.toString().isNotEmpty()
        if (!finValido) {
            binding.fin.error = "Este campo es obligatorio"
        }
        camposValidos.add(finValido)

        val fechasValidas = binding.inicio.text.toString().compareTo(binding.fin.text.toString()) < 0
        if (!fechasValidas) {
            binding.fin.error = "La fecha final debe ser posterior a la fecha inicial"
        }

        if(binding.estados.selectedItem.toString()=="0"){
             camposValidos.add(false)
        }

        if(binding.subscripcion.selectedItem.toString()=="0"){
            camposValidos.add(false)
        }
        camposValidos.add(fechasValidas)

        return camposValidos.all { it }
    }


    override suspend fun mostarEstados(estado: List<Estado>) {
        llenarSpinnerEstados(estado)
    }

    override suspend fun mostrarSubs(subscripcion: List<Subscripcion>) {
        llenarSpinnerSubs(subscripcion)
    }

    override fun onDataSet(cliente: Cliente) {
        clienteSelect= cliente
        binding.cliente.setText(clienteSelect.nombre+" "+clienteSelect.apellidoM+" "+clienteSelect.apellidoP)
    }
}