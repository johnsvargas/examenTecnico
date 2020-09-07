package com.juancarlos.examentecnico.viewmodel

import androidx.lifecycle.ViewModel
import com.juancarlos.examentecnico.model.reposotories.EmployeesRepository

class MenuViewModel:ViewModel() {
    private val employeesRepository = EmployeesRepository()

    fun getUrlEmployees() = employeesRepository.urlEmployeesJson

    fun callEmployees(){
        employeesRepository.callEmployees()
    }
}