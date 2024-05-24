package com.example.vaktijapro.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vaktijapro.model.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)
    @Update
    suspend fun update(user: User)
    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): Flow<User>

    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserEmail(email: String): Flow<User>

    @Query("SELECT * FROM users ORDER BY username ASC")
    fun getUsersOrderedByUsername(): Flow<List<User>>
    @Query("SELECT * FROM users ORDER BY email ASC")
    fun getUsersOrderedByEmail(): Flow<List<User>>
}