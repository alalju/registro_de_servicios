package com.example.myapplication.db.entidades

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Servicio

data class ServicioCliente (@ColumnInfo(name = "id_servicio") val id_servicio: Int,
@ColumnInfo(name = "nombre")  var nombre: String,
@ColumnInfo(name = "concepto_pago")  var concepto_pago: String,
@ColumnInfo(name = "monto_pago")  var monto_pago: String,
@ColumnInfo(name = "fecha_ini")  var inicio: String,
@ColumnInfo(name = "fecha_fin")  var fin: String,
@ColumnInfo(name = "id_cliente") var id_cliente: Int=0,
@ColumnInfo(name = "id_subscripcion") var id_subscripcion: Int=0,
@ColumnInfo(name = "id_estado") var id_estado: Int=0,
@ColumnInfo(name = "nombre_cliente")  var nombre_cliente: String
)