Le, Nhat Hung
260793376

April 14 2018

COMP 303 - Project

Question 1


Instruction to create new theme:
Create a new class. Make it extend Cool303Theme. Copy and customize the constructor template below:
public CustomTheme() {
    setBackgroundColor(new Color(int red, int green, int blue));
    setBorderColor(new Color(int red, int green, int blue));
    setButtonColor(new Color(int red, int green, int blue));

    setBorderThickness(int borderThickness);
    setCornerRadius(int cornerRadius);
}


JavaDoc instruction: the right file to click is Question_1_JavaDoc/index.html.


Techniques:
- Encapsulation / code reuse / contract through interface: Cool303Component interface implemented by Cool303Button and Cool303Container.
- Defensive programming: Try/catches in Cool303Container's constructors, inherited by Cool303Root.
- Anonymous classes: Cool303Button.java constructors.


Design pattern:
- Composite pattern: 
Root, Container and Button all implement Cool303Component, while Container (and Root) aggregrates Cool303Components. Calling root.paint() iterates through all of root's components and call their paint().

- Observer pattern:
When Root adds a Container, this container registers to the Root by taking the root's pointer as a field. Each time something new is added into the Container, the Container notifies its parent Root so the latter can apply the theme on the newly added element. Also each time a new Container is added into the Root, the Root automatically paints it to update the View (the window in this case).