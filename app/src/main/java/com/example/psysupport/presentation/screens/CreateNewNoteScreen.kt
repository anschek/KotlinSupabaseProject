package com.example.psysupport.presentation.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.psysupport.model.MoodType
import com.example.psysupport.model.User
import com.example.psysupport.presentation.components.ComboBox
import com.example.psysupport.presentation.screens.viewmodels.NewNoteViewModel
import io.github.jan.supabase.gotrue.user.UserInfo
import java.util.Calendar

@Composable
//@Preview
fun CreateNewNoteScreen(navController: NavController, currentUser: MutableState<User?>) {
    val vm: NewNoteViewModel = viewModel()
    vm.curUser.value = currentUser.value
    val selectedMoodType = remember { mutableStateOf<MoodType?>(null) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            vm.selectedDate.value = "$year-${ if(month<9) "0${month+1}" else month+1}-${if(dayOfMonth<10) "0$dayOfMonth" else dayOfMonth}"
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Выбранная дата: ${vm.selectedDate.value}")
        Button(onClick = { datePickerDialog.show()}) {
            Text("Выбрать дату")
        }

        Text("Тип основной эмоции дня")
        ComboBox(
            items = vm.moodTypes.value,
            selectedItem = selectedMoodType.value,
            onItemSelected = { selected -> selectedMoodType.value = selected
                               selectedMoodType.value?.let { vm.selectMoodType(it) }},
            itemText = { moodType -> moodType?.moodName ?: "" } // Указываем поле для отображения
        )
        Text("Выбранный тип эмоции ${selectedMoodType.value}")

        Text("Основная эмоция дня")
        ComboBox(
            items = vm.filteredEmotions.value,
            selectedItem = vm.selectedEmotion.value,
            onItemSelected = { selected -> vm.selectedEmotion.value = selected},
            itemText = { emotion -> emotion?.emotionName ?: "" } // Указываем поле для отображения
        )
        Text("Выбранная основная эмоция ${vm.selectedEmotion.value}")

        Text("Заметка")
        TextField(
            value = vm.typedNote.value,
            onValueChange = {newNote -> vm.typedNote.value = newNote},
            //минимальная высота, но т.к. maxLines не указан, максимум - любой
            modifier = Modifier.heightIn(min=150.dp)
        )
        //TODO добавить общую оценку как картинку
        //Text("Общая оценка дня")
        Button(onClick = { vm.createNewNote() }) {
            Text("Сохранить")
        }
    }
}
