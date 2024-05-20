//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 - Furniture
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
// Online Sources: NA
//
///////////////////////////////////////////////////////////////////////////////


import processing.core.PImage;
import java.io.File;

/**
 * Furniture class: a class that creates and initializes furniture objects, and determines their
 * behaviors when created and drawn
 */
public class Furniture {

  public final PImage IMAGE; // field that stores image of furnitue
  private String name; // stores name of furniture

  private int x; // stores x position of furniture
  private int y; // stores y position of furniture

  /**
   * A constructor which takes as argument a String name, an int x, and an int y, in that order, and
   * sets the corresponding instance variables.
   * 
   * @param name - name of furniture object: can be "bed", "box", "nightstand", or "rug"
   * @param x    - x position of center of furniture object
   * @param y    - y position of center of furniture object
   */
  public Furniture(String name, int x, int y) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.IMAGE = Utility.loadImage("images" + File.separator + name + ".png");
  }


  /**
   * 
   * A constructor which takes as argument a String name, and sets corresponding instance variables.
   * x and y position are set to the center of the application
   * 
   * @param name - name of furniture object: can be "bed", "box", "nightstand", or "rug"
   */
  public Furniture(String name) {
    this(name, Utility.width() / 2, Utility.height() / 2);

  }


  /**
   * getter method for the x field that returns x position of object
   * 
   * @return the current x position of the object
   */
  public int getX() {
    return x;
  }

  /**
   * Getter method for the y field
   * 
   * @return the current y position of the furniture object instance
   */
  public int getY() {
    return y;
  }

  /**
   * Getter method for the name field; used to access name of object
   * 
   * @return name of furniture object
   */
  public String name() {
    return name;
  }


  /**
   * draws the Furniture object at its current position no return value
   */
  public void draw() {
    Utility.image(IMAGE, x, y);
  }



}
