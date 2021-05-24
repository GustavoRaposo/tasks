package com.gustavoraposo.roomexample.data.repository

import com.gustavoraposo.roomexample.data.model.User
import com.gustavoraposo.roomexample.ui.signup.RegistrationViewParams

interface UserRepository {
    suspend fun createUser(registrationViewParams: RegistrationViewParams)
    fun getUser(id: Long): User
    fun login(username: String, password: String): Long
}