package com.gerdev.com.repositorio

import com.gerdev.com.model.Empleado
import com.gerdev.com.util_conect_db.ConnectionDB
import java.sql.*
import kotlin.jvm.Throws

class RepositorioEmpleadoImpl : IRepositorioEmpleado<Empleado> {

    private val connectDB: Connection?
        get() {
            return ConnectionDB.crearConexione() //Realiza la conexion de la DB
        }

    private var stm: Statement? = null
    private var result: ResultSet? = null

    @Throws(SQLException::class)
    override fun listadoEmpleados(): MutableList<Empleado>? {
        val listadoEmpleado = mutableListOf<Empleado>()
        return try {
            this.stm = this.connectDB?.createStatement()
            this.result = this.stm?.executeQuery("SELECT * FROM  empleados")
            while (this.result?.next() == true) {
                valoresResultEmpleado(
                    Empleado()
                ).let { listadoEmpleado.add(it) } //Ayuda a realizar la iteracion con agregar a la lista
            }
            listadoEmpleado
        } catch (ex: SQLException) {
            ex.printStackTrace()
            println("\n ${ex.message}")
            null
        } finally {
            cierreComando()
        }
    }

    override fun buscarEmpleado(id: Long): Empleado? {
        return try {
            val prepareStat: PreparedStatement? =
                this.connectDB?.prepareStatement("SELECT * FROM  empleados WHERE id_empleado = ?")?.apply {
                    setLong(1, id)
                }
            if (prepareStat != null) {
                this.result = prepareStat.executeQuery()
            }
            if (this.result?.next() == true) {
                valoresResultEmpleado(Empleado())
            } else {
                null
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
            println(ex.message)
            null
        } finally {
            cierreComando()
        }
    }

    override fun guardar(t: Empleado?): Boolean {
        TODO("Not yet implemented")
    }

    override fun eliminaEmpleado(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    private fun cierreComando() {
        this.stm?.close()
        this.result?.close()
    }

    private fun valoresResultEmpleado(empleado: Empleado): Empleado {
        /*
        Este metodo realiza el llenado del obj empleado con los valores del resultSet, para que se mas dinamico
         */
        empleado.apellido = this.result?.getString("apellido_cliente")
        empleado.nombre = this.result?.getString("nombre_cliente")
        empleado.id = this.result?.getLong("id_empleado")
        empleado.correo = this.result?.getString("correo_cliente")
        return empleado
    }

}