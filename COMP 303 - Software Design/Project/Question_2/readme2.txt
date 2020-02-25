Le, Nhat Hung
260793376

April 13 2018

COMP 303 - Project

Question 2

Clarification concerning code:
To respect the assignment sheet, the Autonomous, Moveable and Immovable objects keep their 'x' and 'y' fields for coordinates.
However, the fields are 'x' and 'y' by name only and should be understood as 'row' and 'column'.
This means when adding to the world array, remember that (0,0) means the world's the top left corner, and (1,0) means down one row.
For this reason, the directions are understood this way:
- UP is x-1
- DOWN is x+1
- LEFT is y-1
- RIGHT is y+1

Thank you.
 

Techniques:
- Encapsulation / code reuse / contract through abstract class: Item.java abstract class extended by Autonomous, Moveable and Immovable.
- Defensive programming: Try/catches in constructor of all objects, World.add() lines 106-114.
- Bitwise operations for booleans: Up down left right directions defined in Item.java. Random direction is chosen through array lookup.
- Recursion: World.java bump() lines 145-185.
- Anonymous objects: World.java lines 34-37.

Design patterns:
- Composite pattern: World.java lines 44-58
World aggreates multiple Item objects. Item is an interface implemented by Autonomous, Moveable and Immovable. World.step() iterates through every Autonomous objects and calls Autonomous.step(). World.step() treats World as a primitive object.

- Model View Controller: World.java step() lines 44-60
World.step() calls Item.step() which first updates the Item's x and y coordinates and then notifies World and updates the World object's 2D array from within. In this case, Items are the Model and World's array (or the world window) is the View.