package com.aritra.notify.screens.notes.homeScreen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aritra.notify.data.models.Note
import com.aritra.notify.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    application: Application,
    private val homeRepository: NoteRepository
) : AndroidViewModel(application) {

    var notesModel by mutableStateOf(emptyList<Note>())

    fun getAllNotes() {
        viewModelScope.launch {
            homeRepository.getAllNotesFromRoom().collect { response ->
                notesModel = response
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            homeRepository.deleteNoteFromRoom(note)
        }
    }

    fun searchNotesByTitle(searchQuery: String) {
        viewModelScope.launch {
            homeRepository.searchNotesByTitleFromRoom(searchQuery).collect { response ->
                notesModel = response
            }
        }
    }
}