package com.example.psysupport.presentation.screens.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psysupport.domain.Constants
import com.example.psysupport.model.Emotion
import com.example.psysupport.model.MoodType
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class NewNoteViewModel(
    val curUser: MutableState<UserInfo?>
): ViewModel() {
    private val _moodTypes = mutableStateOf(listOf<MoodType>())
    val moodTypes: State<List<MoodType>> = _moodTypes//State действует только на чтение

    private val _emotions = mutableStateOf(listOf<Emotion>())
    val emotions: State<List<Emotion>> = _emotions

    private val _selectedMoodType = mutableStateOf<MoodType?>(null)
    val selectedMoodType: State<MoodType?> = _selectedMoodType

    val defaultDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val selectedDate = mutableStateOf(LocalDate)

    private val _filteredEmotions = mutableStateOf(listOf<Emotion>())
    val filteredEmotions: State<List<Emotion>> = _filteredEmotions


    init {// Загрузка данных при инициализации
        viewModelScope.launch {
            _moodTypes.value = Constants.supabaseClient.from("MoodTypes")
                .select().decodeList<MoodType>()
            _emotions.value = Constants.supabaseClient.from("Emotions")
                .select().decodeList<Emotion>()
        }
    }

    fun selectMoodType(moodType: MoodType){
        _selectedMoodType.value = moodType
        filterEmotionsByMoodType(moodType.id)
    }

    private fun filterEmotionsByMoodType(moodTypeId: Int){
        _filteredEmotions.value = emotions.value.filter { emotion ->
            emotion.moodTypeId == moodTypeId }
    }

    //onCreateNewNote
    //user_id+note_date+emotion_id+note_daily_assessment
}