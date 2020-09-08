package com.juancarlos.examentecnico.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juancarlos.examentecnico.model.reposotories.EmployeesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel:ViewModel() {
    private val employeesRepository = EmployeesRepository()

    fun getUrlEmployees() = employeesRepository.urlEmployeesJson

    fun callEmployees(){
        viewModelScope.launch(Dispatchers.IO) {
            employeesRepository.callEmployees()
        }
    }
}