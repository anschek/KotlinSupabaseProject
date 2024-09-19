package com.example.psysupport.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.psysupport.model.MoodType
import com.example.psysupport.presentation.screens.viewmodels.NewNoteViewModel
import io.github.jan.supabase.gotrue.user.UserInfo

@Composable
//@Preview
fun CreateNewNoteScreen(navController: NavController, currentUser: MutableState<UserInfo?>) {
    val vm: NewNoteViewModel = viewModel()
    vm.curUser.value = currentUser.value

    val selectedMoodType = remember { mutableStateOf<MoodType?>(null) }
    Column(
        modifier = Modifier
            .padding(vertical = 40.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Text("Дата фиксации состояния")
        Text("Тип основной эмоции дня")
        ComboBox(
            items = vm.moodTypes.value,
            selectedItem = selectedMoodType.value,
            onItemSelected = { selected -> selectedMoodType.value = selected },
            itemText = { moodType -> moodType?.moodName ?: "" } // Указываем поле для отображения
        )
        Text("Выбранная основная эмоция ${selectedMoodType.value}")
        //LaunchedEffect(selectedMoodType) { vm.selectMoodType(selectedMoodType)}

        Text("Основная эмоция дня")
        Text("Заметка")
        Text("Общая оценка дня")
        Button(onClick = { /*TODO*/ }) {
            Text("Сохранить")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ComboBox(
    items:List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,//выбор объекта
    itemText: (T) -> String//по какому полю вывод
){
    var expanded by remember { mutableStateOf(false) }
    // Контейнер для TextField и выпадающего меню
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded } // Изменяем состояние при нажатии
    ) {
        TextField(
            // Выводим строку, связанную с выбранным элементом
            value = itemText(selectedItem),//?.let(itemText)?: "",
            onValueChange = {},
            modifier = Modifier.menuAnchor(),//якорь меню
            readOnly = true
        )
        // Выпадающее меню
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }//закрытие меню
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                    onItemSelected(item) // Устанавливаем выбранный элемент
                    expanded = false // Закрываем меню
                    }, text = {Text(itemText(item))}
                )
            }
        }
    }
}