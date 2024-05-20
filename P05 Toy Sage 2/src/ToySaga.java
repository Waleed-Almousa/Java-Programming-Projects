//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 - ToySage Class
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
// Online Sources:
//
//
//
///////////////////////////////////////////////////////////////////////////////



import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class implements the main graphic user interface (GUI) of the p05 Toy Saga II program
 */
public class ToySaga extends PApplet {

  // CONSTANTS
  // PATH to the folder of all images
  private static final String IMAGES_PATH = "images" + File.separator;

  // filename of the day background image of this toy saga
  protected static final String DAY_BACKGROUND = IMAGES_PATH + "backgroundDay.png";

  // filename of the night background image of this toy saga
  protected static final String NIGHT_BACKGROUND = IMAGES_PATH + "backgroundNight.png";

  // filename of the image of the bed
  protected static final String BED = IMAGES_PATH + "bed.png";

  // filename of the image of the nightstand
  protected static final String NIGHTSTAND = IMAGES_PATH + "nightstand.png";

  // filename of the image of the rug
  protected static final String RUG = IMAGES_PATH + "rug.png";

  // filename of the image of the car
  protected static final String CAR = IMAGES_PATH + "car.png";

  // filename of the image of the teddy bear
  protected static final String BEAR = IMAGES_PATH + "teddyBear.png";

  // filename of the image of the hoverball when it is on (night mode)
  protected static final String HOVERBALL_ON = IMAGES_PATH + "hoverBallOn.png";

  // filename of the image of the hoverball when it is off (day mode)
  protected static final String HOVERBALL_OFF = IMAGES_PATH + "hoverBallOff.png";

  // day mode
  protected static final String DAY_MODE = "DAY";

  // night mode
  protected static final String NIGHT_MODE = "NIGHT";

  // Maximum number of visible toys that can be stored in the drawableObjects list.
  private static final int MAX_TOYS_COUNT = 8;

  // other fields
  private static PImage backgroundImage; // PImage object that represents the background image



  // The drawableObjects arraylist stores elements of type Drawable (interface Drawable) ONLY.
  private ArrayList<Drawable> drawableObjects;

  // mode represents the current mode of this ToySaga application.
  private String mode;


  /**
   * Driver method that launches the application by calling this.runApplication()
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {

    PApplet.main("ToySaga");



  }

  /**
   * Gets the current mode of this Toy Saga app. The mode might be DAY or NIGHT.
   * 
   * @return the current mode of this application
   */
  public String getMode() {
    return mode;
  }

  /**
   * Returns true if this ToySaga mode is NIGHT_MODE
   * 
   * @return true if this ToySaga mode is NIGHT_MODE
   */
  public boolean isNightMode() {
    return mode.equals(NIGHT_MODE);
  }


  /**
   * Switches the mode of this toy saga application and loads the background image of the switched
   * mode. <BR>
   * 
   * Meaning, sets the mode to NIGHT_MODE if it was DAY_MODE and vice versa, and updates the
   * background image accordingly.
   */
  public void switchMode() {
    if (mode.equals(DAY_MODE)) {
      mode = NIGHT_MODE;
      backgroundImage = loadImage(NIGHT_BACKGROUND);
    } else {
      mode = DAY_MODE;
      backgroundImage = loadImage(DAY_BACKGROUND);
    }
  }

  // uncomment the below code and complete the missing implementations
  /**
   * Sets the size of the display window of this graphic application
   */
  @Override
  public void settings() {
    this.size(800, 600);
  }

  /**
   * Sets the title and defines the initial environment properties of this graphic application. <br>
   * This method initializes all the data fields defined in this class.
   */
  @Override
  public void setup() {

    SwitchButton.setProcessing(this);

    // Set the Processing for the GraphicObject class to be this ToySaga object
    GraphicObject.setProcessing(this);



    this.getSurface().setTitle("P5 Toy Saga v2.0");
    this.textAlign(CENTER, CENTER);// horizontal alignment: center, vertical alignment: center
    this.imageMode(CENTER);// interprets the second and third parameters of image() as the
    // image’s center point.
    this.rectMode(CORNERS); // interprets the first two parameters of rect() as the location
    // of one corner, and the third and fourth parameters as the
    // location of the opposite corner.
    this.focused = true;// sets the processing program to be focused (true), meaning that
    // it is active and will accept input from mouse or keyboard
    backgroundImage = loadImage(DAY_BACKGROUND);
    mode = DAY_MODE;
    drawableObjects = new ArrayList<>();


    // Create a new SwitchButton at position (565, 20) and add it to the drawableObjects list
    SwitchButton switchButton = new SwitchButton(565, 20);
    drawableObjects.add(switchButton);

    // Create a new GraphicObject BED at position (520, 270) and add it to the drawableObjects list
    GraphicObject bed = new GraphicObject(BED, 520, 270);
    drawableObjects.add(bed);

    // Create a new GraphicObject RUG at position (220, 370) and add it to the drawableObjects list
    GraphicObject rug = new GraphicObject(RUG, 220, 370);
    drawableObjects.add(rug);

    // Create a new GraphicObject NIGHTSTAND at position (325, 240) and add it to the
    // drawableObjects list
    GraphicObject nightstand = new GraphicObject(NIGHTSTAND, 325, 240);
    drawableObjects.add(nightstand);

  }

  /**
   * This callback method continuously draws and updates the application display window. It is
   * automatically called directly after setup() and continuously executes until the program is
   * stopped.
   * 
   * This method first draws the background image to the center of the screen. Then, it draws every
   * object stored in the drawableObjects list
   */
  @Override
  public void draw() {

    image(backgroundImage, width / 2, height / 2);
    for (Drawable obj : drawableObjects)
      obj.draw();
  }

  /**
   * Callback method called once after every time the mouse button is pressed.
   * 
   * This method calls the onClick() method on every instance of MouseListener stored in the
   * drawableObjects list
   * 
   */
  @Override
  public void mousePressed() {
    for (Drawable obj : drawableObjects) {
      if (obj instanceof MouseListener) {
        MouseListener listener = (MouseListener) obj;
        if (listener.isMouseOver()) {
          listener.onClick();
        }
      }
    }
  }



  /**
   * Callback method called every time the mouse button is released.
   * 
   * This method calls the onRelease() method on every instance of MouseListener stored in the
   * drawableObjects list
   * 
   */
  @Override
  public void mouseReleased() {

    for (Drawable obj : drawableObjects) {
      if (obj instanceof MouseListener) {
        MouseListener listener = (MouseListener) obj;
        listener.onRelease();
      }
    }

  }

  /**
   * Callback method called once every time a key is pressed. The key that was pressed is returned
   * by the this.key() this method.<BR>
   * The ToySaga.keyPressed() method performs the below actions based on the pressed key: <BR>
   *
   * - Pressing 'c' or 'C' adds a new Car object at the mouse position if the MAX TOYS COUNT is not
   * reached. <BR>
   * - Pressing 't' or 'T' adds a new TeddyBear object at the mouse position if the MAX TOYS COUNT
   * is not reached. <BR>
   * - Pressing 'h' or 'H' adds a new Hoverball object at the mouse position if the MAX TOYS COUNT
   * is not reached. <BR>
   * - Pressing 'd' or 'D' sets/switches the mode to DAY_MODE and loads the DAY_BACKGROUND for the
   * background image of this application. <BR>
   * - Pressing 'n' or 'N' sets/switches the mode to NIGHT_MODE and loads the NIGHT_BACKGROUND for
   * the background image of this application. <BR>
   *
   */
  @Override
  public void keyPressed() {

    char input = Character.toUpperCase(this.key);
    switch (input) {
      case 'C':
        if (getToyCount() < MAX_TOYS_COUNT) {
          drawableObjects.add(new Car(mouseX, mouseY));
        }
        break;
      case 'T':
        if (getToyCount() < MAX_TOYS_COUNT) {
          drawableObjects.add(new TeddyBear(mouseX, mouseY));
        }
        break;
      case 'H':
        if (getToyCount() < MAX_TOYS_COUNT) {
          drawableObjects.add(new Hoverball(mouseX, mouseY));
        }
        break;
      case 'D':
        mode = DAY_MODE;
        backgroundImage = loadImage(DAY_BACKGROUND);
        break;
      case 'N':
        mode = NIGHT_MODE;
        backgroundImage = loadImage(NIGHT_BACKGROUND);
        break;
    }

  }


  /**
   * Returns true if NO Toy object is currently dragging. We assume that there is at most one object
   * being dragged at a given time.
   * 
   * @return true if no toy is being dragged, or false otherwise.
   */
  public boolean noToyIsDragging() {
    for (Drawable obj : drawableObjects) {
      if (obj instanceof Toy && ((Toy) obj).isDragging()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets the number of Toy instances present in this application
   * 
   * @return the number of Toy objects stored in the drawableObjects list
   */
  public int getToyCount() {
    int count = 0;
    for (Drawable obj : drawableObjects) {
      if (obj instanceof Toy) {
        count++;
      }
    }
    return count;
  }



}
