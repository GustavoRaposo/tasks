package com.gustavoraposo.roomexample.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gustavoraposo.roomexample.R
import com.gustavoraposo.roomexample.data.database.AppDatabase
import com.gustavoraposo.roomexample.data.repository.UserDataSource
import com.gustavoraposo.roomexample.databinding.SignUpFragmentBinding

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private val viewModel: SignUpViewModel by activityViewModels(
        factoryProducer = {
            SignUpViewModel.SignUpViewModeFactory(
                userRepository = UserDataSource(AppDatabase.invoke(requireContext()).userDao())
            )
        }
    )
    private var _bindding: SignUpFragmentBinding? = null
    private val binding get() = _bindding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindding = SignUpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.buttonSignUpConfirm.setOnClickListener {
            val username = binding.editTextSignUpUsername.text.toString().trim()
            val email = binding.editTextSignUpEmail.text.toString().trim()
            val password = binding.editTextSignUpPassword.text.toString().trim()
            val confirmPassword = binding.editTextSignUpConfirmPassword.text.toString().trim()

            if (username.isNotEmpty() && email.isNotEmpty() and password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    viewModel.signUp(username, email, password)
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.loginFragment)
                } else Toast.makeText(
                    requireContext(),
                    "The confirmation don't match with your password",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}