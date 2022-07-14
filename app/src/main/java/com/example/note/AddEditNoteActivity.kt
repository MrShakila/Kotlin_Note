package com.example.note

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*


class AddEditNoteActivity : AppCompatActivity() {
                    lateinit var noteTitleEdit : EditText
                    lateinit var noteDescriptionEdit:EditText
                    lateinit var addupdateBtn:Button
                    lateinit var  viewModel: NotesViewModel
                    var  noteId =-1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        noteTitleEdit = findViewById(R.id.editnotetitle)
        noteDescriptionEdit =findViewById(R.id.notedesc)
        addupdateBtn =findViewById(R.id.saveeditbutton)

        viewModel = ViewModelProvider(
            this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)

        val noteType = intent.getStringExtra("notetype")
        if (noteType.equals("Edit")){
            val  noteTitle = intent.getStringExtra("Notetitle")
            val  noteDesc = intent.getStringExtra("NoteDescription")
            noteId = intent.getIntExtra("NoteId",-1 )
            addupdateBtn.setText("Updated Note")
            noteTitleEdit.setText(noteTitle)
            noteDescriptionEdit.setText(noteDesc)
        }else{
            addupdateBtn.setText("Save Note")
        }

        addupdateBtn.setOnClickListener {
            val noteTitle = noteTitleEdit.text.toString()
            val notedesc = noteDescriptionEdit.text.toString()
            if (noteType.equals("Edit")){
                if (noteTitle.isNotEmpty() && notedesc.isNotEmpty()){
                    val  sdf = SimpleDateFormat("dd MM,yyyy-HH:mm")
                    val currentDate:String =sdf.format(Date())
                    viewModel.addNote(Note(noteTitle,notedesc,currentDate))
                    Toast.makeText(this,"Note Added...",Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()

    }
}


}