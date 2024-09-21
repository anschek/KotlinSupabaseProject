package com.example.psysupport.presentation.screens.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psysupport.domain.Constants
import com.example.psysupport.model.Article
import com.example.psysupport.model.ArticleType
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class ArticlesListViewModel():ViewModel() {
    private val _articlesList = mutableStateOf<List<Article>>(emptyList())
    val articlesList: State<List<Article>> = _articlesList

    init {
        viewModelScope.launch {
            _articlesList.value = Constants.supabaseClient.from("Articles")
                .select().decodeList<Article>()
            Log.d("ArticlesList", "Success")
            if (_articlesList.value.isNotEmpty()) {
                Log.d("ArticlesList", "Success")
            } else {
                Log.d("ArticlesList", "No articles found")
            }
        }
    }

    suspend fun getArticleTypeById(articleTypeId: Int): ArticleType{
        return Constants.supabaseClient.from("ArticleTypes")
            .select(){
                filter {
                    ArticleType::id eq articleTypeId
                }
            }.decodeSingle<ArticleType>()
    }
}