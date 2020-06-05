package com.inspirecoding.retrofitdemo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.inspirecoding.retrofitdemo.R
import com.inspirecoding.retrofitdemo.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment()
{
    private lateinit var binding: FragmentRegisterBinding

    override fun onStart()
    {
        super.onStart()

        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_register,
            container,
            false)
        return binding.root
    }
}
