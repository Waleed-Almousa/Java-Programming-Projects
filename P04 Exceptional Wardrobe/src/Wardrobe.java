//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 - Wardrobe Class
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
// javadoc comments provided by instructors
// Online Sources:
//
//
//
///////////////////////////////////////////////////////////////////////////////


import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.text.ParseException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * An instantiable class that represents a Wardrobe. A Wardrobe object contains and manages
 * Clothing. For use in the Wardrobe Manager project.
 */
public class Wardrobe {

  private Clothing[] wardrobe; // an oversized compacted array that stores the clothing inside the
                               // wardrobe

  private int wardrobeSize; // the number of clothing in this wardrobe


  /**
   * Creates a new Wardrobe object that is empty with the given capacity
   * 
   * @param capacity - the number of clothing that the wardrobe can fit
   * @throws IllegalArgumentException - with a descriptive message if the capacity is
   *                                  non-posotive(less than or equal to zero)
   */
  public Wardrobe(int capacity) {

    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be posotive");
    }

    this.wardrobeSize = 0;
    this.wardrobe = new Clothing[capacity];

    for (int i = 0; i < capacity; ++i) {
      this.wardrobe[i] = null;
    }

  }


  /**
   * Finds and returns the piece of clothing with the matching description and brand. the
   * comparisons are CASE INSENSITIVE
   * 
   * @param description - the description of the piece of clothing to find
   * @param brand       - the brand of the piece of clothing to find
   * @return - the clothing object in the wardrobe that matches the given description and brand
   * @throws NoSuchElementException - with descriptive message if the clothing does not exist in the
   *                                wardrobe
   */
  public Clothing getClothing(String description, String brand) {
    for (int i = 0; i < this.wardrobeSize; ++i) {
      if (this.wardrobe[i].getDescription().equalsIgnoreCase(description)) {
        if (this.wardrobe[i].getBrand().equalsIgnoreCase(brand)) {
          return this.wardrobe[i];
        }
      }
    }
    throw new NoSuchElementException(
        "No item in this wardrobe matches specified brand and description");

  }



  /**
   * Adds a piece of clothing at the end of the wardrobe. If the wardrobe does not have room for the
   * piece of clothing, the wardrobe expands by doubling in capacity. Then adds the new piece of
   * clothing.
   * 
   * @param toAdd- the piece of clothing to add to the wardrobe
   * @throws IllegalArgumentException - with a descriptive message if toAdd is already in wardrobe
   */
  public void addClothing(Clothing toAdd) throws IllegalArgumentException {
    try {
      this.getClothing(toAdd.getDescription(), toAdd.getBrand());
      throw new IllegalArgumentException("Item already in wardrobe");
    } catch (NoSuchElementException e) {
      // if wardrobe is full, double its size and add new clothing
      if (this.wardrobe[this.wardrobe.length - 1] != null) {
        // copy orignial array
        int oldSize = this.wardrobeSize;
        Clothing[] origArray = new Clothing[oldSize];
        for (int i = 0; i < oldSize; ++i) {
          origArray[i] = this.wardrobe[i];
        }

        // update size of new array
        this.wardrobe = new Clothing[this.wardrobe.length * 2];
        // add all previous elements to new wardrobe
        for (int i = 0; i < oldSize; ++i) {
          this.wardrobe[i] = origArray[i];
        }
        // add new element to array
      }
      // once wardrobe is not full, add clothing to first null element
      for (int i = 0; i < this.wardrobe.length; ++i) {
        if (this.wardrobe[i] == null) {
          this.wardrobe[i] = toAdd;
          this.wardrobeSize++;
          break;
        }
      }
    }
  }


  /**
   * Wears the piece of Clothing in this Wardrobe equal to the provided Clothing on the given date.
   * 
   * @param toWear - the piece of clothing in the Wardrobe that we want to wear
   * @param year   - the year that it will be worn
   * @param month  - the month that it will be worn
   * @param day    - the day that it will be worn
   * @throws IllegalArgumentException - with a descriptive message if the year is less than 1, or
   *                                  the month is outside the range [1,12]
   */
  public void wearClothing(Clothing toWear, int year, int month, int day) {

    if (year < 1)
      throw new IllegalArgumentException("Year must be at least 1");
    if (month < 1 || month > 12)
      throw new IllegalArgumentException("Month must be in range [1, 12]");

    for (int i = 0; i < this.wardrobeSize; ++i) {
      if (this.wardrobe[i].equals(toWear)) {
        int timesWorn = this.wardrobe[i].getNumOfTimesWorn() + 1;
        this.wardrobe[i] = new Clothing(toWear.getDescription(), toWear.getBrand(), timesWorn,
            LocalDate.of(year, month, day));
        break;
      }
    }

  }

  /**
   * Getter for the capacity of this wardrobe
   * 
   * @return the number of pieces of clothing this wardrobe can potentially hold
   */
  public int capacity() {
    return this.wardrobe.length;
  }


  /**
   * Getter for the size of this wardrobe
   * 
   * @return the number of pieces of clothing in this wardrobe
   */
  public int size() {
    return this.wardrobeSize;
  }

  /**
   * Removes the piece of clothing from the wardrobe that has a matching description and brand.
   * 
   * @param description - the description of the piece of clothing to remove
   * @param brand       - the brand of the piece of clothing to remove
   * @throws IllegalStateException  - with a descriptive message if the wardrobe is empty
   * @throws NoSuchElementException - with a descriptive message if the piece of clothing is not in
   *                                the wardrobe
   */
  public void removeClothing(String description, String brand) {

    if (this.wardrobeSize == 0)
      throw new IllegalStateException("Wardrobe is empty");
    try {
      this.getClothing(description, brand);
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Piece of clothing is not in wardrobe");
    }

    for (int i = 0; i < this.wardrobe.length; ++i) {
      if (this.wardrobe[i] == null)
        break;
      if (this.wardrobe[i].getBrand().equalsIgnoreCase(brand)
          && this.wardrobe[i].getDescription().equalsIgnoreCase(description)) {
        for (int j = i; j < this.wardrobe.length - 1; ++j) {
          this.wardrobe[j] = null;
          this.wardrobe[j] = this.wardrobe[j + 1];
          this.wardrobe[j + 1] = null;
        }
        this.wardrobe[this.wardrobeSize] = null;


      }

    }
    this.wardrobeSize--;

  }

  /**
   * Removes all pieces of clothing from the wardrobe whose last worn date is BEFORE the given day,
   * month, and year.
   * 
   * @param year  - the year of the date to use to remove clothes
   * @param month - the month of the date to use to remove clothes
   * @param day   - the day of the date to use to remove clothes
   */
  public void removeAllClothingWornBefore(int year, int month, int day) {

    LocalDate otherDate = LocalDate.of(year, month, day);
    for (int i = 0; i < this.wardrobeSize; ++i) {
      if (this.wardrobe[i].getLastWornDate() == null) {
        this.removeClothing(this.wardrobe[i].getDescription(), this.wardrobe[i].getBrand());
        i--;
      } else if (this.wardrobe[i].getLastWornDate().isBefore(otherDate)) {
        this.removeClothing(this.wardrobe[i].getDescription(), this.wardrobe[i].getBrand());
        i--;
      }
    }

  }



  /**
   * Removes all pieces of clothing from the wardrobe who have been worn less times than the given
   * threshold.
   * 
   * @param threshold - the upperbound (exclusive) of number of times worn
   */
  public void removeAllClothingWornNumTimes(int threshold) {
    for (int i = 0; i < this.wardrobeSize; ++i) {
      if (this.wardrobe[i].getNumOfTimesWorn() < threshold) {
        this.removeClothing(this.wardrobe[i].getDescription(), this.wardrobe[i].getBrand());
        i--;
      }
    }

  }

  /**
   * Creates a new Clothing object based on the given String formatted
   * "description,brand,lastWornDate,timesWorn".
   * 
   * @param str - the String parse to make a Clothing object
   * @return a Clothing object with the pieces of information in the given string
   * @throws ParseException - with a descriptive message if the string does not have the 4 required
   *                        pieces of information OR if there was an issue converting pieces of
   *                        information to an int or Date object
   */
  public static Clothing parseClothing(String str) throws ParseException {

    String[] parts = str.split(",");
    if (parts.length != 4) {
      throw new ParseException("Invalid format: must contain 4 parts separated by commas", 0);
    }

    if (parts[0] == null || parts[0].isBlank() || parts[1] == null || parts[1].isBlank())
      throw new ParseException("Description and brand must not be null", 0);

    String description = parts[0];
    String brand = parts[1];
    LocalDate lastWornDate = null;
    int timesWorn = 0;

    try {
      if (!parts[2].equals("null")) {
        String[] dateParts = parts[2].split("/");
        if (dateParts.length != 3)
          throw new ParseException("Improperly formatted date", 0);
        int year = Integer.parseInt(dateParts[2]);
        int day = Integer.parseInt(dateParts[1]);
        int month = Integer.parseInt(dateParts[0]);

        if (year == 0 || month == 0 || day == 0)
          throw new ParseException("Bad m/d/y", 0);

        lastWornDate = LocalDate.of(year, month, day);
      }
      timesWorn = Integer.parseInt(parts[3]);
    } catch (NumberFormatException e) {
      throw new ParseException("Error parsing last date worn or times worn", 0);
    }

    return new Clothing(description, brand, timesWorn, lastWornDate);
  }



  /**
   * Loads all of pieces of clothing into this wardrobe from the designated file. Each piece of
   * clothing in the Wardrobe is written on its own line, formatted as
   * description,brand,lastWornDate,timesWorn. The date must be formatted MM/DD/YYYY. If a line is
   * NOT properly formatted 1) the String "Cannot parse line to Clothing object" should be printed
   * out to the console and 2) will be skipped, the method should continue to read the remaining
   * lines.
   * 
   * @param saveFile - the File that the information should be read from
   * @return true if ANY of the lines from the file were parsed successfully into Clothing for this
   *         Wardrobe, false otherwise
   */
  public boolean loadFromFile(File saveFile) {
    int count = 0;
    try (Scanner scanner = new Scanner(saveFile)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        try {
          Clothing clothing = parseClothing(line);
          this.addClothing(clothing);
          count++;
        } catch (ParseException e) {
          System.out.println("Cannot parse line to Clothing object: " + line);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e.getMessage());
    }

    if (count > 0)
      return true;
    else
      return false;
  }


  /**
   * Saves all of pieces of clothing in this wardrobe to the designated file. Each piece of clothing
   * in the Wardrobe is written on its own line, formatted as
   * description,brand,lastWornDate,timesWorn. The date must be formatted MM/DD/YYYY.For example,
   * lets say a piece of clothing had a description of "black t-shirt", brand of "Gildan", a
   * lastWorn date of October 25th, 2023, and has been worn 15 times. Then the line in the text file
   * is "black t-shirt,Gildan,10/25/2023,15"\n.
   * 
   * @param saveFile - the File that the information should be written to
   * @return true if the file saved successfully, false otherwise
   * 
   */
  public boolean saveToFile(File saveFile) {

    int count = 0;

    try (PrintWriter writer = new PrintWriter(saveFile)) {
      for (int i = 0; i < this.wardrobeSize; i++) {
        Clothing clothing = wardrobe[i];
        String line = clothing.getDescription() + "," + clothing.getBrand() + ","
            + (clothing.getLastWornDate() != null ? clothing.getLastWornDate().toString() : "null")
            + "," + clothing.getNumOfTimesWorn() + "\n";
        writer.print(line);
        count++;
      }
    } catch (FileNotFoundException e) {
      System.out.println("Failed to save to file: " + e.getMessage());
    }

    if (count > 0)
      return true;
    else
      return false;


  }


  /**
   * Gets the array that contains all the Clothing in the wardrobe.
   * 
   * @return the wardrobe array
   */
  protected Clothing[] getArray() {
    return this.wardrobe;
  }

  /**
   * Creates and returns a string representation of this Wardrobe object. Each piece of clothing in
   * the wardrobe should be printed in order on a new line enclosed in [] brackets. The last line
   * should NOT have a new line character.
   * 
   * @Override toString in class Object
   * @return the String representation of this Wardrobe object
   */
  public String toString() {
    String result = "";
    for (int i = 0; i < this.wardrobeSize; i++) {
      result += "[" + this.wardrobe[i].toString() + "]\n";
    }
    return result;

  }



}
