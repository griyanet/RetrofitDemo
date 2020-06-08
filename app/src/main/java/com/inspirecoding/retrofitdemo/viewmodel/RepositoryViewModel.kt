package com.inspirecoding.retrofitdemo.viewmodel

import com.inspirecoding.retrofitdemo.R
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.inspirecoding.retrofitdemo.MyApp
import com.inspirecoding.retrofitdemo.model.Resource
import com.inspirecoding.retrofitdemo.model.User
import com.inspirecoding.retrofitdemo.repository.UserRepository
import kotlinx.coroutines.Dispatchers

class RepositoryViewModel: ViewModel()
{
    private val userRepository: UserRepository = UserRepository()

    private var _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User>
        get() = _currentUser

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    fun getUser(email: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try
        {
            val user = Resource.success(data = userRepository.getUser(email))
            emit(user)
        }
        catch (exception: Exception)
        {
            emit(Resource.error(data = null, message = exception.message ?: MyApp.applicationContext().getString(
                R.string.error_occurred)))
        }
    }

    fun registerUser(email: String, name: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try
        {
            emit(Resource.success(data = userRepository.registerUser(email, name, password)))
        }
        catch (exception: Exception)
        {
            emit(Resource.error(data = null, message = exception.message ?: MyApp.applicationContext().getString(
                R.string.error_occurred)))
        }
    }
    fun getAllUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try
        {
            emit(Resource.success(data = userRepository.getAllUsers()))
        }
        catch (exception: Exception)
        {
            emit(Resource.error(data = null, message = exception.message ?: MyApp.applicationContext().getString(
                R.string.error_occurred)))
        }
    }

    fun setCurrentlyLoggedInUser(user: User?)
    {
        _currentUser.value = user ?: User()
    }

    fun sendToastMessage(message: String?)
    {
        _toast.value = message
    }
}