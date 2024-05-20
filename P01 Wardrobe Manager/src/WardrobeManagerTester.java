/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: WardrobeManagerTester
// Course: CS 300 Spring 2024
//
// Author: Waleed Almousa
// Email: walmousa@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NA
// Partner Email: NA
// Partner Lecturer's Name: NA
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Instructors helped answer my questions on piazza
// Online Sources:
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2024/spring/p1/doc/WardrobeManager.html
// Used for Javadoc comments before each method
// https://www.digitalocean.com/community/tutorials/java-array-contains-value
// Used for Contains method, showed me the syntax of .equalsignorecase() function
// https://www.digitalocean.com/community/tutorials/java-remove-character-string
// used for getMostRecent function, showed me how to remove "-" from the dates
//
///////////////////////////////////////////////////////////////////////////////


import java.util.Arrays;

/**
 * This class tests that the methods in the WardrobeManager class work as intended
 */
public class WardrobeManagerTester {

  //// CONTAINS

  /**
   * Test method for verifying that an empty array does not contain any clothes
   * 
   * @returns false if any of the conditions we are verifying are not correct, returns true only if
   *          all tests pass
   */
  public static boolean testContainsEmpty() {

    // (1) set up the test variables and call the method we are testing
    String[][] empty = new String[10][];
    int size = 0;
    boolean expected = false;
    boolean actual = WardrobeManager.containsClothing("green crop top", "H&M", empty, size);

    // (2) verify the expected return value
    if (expected != actual)
      return false;

    // (3) verify size is still 0:
    if (size != 0)
      return false;

    // (4) verify that NOTHING ELSE was changed unexpectedly
    for (int i = 0; i < empty.length; ++i) {
      if (empty[i] != null)
        return false;
    }

    // (5) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }



  /**
   * PROVIDED: example test method for verifying whether an item is already in the wardrobe.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testContainsTrue() {
    // (1) set up the test variables and call the method we are testing - EXACT
    // MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = true;
    boolean actual = WardrobeManager.containsClothing("black t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) another test method call, this time with case difference (that should
    // still match!)
    actual = WardrobeManager.containsClothing("dark blue jeans", "LEVI", wardrobe, size);
    if (expected != actual)
      return false;

    // (4) since this method should not modify the array, let's check it against our
    // copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Test method for verifying that an array that does not contain a specified item returns "false"
   * 
   * @returns false if any of the conditions we are verifying are not correct, returns true only if
   *          all tests pass
   */
  public static boolean testContainsFalse() {

    // (1) set up the test variables and call the method we are testing - no match
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = false;
    boolean actual = WardrobeManager.containsClothing("white shirt", "nike", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) since this method should not modify the array, let's check it against our
    // copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    return true;

  }

  //// ADD

  /**
   * PROVIDED: example test method for adding a new clothing item to an EMPTY oversize array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testAddToEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] empty = new String[10][];
    int size = 0;
    int expected = 1;
    int actual = WardrobeManager.addClothing("green crop top", "H&M", empty, size);

    // (2) verify the expected return value
    if (expected != actual)
      return false;

    // (3) verify that the provided array was updated correctly
    if (empty[0] == null)
      return false;
    if (!empty[0][0].equalsIgnoreCase("green crop top") || !empty[0][1].equalsIgnoreCase("H&M")
        || !empty[0][2].equals("never"))
      return false;

    // (4) verify that NOTHING ELSE was changed unexpectedly
    for (int i = 1; i < empty.length; i++) {
      if (empty[i] != null)
        return false;
    }

    // (5) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }

  /**
   * test method for adding a new clothing item to a non-EMPTY oversized array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testAddNonEmpty() {

    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;
    int expected = 3;
    int actual = WardrobeManager.addClothing("green crop top", "H&M", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;


    // (3) verify our new array contains the added item
    if (!WardrobeManager.containsClothing("green crop top", "H&M", wardrobe, 3))
      return false;

    // (5) verify that NOTHING ELSE was changed unexpectedly
    for (int i = 3; i < wardrobe.length; i++) {
      if (wardrobe[i] != null)
        return false;
    }

    return true;

  }

  /**
   * test method for attempting to add a duplicate clothing item to an oversized array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testAddDuplicate() {

    // (1) set up the test variables and call the method we are testing - adding a duplicate
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    int expected = 2;
    int actual = WardrobeManager.addClothing("black t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (4) verify that NOTHING ELSE was changed unexpectedly
    for (int i = 2; i < wardrobe.length; ++i) {
      if (wardrobe[i] != null)
        return false;
    }

    return true;
  }


  /**
   * test method for attempting to add a clothing item to a full, oversized array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testAddToFull() {
    // (1) set up the test variables and call the method we are testing - adding an item to a full
    // array
    String[][] wardrobe =
        {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"}};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    int expected = 2;
    int actual = WardrobeManager.addClothing("green t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;


    return true;
  }

  //// INDEX OF

  /**
   * Verifies that indexOfClothing function returns -1 when used on an empty wardrobe
   * 
   * @returns false if output is not as expected, true otherwise
   */
  public static boolean testIndexOfEmpty() {

    String[][] empty = new String[10][];
    int size = 0;
    int expected = -1;
    int actual = WardrobeManager.indexOfClothing("green crop top", "H&M", empty, size);

    if (expected != actual)
      return false;

    // (3) verify size is still 0:
    if (size != 0)
      return false;

    return true;
  }


  /**
   * Verifies that indexOfClothing function returns the correct when used to find the first or last
   * item in a wardrobe
   * 
   * @returns false if output is not as expected, true otherwise
   */
  public static boolean testIndexOfFirstLast() {

    // testing first:
    String[][] wardrobe =
        {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"}};
    int size = 2;
    int expected = 0;
    int actual = WardrobeManager.indexOfClothing("black t-shirt", "Hanes", wardrobe, size);
    if (expected != actual)
      return false;

    // testing last:
    String[][] wardrobe2 = {{"green crop top", "H&M", "2024-01-02"},
        {"black t-shirt", "Gildan", "2023-10-31"}, {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"}, {"black halter", "asos", "never"}, null};
    int size2 = 5;
    int expected2 = 4;
    int actual2 = WardrobeManager.indexOfClothing("black halter", "asos", wardrobe2, size2);
    if (expected2 != actual2)
      return false;

    return true;
  }



  /**
   * Verifies that indexOfClothing function returns the correct index when used to find an item that
   * is not the first or last item in a wardrobe
   * 
   * @returns false if output is not as expected, true otherwise
   */
  public static boolean testIndexOfMiddle() {

    String[][] wardrobe2 = {{"green crop top", "H&M", "2024-01-02"},
        {"black t-shirt", "Gildan", "2023-10-31"}, {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"}, {"black halter", "asos", "never"}, null};
    int size2 = 5;
    int expected2 = 1;
    int actual2 = WardrobeManager.indexOfClothing("black t-shirt", "Gildan", wardrobe2, size2);
    if (expected2 != actual2)
      return false;

    return true;
  }


  /**
   * Verifies that indexOfClothing function returns the correct index when used to find an item that
   * is not in a wardrobe
   * 
   * @returns false if output is not as expected, true otherwise
   */
  public static boolean testIndexOfNoMatch() {

    String[][] wardrobe2 = {{"green crop top", "H&M", "2024-01-02"},
        {"black t-shirt", "Gildan", "2023-10-31"}, {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"}, {"black halter", "asos", "never"}, null};
    int size2 = 5;
    int expected2 = -1;
    int actual2 = WardrobeManager.indexOfClothing("yellow t-shirt", "Gidn", wardrobe2, size2);
    if (expected2 != actual2)
      return false;

    return true;
  }

  //// WEAR

  /**
   * verifies that wearClothing returns true and updates date when it is supposed to
   * 
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testWearClothingTrue() {

    String[][] wardrobe2 = {{"green crop top", "H&M", "2024-01-02"},
        {"black t-shirt", "Gildan", "2023-10-31"}, {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"}, {"black halter", "asos", "never"}, null};
    int size2 = 5;
    boolean expected2 = true;
    boolean actual2 =
        WardrobeManager.wearClothing("black t-shirt", "Gildan", "2000-11-11", wardrobe2, size2);
    if (expected2 != actual2)
      return false;
    int index = WardrobeManager.indexOfClothing("black t-shirt", "Gildan", wardrobe2, size2);

    if (!wardrobe2[index][2].equals("2000-11-11"))
      return false;

    return true;

  }


  /**
   * verifies that wearClothing returns false when no item in wardrobe matches its parameters
   * 
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testWearClothingNoMatch() {

    String[][] wardrobe2 = {{"green crop top", "H&M", "2024-01-02"},
        {"black t-shirt", "Gildan", "2023-10-31"}, {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"}, null};
    int size2 = 4;
    boolean expected2 = false;
    boolean actual2 =
        WardrobeManager.wearClothing("yellow t-shirt", "nike", "2001-11-11", wardrobe2, size2);
    if (expected2 != actual2)
      return false;

    return true;
  }

  //// BRAND COUNT
  /**
   * 
   * verifies that getBrandCount function correctly counts items of specified brand when all items
   * in the wardrobe match that brand
   * 
   * @return true if method functionality is verified, returns false otherwise
   */
  public static boolean testBrandCountAllMatch() {

    String[][] wardrobe2 =
        {{"green crop top", "GAP", "2024-01-02"}, {"black t-shirt", "GAP", "2023-10-31"},
            {"cargo pants", "GAP", "2023-12-29"}, {"christmas sweater", "GAP", "2023-12-25"}, null};
    int size2 = 4;
    int expected2 = 4;
    int actual2 = WardrobeManager.getBrandCount("GAP", wardrobe2, size2);
    if (expected2 != actual2)
      return false;

    return true;
  }

  /**
   * 
   * verifies that getBrandCount function correctly counts items of specified brand when some items
   * in the wardrobe match that brand
   * 
   * @return true if method functionality is verified, returns false otherwise
   */
  public static boolean testBrandCountSomeMatch() {


    String[][] wardrobe2 = {{"green crop top", "GAP", "2024-01-02"},
        {"black t-shirt", "nike", "2023-10-31"}, {"cargo pants", "adidas", "2023-12-29"},
        {"christmas sweater", "GAP", "2023-12-25"}, null};
    int size2 = 4;
    int expected2 = 2;
    int actual2 = WardrobeManager.getBrandCount("GAP", wardrobe2, size2);
    if (expected2 != actual2)
      return false;

    return true;

  }

  /**
   * 
   * verifies that getBrandCount function correctly counts items of specified brand when no items in
   * the wardrobe match that brand
   * 
   * @return true if method functionality is verified, returns false otherwise
   */
  public static boolean testBrandCountNoMatch() {

    String[][] wardrobe2 = {{"green crop top", "GAP", "2024-01-02"},
        {"black t-shirt", "nike", "2023-10-31"}, {"cargo pants", "adidas", "2023-12-29"},
        {"christmas sweater", "GAP", "2023-12-25"}, null};
    int size2 = 4;
    int expected2 = 0;
    int actual2 = WardrobeManager.getBrandCount("Gildannnn", wardrobe2, size2);
    if (expected2 != actual2)
      return false;

    return true;

  }

  //// COUNT UNWORN

  /**
   * 
   * verifies that getNumUnwornClothes function correctly counts unworn clothes when all items in
   * the wardrobe have never been worn
   * 
   * @return true if method functionality is verified, returns false otherwise
   */
  public static boolean testUnwornCountAllMatch() {

    String[][] wardrobe2 = {{"green crop top", "GAP", "never"}, {"black t-shirt", "nike", "never"},
        {"cargo pants", "adidas", "never"}, {"christmas sweater", "GAP", "never"}, null};
    int size2 = 4;
    int expected2 = 4;
    int actual2 = WardrobeManager.getNumUnwornClothes(wardrobe2, size2);

    if (expected2 != actual2)
      return false;

    return true;
  }


  /**
   * verifies that getNumUnwornClothes function correctly counts unworn clothes when some items in
   * the wardrobe have never been worn
   * 
   * @return true if method functionality is verified, returns false otherwise
   */
  public static boolean testUnwornCountSomeMatch() {


    String[][] wardrobe2 =
        {{"green crop top", "GAP", "2001-12-2"}, {"black t-shirt", "nike", "never"},
            {"cargo pants", "adidas", "2022-10-4"}, {"christmas sweater", "GAP", "never"}, null};
    int size2 = 4;
    int expected2 = 2;
    int actual2 = WardrobeManager.getNumUnwornClothes(wardrobe2, size2);

    if (expected2 != actual2)
      return false;

    return true;
  }


  /**
   * verifies that getNumUnwornClothes function correctly counts unworn clothes when no items in the
   * wardrobe have never been worn
   * 
   * @return true if method functionality is verified, returns false otherwise
   */
  public static boolean testUnwornCountNoMatch() {



    String[][] wardrobe2 = {{"green crop top", "GAP", "2001-12-2"},
        {"black t-shirt", "nike", "2002-01-22"}, {"cargo pants", "adidas", "2022-10-4"},
        {"christmas sweater", "GAP", "2023-05-10"}, null};
    int size2 = 4;
    int expected2 = 0;
    int actual2 = WardrobeManager.getNumUnwornClothes(wardrobe2, size2);

    if (expected2 != actual2)
      return false;

    return true;

  }

  //// MOST RECENTLY WORN

  /**
   * PROVIDED: example test method for verifying that the most recently worn item is found
   * correctly. Note that this tester is not comprehensive; if you wish to verify additional
   * behavior you are welcome to add additional tester methods (please specify such methods to be
   * PRIVATE).
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testMostRecentlyWorn() {
    // (1) set up the test variables and call the method we are testing - EXACT
    // MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
        {"grey UW hoodie", "gildan", "2020-03-16"}, {"dark blue jeans", "Levi", "2024-01-25"},
        {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 2;
    int actual = WardrobeManager.getMostRecentlyWorn(wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) since this method should not modify the array, let's check it against our
    // copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  //// REMOVE BY INDEX

  /**
   * Test function to verify the size of the array when last item is removed
   * 
   * @return true if method functionality if verified, false otherwise
   */
  public static boolean testRemoveLastItem() {

    // test removing last non-null element
    String[][] wardrobe2 = {{"green crop top", "GAP", "2001-12-2"},
        {"black t-shirt", "nike", "2002-01-22"}, {"cargo pants", "adidas", "2022-10-4"},
        {"christmas sweater", "GAP", "2023-05-10"}, null};
    int size2 = 4;
    int expected2 = 3;
    int actual2 = WardrobeManager.removeClothingAtIndex(size2 - 1, wardrobe2, size2);

    if (expected2 != actual2)
      return false;

    return true;


  }



  /**
   * Test function to verify the size of the array when first item is removed
   * 
   * @return true if method functionality if verified, false otherwise
   */
  public static boolean testRemoveFirstItem() {

    String[][] wardrobe2 = {{"green crop top", "GAP", "2001-12-2"},
        {"black t-shirt", "nike", "2002-01-22"}, {"cargo pants", "adidas", "2022-10-4"},
        {"christmas sweater", "GAP", "2023-05-10"}, null};
    int size2 = 4;
    int expected2 = 3;
    int actual2 = WardrobeManager.removeClothingAtIndex(0, wardrobe2, size2);

    if (expected2 != actual2)
      return false;

    return true;


  }

  /**
   * Test function to verify the size of the array when an index>size is passed to the remove
   * function
   * 
   * @return true if method functionality if verified, false otherwise
   */
  public static boolean testRemoveBadIndex() {

    String[][] wardrobe2 = {{"green crop top", "GAP", "2001-12-2"},
        {"black t-shirt", "nike", "2002-01-22"}, {"cargo pants", "adidas", "2022-10-4"},
        {"christmas sweater", "GAP", "2023-05-10"}, null};
    int size2 = 4;
    int expected2 = 4;
    int actual2 = WardrobeManager.removeClothingAtIndex(20, wardrobe2, size2);

    if (expected2 != actual2)
      return false;

    return true;


  }

  //// REMOVE ALL UNWORN

  /**
   * Test function to verify the size of the array when the removAllUnworn function is used and no
   * items are unworn
   * 
   * @return true if method functionality if verified, false otherwise
   */
  public static boolean testRemoveUnwornNoMatch() {

    String[][] wardrobe2 = {{"green crop top", "GAP", "2001-12-2"},
        {"black t-shirt", "nike", "2002-01-22"}, {"cargo pants", "adidas", "2022-10-4"},
        {"christmas sweater", "GAP", "2023-05-10"}, null};
    int size2 = 4;
    int expected2 = 4;
    int actual2 = WardrobeManager.removeAllUnworn(wardrobe2, size2);

    if (expected2 != actual2)
      return false;

    return true;
  }


  /**
   * Test function to verify the size of the array when removeAllUnworn function is used and some
   * items in specified wardrobe are unworn
   * 
   * @return true if method functionality if verified, false otherwise
   */
  public static boolean testRemoveUnwornSomeMatch() {


    String[][] wardrobe2 =
        {{"green crop top", "GAP", "never"}, {"black t-shirt", "nike", "2002-01-22"},
            {"cargo pants", "adidas", "never"}, {"christmas sweater", "GAP", "2023-05-10"}, null};
    int size2 = 4;
    int expected2 = 2;
    int actual2 = WardrobeManager.removeAllUnworn(wardrobe2, size2);

    if (expected2 != actual2)
      return false;

    // verify no unworn clothes are present after removing all unworn
    int expect = 0;
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe2, actual2);
    if (expect != actual)
      return false;

    return true;

  }


  /**
   * Test function to verify the size of the array when removeAllUnworn function is used and ALL
   * items in specified wardrobe are unworn
   * 
   * @return true if method functionality if verified, false otherwise
   */
  public static boolean testRemoveUnwornAllMatch() {


    String[][] wardrobe2 = {{"green crop top", "GAP", "never"}, {"black t-shirt", "nike", "never"},
        {"cargo pants", "adidas", "never"}, {"christmas sweater", "GAP", "never"}, null};
    int size2 = 4;
    int expected2 = 0;
    int actual2 = WardrobeManager.removeAllUnworn(wardrobe2, size2);

    if (expected2 != actual2)
      return false;

    // verify no unworn clothes are present after removing all unworn
    int expect = 0;
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe2, actual2);
    if (expect != actual)
      return false;

    return true;


  }

  /**
   * PROVIDED: calls all tester methods and displays the results of the tests.
   * 
   * All tests are called in the order they were provided in this file. The output of this method
   * will NOT be graded so you may modify it however you wish.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("CONTAINS:\n  " + (testContainsEmpty() ? "pass" : "FAIL") + ", "
        + (testContainsTrue() ? "pass" : "FAIL") + ", " + (testContainsFalse() ? "pass" : "FAIL"));
    System.out.println("ADD:\n  " + (testAddToEmpty() ? "pass" : "FAIL") + ", "
        + (testAddNonEmpty() ? "pass" : "FAIL") + ", " + (testAddDuplicate() ? "pass" : "FAIL")
        + ", " + (testAddToFull() ? "pass" : "FAIL"));
    System.out.println("INDEX OF:\n  " + (testIndexOfEmpty() ? "pass" : "FAIL") + ", "
        + (testIndexOfFirstLast() ? "pass" : "FAIL") + ", "
        + (testIndexOfMiddle() ? "pass" : "FAIL") + ", "
        + (testIndexOfNoMatch() ? "pass" : "FAIL"));
    System.out.println("WEAR:\n  " + (testWearClothingTrue() ? "pass" : "FAIL") + ", "
        + (testWearClothingNoMatch() ? "pass" : "FAIL"));
    System.out.println("BRAND COUNT:\n  " + (testBrandCountAllMatch() ? "pass" : "FAIL") + ", "
        + (testBrandCountSomeMatch() ? "pass" : "FAIL") + ", "
        + (testBrandCountNoMatch() ? "pass" : "FAIL"));
    System.out.println("UNWORN COUNT:\n  " + (testUnwornCountAllMatch() ? "pass" : "FAIL") + ", "
        + (testUnwornCountSomeMatch() ? "pass" : "FAIL") + ", "
        + (testUnwornCountNoMatch() ? "pass" : "FAIL"));
    System.out.println("MOST RECENTLY WORN:\n  " + (testMostRecentlyWorn() ? "pass" : "FAIL"));
    System.out.println("REMOVE BY INDEX:\n  " + (testRemoveLastItem() ? "pass" : "FAIL") + ", "
        + (testRemoveFirstItem() ? "pass" : "FAIL") + ", "
        + (testRemoveBadIndex() ? "pass" : "FAIL"));
    System.out.println("REMOVE UNWORN:\n  " + (testRemoveUnwornNoMatch() ? "pass" : "FAIL") + ", "
        + (testRemoveUnwornSomeMatch() ? "pass" : "FAIL") + ", "
        + (testRemoveUnwornAllMatch() ? "pass" : "FAIL"));

  }

}
