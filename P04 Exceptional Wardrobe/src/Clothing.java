//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 - Clothing Class
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
// Persons: Instructors helped answer questions on Piazza. Javadoc comments provided by instructors
// Online Sources:
//
//
//
///////////////////////////////////////////////////////////////////////////////


import java.time.LocalDate;
import java.lang.Object;

/*
 * An instantiable class that represents a piece of Clothing. For use in the Wardrobe Manager
 * project.
 */
public class Clothing {

  private String description; // The description for this piece of clothing.

  private String brand; // The brand for this piece of clothing.

  private LocalDate lastWornDate; // The date that this piece of clothing was last worn.

  private int timesWorn; // The number of times this piece of clothing has been worn.



  /**
   * Creates a new clothing object with the given description and brand. This piece of clothing has
   * been never worn, so its lastWornDate will be null.
   * 
   * @param description the description for this piece of clothing
   * @param brand       - the brand of this piece of clothing
   * @throws IllegalArgumentException - with a descriptive message if the description or brand is a
   *                                  blank
   */
  public Clothing(String description, String brand) {
    if (description == null || description.equals(" ") || description.equals("")
        || description.isBlank()) {
      throw new IllegalArgumentException("Description is blank");
    }
    if (brand == null || brand.equals(" ") || brand.equals("") || brand.isBlank()) {
      throw new IllegalArgumentException("Brand is blank");
    }
    this.description = description;
    this.brand = brand;
    this.lastWornDate = null;
    this.timesWorn = 0;

  }


  /**
   * Creates a new clothing object with the given description, brand, timesWorn and lastWorn date
   * values.
   * 
   * @param description  - the description for this piece of clothing
   * @param brand        - the brand of this piece of clothing
   * @param timesWorn    - the number of times this piece of clothing has been worn
   * @param lastWornDate - the date that this piece of clothing was last worn
   * @throws IllegalArgumentException - with a descriptive message if the description or brand is a
   *                                  blank
   */
  public Clothing(String description, String brand, int timesWorn, LocalDate lastWornDate) {
    if (description == null || description.equals(" ") || description.equals("")
        || description.isBlank()) {
      throw new IllegalArgumentException("Description is blank");
    }
    if (brand == null || brand.equals(" ") || brand.equals("") || brand.isBlank()) {
      throw new IllegalArgumentException("Brand is blank");
    }

    this.description = description;
    this.brand = brand;
    this.lastWornDate = lastWornDate;
    this.timesWorn = timesWorn;
  }

  /**
   * Updates the number of times this piece of clothing has been worn and the last worn date
   * 
   * @param year  - the year of the last worn date
   * @param month - the month of the last worn date
   * @param day   - the day of the last worn date
   * @throws IllegalArgumentException - with a descriptive message if the year is less than 1, or
   *                                  the month is outside the range [1, 12]
   */
  public void wearClothing(int year, int month, int day) throws IllegalArgumentException {
    if (year < 1) {
      throw new IllegalArgumentException("Year should be greater than or equal to 1");
    }
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException("Month must be in range [1, 12]");
    }

    this.timesWorn++;
    this.lastWornDate = LocalDate.of(year, month, day);
  }

  /**
   * Getter for description of this piece of clothing
   * 
   * @return this clothing's description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Getter for brand of this piece of clothing
   * 
   * @return this clothing's brand
   */
  public String getBrand() {
    return this.brand;
  }

  /**
   * Getter for the date this piece of clothing was last worn
   * 
   * @return this clothing's last worn date
   */
  public LocalDate getLastWornDate() {
    return this.lastWornDate;
  }


  /**
   * Getter for the number of times this piece of clothing has been worn
   * 
   * @return this clothing's number of times worn
   */
  public int getNumOfTimesWorn() {
    return this.timesWorn;
  }



  /**
   * Checks if Object o equals this clothing object, that is the current instance of Clothing.
   * 
   * @Override equals in class Object
   * @param o - the object to check if it equal to this piece of clothing
   * @return true if and only if 1) o is of type Clothing 2) the brands match ignoring case AND 3)
   *         the descriptions match ignoring case, otherwise return false
   */
  public boolean equals(Object o) {
    if ((o instanceof Clothing)) {
      if (this.getBrand().equals(((Clothing) o).getBrand())) {
        if (this.getDescription().equals(((Clothing) o).getDescription())) {
          return true;
        }
      }
    }


    return false;
  }


  /**
   * Creates and returns a string representation of this Clothing object. The String is to be
   * formatted as follows: description,brand,lastWornDate,timesWorn The date must be formatted
   * MM/DD/YYYY. For example, lets say a piece of clothing had a description of "black t-shirt",
   * brand of "Gildan", a lastWorn date of September 5th, 2023, and has been worn 15 times. Then the
   * returned string is "black t-shirt,Gildan,09/05/2023,15". If the last worn date is null, then
   * the lastWornDate should be the String "null".
   * 
   * @Override toString in class Object
   * @return the String representation of this Clothing object
   */
  public String toString() {

    String fullDate;
    try {
      String date = this.lastWornDate.toString();
      String[] parts = date.split("-");
      fullDate = parts[1] + "/" + parts[2] + "/" + parts[0];
    } catch (NullPointerException e) {
      fullDate = "null";
    }
    String toPrint = this.description + "," + this.brand + "," + fullDate + "," + this.timesWorn;
    return toPrint;
  }



}
