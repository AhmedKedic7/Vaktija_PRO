package com.example.vaktijapro.model.repositories

import com.example.vaktijapro.model.daos.UserDao
import com.example.vaktijapro.model.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao): BaseRepository<User> {
    override suspend fun insert(t: User) = userDao.insert(t)
    override suspend fun update(t: User) = userDao.update(t)
    override suspend fun delete(t: User) = userDao.delete(t)
    fun getUsersOrderedByUsername(): Flow<List<User>> = userDao.getUsersOrderedByUsername()
    fun getUsersOrderedByEmail(): Flow<List<User>> = userDao.getUsersOrderedByUsername()
}
