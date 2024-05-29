package com.example.vaktijapro.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="prayers")
data class Prayer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "prayer_id")
    val prayerId: Int = 0,

    @ColumnInfo(name = "prayer")
    var prayer: String,



)