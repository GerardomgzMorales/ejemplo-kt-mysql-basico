package com.gerdev.com.repositorio

import com.gerdev.com.model.AreaTrabajo
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
            this.result =
                this.stm?.executeQuery("SELECT em.*, ar.id_area_trabajo as idTrabajo, ar.informacion_area as infoArea, ar.correo_area as correo, ar.fecha_registro as fecha FROM  empleados as em inner join area_trabajo as ar on em.id_area_trabajo = ar.id_area_trabajo")

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
            this.result =
                this.connectDB?.prepareStatement("SELECT em.*, ar.id_area_trabajo as idTrabajo, ar.informacion_area as infoArea, ar.correo_area as correo, ar.fecha_registro as fecha FROM  empleados as em inner join area_trabajo as ar on em.id_area_trabajo = ar.id_area_trabajo WHERE em.id_empleado = ?")
                    ?.apply {
                        setLong(1, id)
                    }?.executeQuery()
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
        val insertSQLEmpleado: String
        return try {
            if (t != null) {
                insertSQLEmpleado = if (t.id != null) {
                    "UPDATE empleados\n" + "SET apellido_cliente = ?,  correo_cliente = ?, nombre_cliente = ? \n" + "WHERE id_empleado = ? "
                } else {
                    "INSERT INTO empleados\n" + "(apellido_cliente, correo_cliente, nombre_cliente)\n" + "VALUES(?, ?, ?)\n"
                }
                connectDB?.prepareStatement(insertSQLEmpleado)?.apply {
                    setString(1, t.apellido)
                    setString(2, t.correo)
                    setString(3, t.nombre)
                    if (t.id != null) {
                        setLong(4, t.id!!)
                    }
                }?.executeUpdate()!! > 0
            } else {
                false
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
            false
        } finally {
            cierreComando()
        }
    }

    override fun eliminaEmpleado(id: Long): Boolean {
        val sqlDeleteEmpleado = "DELETE FROM empleados\n" + "WHERE id_empleado = ? \n"
        return try {
            connectDB?.prepareStatement(sqlDeleteEmpleado)?.apply {
                setLong(1, id)
                executeUpdate()
            }
            true
        } catch (ex: SQLException) {
            ex.printStackTrace()
            println(ex.message)
            false
        } finally {
            cierreComando()
        }
    }

    private fun cierreComando() {
        this.stm?.close()
        this.result?.close()
    }

    private fun valoresResultEmpleado(empleado: Empleado): Empleado {
        /*
        Este metodo realiza el llenado del obj empleado con los valores del resultSet, para que se mas dinamico
         */

        empleado.apellido = this.result?.getString("apelldios_empleado")
        empleado.nombre = this.result?.getString("nombre_empleado")
        empleado.id = this.result?.getLong("id_empleado")
        empleado.correo = this.result?.getString("correo_empleado")
        empleado.fechaRegistro = this.result?.getDate("fecha_registro")
        //elementos para el
        val areaTrabajo = AreaTrabajo()
        areaTrabajo.id = this.result?.getLong("idTrabajo")
        areaTrabajo.informationArea = this.result?.getString("infoArea")
        areaTrabajo.correoArea = this.result?.getString("correo")
        areaTrabajo.fechaRegistro = this.result?.getDate("fecha")

        //carga del area en enpleado
        empleado.idAreaTrabajo = areaTrabajo
        return empleado
    }

}