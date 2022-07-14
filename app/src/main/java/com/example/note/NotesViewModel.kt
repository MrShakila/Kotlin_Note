package com.example.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {
    val  allNotes: LiveData<List<Note>>
    val repositry: NoteRepositry

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repositry = NoteRepositry(dao)
        allNotes = repositry.allNote
    }

    fun  deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO ) {
        repositry.delete(note)
    }

    fun  updateNote(note: Note) =viewModelScope.launch (Dispatchers.IO){
        repositry.update(note)
    }
    fun  addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repositry.insert(note)
    }
}