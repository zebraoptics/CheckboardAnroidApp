package com.example.checkerboardview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rowsInput = findViewById<EditText>(R.id.rowsInput)
        val colsInput = findViewById<EditText>(R.id.colsInput)
        val generateButton = findViewById<Button>(R.id.generateButton)

        generateButton.setOnClickListener {
            // Read input values, defaulting to 0 if empty or invalid
            val rows = rowsInput.text.toString().toIntOrNull() ?: 0
            val cols = colsInput.text.toString().toIntOrNull() ?: 0

            // Validate that the inputs are greater than 0
            if (rows > 0 && cols > 0) {
                // Create an intent to start the CheckerboardActivity
                val intent = Intent(this, CheckerboardActivity::class.java).apply {
                    putExtra(CheckerboardActivity.EXTRA_ROWS, rows)
                    putExtra(CheckerboardActivity.EXTRA_COLS, cols)
                }
                startActivity(intent)
            } else {
                // Show an error message if the input is invalid
                Toast.makeText(this, "Please enter valid numbers greater than 0 for rows and columns.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
