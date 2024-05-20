//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 - Toy Saga
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
//
///////////////////////////////////////////////////////////////////////////////

import processing.core.PImage;
import java.util.ArrayList;
import java.io.File;


/**
 * This class implements the main user interface of the p03 Toy Saga I program
 */
public class ToySaga {

  private static PImage backgroundImage; // stores the applications background image
  private static ArrayList<Furniture> furnitureList;
  private static ArrayList<Toy> toyList;
  private static final String BOX_NAME = "box";
  private static final int MAX_TOYS_COUNT = 8;


  /**
   * Initializes the ToySage data fields. This callback method is called once when the program
   * starts.
   */
  public static void setup() {
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");

    furnitureList = new ArrayList<>();
    toyList = new ArrayList<>();

    furnitureList.add(new Furniture("bed", 520, 270));
    furnitureList.add(new Furniture("rug", 220, 370));
    furnitureList.add(new Furniture("nightstand", 325, 240));
    furnitureList.add(new Furniture(BOX_NAME, 90, 230));
  }


  /**
   * 
   * This callback method continuously draws and updates the application display window. It is
   * automatically called directly after setup() and continuously executes until the program is
   * stopped. This method first draws the background image to the center of the screen. Updates the
   * contents of the toyList to remove any toy which is over the box furniture, and draws furniture
   * and toy objects.
   */
  public static void draw() {

    // Draw the background image at the center of the screen
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    for (Furniture furniture : furnitureList) {
      furniture.draw();
    }

    for (Toy toy : toyList) {
      toy.draw();
    }


    for (int i = 0; i < toyList.size(); ++i) {
      if (toyList.get(i).isOver(getToyBox())) {
        toyList.remove(i);
      }
    }

  }

  /**
   * Returns the Furniture object with a name matching BOX_NAME (exact match: case sensitive
   * comparison) if any is found.
   * 
   * @return : the box Furniture, or null of no match found.
   */
  public static Furniture getToyBox() {

    for (Furniture furniture : furnitureList) {
      if (furniture.name().equals(BOX_NAME)) {
        return furniture;
      }
    }

    return null;
  }

  /**
   * Returns the toy which is currently dragging. We assume that there is at most one toy object
   * being dragged at a given time.
   * 
   * @return the toy being dragged, or null if no toy is dragging.
   * 
   */
  public static Toy getDraggingToy() {

    for (Toy toy : toyList) {
      if (toy.isDragging()) {
        return toy;
      }
    }
    return null;


  }

  /**
   * Callback method called once after every time the mouse button is pressed. If no toy is
   * dragging, this method checks whether the mouse is over a toy and start dragging it. If the
   * mouse is over multiple toys, only the toy at the lowest index will start dragging.
   * 
   */
  public static void mousePressed() {

    Toy draggingToy = getDraggingToy();

    if (draggingToy == null) {
      for (int i = 0; i < toyList.size(); i++) {
        if (toyList.get(i).isOver(Utility.mouseX(), Utility.mouseY())) {
          toyList.get(i).startDragging();
          return;
        }
      }

    }


  }

  /**
   * 
   * 
   * Callback method called every time the mouse button is released. This method stops dragging any
   * toy stored in the toy list.
   */
  public static void mouseReleased() {
    // Stop dragging any toy stored in the toy list
    for (int i = 0; i < toyList.size(); i++) {
      toyList.get(i).stopDragging();
    }
  }

  /**
   * Callback method called once every time a key is pressed. The key that was pressed is returned
   * by the Utility.key() utility method. The ToySaga.keyPressed() method performs the below actions
   * based on the pressed key:
   * 
   * - Pressing 'c' or 'C' adds a new toy car at the mouse position if the MAX TOYS COUNT is not
   * reached.
   * 
   * - Pressing 't' or 'T'adds a teddy bear toy at the mouse position if the MAX TOYS COUNT is not
   * reached.
   * 
   * - Pressing 'r' or 'R' rotates a toy if the mouse is over it. Only one toy is rotated at once.
   * 
   */
  public static void keyPressed() {

    char input = Utility.key();
    if (toyList.size() < MAX_TOYS_COUNT) {
      switch (input) {
        case 'c':
        case 'C':
          toyList.add(new Toy("car", Utility.mouseX(), Utility.mouseY()));
          break;
        case 't':
        case 'T':
          toyList.add(new Toy("teddyBear", Utility.mouseX(), Utility.mouseY()));
          break;
        case 'r':
        case 'R':
          for (int i = 0; i < toyList.size(); ++i) {
            if (toyList.get(i).isOver(Utility.mouseX(), Utility.mouseY())) {
              toyList.get(i).rotate();
              break;
            }
          }
          break;

      }
    }

  }

  public static void main(String[] args) {

    Utility.runApplication(); // starts the application


  }

}
