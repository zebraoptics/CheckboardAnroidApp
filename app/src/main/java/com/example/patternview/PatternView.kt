package com.example.patternview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import org.opencv.android.Utils
import org.opencv.objdetect.Objdetect
import org.opencv.objdetect.CharucoBoard
import org.opencv.core.Mat
import org.opencv.core.Size
import kotlin.math.min

class PatternView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var rows = 8
    private var cols = 8
    private var patternType = "Checkerboard"

    private val paintBlack = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }
    private val paintWhite = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    fun setBoardDimensions(rows: Int, cols: Int, pattern: String) {
        this.rows = rows
        this.cols = cols
        this.patternType = pattern
        // Request a redraw with the new dimensions
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (rows <= 0 || cols <= 0) return

        if (patternType == "ChArUco") {
            drawCharucoBoard(canvas)
            return
        }

        // Determine the largest possible square size that fits within the view
        val squareSize = min(width.toFloat() / cols, height.toFloat() / rows)

        // Calculate the total width and height of the board
        val boardWidth = squareSize * cols
        val boardHeight = squareSize * rows

        // Calculate the offsets to center the board on the canvas
        val offsetX = (width - boardWidth) / 2f
        val offsetY = (height - boardHeight) / 2f

        // Draw the pattern squares
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val paint = if ((row + col) % 2 == 0) paintWhite else paintBlack

                // *** FIX IS HERE: Add offsetX and offsetY to the coordinates ***
                val left = offsetX + (col * squareSize)
                val top = offsetY + (row * squareSize)
                val right = left + squareSize
                val bottom = top + squareSize

                canvas.drawRect(left, top, right, bottom, paint)
            }
        }
    }

    private fun drawCharucoBoard(canvas: Canvas) {
        val dictionary = Objdetect.getPredefinedDictionary(Objdetect.DICT_6X6_250)

        val squareSize = min(width.toFloat() / cols, height.toFloat() / rows)
        val markerSize = squareSize * 0.5f

        val board = CharucoBoard(Size(cols.toDouble(), rows.toDouble()), squareSize, markerSize, dictionary)

        val mat = Mat()
        board.generateImage(Size(width.toDouble(), height.toDouble()), mat, 0, 1)

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(mat, bitmap)

        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }
}