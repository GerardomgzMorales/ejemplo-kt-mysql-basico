package com.gerdev.com.main

import com.gerdev.com.model.Empleo
import com.gerdev.com.util_conect_db.ConnectionDB
import java.sql.*

class EjemploJDBC {
    companion object {
        @JvmStatic
        fun connexionBDMaria() {
            var conn: Connection? = null
            var stmt: Statement? = null
            var resultado: ResultSet? = null
            try {
                conn = ConnectionDB.crearConexione()
                stmt = conn?.createStatement()
                println("\nHola hay una connexion con exito\n")
                resultado = stmt?.executeQuery("SELECT * FROM  empleados")
                while (resultado?.next() == true) {
                    val empleo: Empleo = Empleo()

                    empleo.correo = resultado.getString(
                        "correo_cliente"
                    )
                    empleo.id = resultado.getLong("id_empleado")

                    empleo.nombre = resultado.getString(
                        "nombre_cliente"
                    )

                    empleo.apellido = resultado.getString("apellido_cliente")

                    println("$empleo")


                }
            } catch (ex: SQLException) {
                ex.printStackTrace()
                println(ex.message)
            } finally {
                resultado?.close()
                conn?.close()
                stmt?.close()
            }
        }
    }
}

fun main() {
    EjemploJDBC.connexionBDMaria()
}