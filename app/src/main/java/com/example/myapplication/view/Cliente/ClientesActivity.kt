package com.example.myapplication.view.Cliente

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adpaters.ClienteAdapter
import com.example.myapplication.databinding.ActivityClientesBinding
import com.example.myapplication.interfaces.view.ClientesView
import com.example.myapplication.models.Presenters.ClientesPresenter
import com.example.registrodeservicios.db.entidades.Cliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientesActivity : AppCompatActivity(), ClientesView, ClienteAdapter.ItemSelect {
    private lateinit var binding: ActivityClientesBinding
    private lateinit var presenter: ClientesPresenter
    private lateinit var listCliente: List<Cliente>
    private lateinit var adapter: ClienteAdapter
    private lateinit var clienteSelect:Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityClientesBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        //Consultar los clientes registrados
        presenter= ClientesPresenter(this, this.applicationContext)
        lifecycleScope.launch {
            inicializarRecycler()
        }

        //Agregar un usuario: LLeva a formulario de registro
        binding.agregarusuario.setOnClickListener {
            var intent= Intent(this, RegistrarClienteActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.buscarCliente.clearFocus()
        binding.buscarCliente.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch(Dispatchers.IO){
                    if (newText != null) {
                        presenter.consultarClientesPorNombre(newText)
                    }
                }
                return true
            }
        })

    }
    suspend fun inicializarRecycler(){
        binding.sRviewClientes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        presenter.consultarClientes()
        adapter= ClienteAdapter(listCliente,this)
        binding.sRviewClientes.adapter= adapter
    }

    override suspend fun mostrarClientes(list: List<Cliente>) {
        listCliente= list
        if(listCliente.isEmpty()){
            System.out.println("La lista esta vacia");

        }else{
            System.out.println("La lista nooo esta vacia");
            System.out.println(listCliente.get(0).nombre)
        }

    }

    override suspend fun mostrarClientesPorNombre(list: List<Cliente>) {
        adapter= ClienteAdapter(list,this)
        adapter.searchcliente(list)
        //binding.sRviewClientes.adapter= adapter
    }

    override fun itemEdit(cliente: Cliente) {
        clienteSelect= cliente
        var intent= Intent(this,VerClienteActivity::class.java)
        intent.putExtra("id", cliente.id_cliente)
        startActivity(intent)
    }
}