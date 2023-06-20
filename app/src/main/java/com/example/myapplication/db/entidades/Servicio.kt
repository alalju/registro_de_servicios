package com.example.registrodeservicios.db.entidades

import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servicio")
data class Servicio(@ColumnInfo(name = "id_servicio") @PrimaryKey(autoGenerate = true) val id_servicio: Int){
    @ColumnInfo(name = "nombre") lateinit var nombre: String
    @ColumnInfo(name = "concepto_pago") lateinit var concepto_pago: String
    @ColumnInfo(name = "monto_pago") lateinit var monto_pago: String
    @ColumnInfo(name = "fecha_ini") lateinit var inicio: String
    @ColumnInfo(name = "fecha_fin") lateinit var fin: String
    @ColumnInfo(name = "id_cliente") var id_cliente: Int=0
    @ColumnInfo(name = "id_subscripcion") var id_subscripcion: Int=0
    @ColumnInfo(name = "id_estado") var id_estado: Int=0

    constructor(
        nombre: String,
        concepto: String,
        monto:String,
        inicio: String,
        fin: String,
        id_cliente: Int,
        id_subscripcion: Int,
        id_estado: Int
    ):this(0){
        this.nombre= nombre
        this.concepto_pago= concepto
        this.monto_pago= monto
        this.inicio= inicio
        this.fin= fin
        this.id_cliente=id_cliente
        this.id_subscripcion= id_subscripcion
        this.id_estado= id_estado
    }


    constructor(
        id_servicio: Int,
        nombre: String,
        concepto: String,
        monto:String,
        inicio: String,
        fin: String,
        id_cliente: Int,
        id_subscripcion: Int,
        id_estado: Int
    ):this(id_servicio){
        this.nombre= nombre
        this.concepto_pago= concepto
        this.monto_pago= monto
        this.inicio= inicio
        this.fin= fin
        this.id_cliente=id_cliente
        this.id_subscripcion= id_subscripcion
        this.id_estado= id_estado
    }


}

