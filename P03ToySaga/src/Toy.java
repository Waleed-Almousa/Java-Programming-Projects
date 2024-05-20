//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 - Toy
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
// Javadoc comments copied from project instructions
// Online Sources:
// - https://www.geeksforgeeks.org/find-two-rectangles-overlap/ helped code the logic for isOver
//////////////// method
//
///////////////////////////////////////////////////////////////////////////////



import java.io.File;
import processing.core.PImage;

/**
 * 
 * Toy class: this class creates toy objects and implements methods that determine the behaviors of
 * Toy objects, including their sizes, rotation of toy objects, determines whether the toy is being
 * dragged or not, draws the toys, and determines whether or not a toy object is overlapping or
 * intersecting with other toy objects or furniture objects
 */
public class Toy {

  public final PImage IMAGE; // image of toy item

  private int x; // x-position of toy
  private int y; // y position of toy

  private boolean isDragging; // stores a boolean indicating whether this object is being dragged or
                              // not.
  private int rotations; // counts the number of times this object has been rotated 90 degrees
                         // clockwise


  /**
   * Constructor to create a toy object at a custom position
   * 
   * @param name - name of toy objects: either "teddyBear" or "car"
   * @param x    - x position of the center of the toy object
   * @param y    - y position of the center of the toy object
   */
  public Toy(String name, int x, int y) {
    this.IMAGE = Utility.loadImage("images" + File.separator + name + ".png");
    this.x = x;
    this.y = y;
    this.isDragging = false;
    this.rotations = 0;
  }

  /**
   * Overloaded Constructor to create a toy object at the center of the application
   * 
   * @param name - name of toy object: either "teddyBear" or "car"
   */
  public Toy(String name) {
    this.IMAGE = Utility.loadImage("images" + File.separator + name + ".png");
    this.x = Utility.width() / 2;
    this.y = Utility.height() / 2;
    this.isDragging = false;
    this.rotations = 0;

  }


  /**
   * getter method used to access current x position of toy object
   * 
   * @return the current x position of toy object
   */
  public int getX() {
    return x;
  }


  /**
   * getter method that returns the current y poisiton of the center of a Toy object
   * 
   * @return the current y position of a toy instance
   */
  public int getY() {
    return y;
  }


  /**
   * 
   * instance method that Sets the X position of toy object to desured x position given by param x
   * 
   * @param x desired x position of toy object
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * instance method that sets the y position of toy object to the specified y position given by
   * param int y
   * 
   * @param y desired y position of toy object which the toy is set to after this method is called
   */
  public void setY(int y) {
    this.y = y;
  }


  /**
   * instance method that returns the number of times a toy has been rotated
   * 
   * @return the number of times a toy instance has been rotated
   */
  public int getRotationsCount() {
    return rotations;
  }


  /**
   * instance method for determining whether or not a toy instance is currently being dragged or not
   * 
   * @return true if toy is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }


  /**
   * instance method that sets isDragging to true when a toy starts to be dragged
   */
  public void startDragging() {
    isDragging = true;
  }

  /**
   * 
   * instance method that sets isDragging to false when a toy stops being dragged
   */
  public void stopDragging() {
    isDragging = false;
  }


  /**
   * 
   * instance method called whenever a toy is rotated increments the rotations field by one each
   * time a toy is rotated
   */
  public void rotate() {
    rotations++;
  }


  /**
   * instance method used to move a toy object which takes as arguments an int dx and an int dy, and
   * adds these values to the x and y fields, respectively. If the updated x or y value is outside
   * the bounds of the window, it is reset to the closest value which is inside the window. For
   * instance, if x is-5 after adding dx, it is reset to 0. increments x position of toy by dx, and
   * increments y position of toy by dy
   * 
   * @param dx the change in the x position of the toy
   * @param dy the change in the y position of the toy
   */
  public void move(int dx, int dy) {
    x += dx;
    y += dy;


    // ensure x and y are within the bounds
    if (x < 0)
      x = 0;
    if (x > Utility.width())
      x = Utility.width();
    if (y < 0)
      y = 0;
    if (y > Utility.height())
      y = Utility.height();

  }


  /**
   * first updates the position of the Toy using move() if it is dragging, and then draws the Toy at
   * the updated position. If the Toy is dragging, moves it by the difference between the current
   * and previous positions (current - previous) of the mouse.
   * 
   * Once the position has been updated, draws the Toy by calling the following provided private
   * helper method drawToyImage().
   * 
   */
  public void draw() {
    if (isDragging) {
      move(Utility.mouseX() - Utility.pmouseX(), Utility.mouseY() - Utility.pmouseY());
    }
    // Draw the toy image
    drawToyImage();
  }


  /**
   * Helper method to draw an image accounting for any rotations to the screen. The implementation
   * of this method is fully provided in the write-up.
   */
  private void drawToyImage() {
    Utility.pushMatrix();
    Utility.translate(x, y);
    Utility.rotate(this.rotations * Utility.PI / 2);
    Utility.image(IMAGE, 0.0f, 0.0f);
    Utility.popMatrix();

  }


  /**
   * checks whether a toy instance is overlapping with a specified x and y position. returns a
   * boolean indicating whether the position (x,y) is within the rectangle of the PImage
   * representing this Toy, including the boundary.
   * 
   * @param x the x position that is checked if it is overlapping with the toy
   * @param y the y position that is checked if it is overlapping with the toy
   * @return true is x and y position are within the boundaries of the toy object, false otherwise
   */
  public boolean isOver(int x, int y) {
    int w = IMAGE.width;
    int h = IMAGE.height;
    if (this.rotations % 2 == 1) {
      int temp = w;
      w = h;
      h = temp;
    }

    if (x <= (this.x + (w / 2)) && x >= (this.x - (w / 2))) {
      // check if y coordinate of mouse is within Y range of the symbol
      if (y <= (this.y + (h / 2)) && y >= (this.y - (h / 2))) {
        return true;
      }
    }


    return false;
  }

  /**
   * Overloaded method to check if the toy overlaps with a furniture object returns a boolean
   * indicating whether the image of this Toy overlaps with the image of the given Furniture object.
   * That is, it returns true if any point within the Toy image is within the Furniture image,
   * including the boundary
   * 
   * @param other the furniture object that is compared to the toy object and checked if it is
   *              overlapping
   * @return true if any point of the toy object instersects with any point of the furniture object,
   *         false otherwise
   */
  public boolean isOver(Furniture other) {



    int toyX = this.getX();
    int toyY = this.getY();
    int toyW = IMAGE.width;
    int toyH = IMAGE.height;
    if (this.rotations % 2 == 1) {
      int temp = toyW;
      toyW = toyH;
      toyH = temp;
    }

    int furnX = other.getX();
    int furnY = other.getY();
    int furnW = other.IMAGE.width;
    int furnH = other.IMAGE.height;


    // CITE: logic of overlapping rectangles - GeeksForGeeks.com
    // helped me with coding the logic for if the rectangles overlap in the following if statements

    if ((toyX - toyW / 2 >= furnX - furnW / 2 && toyX - toyW / 2 <= furnX + furnW / 2)
        || (toyX + toyW / 2 >= furnX - furnW / 2 && toyX + toyW / 2 <= furnX + furnW / 2)) {
      if ((toyY - toyH / 2 >= furnY - furnH / 2 && toyY - toyH / 2 <= furnY + furnH / 2)
          || (toyY + toyH / 2 >= furnY - furnH / 2 && toyY + toyH / 2 <= furnY + furnH / 2)) {
        return true;
      }
    }

    if ((furnX - furnW / 2 >= toyX - toyW / 2 && furnX - furnW / 2 <= toyX + toyW / 2)
        || (furnX + furnW / 2 >= toyX - toyW / 2 && furnX + furnW / 2 <= toyX + toyW / 2)) {
      if ((furnY - furnH / 2 >= toyY - toyH / 2 && furnY - furnH / 2 <= toyY + toyH / 2)
          || (furnY + furnH / 2 >= toyY - toyH / 2 && furnY + furnH / 2 <= toyY + toyH / 2)) {
        return true;
      }
    }

    return false;

  }



}
