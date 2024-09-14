package com.example.psysupport.mainActivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psysupport.domain.Constants
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch

class MainViewModel():ViewModel() {
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
                Log.d("Auth", "User Id: ${Constants.supabaseClient.auth.currentUserOrNull()!!.id}")
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
                Log.d("Auth", "User: ${Constants.supabaseClient.auth.currentUserOrNull()!!.email}")
                Log.d("Auth", "User Id: ${Constants.supabaseClient.auth.currentUserOrNull()!!.id}")
            }catch(e: Exception){
                Log.e("Auth", e.message.toString())
            }
        }
    }
}