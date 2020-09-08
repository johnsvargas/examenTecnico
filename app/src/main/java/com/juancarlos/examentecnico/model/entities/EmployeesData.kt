package com.juancarlos.examentecnico.model.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class EmployeesData (
    @PrimaryKey @ColumnInfo(name = "id_employees") val id:String,
    val name:String,
    val location: Locale,
    val mail: String)