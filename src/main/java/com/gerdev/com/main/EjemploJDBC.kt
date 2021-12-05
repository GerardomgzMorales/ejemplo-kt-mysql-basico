package com.gerdev.com.main

import com.gerdev.com.model.AreaTrabajo
import com.gerdev.com.model.Empleado
import com.gerdev.com.repositorio.IRepositorioEmpleado
import com.gerdev.com.repositorio.RepositorioEmpleadoImpl
import java.util.*

class EjemploJDBC {
    companion object {
        @JvmStatic
        fun connexionBDMaria() {
            val repoDAO: IRepositorioEmpleado<Empleado> = RepositorioEmpleadoImpl()

            //  repoDAO.listadoEmpleados()?.forEach { p -> println("\n$p") }
            val empleado = repoDAO.buscarEmpleado(5)
            println("\n${empleado ?: "No hay usuario en esta BD con el id ingresado"}")

            /* empleado?.nombre = "Ejemplo insert"
             empleado?.apellido = "Valores insert"
             empleado?.fechaRegistro = Date()
             empleado?.id = null
             empleado?.correo = "Insert@gmail.com"
             empleado?.idAreaTrabajo = AreaTrabajo(2, null, null, null)*/

            //println("\nSe inserto o update: ${repoDAO.guardar(empleado)}")
            println(repoDAO.eliminaEmpleado(5))


        }
    }
}

fun main() {
    EjemploJDBC.connexionBDMaria()
}