package com.example.myapplication.view.Servicio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adpaters.ClienteAdapter
import com.example.myapplication.Adpaters.ServicioAdapter
import com.example.myapplication.databinding.ActivityServicioBinding
import com.example.myapplication.db.entidades.ServicioCliente
import com.example.myapplication.interfaces.view.Serviciosview
import com.example.myapplication.models.Presenters.ServiciosPresenter
import com.example.registrodeservicios.db.entidades.Servicio
import kotlinx.coroutines.launch

class ServicioActivity : AppCompatActivity(), Serviciosview, ServicioAdapter.ItemSelect {
    private lateinit var binding: ActivityServicioBinding
    private lateinit var presenter: ServiciosPresenter
    private lateinit var adapter: ServicioAdapter
    private lateinit var servicioSelect: Servicio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter= ServiciosPresenter(this, this.applicationContext)
        lifecycleScope.launch {
            presenter.cosultarServicio()
        }

        binding.agregarservicio.setOnClickListener{
            val intent = Intent(this, RegistrarServicioActivity::class.java)
            startActivity(intent)

        }
    }

    private fun iniciarRecycler(list: List<Servicio>){
        binding.sRviewServicios.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        adapter= ServicioAdapter(list,this)
        binding.sRviewServicios.adapter= adapter
    }

    override fun mostrarServicio(list: List<Servicio>) {
        iniciarRecycler(list)

    }

    override fun selectCliente(servicio: Servicio) {
        servicioSelect= servicio
        var intent= Intent(this, VerServicioActivity::class.java)
        intent.putExtra("id_cliente",servicio.id_cliente)
        System.out.println("el id cliente a pasar es: "+ servicio.id_cliente)
        intent.putExtra("id_servicio",servicio.id_servicio)
        System.out.println("el id servicio a pasar es: "+ servicio.id_servicio)
        startActivity(intent)
    }

}