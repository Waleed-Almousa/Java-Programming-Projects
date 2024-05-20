//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 - Toy Class
// Course: CS 300 Spring 2024
//
// Author: Waleed Almousa
// Email: walmousa@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Parth Amrute
// Partner Email: Amrute@wisc.edu
// Partner Lecturer's Name: Mounda Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Instructors helped answer questions on Piazza
// javadoc comments provided by instructors
// Online Sources:
//
//
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models Toy objects in the p05 Toy Saga II program
 */
public class Toy extends GraphicObject implements MouseListener, Movable {

  private boolean isDragging; // indicates whether this Toy object is being dragged or not



  /**
   * Constructs a new Toy object positioned at the center of the display window. The image assigned
   * to this Toy object is defined by the provided filename. A newly created Toy object is not being
   * dragged and have been rotated zero times.
   * 
   * @param filename - filename of the image of this Toy object.
   */
  public Toy(String filename) {
    super(filename);
    this.isDragging = false;
  }



  /**
   * 
   * Constructs a new Toy object positioned at the specific x and y position within the display
   * window. The image assigned to this Toy object is defined by the provided filename. A newly
   * created Toy object is not being dragged and have been rotated zero times.
   * 
   * @param filename - filename of the image of this Toy object.
   * @param x        - x-position (horizontal position) of this Toy object
   * @param y        - y-position (vertical position) of this Toy object
   */
  public Toy(String filename, int x, int y) {
    super(filename, x, y);
    this.isDragging = false;
  }


  /**
   * Draws this Toy to the display window at its current (x,y) position taking into account of its
   * moves. This method first moves this toy by calling its move() method. Then, it draws it to the
   * screen as a GraphicObject by calling the draw() method defined in the super class.
   */
  @Override
  public void draw() {
    this.move();
    super.draw();

  }

  /**
   * Checks whether this Toy object is dragging
   * 
   * @return true if this toy object is dragging, false otherwise
   */
  public boolean isDragging() {

    return (this.isDragging);

  }


  /**
   * Starts dragging this object by setting its instance field isDragging to true.
   */
  public void startDragging() {
    this.isDragging = true;
  }


  /**
   * Stops dragging this object by setting its instance field isDragging to false.
   */
  public void stopDragging() {
    this.isDragging = false;

  }


  /**
   * Moves this toy object with the specific dx, and dy moves. This toy should not get out of the
   * boundaries of the display window of the toy saga.
   * 
   * @param dx - horizontal move
   * @param dy - verticle move
   */
  protected void move(int dx, int dy) {


    if (this.x < 0) {
      this.x = 0;
    } else if (this.x > toySaga.width) {
      this.x = toySaga.width;
    }
    if (this.y < 0) {
      this.y = 0;
    } else if (this.y > toySaga.height) {
      this.y = toySaga.height;

    }
    this.x += dx;
    this.y += dy;



  }


  /**
   * If this toy is dragging, this method moves it following the mouse moves. The current x-position
   * of the mouse is given by toySaga.mouseX The current y-position of the mouse is given by
   * toySaga.mouseY The old x-position of the mouse (in the frame previous to the current frame) is
   * given by toySaga.pmouseX The old y-position of the mouse (in the frame previous to the current
   * frame) is given by toySaga.pmouseY
   * 
   * Specified by: move in interface Movable
   */
  public void move() {
    if (isDragging) {
      int dx = toySaga.mouseX - toySaga.pmouseX;
      int dy = toySaga.mouseY - toySaga.pmouseY;
      move(dx, dy);
    }

  }

  /**
   * Checks whether this Toy object is over a given point: (x, y) position in the screen.
   *
   * 
   * @param x - x-position within the display window
   * @param y - y-position within the display window
   * @return true if this toy is over the specific (x,y) coordinates.
   */
  public boolean isOver(int x, int y) {

    int w = image.width; // width of toy object
    int h = image.height; // height of toy object


    // check if this.x is within toys boundaries
    if (x <= (this.x + (w / 2)) && x >= (this.x - (w / 2))) {
      // check if y coordinate of mouse is within Y range of the symbol
      if (y <= (this.y + (h / 2)) && y >= (this.y - (h / 2))) {
        return true;
      }
    }
    return false;



  }

  /**
   * If no toy is dragging within the toy saga, this method begins dragging this Toy if the mouse is
   * over it and no toy is dragging in the toy saga scene
   * 
   * Specified by: onClick in interface MouseListener
   */
  public void onClick() {

    if (toySaga.noToyIsDragging() && isOver(toySaga.mouseX, toySaga.mouseY)) {
      startDragging();
    }

  }

  /**
   * 
   * Stops dragging this Toy
   * 
   * Specified by:
   * 
   * onRelease in interface MouseListener
   */
  public void onRelease() {
    this.stopDragging();

  }


  /*
   * 
   * Returns true the mouse is over the this Toy
   */
  public boolean isMouseOver() {
    return isOver(toySaga.mouseX, toySaga.mouseY);

  }



}
