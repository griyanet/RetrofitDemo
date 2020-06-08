package com.inspirecoding.retrofitdemo.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.observe
import com.inspirecoding.retrofitdemo.R
import com.inspirecoding.retrofitdemo.adapter.UserAdapter
import com.inspirecoding.retrofitdemo.databinding.FragmentMainBinding
import com.inspirecoding.retrofitdemo.utils.Status
import com.inspirecoding.retrofitdemo.viewmodel.RepositoryViewModel

class MainFragment : Fragment()
{
    private lateinit var binding: FragmentMainBinding
    private lateinit var userAdapter: UserAdapter
    private val repositoryViewModel by navGraphViewModels<RepositoryViewModel>(R.id.navigation_graph)


    override fun onStart()
    {
        super.onStart()

        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_main,
            container,
            false)

        val toolbar = (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar)
        toolbar.navigationIcon = null

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        initRecyclerView()

        repositoryViewModel.getAllUsers().observe(viewLifecycleOwner) {_resource ->
            when (_resource.status)
            {
                Status.SUCCESS -> {
                    setProgressBarVisibility(View.GONE)
                    val listOfUser = _resource.data
                    listOfUser?.let { _listOfUser ->
                        userAdapter.addAllUser(_listOfUser)
                    }
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

    private fun setProgressBarVisibility(visible: Int)
    {
        binding.progressBar.visibility = visible
    }

    private fun initRecyclerView()
    {
        context?.let { _context ->
            userAdapter = UserAdapter(_context)
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(_context)
                adapter = userAdapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when(item.itemId)
        {
            R.id.item_search -> {
                findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

