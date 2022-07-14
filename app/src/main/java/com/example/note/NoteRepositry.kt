package com.example.note

import androidx.lifecycle.LiveData

class NoteRepositry(private  val notesDao: NotesDao) {
    val  allNote: LiveData<List<Note>> =notesDao.getAllNotes()

    suspend fun insert(note: Note){
        notesDao.insert(note)
    }
    suspend fun  update(note: Note){
        notesDao.update(note)
    }
    suspend fun  delete(note: Note){
        notesDao.delete(note)
    }



}