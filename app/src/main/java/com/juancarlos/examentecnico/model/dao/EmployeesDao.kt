package com.juancarlos.examentecnico.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.juancarlos.examentecnico.model.entities.EmployeesData

@Dao
interface EmployeesDao {

    @Query("SELECT * FROM employees")
    fun getAll(): List<EmployeesData>

    @Query("SELECT * FROM employees WHERE id_employees IN (:userIds)")
    fun loadAllByIds(userIds: Array<String>): List<EmployeesData>


    @Insert
    fun insertAll(vararg employees: EmployeesData)

    @Delete
    fun delete(employees: EmployeesData)

}

