package com.example.psysupport.presentation.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

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
                    }, text = { Text(itemText(item)) }
                )
            }
        }
    }
}