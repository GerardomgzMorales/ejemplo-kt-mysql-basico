package com.gerdev.com.main

import com.gerdev.com.model.Empleado
import com.gerdev.com.repositorio.IRepositorioEmpleado
import com.gerdev.com.repositorio.RepositorioEmpleadoImpl

class EjemploJDBC {
    companion object {
        @JvmStatic
        fun connexionBDMaria() {
            val repoDAO: IRepositorioEmpleado<Empleado> = RepositorioEmpleadoImpl()

            repoDAO.listadoEmpleados()?.forEach { p -> println("\n$p") }
            println("\n${repoDAO.buscarEmpleado(2) ?: "No hay usuario en esta BD con el id ingresado"}")

            //println("\nSe inserto o update: ${repoDAO.guardar(Empleado(null, "Jose Chato", " Zacatecas", "85858@gmail.com"))}")
            //println(repoDAO.eliminaEmpleado(2))


        }
    }
}

fun main() {
    EjemploJDBC.connexionBDMaria()
}