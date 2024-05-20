//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 - GraphicObjects Class
// Course: CS 300 Spring 2024
//
// Author: Waleed Almousa
// Email: walmousa@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Instructors helped answer questions on Piazza
// javadoc comments provided by instructors
//
//
//
///////////////////////////////////////////////////////////////////////////////



/**
 * This class models GraphicObject objects. This is an implementation of the Drawable interface
 */
public class GraphicObject implements Drawable {

  protected processing.core.PImage image; // Processing image of this GraphicObject

  protected static ToySaga toySaga; // Reference to the ToySaga graphic application where this
                                    // object will be displayed.

  protected int x; // x-position of this GraphicObject object in the display window

  protected int y; // y-position of this GraphicObject object in the display window

  /**
   * 
   * Constructs a new GraphicObject object positioned at the center of the display window.
   * 
   * @param filename - filename of the image of this graphic object
   */
  public GraphicObject(String filename) {
    this.image = toySaga.loadImage(filename);
    this.x = toySaga.width / 2;
    this.y = toySaga.height / 2;

  }


  /**
   * Constructs a new GraphicObject object positioned at the center of the display window. The image
   * assigned to this GraphicObject object is defined by the provided filename.
   * 
   * @param filename - filename of the image of this graphic object
   * @param x        - x-position (horizontal position) of this GraphicObject object
   * @param y        - y-position (vertical position) of this GraphicObject object
   */
  public GraphicObject(String filename, int x, int y) {
    this.image = toySaga.loadImage(filename);
    this.x = x;
    this.y = y;

  }


  /**
   * Mutates the image of this GraphicObject by reloading it
   * 
   * @param filename - filename of the image to load
   */
  public void setImage(String filename) {
    this.image = toySaga.loadImage(filename);

  }


  /**
   * Draws the image of this GraphicObject to the display window at its current (x,y) position
   * Specified by: draw in interface Drawable
   * 
   */
  public void draw() {
    toySaga.image(this.image, x, y);

  }

  /**
   * Gets the x-position of this GraphicObject object
   * 
   * @return the x-position of this GraphicObject object
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the y-position of this GraphicObject object
   * 
   * @return the y-position of this GraphicObject object
   */
  public int getY() {
    return this.y;
  }


  /**
   * Sets the ToySaga PApplet object where this graphic object will be drawn
   * 
   * @param toySaga - PApplet object that represents the display window of the ToySaga app
   */
  public static void setProcessing(ToySaga toySaga) {
    GraphicObject.toySaga = toySaga;


  }



}
