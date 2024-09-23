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
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.launch

class NewArticleViewModel():ViewModel() {
    private val _articleTypes = mutableStateOf(listOf<ArticleType?>())
    val articleTypes: State<List<ArticleType?>> = _articleTypes

    init{
        viewModelScope.launch {
            _articleTypes.value = Constants.supabaseClient.from("ArticleTypes")
                .select().decodeList<ArticleType>()
            if(_articleTypes.value.isEmpty()){
                Log.e("CreateArticle", "list of article types is empty")
            }
        }
    }

    val selectedType = mutableStateOf<ArticleType?>(null)
    val selectedDate = mutableStateOf("")
    val title = mutableStateOf("")
    val author = mutableStateOf("")
    val content = mutableStateOf("")

    fun createNewArticle(){
        viewModelScope.launch {
            val newId = Constants.supabaseClient.from("Articles")
                .select(){
                    order(column = "id", order = Order.DESCENDING)
                    limit(1)
                }.decodeSingle<Article>().id + 1
            val newArticle = Article(
                id = newId,
                typeId = selectedType.value!!.id,
                author = author.value,
                postingDate = selectedDate.value,
                title = title.value,  content = content.value
            )
            try{

                Constants.supabaseClient.from("Articles")
                    .insert(newArticle)
                Log.d("CreateArticle", "Success")
            }catch (e: Exception){
                Log.e("CreateArticle", "id ${newId}, typeId ${newArticle.typeId}, author ${newArticle.author}, " +
                        "date ${newArticle.postingDate}," +
                        "title ${newArticle.title}, content ${newArticle.content}")
                Log.e("CreateArticle", e.message.toString())
            }

        }
    }
}