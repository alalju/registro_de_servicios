package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.view.Cliente.ClientesActivity
import com.example.myapplication.view.Servicio.ServicioActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        binding.botonCliente.setOnClickListener {
            val intent= Intent(this, ClientesActivity::class.java)
            startActivity(intent)
        }

        binding.botonServicios.setOnClickListener {
            val intent = Intent(this, ServicioActivity::class.java)
            startActivity(intent)
        }

    }
}