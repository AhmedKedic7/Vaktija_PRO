package com.example.vaktijapro.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="ayats")
data class Ayat(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name="ayat_id")
    val ayat_id:Int=0,

    @ColumnInfo(name="ayat_info")
    val ayat_info: String,

    @ColumnInfo(name="ayat")
    val ayat: String
)


