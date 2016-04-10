# IGN Code-Foo 6

***Pranav Kumar***

##Part 1:

Take a short video (no more than 5 minutes) introducing yourself, showing your passion for IGN, and telling us why we should pick you to participate in IGN's Code-Foo program. Upload it to YouTube and send us the link.

#####[Video Submission](http://youtube.com)

##Part 2:

The Avengers just made a surprise visit to San Francisco. How many legos would it take to rebuild the Golden Gate Bridge? Describe each step in your thought process.

#####Solution:

##Part 3:

Write a program that determines the best Scrabble™ word from a list of letters. You can use this list of words as your data source or pick a public API like this one: dictionaryapi. Scrabble™ letter values can be found here.

#####Solution: [Source](https://github.com/KumarHX/Code-Foo-6/blob/master/Part%203/ScrabbleWord.java) This program takes a user input (makes sure it is a valid scrabble hand) and finds all the words that can be created based on the wordbase provided by IGN. All the words that can be created based on the hand and the most valuable word (based on letter values and double word score for consecutive letters) are printed to the console. [Test Runs](https://github.com/KumarHX/Code-Foo-6/blob/master/Part%203/TestRun.txt)

#####To Run: cd to Part\ 3 and run the command 'java ScrabbleWord' in the terminal

##Part 4 (Backend):

####a:

Aliens have taken your keyboard, and replaced it with an off brand AZERTY keyboard with a sticky 'H' key. Write a program to convert the AZERTY keys you press to QWERTY.

#####Solution: [Source](https://github.com/KumarHX/Code-Foo-6/blob/master/Part%204%20(Backend)/Part%20A/Azerty.java) This program uses a hashmap to map QWERTY values to the representive AZERTY values. The AZERTY keyboard letters are the keys in the hashmap and the QWERTY keyboard letters are the values for the representive keys. For the sticky 'H' key, this program finds the substring till the first 'H' and makes that the stickied value. It then replaces all 'H' with the stickied value.  [Test Runs]( https://github.com/KumarHX/Code-Foo-6/blob/master/Part%204%20(Backend)/Part%20A/TestRuns.txt)

#####To Run: cd to Part A in the Part 4 (backend) directory and run the command 'java Azerty' in the terminal

####b:

Write a library from scratch that can parse date strings and convert them to a valid ISO 8601 date/time format. Every value in this list should be supported.

#####Solution: [Source](https://github.com/KumarHX/Code-Foo-6/blob/master/Part%204%20(Backend)/Part%20B/ISOlibrary.py) This library converts different date values into the ISO date format. It breaks the inputted date up into chunks, uses regex to find the pattern of the chunk and uses helper functions to create the ISO format. If there are any missing values, it puts the current year, month and/or day and 0 for time values. [Test Runs](https://github.com/KumarHX/Code-Foo-6/blob/master/Part%204%20(Backend)/Part%20B/TestRun.txt) 

#####To Run: cd to Part B in the Part 4 (backend) directory and run the command 'python ISOlibraryRunner.py' in the terminal (cannot have python 3.X because this program is written for python 2.7 and the print commands/user input syntax changed for the newer versions of python)

##Bonus:

Programmatically create a card game that uses an AI opponent. The game can be an original creation or it can be modeled after an existing ruleset. Some examples include Black Jack, Poker, Caravan, Gwent, Apples to Apples and Cards Against Humanity.


![Webview screenshot](http://s21.postimg.org/sh3hq7xc7/Screen_Shot_2016_04_10_at_2_37_38_PM.png)

#####Solution: [Source](https://github.com/KumarHX/Code-Foo-6/tree/master/Bonus) My solution to this bonus question is the 'thirteens' card game with an AI opponent. The objective is to find (from 9 cards at a time from a 52 card deck) 2 cards that add up to 13 or one king card. The objective is to go as fast as you can because the AI opponent gets points for finding sets too (have to be faster than the AI (artificailly slowed down the algorithm for the AI to make the game possible)). If at the end (no cards that are king or pair that adds up to 13) you win if you have more than or equal to as many points as the AI. Build with Java and used the java.awt library for graphics.    [Test Runs](https://github.com/KumarHX/Code-Foo-6/blob/master/Part%203/TestRun.txt)

#####To Run: cd to Bonus and run the command 'java ThirteensGUIRunner' in the terminal




