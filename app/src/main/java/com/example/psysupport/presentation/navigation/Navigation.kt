package com.example.psysupport.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.psysupport.model.User
import com.example.psysupport.presentation.screens.ArticlesListScreen
import com.example.psysupport.presentation.screens.AuthScreen
import com.example.psysupport.presentation.screens.CreateNewNoteScreen
import com.example.psysupport.presentation.screens.HomeScreen
import io.github.jan.supabase.gotrue.user.UserInfo

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val currentUser = remember { mutableStateOf<User?>(null) }
    NavHost(
        navController = navController,
        startDestination = "auth"){
        //composable определяет маршруты и соответствующие им Composable-функции
        composable("auth"){
            AuthScreen(navController, currentUser)
        }
        composable("home_screen"){
            HomeScreen(navController, currentUser)
        }
        composable("create_new_note"){
            CreateNewNoteScreen(navController, currentUser)
        }
        composable("show_articles_list"){
            ArticlesListScreen(navController)
        }
    }
}