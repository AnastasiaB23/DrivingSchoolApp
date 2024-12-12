package com.example.drivingschoolapp.ROOM

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val dao: UserDao) : UserRepository {

    override suspend fun insert(user: UserEntity) {
        dao.insertUser(user)
    }

    override suspend fun update(user: UserEntity) {
        dao.updateUser(user)
    }

    override suspend fun getCurrentUser(): Flow<List<UserEntity>> {
        return flow {
            val user = dao.getUser()
            emit(listOf(user))
        }
    }
}
