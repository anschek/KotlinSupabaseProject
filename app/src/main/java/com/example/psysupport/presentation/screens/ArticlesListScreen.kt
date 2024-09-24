package com.example.psysupport.presentation.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.psysupport.model.Article
import com.example.psysupport.presentation.screens.viewmodels.ArticlesListViewModel

@Composable
fun ArticlesListScreen(navController: NavController) {
    val vm: ArticlesListViewModel = viewModel()
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(top=50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Статьи и советы", fontSize = 25.sp, modifier = Modifier.padding(top = 20.dp, bottom = 5.dp))
        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(vm.articlesList.value){article -> ShowArticle(article) }
        }
    }
}

@Composable
fun ShowArticle(article: Article){
    val perfectBlue = Color(0xFF076672)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .border(1.dp, color = perfectBlue, shape = RoundedCornerShape(10.dp))
    ){
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Log.d("ArticlesLList", "Id: ${article.id} Автор: ${article.author} Дата: ${article.postingDate}")
            Text("Автор: ${article.author}")
            Text("Дата: ${article.postingDate}")

            Text(article.title, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = perfectBlue)
            Text(article.content, maxLines = 3)
        }
    }
}