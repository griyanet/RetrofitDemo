package com.inspirecoding.retrofitdemo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.inspirecoding.retrofitdemo.R
import com.inspirecoding.retrofitdemo.databinding.FragmentLoginBinding
import com.inspirecoding.retrofitdemo.utils.Status
import com.inspirecoding.retrofitdemo.viewmodel.RepositoryViewModel
import androidx.lifecycle.observe

class LoginFragment : Fragment()
{
    private lateinit var binding: FragmentLoginBinding
    private val repositoryViewModel by navGraphViewModels<RepositoryViewModel>(R.id.navigation_graph)

    var email = ""
    var password = ""

    override fun onStart()
    {
        super.onStart()

        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRegisterNow.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            if(validateFields())
            {
                repositoryViewModel.getUser(email).observe(viewLifecycleOwner) {_resource ->
                    when (_resource.status)
                    {
                        Status.SUCCESS -> {
                            setProgressBarVisibility(View.GONE)

                            repositoryViewModel.setCurrentlyLoggedInUser(_resource.data)

                            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                        }
                        Status.ERROR -> {
                            setProgressBarVisibility(View.GONE)
                            repositoryViewModel.sendToastMessage(_resource.message)
                        }
                        Status.LOADING -> {
                            setProgressBarVisibility(View.VISIBLE)
                        }
                    }
                }
            }
        }
    }


    private fun setProgressBarVisibility(visible: Int)
    {
        binding.progressBar.visibility = visible
    }

    private fun validateFields(): Boolean
    {
        var isValid = true

        email = binding.tietLoginEmail.text.toString().trim()
        password = binding.tietLoginPassword.text.toString().trim()


        /** Check the format of email **/
        if (email.isEmpty() || !email.contains("@") || !email.contains("."))
        {
            binding.tietLoginEmail.error = getString(R.string.email_should_be)
            isValid = false
        }
        else
        {
            binding.tietLoginEmail.error = null
        }

        /** Check the password **/
        if (password.isEmpty())
        {
            binding.tietLoginPassword.error = getString(R.string.password_is_empty)
            isValid = false
        }
        else
        {
            binding.tietLoginPassword.error = null
        }

        return isValid
    }
}
