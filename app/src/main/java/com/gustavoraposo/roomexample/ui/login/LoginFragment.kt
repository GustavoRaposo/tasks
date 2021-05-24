package com.gustavoraposo.roomexample.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.gustavoraposo.roomexample.R
import com.gustavoraposo.roomexample.data.database.AppDatabase
import com.gustavoraposo.roomexample.data.repository.UserDataSource
import com.gustavoraposo.roomexample.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by activityViewModels(
        factoryProducer = {
            LoginViewModel.LoginViewModelFactory(
                userRepository = UserDataSource(AppDatabase.invoke(requireContext()).userDao())
            )
        }
    )
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.buttonLoginSignIn.setOnClickListener {
            val username = binding.editTextLoginUsername.text.toString().trim()
            val password = binding.editTextLoginPassword.text.toString().trim()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signIn(username, password)?.observe(viewLifecycleOwner, Observer {
                    if(it == null){
                        Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_LONG).show()
                    }else findNavController().navigate(R.id.tasksFragment)
                })
            }
        }
        binding.buttonLoginSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }

}