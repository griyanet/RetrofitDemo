package com.inspirecoding.retrofitdemo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.lifecycle.observe
import com.inspirecoding.retrofitdemo.utils.Status.SUCCESS
import com.inspirecoding.retrofitdemo.utils.Status.ERROR
import com.inspirecoding.retrofitdemo.utils.Status.LOADING
import com.inspirecoding.retrofitdemo.R
import com.inspirecoding.retrofitdemo.databinding.FragmentRegisterBinding
import com.inspirecoding.retrofitdemo.viewmodel.RepositoryViewModel

class RegisterFragment : Fragment()
{
    private lateinit var binding: FragmentRegisterBinding
    private val repositoryViewModel by navGraphViewModels<RepositoryViewModel>(R.id.navigation_graph)

    var username = ""
    var email = ""
    var password = ""

    override fun onStart()
    {
        super.onStart()

        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            if(validateFields())
            {
                repositoryViewModel.registerUser(email, username, password).observe(viewLifecycleOwner) { _resource ->
                    when (_resource.status)
                    {
                        SUCCESS -> {
                            setProgressBarVisibility(View.GONE)

                            repositoryViewModel.setCurrentlyLoggedInUser(_resource.data)

                            findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
                        }
                        ERROR -> {
                            setProgressBarVisibility(View.GONE)
                            repositoryViewModel.sendToastMessage(_resource.message)
                        }
                        LOADING -> {
                            setProgressBarVisibility(View.VISIBLE)
                        }
                    }
                }
            }
        }

        binding.tvAlreadyHaveAnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        repositoryViewModel.toast.observe(viewLifecycleOwner) { _message ->
            Toast.makeText(context, _message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setProgressBarVisibility(visible: Int)
    {
        binding.progressBar.visibility = visible
    }


    private fun validateFields(): Boolean
    {
        var isValid = true

        username = binding.tietRegisterUsername.text.toString().trim()
        email = binding.tietRegisterEmail.text.toString().trim()
        password = binding.tietRegisterPassword.text.toString().trim()

        /** Check the name **/
        if (username.isEmpty())
        {
            binding.tietRegisterUsername.error = getString(R.string.name_is_empty)
            isValid = false
        }
        else
        {
            binding.tietRegisterUsername.error = null
        }

        /** Check the format of email **/
        if (email.isEmpty() || !email.contains("@") || !email.contains("."))
        {
            binding.tietRegisterEmail.error = getString(R.string.email_should_be)
            isValid = false
        }
        else
        {
            binding.tietRegisterEmail.error = null
        }

        /** Check the password **/
        if (password.isEmpty())
        {
            binding.tietRegisterPassword.error = getString(R.string.password_is_empty)
            isValid = false
        }
        else
        {
            binding.tietRegisterPassword.error = null
        }

        return isValid
    }
}
