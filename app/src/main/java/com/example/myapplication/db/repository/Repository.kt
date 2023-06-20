package com.example.registrodeservicios.db.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.db.entidades.ServicioCliente
import com.example.registrodeservicios.db.app.AppDB
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val DATABASE_NAME = "servicios.db"
class Repository private constructor(context: Context){

    private val dbRoom: AppDB = Room.databaseBuilder(
        context.applicationContext,
        AppDB:: class.java,
        DATABASE_NAME
    ).addCallback(prepopulateCallbackEstado(context))
        .addCallback(prepopulateCallbackSubscripcion(context))
        .build()
    private val clienteDao = dbRoom.clienteDao()
    private val servicioDao = dbRoom.servicioDao()
    private val estadoDao= dbRoom.estadoDao()
    private val subscropcionDao= dbRoom.subscripcionDao()

    //Operaciones para el Cliente
    suspend fun insertAll(cliente: Cliente) = clienteDao.insertAll(cliente = cliente)
    suspend fun consultarClientes(): List<Cliente> = clienteDao.consultarClientes()
    suspend fun consultarClienteById(id: Int): Cliente= clienteDao.consultarClientebyId(id=id)
    fun actualizarCliente(cliente: Cliente) = clienteDao.updateCliente(cliente)
    suspend fun eliminarCliente(cliente: Cliente) = clienteDao.delete(cliente)
    suspend fun eliminrServicioPorIdCliente(id: Int)= clienteDao.eliminarServiciosPorIdCliente(idCliente = id)
    fun consultarClientePorNombre(nombre: String): List<Cliente> = clienteDao.buscarPorNombre(nombre= nombre)

    //Operaciones Servicio
    suspend fun insertarAll(servicio: Servicio)= servicioDao.insertAll(servicio= servicio)
    suspend fun consultarServicios(): List<Servicio> =servicioDao.consultarServicio()
    suspend fun consultarServicioById(id: Int): Servicio = servicioDao.consultarClientebyId(id= id)
    fun actualizarServicio(servicio: Servicio) = servicioDao.updateServicio(servicio)
    suspend fun eliminarServicio(servicio: Servicio) = servicioDao.delete(servicio)
    suspend fun consultarServicioCliente(): LiveData<List<ServicioCliente>> = servicioDao.getServiciosConNombreCliente()


    //Operaciones Estado
    suspend fun consultarEstados():List<Estado> = estadoDao.consultarEstados()
    suspend fun consultarEstadoById(id: Int):Estado = estadoDao.consultarEstadobyId(id= id)

    //Operaciones Subscripcion
    suspend fun consultarSubs():List<Subscripcion> = subscropcionDao.consultarSubs()
    suspend fun consultarSubsById(id: Int): Subscripcion = subscropcionDao.consultarSubscripcionbyId(id= id)

    companion object{
        private var INSTANCE: Repository? = null

        fun inicializar(context: Context){
            if(INSTANCE == null){
                INSTANCE = Repository(context)
            }
        }

        fun get(): Repository{
            return INSTANCE ?: throw IllegalStateException("UserRepository debe ser inicializado")
        }
    }

    private fun prepopulateCallbackEstado(context: Context): RoomDatabase.Callback {
        return object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        insetarEstados(database, context)
                    }
                }
            }
        }
    }
    private fun prepopulateCallbackSubscripcion(context: Context): RoomDatabase.Callback {
        return object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        insetarSubs(database, context)
                    }
                }
            }
        }
    }

    private suspend fun insetarEstados(database: Repository, context: Context) {
        val subsList = listOf(
            Estado(1, "Pagado"),
            Estado(2, "Pendiente")
        )
        database.estadoDao.insertAll(subsList)
    }
    private suspend fun insetarSubs(database: Repository, context: Context) {
        val subsList = listOf(
            Subscripcion(1, "Dia"),
            Subscripcion(2, "Semana"),
            Subscripcion(3, "Mes")
        )
        database.subscropcionDao.insertAll(subsList)
    }
}