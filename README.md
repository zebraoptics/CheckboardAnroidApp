# CheckboardAnroidApp
Checkerboard is one of the mostly adapted patterns that are used for camera calibration. The checkerboard pattern are usually printed on a piece of papar and attached on a flat surface. However, there are two main challenges:
 - It is not flexible to use the printed pattern in terms of the checker size (col and row numbers).
 - The holder of the printed page may not be flat, resulting in calibration errors in the camera calibrations.


This is a simple Android Studio project written in Kotlin. In general, it takes inputs of the row and col numbers of a checkerboard, and generate and show it in the next view.  It returns to the args page if double-clicking the checkerboard pattern. The display of a tablet or a smartphone are very flat. Additionaly, the dimension of the display are precisely described in the specification sheets in most of the cases. So the screen based checkerboard pattern could provide reliable object points in camera calibration tasks.
