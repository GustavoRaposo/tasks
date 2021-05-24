package com.gustavoraposo.roomexample.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gustavoraposo.roomexample.data.repository.UserRepository
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val registrationViewParams = RegistrationViewParams()

    class SignUpViewModeFactory(private val userRepository: UserRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignUpViewModel(userRepository) as T
        }
    }

    fun signUp(username: String, email: String, password: String) {
        if (isValidUserCredentials(username, email, password)) {
            viewModelScope.launch {
                registrationViewParams.username = username
                registrationViewParams.email = email
                registrationViewParams.password = password
                userRepository.createUser(registrationViewParams)
            }
        }
    }

    private fun isValidUserCredentials(username: String, email: String, password: String): Boolean {
        val usernameRegex = Pattern.matches("[a-zA-Z0-9]{1,30}", username)
        val passwordRegex = Pattern.matches("[a-zA-Z0-9]{5,10}", password)
        return usernameRegex && passwordRegex
    }


}