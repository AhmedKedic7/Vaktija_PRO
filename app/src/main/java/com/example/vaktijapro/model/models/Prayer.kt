package com.example.vaktijapro.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="prayers",
foreignKeys = [ForeignKey(
entity = User::class,
parentColumns = ["id"],
childColumns = ["user_id"],
onDelete = ForeignKey.CASCADE
)]
)
data class Prayer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "prayer_id")
    val prayerId: Int = 0,

    @ColumnInfo(name = "prayer")
    var prayer: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long,

    @ColumnInfo(name = "user_id")
    val userId: Int



)