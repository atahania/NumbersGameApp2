package com.example.numbersgameapp2

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout
    private lateinit var guessField: EditText
    private lateinit var guessButton: Button
    private lateinit var messages: ArrayList<String>
    private lateinit var recyclerView: RecyclerView

    private var answer= 0
    private var guesses= 3

    private var tag = "MAIN_ACTIVITY"

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    answer = Random.nextInt(10)
    clRoot = findViewById(R.id.clRoot)
    messages = ArrayList()
    recyclerView = findViewById(R.id.rvMessages)
    recyclerView.adapter =MessageAdapter(this, messages)
    recyclerView.layoutManager = LinearLayoutManager(this)
    guessField = findViewById(R.id.etGuessField)
    guessButton = findViewById(R.id.btGuessButton)
    guessButton.setOnClickListener { addMessage() }
}
@SuppressLint("NotifyDataSetChanged")
private fun addMessage(){
    val msg = guessField.text.toString()
    if (msg.isNotEmpty()) {
        if (guesses > 0) {
            Log.d(tag, "Level 1")
            if (msg.toInt() == answer) {
                Log.d(tag, "Level 2")
                //disableEntry()
                showAlertDialog("you win!\n\nPlay again? ")
            } else {
                Log.d(tag, "Level 3")
                guesses--
                Log.d(tag, "Level 4")
                messages.add("you guessed $msg")
                Log.d(tag, "Level 5")
                messages.add("you have $guesses guesses left")
                Log.d(tag, "Level 6")
            }
            if (guesses == 0) {
                disableEntry()
                messages.add("you lose...\nThe correct answer was $answer.\n\nPlay again?")

            }
        }
        guessField.text.clear()
        guessField.clearFocus()
        recyclerView.adapter?.notifyDataSetChanged()
    } else {
        Snackbar.make(clRoot, "Please enter a number", Snackbar.LENGTH_LONG).show()
    }
}
private fun disableEntry() {
    guessButton.isEnabled = false
    guessButton.isClickable = false
    guessField.isEnabled = false
    guessField.isClickable = false
}
private fun showAlertDialog(title: String) {
    val dialogBuilder = AlertDialog.Builder(this)
    dialogBuilder.setMessage(title)
        .setCancelable(false)
        .setPositiveButton("yes", DialogInterface.OnClickListener {
                dialog, id -> this.recreate()
        })
        .setNegativeButton("No", DialogInterface.OnClickListener {
                dialog, id -> dialog.cancel()
        })
    val alert = dialogBuilder.create()
    alert.setTitle("Game Over")
    alert.show()
}}