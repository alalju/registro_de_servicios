package com.example.myapplication.view.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adpaters.SelectClienteAdapter
import com.example.myapplication.databinding.FragmentSelectClienteBinding
import com.example.myapplication.interfaces.IPresenters.ISelectClientePresente
import com.example.myapplication.interfaces.view.SelectClienteView
import com.example.myapplication.models.Presenters.SelectClientePresenter
import com.example.registrodeservicios.db.entidades.Cliente
import kotlinx.coroutines.launch

class SelectClienteFragment : DialogFragment(),SelectClienteAdapter.ItemSelect,SelectClienteView {
    private lateinit var actividad: Activity
    private lateinit var adapter: SelectClienteAdapter
    private lateinit var binding: FragmentSelectClienteBinding
    private lateinit var presernter: ISelectClientePresente
    private lateinit var listCliente:List<Cliente>
    private  var selectCliente: Cliente? = null
    private var listener :OnTextViewDataSet? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return lista()



    }

    private fun lista(): Dialog {
        binding = FragmentSelectClienteBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        presernter= SelectClientePresenter(this, requireContext())


        lifecycleScope.launch {
            presernter.consultarClientes()
        }




        return builder.create()
    }

    private fun crearRecycler(){
        binding.reciclerclientes.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        //presenter.consultarClientes()
        adapter= SelectClienteAdapter(listCliente,this)
        binding.reciclerclientes.adapter= adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            actividad = context
            if (actividad is OnTextViewDataSet) {
                listener = actividad as OnTextViewDataSet
            }
        } else {
            throw RuntimeException("$context debe implementar OnTextViewDataSet")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSelectClienteBinding.inflate(inflater, container, false)
        return binding.root
    }



    interface OnTextViewDataSet {
        fun onDataSet(cliente: Cliente)
    }

    override fun selectCliente(cliente: Cliente) {
        selectCliente= cliente
        listener?.onDataSet(cliente)
        dismiss()
    }

    override fun mostrarClientes(list: List<Cliente>) {
        listCliente= list
        crearRecycler()
    }
}