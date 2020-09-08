package com.juancarlos.examentecnico.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juancarlos.examentecnico.model.dao.EmployeesDao
import com.juancarlos.examentecnico.model.entities.EmployeesData

@Database(entities = [EmployeesData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employees(): EmployeesDao
}