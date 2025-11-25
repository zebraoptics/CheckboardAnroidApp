package com.example.patternview

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var selectedPattern: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rowsInput = findViewById<EditText>(R.id.rowsInput)
        val colsInput = findViewById<EditText>(R.id.colsInput)
        val generateButton = findViewById<Button>(R.id.generateButton)
        val patternSelector = findViewById<Spinner>(R.id.patternSelector)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.pattern_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            patternSelector.adapter = adapter
        }

        patternSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedPattern = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        generateButton.setOnClickListener {
            // Read input values, defaulting to 0 if empty or invalid
            val rows = rowsInput.text.toString().toIntOrNull() ?: 0
            val cols = colsInput.text.toString().toIntOrNull() ?: 0

            // Validate that the inputs are greater than 0
            if (rows > 0 && cols > 0) {
                // Create an intent to start the PatternActivity
                val intent = Intent(this, PatternActivity::class.java).apply {
                    putExtra(PatternActivity.EXTRA_ROWS, rows)
                    putExtra(PatternActivity.EXTRA_COLS, cols)
                    putExtra(PatternActivity.EXTRA_PATTERN, selectedPattern)
                }
                startActivity(intent)
            } else {
                // Show an error message if the input is invalid
                Toast.makeText(this, "Please enter valid numbers greater than 0 for rows and columns.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
