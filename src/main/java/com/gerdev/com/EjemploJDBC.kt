package com.gerdev.com

import java.sql.*

class EjemploJDBC {

    companion object {

        private const val url = "jdbc:mariadb://localhost:3306/db_spring"
        private const val userDB = "root"
        private const val passwordDB = ""

        @JvmStatic
        fun connexionBDMaria() {
            var conn: Connection? = null
            var stmt: Statement? = null
            var resultado: ResultSet? = null
            try {
                conn = DriverManager.getConnection(url, userDB, passwordDB)
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
                resultado?.close();
                conn?.close()
                stmt?.close()
            }
        }
    }


}

fun main() {

    EjemploJDBC.connexionBDMaria()

}