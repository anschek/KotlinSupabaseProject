package com.example.psysupport.presentation.screens.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psysupport.domain.Constants
import com.example.psysupport.model.User
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class AuthViewModel():ViewModel() {
    private val _curUser = mutableStateOf<User?>(null)
    val curUser: State<User?> = _curUser
    private var success = true
    //авторизация
    suspend fun onSignInWithEmailPassword(userEmail: String, userPassword: String): Boolean{
            try{//Обращение к объекту констнат и авторизация с помощью почты
                val user = Constants.supabaseClient.auth.signInWith(Email){
                    email = userEmail
                    password = userPassword
                }//вывод отладочных логов d-debug
                Log.d("Auth", "User: ${user.toString()}")

                val curUserInfo = Constants.supabaseClient.auth.currentUserOrNull()
                _curUser.value = Constants.supabaseClient.from("Users").select() {
                    filter {
                        User::id eq curUserInfo!!.id
                    }
                }.decodeSingle<User>()

                Log.d("Auth", "User Id: ${_curUser.value!!.id}")
            }catch(e: Exception){
                //вывод логов ошибки e-error
                Log.e("Auth", e.message.toString())
                success = false
            }
        Log.d("Message", success.toString())
        return success
    }
    //регистрация (идентична авторизации)
    suspend fun onSignUpWithEmailPassword(userEmail: String, userPassword: String): Boolean{
            try{
                val user = Constants.supabaseClient.auth.signInWith(Email){
                    email = userEmail
                    password = userPassword
                }
                Log.d("Auth", "User: ${user.toString()}")

                val curUserInfo = Constants.supabaseClient.auth.currentUserOrNull()
                _curUser.value = Constants.supabaseClient.from("Users").select() {
                    filter {
                        User::id eq curUserInfo!!.id
                    }
                }.decodeSingle<User>()

                Log.d("Auth", "User Id: ${_curUser.value!!.id}")
            }catch(e: Exception){
                Log.e("Auth", e.message.toString())
                success = false
            }
        return success
    }

}