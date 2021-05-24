package com.gustavoraposo.roomexample.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gustavoraposo.roomexample.data.repository.UserRepository
import com.gustavoraposo.roomexample.ui.signup.RegistrationViewParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val registrationViewParams = RegistrationViewParams()
    private var _login: LiveData<Long>? = null

    class LoginViewModelFactory(private val userRepository: UserRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(userRepository) as T
        }
    }

    fun signIn(username: String, password: String): LiveData<Long>?{
        _login = userRepository.login(username, password)
        return _login
    }
}