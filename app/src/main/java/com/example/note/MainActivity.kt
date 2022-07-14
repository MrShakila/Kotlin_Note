package com.example.note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {
    lateinit var  notesRv:RecyclerView
    lateinit var  addFAB: FloatingActionButton
    lateinit var  viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesRv = findViewById(R.id.idRVNotes)
        addFAB = findViewById(R.id.idFABAddNote)
        notesRv.layoutManager= LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(this,this,this)
    notesRv.adapter = noteRVAdapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener{
            val  intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(note: Note) {
       viewModel.deleteNote(note)
        Toast.makeText(this,"${note.notetitle} Deleted",Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {
        val  intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
        intent.putExtra("notetype","Edit")
        intent.putExtra("Notetitle",note.notetitle)
        intent.putExtra("NoteDescription",note.notediscription)
        intent.putExtra("NoteId",note.id)
        startActivity(intent)
        this.finish()

    }
}