
----------------------------------------------------------
Design techniques and design patterns used in this project
----------------------------------------------------------

- Inheritance
  --------------------------------------------------------
  Components inherit form the abstract class Cool303Component, Containers inherit from the abstract class Cool303Component and Cool303Composite. 
  This gives a common foundation to all the components and a common family tree, 
  to allow different components types to be passed as parameters of functions or stored in arrays. 
  The choice of an abstract class usage has been made to allow the methods to have the protected modifier.

- Interface Cool303Theme
  --------------------------------------------------------
  All themes must implement the Cool303Theme interface. 
  This give us a template to guarantee all the methods will be implemented.

- Composite 
  --------------------------------------------------------
  Containers and roots are composite elements storing other components. 
  They broadcast the method paint() call to all their children.

- Strategy pattern
  --------------------------------------------------------
  Themes implements strategies on how applications will be stylized.

- Decorator pattern
  --------------------------------------------------------
  All Components and Containers wrap swing components and them them internally. 
  They decorate the swing methods like add, setTitle, setPreferedSize.

- MVC
  --------------------------------------------------------
  The MVC architecture is implemented. Each container(including roots) contains an array of Cool303Components so they act as models.
  The controller is the root, which dispatch the paint() calls to render the view (all the swing Components). 

- Observer pattern
  --------------------------------------------------------
  Containers call Root.notify(Cool303Component component) when elements are added into them 
  to ask the root to paint them. If root is not yet defined, this means the container is not yet added to a root. 
  Everything will be paint via the Root.add() method when they will be inserted in a root.

- Exception
  --------------------------------------------------------
  An exception is throw to prevent illegal usages (theme is null in the root constructor).