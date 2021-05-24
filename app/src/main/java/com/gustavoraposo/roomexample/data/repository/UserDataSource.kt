package com.gustavoraposo.roomexample.data.repository

import com.gustavoraposo.roomexample.data.database.dao.UserDao
import com.gustavoraposo.roomexample.data.database.toUser
import com.gustavoraposo.roomexample.data.database.toUserEntity
import com.gustavoraposo.roomexample.data.model.User
import com.gustavoraposo.roomexample.ui.signup.RegistrationViewParams

class UserDataSource(private val userDao: UserDao): UserRepository{
    override suspend fun createUser(registrationViewParams: RegistrationViewParams) {
        userDao.create(registrationViewParams.toUserEntity())
    }

    override fun getUser(id: Long): User {
        return userDao.getUser(id).toUser()
    }

    override fun login(username: String, password: String): Long {
        return userDao.login(username, password)
    }
}