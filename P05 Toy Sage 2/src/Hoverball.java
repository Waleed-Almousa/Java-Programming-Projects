//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 - HoverBall Class
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
///////////////////////////////////////////////////////////////////////////////

import processing.core.PApplet;



/**
 * This class models a Hoverball, which inherits from Toy. Properties of the car include the x and y
 * position of the ball
 */
public class Hoverball extends Toy implements Drawable, MouseListener, Movable {


  // Fields inheritited from class GraphichObject:
  // image, toySaga, x, y

  /**
   * Constructs a new Hoverball whose image filename is ToySaga.HOVERBALL_OFF located at the
   * specified (x, y) position wuthin the display window
   * 
   * @param x - x-position of this Hoverball
   * @param y - y-position of this Hoverball
   */
  public Hoverball(int x, int y) {
    super(ToySaga.HOVERBALL_OFF, x, y);

  }

  /**
   * Sets the image of this Hoverball to ToySaga.HOVERBALL_ON if the toySaga mode is NIGHT_MODE and
   * to ToSaga.HOVERBALL_OFF, otherwise.
   */
  private void switchOnOff() {

    if (toySaga.isNightMode()) {
      this.image = toySaga.loadImage(ToySaga.HOVERBALL_ON);
    } else {
      this.image = toySaga.loadImage(ToySaga.HOVERBALL_OFF);
    }

  }

  /**
   * This method first sets the image of this Hoverball to ToySaga.HOVERBALL_ON if the toySaga mode
   * is NIGHT_MODE and to ToSaga.HOVERBALL_OFF, otherwise. Then, it draws this Hoverball by calling
   * the draw() method defined in its super class.
   * 
   * Specified by: draw in interface Drawable
   * 
   * Overrides: draw in class Toy
   */
  @Override
  public void draw() {
    switchOnOff(); // Sets the image based on day/night mode
    super.draw(); // Call the draw method of the superclass
  }


  /**
   * If the toySaga mode is night mode, this method bounces this Hoverball vertically by moving it
   * with dx equals to zero and dy equals to a factor of 6 * PApplet.sin(toySaga.frameCount * 0.1f)
   * use Math.round to cast the float to an int If the toySaga mode is day mode, this method moves
   * this Hoverball as an ordinary Toy by calling the move() method defined in its super class.
   * 
   * 
   * Specified by: move in interface Movable
   * 
   * Overrides: move in class Toy
   */
  @Override
  public void move() {

    if (toySaga.isNightMode()) {
      int dY = Math.round(6 * PApplet.sin(toySaga.frameCount * 0.1f));
      super.move(0, dY);
    } else {
      super.move();
    }


  }



}
