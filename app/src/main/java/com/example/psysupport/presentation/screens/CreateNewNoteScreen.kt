package com.example.psysupport.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.psysupport.presentation.screens.viewmodels.AuthViewModel
import com.example.psysupport.presentation.screens.viewmodels.NewNoteViewModel
import io.github.jan.supabase.gotrue.user.UserInfo

@Composable
//@Preview
fun CreateNewNoteScreen(navController: NavController, currentUser: MutableState<UserInfo?>) {
    val vm: NewNoteViewModel = viewModel()
    vm.curUser.value = currentUser.value
}