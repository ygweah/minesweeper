# minesweeper
Minesweeper in Java

Title:
	This is the help file for the Minesweeper game.

Overview:
	This game is a version of Minesweeper game totally written in Java. The GUI interface should be familiar to those who have played the game from MS.  Most of the GUI and performance issues have been addressed in version 3.0.

Install:
	Unzip all the source files and image gif files into a directory.  Compile with "javac Minesweeper.java" and then run with "java Minesweeper" under JDK 1.1 and plus.

About Program:
	The program has been developed and tested using Symantec Visual Cafe 2.5 (JDK 1.1 compatible) for Windows NT 4.0.  It also can be successfully re-complied and run under JDK 1.1.8, although extensive test has not been done that way.
	The program has extensive use of custom AWT components and object-based io.  In the future version, swing and native methods may be used with improved algorithm to achieve better performance or just to have fun.

Changes:
	The better counter and timer are now implemented using image.
	Grids are drawn directly in GridArray with double buffering.
	Timer thread is improved.
	WaitMessage is no longer needed.

About Author:
	Crazy about programming.
	Also very strong interest and background in Science.
	Currently a Depaul University student in M.S., Distributed System.

	Contact: yuhonguo@hotmail.com or 847-382-0508 ask Yuhong
 
Enclosed Source Files:

	AnimatedButton.java
	BestRecord.java
	BestTimeDialog.java
	Board.java
	CheckboxMenuitemGroup.java

	ContentDialog.javas
	Counter.java
	CustomDialog.java
	Digit.java
	GameDimension.java

	GameListener.java
	GameRecord.java
	Grid.java
	GridArray.java
	ImageArea.java

	ImageButton.java
	InfoDialog.java
	InputBestTimeDialog.java
	LabelArea.java
	Minesweeper.java

	ThreeD_BorderLayout.java
	ThreeD_Panel.java
	Timer.java

	readme.txt
	
Design:
	This program makes great deal of use of customized AWT component.  The main class is Minesweeper, a Frame with menu displaying GUI component.  The most important component is GridArray, containing methods dealing with drawing and game logic.  The AnimatedButton, Counter and Timer are other component.  They are placed in a ThreeD_Panel with ThreeD_BorderLayout manager to create 3D effect at the border.  In the menu, a CheckboxMenuitemGroup is used to create exclusive menu items.  The game records are stored and retrieved in the file using serialization.

Acknowledgement:
	Special thanks to my wife Qian for her support and patience.
	
