package com.example.psysupport.presentation.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.psysupport.presentation.components.ComboBox
import com.example.psysupport.presentation.screens.viewmodels.NewArticleViewModel
import java.util.Calendar

@Composable
fun CreateNewArticleScreen(navController: NavController) {
    //Text("on this page you can create new article")
    val vm: NewArticleViewModel = viewModel()

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            vm.selectedDate.value = "$year-${ if(month<9) "0${month+1}" else month+1}-${if(dayOfMonth<10) "0$dayOfMonth" else dayOfMonth}"
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
    )
    Column {
        Text("Тип статьи")
        ComboBox(
            items = vm.articleTypes.value,
            selectedItem = vm.selectedType.value,
            onItemSelected = { selected -> vm.selectedType.value = selected},
            itemText = { articleType -> articleType?.name ?: "" } // Указываем поле для отображения
        )
        Text("Выбранный тип статьи ${vm.selectedType.value}")

        Button(onClick = { datePickerDialog.show() }) {
            Text("Дата")
        }
        Text("Выбранная дата ${vm.selectedDate.value}")

        Text("Автор")
        TextField(value = vm.author.value, onValueChange = {value -> vm.author.value = value})
        Text("автор ${vm.author.value}")

        Text("Заголовок")
        TextField(value = vm.title.value, onValueChange = {value -> vm.title.value = value})
        Text("Заголовок ${vm.title.value}")

        Text("Текст статьи")
        TextField(
            value = vm.content.value,
            onValueChange = {value -> vm.content.value = value},
            //минимальная высота, но т.к. maxLines не указан, максимум - любой
            modifier = Modifier.heightIn(min=150.dp)
        )
        Text("Текст статьи ${vm.content.value}")

        Button(onClick = { vm.createNewArticle() }) {
            Text("Добавить статью")
        }
    }


}