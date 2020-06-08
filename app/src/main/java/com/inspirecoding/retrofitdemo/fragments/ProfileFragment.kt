package com.inspirecoding.retrofitdemo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.navGraphViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.inspirecoding.retrofitdemo.R
import com.inspirecoding.retrofitdemo.databinding.FragmentProfileBinding
import com.inspirecoding.retrofitdemo.viewmodel.RepositoryViewModel

class ProfileFragment : Fragment()
{
    private lateinit var binding: FragmentProfileBinding
    private val repositoryViewModel by navGraphViewModels<RepositoryViewModel>(R.id.navigation_graph)

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_profile,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        repositoryViewModel.currentUser.observe(viewLifecycleOwner) { _user ->
            binding.tvName.text = _user.name
        }
        binding.btnLogOut.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }
}
