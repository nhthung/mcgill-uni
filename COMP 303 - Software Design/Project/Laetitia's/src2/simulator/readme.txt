
----------------------------------------------------------
Design techniques and design patterns used in this project
----------------------------------------------------------

- Inheritance
  --------------------------------------------------------
  Movable share common properties with Immovable, and Movable share common properties with Autonomous. 
  By making them inherit from each other, we let them be part of the same family tree.

- Interface Item
  --------------------------------------------------------
  Immovable, Movable and Autonomous implement the interface Item. 
  This allow to threat them as elements of the same type. We could have used Immovable as a base type too, 
  but it makes less sense as Movable and Autonomous are not Immovable. 
  This alow us more flexibility if the map needs to store elements from another family tree.

- Iterator
  --------------------------------------------------------
  To facilitate the movements in the 2D array map, an iterator is implemented. 
  A direction can be specified to iterate toward TOP, RIGHT, BOTTOM, LEFT.

- Composite
  --------------------------------------------------------
  The world act as a composite element for all the items. 
  All the items are aggregated to it, stored in he map, and World broadcast the step call the all the autonomous items.

- MVC
  --------------------------------------------------------
  The MVC architecture is implemented. The world contains a map, a 2D array containing all items according to their position, so it acts as a model.
  World is also the controller, it triggers the steps (Autonomous movements and bumps), update the positions on the map and render the view (JFrame) on every display call.

- Observer pattern
  --------------------------------------------------------
  World.update(Item item) is called by Movable.move() to notify the world the item's position has changed.
  The world then update the item on the map.

- Exceptions
  --------------------------------------------------------
  Custom exceptions have been implemented to prevent illegal usages.