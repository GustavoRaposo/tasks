package com.gustavoraposo.roomexample.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gustavoraposo.roomexample.data.model.User
import com.gustavoraposo.roomexample.ui.signup.RegistrationViewParams

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String,
    val email: String,
    val password: String
)

fun RegistrationViewParams.toUserEntity() : UserEntity{
    return with(this){
        UserEntity(
            username = this.username,
            email = this.email,
            password = this.password
        )
    }
}

fun UserEntity.toUser():User{
    return User(
        id = this.id,
        username = this.username
    )
}