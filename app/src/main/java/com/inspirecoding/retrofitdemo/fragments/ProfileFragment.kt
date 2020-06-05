package com.inspirecoding.retrofitdemo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.inspirecoding.retrofitdemo.R
import com.inspirecoding.retrofitdemo.databinding.FragmentProfileBinding

class ProfileFragment : Fragment()
{
    private lateinit var binding: FragmentProfileBinding

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
}
