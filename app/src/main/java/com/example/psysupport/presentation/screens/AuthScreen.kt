package com.example.psysupport.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.psysupport.model.User
import com.example.psysupport.presentation.screens.viewmodels.AuthViewModel
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(navController: NavController, currentUser: MutableState<User?>) {
    //соханяет состояния vm
    val vm: AuthViewModel = viewModel()
    val email = remember { mutableStateOf("")}
    val password = remember { mutableStateOf("")}
    currentUser.value = vm.curUser.value
    val success = remember { mutableStateOf<Boolean?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.padding(top=340.dp)
        )
    //использование запуска корутин для возможности возврата результатов из асинхронных функций vm
    val corountineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .height(430.dp)
            .padding(vertical = 80.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Авторизация", fontSize = 25.sp)
        TextField(value = email.value,
            onValueChange = {newText -> email.value=newText},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("example@mail.com")}
        )
        TextField(value = password.value,
            onValueChange = {newText -> password.value=newText},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("password")},
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = {
            corountineScope.launch {
                success.value = vm.onSignInWithEmailPassword(email.value, password.value)
                if(success.value == false){
                    snackbarHostState.showSnackbar(message = "Ошибка авторизации", duration = SnackbarDuration.Short)
                }
            }
        },  modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)) {
            Text("Вход")
        }
        Button(onClick = {
            corountineScope.launch {
                success.value = vm.onSignUpWithEmailPassword(email.value, password.value)
                if(success.value == false){
                    snackbarHostState.showSnackbar(message = "Ошибка регистрации", duration = SnackbarDuration.Short)
                }
            }
        },  modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)) {
            Text("Регистрация")
        }
        if (currentUser.value != null) {
            // побочный эффект, выполнится при изменении currentUser
            LaunchedEffect(currentUser.value) {
                Log.d("Auth", "User go to HomeScreen: ${currentUser.value!!.id}")
                navController.navigate("home_screen")
            }
        }
    }
}