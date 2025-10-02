package com.example.checkerboardview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat

class CheckerboardActivity : AppCompatActivity() {

    // Define constants for the intent extras to avoid typos
    companion object {
        const val EXTRA_ROWS = "EXTRA_ROWS"
        const val EXTRA_COLS = "EXTRA_COLS"
    }

    private lateinit var gestureDetector: GestureDetectorCompat

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkerboard)

        // Retrieve row and column counts from the intent
        val rows = intent.getIntExtra(EXTRA_ROWS, 0)
        val cols = intent.getIntExtra(EXTRA_COLS, 0)

        val checkerboardView = findViewById<CheckerboardView>(R.id.checkerboardView)
        checkerboardView.setBoardDimensions(rows, cols)

        // Initialize the gesture detector to listen for double taps
        gestureDetector = GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                // When a double tap occurs, finish this activity to return to the previous one
                finish()
                return true
            }
        })

        // Set an on-touch listener on the view to pass events to the gesture detector
        checkerboardView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            // Return true to indicate that we have handled the touch event
            true
        }
    }
}
