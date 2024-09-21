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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.psysupport.model.User
import io.github.jan.supabase.gotrue.user.UserInfo

@Composable
fun HomeScreen(navController: NavController, currentUser: MutableState<User?>) {
    //TODO          план разделения ролей
    //для пользователя: |заполнить настроение за день|V
    //                  |      смотреть статьи       |

    // для админа:      |заполнить настроение за день|V
    //                  |      смотреть статьи       |
    //                  |      добавить статью       |


    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(currentUser.value!!.role)
        Button(
            onClick = { navController.navigate("create_new_note") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)) {
            Text("Заметка за день >")
        }
    }
}