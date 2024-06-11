package com.example.asm2_ph43396_kot.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asm2_ph43396_kot.api_service.Api_service
import com.example.asm2_ph43396_kot.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class User_Viewmodel: ViewModel() {
    private val _api_service = Api_service
    val _users: MutableState<List<User>> = mutableStateOf(emptyList())
    var isRegistered = mutableStateOf(false)
    fun register(user: User){
        viewModelScope.launch {
            try {
                val response = _api_service.REGISTER(user)
                if (response.isSuccessful) {
                    Log.e("user", "User registered successfully")
                } else {
                    Log.e("user", "Error registering user: ${response.message()}")
                }
                Log.e("user", "User creation successful (handle response if needed)")
            } catch (e: Exception) {
                Log.e("user", "Error creating user: ${e.message}")
            }
        }
    }
    fun get_users() {
        viewModelScope.launch {
            try {
                val response = _api_service.GET_USER()
                _users.value = response
                Log.e("user", "success")
            }catch (e: Exception){
                Log.e("user",e.message.toString())
            }
        }
    }
    private val _isAuthenticated = MutableLiveData<Boolean?>()
    val isAuthenticated: LiveData<Boolean?> = _isAuthenticated
    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            Log.e("user", "Email or password is empty")
        } else {
            val user = _users.value.find { it.email == email && it.password == password }
            if (user != null) {
                _isAuthenticated.value = true
            } else {
                _isAuthenticated.value = false
            }
        }
    }
    fun resetAuthenticationState() {
        _isAuthenticated.value = null
    }
}