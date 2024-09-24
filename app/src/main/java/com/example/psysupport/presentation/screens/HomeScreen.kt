package com.example.psysupport.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.psysupport.model.User
import io.github.jan.supabase.gotrue.user.UserInfo

@Composable
//@Preview(showBackground = true)
fun HomeScreen(navController: NavController, currentUser: MutableState<User?>) {
//    val navController = rememberNavController()
//    val currentUser = remember {mutableStateOf<User?>(null)}
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Главная", fontSize = 25.sp)
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(start=10.dp),
            horizontalAlignment = Alignment.Start){
            Text("Имя: " + currentUser.value!!.firstname + " " + currentUser.value!!.secondname)
            Text("Дата рождения: " + currentUser.value!!.birthDate)
            Text("Роль в системе: " + currentUser.value!!.role)
        }
        Column(
            modifier = Modifier.height(160.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { navController.navigate("create_new_note") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)) {
                Text("Заметка за день >")
            }
            Button(
                onClick = { navController.navigate("show_articles_list") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)) {
                Text("Образовательный контент >")
            }
             if(currentUser.value!!.role=="admin"){
            Button(
                onClick = { navController.navigate("create_new_article") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)) {
                Text("Добавить статью >")
            }
            }
        }
    }
}