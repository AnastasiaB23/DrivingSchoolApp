package com.example.drivingschoolapp.ROOM

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insert(user: UserEntity)
    suspend fun update(user: UserEntity)

    suspend fun getCurrentUser(): Flow<List<UserEntity>>

}