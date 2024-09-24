package com.example.psysupport.presentation.screens.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psysupport.domain.Constants
import com.example.psysupport.model.DailyMood
import com.example.psysupport.model.DayAssessment
import com.example.psysupport.model.Emotion
import com.example.psysupport.model.MoodType
import com.example.psysupport.model.User
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class NewNoteViewModel(): ViewModel() {
    val curUser = mutableStateOf<User?>(null)

    private val _moodTypes = mutableStateOf(listOf<MoodType>())
    val moodTypes: State<List<MoodType>> = _moodTypes//State действует только на чтение

    private val _emotions = mutableStateOf(listOf<Emotion>())

    private val _selectedMoodType = mutableStateOf<MoodType?>(null)

    private val _filteredEmotions = mutableStateOf(listOf<Emotion>())
    val filteredEmotions: State<List<Emotion>> = _filteredEmotions


    init {// Загрузка данных при инициализации
        viewModelScope.launch {
            _moodTypes.value = Constants.supabaseClient.from("MoodTypes")
                .select().decodeList<MoodType>()
            _emotions.value = Constants.supabaseClient.from("Emotions")
                .select().decodeList<Emotion>()
            _filteredEmotions.value = Constants.supabaseClient.from("Emotions")
                .select().decodeList<Emotion>()
        }
    }

    fun selectMoodType(moodType: MoodType){
        _selectedMoodType.value = moodType
        filterEmotionsByMoodType(moodType.id)
    }

    private fun filterEmotionsByMoodType(moodTypeId: Int){
        _filteredEmotions.value = _emotions.value.filter { emotion ->
            emotion.moodTypeId == moodTypeId }
    }

    val selectedEmotion = mutableStateOf<Emotion?>(null)
    var selectedDate = mutableStateOf("")
    val typedNote = mutableStateOf("")
    val selectedAssessment = mutableStateOf<DayAssessment?>(null)

    fun createNewNote(){
        viewModelScope.launch {
            Log.d("CreateNote", "userId ${curUser.value?.id ?: "no user"} noteDate ${selectedDate.value} " +
                    "emotionId ${selectedEmotion.value?.id ?: "no emotion"} note ${typedNote.value} assessment ${selectedAssessment.value?.id ?: "no assessment"}")
            try{
                val newNote = DailyMood(
                    userId = curUser.value!!.id,
                    noteDate = selectedDate.value,
                    emotionId = selectedEmotion.value!!.id,
                    note = typedNote.value,
                    dailyAssessmentId = selectedAssessment.value?.id
                )
                Constants.supabaseClient.from("DailyMoods").insert(newNote)
                Log.d("CreateNote", "Sucess")
            }catch(e: Exception){
                Log.e("CreateNote", "Error")
                Log.e("CreateNote", e.message.toString())
            }
        }
    }
}