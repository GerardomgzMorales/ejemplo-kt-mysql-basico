package com.gerdev.com.main

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
                    println(
                        "El ID: ${resultado.getLong("id_empleado")}  Nombre: ${resultado.getString("apellido_cliente")} ${
                            resultado.getString(
                                "nombre_cliente"
                            )
                        } Correo es: ${
                            resultado.getString(
                                "correo_cliente"
                            )
                        }".trim().uppercase()
                    )
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