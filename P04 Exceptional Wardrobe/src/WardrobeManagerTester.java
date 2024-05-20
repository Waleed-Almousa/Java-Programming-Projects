//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 - Exceptional Wardrobe
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
//
//
//
///////////////////////////////////////////////////////////////////////////////


import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.text.ParseException;



/**
 * A tester class for the Wardrobe Manager project. It contains various tests to check the
 * correctness of the Wardrobe and Clothing classes.
 */
public class WardrobeManagerTester {

  /**
   * Tests both of the Clothing constructors and all getters for correctness. This test accounts for
   * the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   * 
   * @author Michelle
   */
  public static boolean testClothingConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try {
      // test the 2-argument constructor
      Clothing c = new Clothing("black t-shirt", "Gildan");

      // check that the four instance data fields have been initialized correctly
      // using the getters to do this we are also checking their correctness
      // in a bad implementation either 1) the constructor didn't intialize a data field correctly
      // OR 2) the getter doesn't return the correct value
      if (!c.getDescription().equals("black t-shirt"))
        return false;
      if (!c.getBrand().equals("Gildan"))
        return false;
      if (c.getNumOfTimesWorn() != 0)
        return false;
      if (c.getLastWornDate() != null)
        return false;

      // test the 4 argument constructor
      // same idea as the previous test case
      LocalDate date = LocalDate.of(2024, 2, 14); // create a date object for last worn
      c = new Clothing("jeans", "Levi", 3, date);
      if (!c.getDescription().equals("jeans"))
        return false;
      if (!c.getBrand().equals("Levi"))
        return false;
      if (c.getNumOfTimesWorn() != 3)
        return false;
      if (!c.getLastWornDate().equals(date))
        return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad
                            // implementation
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests that both of the Clothing constructors throw the correct type of exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   * 
   * @author Michelle and Hobbes
   */
  public static boolean testClothingConstructorExceptions() {
    // Here we call constructors with input that should lead to an IllegalArgumentException
    // on a correct (good) implementation. ALL of these cases SHOULD throw exceptions,
    // so we test them in separate try-catch statements to verify that each individual
    // case throws an exception.

    try {
      // test the 2 argument constructor with a blank description
      new Clothing(" ", "Gildan");

      return false; // no exception was thrown when it should have been; it's a broken
                    // implementation
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }

    try {
      // and make sure a blank brand will also throw an exception
      new Clothing("black t-shirt", "  ");

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    try {
      // test the 4 argument constructor with a blank description
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing(" ", "Gildan", 4, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    try {
      // and verifying that a blank brand will also throw an exception
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing("black t-shirt", "  ", 6, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message,
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    // passed all the tests!
    return true;
  }

  /**
   * Tests for the correctness of the Clothing classes' wearClothing() method. This test accounts
   * for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   * 
   */
  public static boolean testClothingWear() {

    Clothing testCloth = new Clothing("Black-t", "Nike");

    // testing for bad year
    try {
      testCloth.wearClothing(0, 10, 10);
      return false;
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    // testing for bad month
    try {
      testCloth.wearClothing(10, 15, 5);
      return false;
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the Wardrobe constructor and all getters for correctness. This test accounts for the fact
   * a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */

  public static boolean testWardrobeConstructorAndGetters() {



    Wardrobe testW = new Wardrobe(10);

    int expectedCap = 10;
    Clothing toAdd = new Clothing("black T", "Nike");
    testW.addClothing(toAdd);
    if (testW.capacity() != expectedCap)
      return false;

    if (!testW.getClothing("black T", "Nike").equals(toAdd)) {
      return false;
    }
    if (testW.size() != 1)
      return false;



    return true;
  }

  /**
   * Tests that the Wardrobe constructor throws the correct type of exception(s) with a message in
   * situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testWardrobeConstructorExceptions() {

    try {
      Wardrobe testW = new Wardrobe(0);
      return false;
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) {
      return false;
    }


    return true;
  }

  /**
   * Tests that the Wardrobe's addClothing() method throws the correct type of exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothingExceptions() {

    Wardrobe testW = new Wardrobe(10);
    Clothing toAdd = new Clothing("black T", "Nike");

    testW.addClothing(toAdd);

    try {
      testW.addClothing(toAdd);
      return false;
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }

    return true;
  }

  /**
   * Tests the Wardrobe's addClothing() method for correctness. This test accounts for the fact a
   * bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothing() {

    Wardrobe testW = new Wardrobe(10);
    Clothing toAdd = new Clothing("black T", "Nike");

    testW.addClothing(toAdd);


    if (!testW.getClothing("black T", "Nike").equals(toAdd))
      return false;

    Clothing toAdd2 = new Clothing("white hat", "adidas", 4, LocalDate.of(2004, 2, 4));

    testW.addClothing(toAdd2);

    if (!testW.getClothing("white haT", "adiDAS").equals(toAdd2))
      return false;


    return true;
  }


  /**
   * Tests the Wardrobe's getClothing() method for correctness. This test accounts for the fact a
   * bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothing() {

    Wardrobe testW = new Wardrobe(10);
    Clothing toAdd = new Clothing("black T", "Nike");

    testW.addClothing(toAdd);

    if (!testW.getClothing("black T", "Nike").equals(toAdd))
      return false;

    return true;
  }



  /**
   * Tests that the Wardrobe's getClothing() method throws the correct type of exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothingExceptions() {


    Wardrobe testW = new Wardrobe(10);
    Clothing toAdd = new Clothing("black T", "Nike");

    testW.addClothing(toAdd);

    try {
      testW.getClothing("white T", "adidas");
      return false;
    } catch (NoSuchElementException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }

    return true;


  }

  /**
   * Tests that the Wardrobe's removeClothing() method throws the correct type of exception(s) with
   * a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothingExceptions() {

    Wardrobe testW = new Wardrobe(10);

    try {
      testW.removeClothing("black", "nike");
      return false;
    } catch (IllegalStateException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }

    Clothing toAdd = new Clothing("black T", "Nike");
    testW.addClothing(toAdd);

    try {
      testW.removeClothing("Yellow", "Addidas");
      return false;
    } catch (NoSuchElementException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }

    return true;
  }

  /**
   * Tests the Wardrobe's removeClothings() method for correctness. This test accounts for the fact
   * a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothing() {


    Wardrobe testW = new Wardrobe(10);
    Clothing toAdd = new Clothing("black T", "Nike");
    testW.addClothing(toAdd);

    testW.removeClothing("black T", "Nike");

    if (testW.size() != 0)
      return false;

    try {
      testW.getClothing("black T", " Nike");
      return false;
    } catch (NoSuchElementException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }

    return true;
  }

  /**
   * Tests the Wardrobe's removeAllClothingWornBefore() method for correctness. This test accounts
   * for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornBefore() {



    Wardrobe testW = new Wardrobe(10);
    Clothing toAdd = new Clothing("black T", "Nike", 1, LocalDate.of(1999, 9, 8));
    Clothing toAdd2 = new Clothing("white T", "add", 1, LocalDate.of(1, 4, 8));


    testW.addClothing(toAdd);
    testW.addClothing(toAdd2);

    testW.removeAllClothingWornBefore(2000, 10, 12);

    if (testW.size() != 0)
      return false;

    try {
      testW.getClothing("black T", " Nike");
      return false;
    } catch (NoSuchElementException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }


    return true;
  }

  /**
   * Tests the Wardrobe's removeAllClothingWornNumTimes() method for correctness. This test accounts
   * for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornNumTimes() {


    Wardrobe testW = new Wardrobe(10);
    Clothing toAdd = new Clothing("black T", "Nike", 1, LocalDate.of(1999, 9, 8));
    Clothing toAdd2 = new Clothing("white T", "add", 0, LocalDate.of(1, 4, 8));
    Clothing toAdd3 = new Clothing("yellow box", "drip", 10, LocalDate.of(1, 4, 8));



    testW.addClothing(toAdd);
    testW.addClothing(toAdd2);
    testW.addClothing(toAdd3);

    testW.removeAllClothingWornNumTimes(3);

    if (testW.size() != 1)
      return false;

    if (testW.toString().isBlank() || testW.toString() == null)
      return false;

    return true;
  }

  /**
   * Tests that the Wardrobe's parseClothing() method throws the correct type of exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothingExceptions() {


    try {
      // Test case with incomplete information
      Wardrobe.parseClothing("black T,Nike,10/23/2022");
      return false; // If no exception is thrown, test fails
    } catch (ParseException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }

    try {
      // Test case with invalid date format
      Wardrobe.parseClothing("black T,Nike,2022-10-23,4");
      return false; // If no exception is thrown, test fails
    } catch (ParseException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }

    try {
      // Test case with non-numeric timesWorn
      Wardrobe.parseClothing("black T,Nike,10/23/2022,abc");
      return false; // If no exception is thrown, test fails
    } catch (ParseException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    }


    return true;
  }

  /**
   * Tests the Wardrobe's parseClothing method for correctness. This test accounts for the fact a
   * bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothing() {

    try {
      // Test case with valid input
      Clothing clothing1 = Wardrobe.parseClothing("black T,Nike,10/23/2022,4");
      if (!clothing1.getDescription().equals("black T") || !clothing1.getBrand().equals("Nike")
          || !clothing1.getLastWornDate().toString().equals("2022-10-23")
          || clothing1.getNumOfTimesWorn() != 4) {
        return false; // If parsed Clothing object does not match expected values, test fails
      }

      // Test case with another valid input
      Clothing clothing2 = Wardrobe.parseClothing("white Shirt,Adidas,12/05/2021,2");
      if (!clothing2.getDescription().equals("white Shirt")
          || !clothing2.getBrand().equals("Adidas")
          || !clothing2.getLastWornDate().toString().equals("2021-12-05")
          || clothing2.getNumOfTimesWorn() != 2) {
        return false; // If parsed Clothing object does not match expected values, test fails
      }

      // All tests passed
      return true;
    } catch (ParseException e) {
      return false; // If an unexpected ParseException is thrown, test fails
    }

  }

  /**
   * Runs all testing methods and prints out their results.
   * 
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllTests() {
    boolean test1 = testClothingConstructorExceptions();
    System.out.println("testClothingConstructorExceptions(): " + (test1 ? "pass" : "FAIL"));

    boolean test2 = testClothingConstructorAndGetters();
    System.out.println("testClothingConstructorAndGetters(): " + (test2 ? "pass" : "FAIL"));

    boolean test3 = testClothingWear();
    System.out.println("testClothingWear(): " + (test3 ? "pass" : "FAIL"));

    boolean test4 = testWardrobeConstructorAndGetters();
    System.out.println("testWardrobeConstructorAndGetters(): " + (test4 ? "pass" : "FAIL"));

    boolean test5 = testWardrobeConstructorExceptions();
    System.out.println("testWardrobeConstructorExceptions(): " + (test5 ? "pass" : "FAIL"));

    boolean test6 = testAddClothingExceptions();
    System.out.println("testAddClothingExceptions(): " + (test6 ? "pass" : "FAIL"));

    boolean test7 = testAddClothing();
    System.out.println("testAddClothing(): " + (test7 ? "pass" : "FAIL"));

    boolean test8 = testGetClothing();
    System.out.println("testGetClothing(): " + (test8 ? "pass" : "FAIL"));

    boolean test9 = testGetClothingExceptions();
    System.out.println("testGetClothingExceptions(): " + (test9 ? "pass" : "FAIL"));

    boolean test10 = testRemoveClothing();
    System.out.println("testRemoveClothing(): " + (test10 ? "pass" : "FAIL"));

    boolean test11 = testRemoveClothingExceptions();
    System.out.println("testRemoveClothingExceptions(): " + (test11 ? "pass" : "FAIL"));

    boolean test12 = testRemoveAllClothingWornBefore();
    System.out.println("testRemoveAllClothingWornBefore(): " + (test12 ? "pass" : "FAIL"));

    boolean test13 = testRemoveAllClothingWornNumTimes();
    System.out.println("testRemoveAllClothingWornNumTimes(): " + (test13 ? "pass" : "FAIL"));

    boolean test14 = testParseClothingExceptions();
    System.out.println("testParseClothingExceptions(): " + (test14 ? "pass" : "FAIL"));

    boolean test15 = testParseClothing();
    System.out.println("testParseClothing(): " + (test15 ? "pass" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
        && test11 && test12 && test13 && test14 && test15;
  }

  public static void main(String[] args) {
    System.out.println("runAllTests(): " + runAllTests());
  }
}
