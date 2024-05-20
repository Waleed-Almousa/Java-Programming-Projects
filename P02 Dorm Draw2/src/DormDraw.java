//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P02 - DormDraw
// Course: CS 300 Spring 2024
//
// Author: Waleed Almousa
// Email: walmousa@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: None
// Partner Email: None
// Partner Lecturer's Name: None
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Instructors helped answer questions on Piazza
// Online Sources:
//////////////// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2024/spring/p2/doc/Utility.html#save(java.lang.String)
//////////////// provided help with using methods from the Utility class
//
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2024/spring/p2/doc/DormDraw.html#setup()
// all method header java doc comments were copied from here.
//
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2024/spring/p2/doc/Symbol.html
// Helped me with using methods from the symbol class
//
///////////////////////////////////////////////////////////////////////////////



import java.io.File;
import processing.core.PImage;

/**
 * This class models an application to draw a floor plan for a dorm
 * 
 */
public class DormDraw {

  private static PImage backgroundImage; // PImage object that represents the background image
  private static Symbol[] symbols; // non-compact perfect size array storing dorm symbols added to
                                   // the display window

  /**
   * Initializes the DormDraw data fields. This callback method is called once when the program
   * starts.
   */
  public static void setup() {
    // set the background image
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");


    symbols = new Symbol[12];
    // symbols[0] = new Symbol("bed.png", 200, 300);
    // symbols[1] = new Symbol("sofa.png", 700, 300);
    // symbols[4] = new Symbol("dresser.png", 100, 100);
    // symbols[9] = new Symbol("plant.png", 100, 500);


  }

  /**
   * Adds a new element (toAdd) to the perfect size array symbols
   * 
   * @param symbols - a non-compact perfect size array storing elements of type Symbol
   * @param toAdd   - the symbol to add
   */
  public static void addSymbol(Symbol[] symbols, Symbol toAdd) {

    for (int i = 0; i < symbols.length; ++i) {
      if (symbols[i] == null) {
        symbols[i] = toAdd;
        break;
      }
    }

  }
  // Adds a new Symbol (toAdd) to the perfect size array symbols.
  // The toAdd Symbol must be added to the first null position in the array.
  // If the array is full, the method does nothing.


  /**
   * Callback method called once every time a key is pressed. The key that was pressed is returned
   * by the Utility.key() utility method. The DormDraw.keyPressed() method performs specific actions
   * based on the pressed key:
   * 
   * - Pressing 'b' adds a new bed at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'c' adds a new chair at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'd' adds a new dresser at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'k' adds a new desk at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'f' adds a new sofa at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'g' adds a new rug at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'p' adds a new plant at the mouse position if the array symbols is not full.
   * 
   * - Pressing BACKSPACE key deletes a dorm symbol if the mouse is over it. The Backspace key char
   * is accessible through Utility.BACKSPACE. It is also the char with int value 8
   * 
   * - Pressing 'r' rotates the image of a dorm symbol if the mouse is over it.
   * 
   * - Pressing 's' saves the current screen (dorm draw canvas) in a "dormDraw.png" file.
   */
  public static void keyPressed() {
    char input = Utility.key();

    switch (input) {
      case 'b':
      case 'B':
        addSymbol(symbols, new Symbol("bed.png", Utility.mouseX(), Utility.mouseY()));
        break;
      case 'c':
      case 'C':
        addSymbol(symbols, new Symbol("chair.png", Utility.mouseX(), Utility.mouseY()));
        break;
      case 'd':
      case 'D':
        addSymbol(symbols, new Symbol("dresser.png", Utility.mouseX(), Utility.mouseY()));
        break;
      case 'k':
      case 'K':
        addSymbol(symbols, new Symbol("desk.png", Utility.mouseX(), Utility.mouseY()));
        break;
      case 'f':
      case 'F':
        addSymbol(symbols, new Symbol("sofa.png", Utility.mouseX(), Utility.mouseY()));
        break;
      case 'g':
      case 'G':
        addSymbol(symbols, new Symbol("rug.png", Utility.mouseX(), Utility.mouseY()));
        break;
      case 'p':
      case 'P':
        addSymbol(symbols, new Symbol("plant.png", Utility.mouseX(), Utility.mouseY()));
        break;
      case 'r':
      case 'R':
        for (int i = 0; i < symbols.length; ++i) {
          if (symbols[i] != null) {
            if (isMouseOver(symbols[i])) {
              symbols[i].rotate();
              break;
            }
          }
        }
        break;
      case Utility.BACKSPACE:
        for (int i = 0; i < symbols.length; ++i) {
          if (symbols[i] != null) {
            if (isMouseOver(symbols[i])) {
              symbols[i] = null;
              break;
            }
          }
        }
        break;
      case 's':
      case 'S':
        Utility.save("DormDraw.png");
        break;
    }


  }



  /**
   * Checks if the mouse is over a given symbol.
   * 
   * @param symbol - reference to a given dorm symbol
   * @return true if the mouse is over the given symbol object (i.e. over the frame of the image of
   *         the symbol), false otherwise
   */
  public static boolean isMouseOver(Symbol symbol) {

    // check if x coordinate of mouse is within the X range of the symbol
    if (Utility.mouseX() <= (symbol.x() + symbol.width() / 2)
        && Utility.mouseX() >= (symbol.x() - symbol.width() / 2)) {
      // check if y coordinate of mouse is within Y range of the symbol
      if (Utility.mouseY() <= (symbol.y() + symbol.height() / 2)
          && Utility.mouseY() >= (symbol.y() - symbol.height() / 2)) {
        return true;
      }
    }
    // return false if mouse is not over the specified symbol
    return false;
  }

  /**
   * Callback method called once after every time the mouse button is pressed.
   */
  public static void mousePressed() {

    for (int i = 0; i < symbols.length; ++i) {
      // first, make sure the symbol element i is not null
      if (symbols[i] != null) {
        // if not null, check is mouse is over this element
        if (isMouseOver(symbols[i])) {
          symbols[i].startDragging();
          break;

        }

      }
    }


  }



  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < symbols.length; ++i) {
      if (symbols[i] != null)
        symbols[i].stopDragging();
    }


  }



  /**
   * This callback method continuously draws and updates the application display window. It is
   * automatically called directly after setup() and continuously executes until the program is
   * stopped.
   */
  public static void draw() {
    Utility.background(Utility.color(255, 250, 250));
    // Draw the background image at the center of the screen
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);


    for (int i = 0; i < symbols.length; ++i) {

      if (symbols[i] != null)
        symbols[i].draw(); // where i is a valid index within the array symbols.

    }


  }



  /**
   * runs the application
   * 
   * @param args
   */
  public static void main(String[] args) {
    Utility.runApplication();

  }

}
