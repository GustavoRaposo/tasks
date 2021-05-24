package com.gustavoraposo.roomexample.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
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
            viewModel.signUp(
                binding.editTextSignUpUsername.text.toString(),
                binding.editTextSignUpEmail.text.toString(),
                binding.editTextSignUpPassword.text.toString()
            )
        }
    }

}