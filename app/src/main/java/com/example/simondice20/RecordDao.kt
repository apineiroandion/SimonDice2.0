package com.example.simondice20

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordDao {
    @Insert
    suspend fun insert(record: Record)

    @Query("SELECT * FROM record_table ORDER BY value DESC LIMIT 1") // se obtiene el record más alto
    suspend fun getRecord(): Record?
}