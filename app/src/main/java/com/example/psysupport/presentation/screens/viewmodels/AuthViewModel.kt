package com.example.psysupport.presentation.screens.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psysupport.domain.Constants
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.launch

class AuthViewModel():ViewModel() {
    private val _curUser = mutableStateOf<UserInfo?>(null)
    val curUser: State<UserInfo?> = _curUser
    //авторизация
    fun onSignInWithEmailPassword(userEmail: String, userPassword: String){
        //запуск асинхрона в жц вм
        viewModelScope.launch {
            try{//Обращение к объекту констнат и авторизация с помощью почты
                val user = Constants.supabaseClient.auth.signInWith(Email){
                    email = userEmail
                    password = userPassword
                }//вывод отладочных логов d-debug
                Log.d("Auth", "User: ${user.toString()}")
                _curUser.value = Constants.supabaseClient.auth.currentUserOrNull()
                Log.d("Auth", "User Id: ${_curUser.value!!.id}")
                Log.d("Auth", "cur User not null? : ${curUser.value != null}")
            }catch(e: Exception){
                //вывод логов ошибки e-error
                Log.e("Auth", e.message.toString())
            }
        }
    }
    //регистрация (идентична авторизации)
    fun onSignUpWithEmailPassword(userEmail: String, userPassword: String){
        viewModelScope.launch {
            try{
                val user = Constants.supabaseClient.auth.signInWith(Email){
                    email = userEmail
                    password = userPassword
                }
                Log.d("Auth", "User: ${user.toString()}")
                _curUser.value = Constants.supabaseClient.auth.currentUserOrNull()
                Log.d("Auth", "User Id: ${_curUser.value!!.id}")
            }catch(e: Exception){
                Log.e("Auth", e.message.toString())
            }
        }
    }
}