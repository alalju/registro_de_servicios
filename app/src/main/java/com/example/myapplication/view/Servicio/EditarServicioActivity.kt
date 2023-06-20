package com.example.myapplication.view.Servicio

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityEditarServicioBinding
import com.example.myapplication.interfaces.view.EditarServicioView
import com.example.myapplication.models.Presenters.EditarServicioPresenter
import com.example.myapplication.view.dialog.SelectClienteFragment
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditarServicioActivity : AppCompatActivity(), EditarServicioView, SelectClienteFragment.OnTextViewDataSet {
    private lateinit var binding: ActivityEditarServicioBinding
    private lateinit var presenter: EditarServicioPresenter
    private lateinit var dialogFragment : SelectClienteFragment
    private var id_servicio: Int= 0
    private var id_cliente: Int= 0
    private lateinit var servicioSelect: Servicio
    private lateinit var clienteSelect: Cliente
    private lateinit var estadoSelect: Estado
    private lateinit var subsSelect: Subscripcion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditarServicioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter= EditarServicioPresenter(this, this.applicationContext)

        val bundle = intent.extras
        id_cliente = bundle?.getInt("id_cliente")!!
        System.out.println("El id del clientes :"+id_cliente)
        id_servicio = bundle?.getInt("id_servicio")!!
        System.out.println("El id del servicio es "+ id_servicio)

        lifecycleScope.launch(Dispatchers.Main){
            presenter.consultarEstados()
            presenter.consultarSubs()
            obtenetValores(id_servicio, id_cliente)
        }
        clickCliente()

        clickGuardar()

    }
    private fun clickGuardar(){
        binding.rsBtnGuardar.setOnClickListener {
            if(validarCampos()){
                val servicio= obtenerValoresServicio()
                lifecycleScope.launch(Dispatchers.IO){
                    presenter.updateServicio(servicio)
                }
                System.out.println("El id del clientes :"+id_cliente)
                System.out.println("El id del servicio es "+ id_servicio)

                val intent = Intent (this, VerServicioActivity::class.java)
                intent.putExtra("id_cliente",id_cliente)
                intent.putExtra("id_servicio",id_servicio)
                startActivity(intent)
                finish()
            }

        }

    }
    suspend fun obtenetValores(id_ser: Int, id_cliente: Int){
        presenter.consultarClienteById(id_cliente)
        presenter.consultarServicioById(id_ser)

    }

    private fun obtenerValoresServicio(): Servicio{
        val nombre= binding.servicio.text.toString()
        val cliente= clienteSelect.id_cliente
        val concepto= binding.concepto.text.toString()
        val monto= binding.monto.text.toString()
        val inicio= binding.inicio.text.toString()
        val fin = binding.fin.text.toString()
        val estado= obtenerIdEstado()
        val subs= obtenerIdSubs()
        System.out.println("El id_estado a actualizar es"+ estado)
        System.out.println("El id_subs a actualizar es"+ subs)
        return Servicio(id_servicio,nombre,concepto, monto, inicio, fin, cliente,subs, estado )
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
    private fun clickCliente(){
        binding.cliente.setOnClickListener{
            dialogFragment = SelectClienteFragment()
            dialogFragment.show(supportFragmentManager, "dialog")
        }
    }

    private fun validarCampos(): Boolean {
        val camposValidos = mutableListOf<Boolean>()

        val serValido = binding.servicio.text.toString().isNotEmpty()
        if (!serValido) {
            binding.concepto.error = "Este campo es obligatorio"
        }
        camposValidos.add(serValido)

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
        camposValidos.add(fechasValidas)

        if(binding.estados.selectedItem.toString()=="0"){
            camposValidos.add(false)
        }

        if(binding.subscripcion.selectedItem.toString()=="0"){
            camposValidos.add(false)
        }
        return camposValidos.all { it }
    }

    private fun llenarEst(){
        runOnUiThread {
            binding.estados.setSelection(servicioSelect.id_estado)
        }
    }
    private fun llenarSub(){
        System.out.println("el id de la subs es: "+servicioSelect.id_subscripcion)
        runOnUiThread {
            binding.subscripcion.setSelection(servicioSelect.id_subscripcion)
        }
    }
    override fun verClienteById(cliente: Cliente) {
        clienteSelect=cliente
        llenarCamposCliente()

    }

    private fun llenarSpinnerEstados(estados: List<Estado>) {
        val elementos = mutableListOf<String>()
        elementos.add("Selecciona el estado")
        for (elemento in estados) {
            elementos.add(elemento.nombre)
        }

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, elementos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.estados.adapter = adapter
    }



    private fun llenarSpinnerSubs(subs: List<Subscripcion>) {
        val elementos = mutableListOf<String>()
        elementos.add("Selecciona la subscripcion")
        for (elemento in subs) {
            elementos.add(elemento.nombre)
        }

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, elementos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.subscripcion.adapter = adapter
    }

    override fun verServicioById(servicio: Servicio) {
        servicioSelect=servicio
        llenarCamposServicio()
        lifecycleScope.launch(Dispatchers.IO) {
            presenter.consultarEstadoById(servicio.id_estado)
            System.out.println("El id de la subs consultada por verServiciobyId es: "+ servicio.id_subscripcion )
            presenter.consultarSubsById(servicio.id_subscripcion)
        }
    }

    override fun verSubsById(subscripcion: Subscripcion) {
        if(subscripcion==null){
            System.out.println("La subscripcion consultada tiene valores!!!")
        }else{
            System.out.println("La subscripcion consultada ¡¡¡¡¡no!!! tiene valores")
        }
        subsSelect=subscripcion
        System.out.println("El id de subs consultada es"+ subscripcion.id_subscripcion)
        llenarSub()

    }

    override fun verEstadoById(estado: Estado) {
        estadoSelect=estado
        llenarEst()
    }

    override fun verClientes(list: List<Cliente>) {
        TODO("Not yet implemented")
    }

    override fun onDataSet(cliente: Cliente) {
        clienteSelect= cliente
        binding.cliente.setText(clienteSelect.nombre+" "+clienteSelect.apellidoM+" "+clienteSelect.apellidoP)
    }
    override suspend fun mostarEstados(estado: List<Estado>) {
        for(i in estado){
            System.out.println(i.nombre)
        }
        llenarSpinnerEstados(estado)
    }

    override suspend fun mostrarSubs(subscripcion: List<Subscripcion>) {
        if(subscripcion.isNotEmpty()){
            System.out.println("La lsita de subs no esta vacia:")
            for(i in subscripcion){
                System.out.println(i.nombre)
            }
        }else{
            System.out.println("La lsita de subs esta vacia!!!:")
        }
        llenarSpinnerSubs(subscripcion)
    }
}