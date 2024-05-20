//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 - Recursive Suitcase Packing Tester
// Course: CS 300 Spring 2024
//
// Author: Waleed Almousa
// Email: walmousa@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Parth Amrute
// Partner Email: Amrute@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Instructors helped answer questions on Piazza
// javadoc comments provided by instructors
// Online Sources:
//// javadoc comments provided by instructors
//
//
///////////////////////////////////////////////////////////////////////////////



import java.util.ArrayList;



/**
 * Class used for testing the methods in the Packing class.
 */
public class PackingTester {
  /**
   * Tester method for the Packing.rushedPacking() method base cases. It should test at least the
   * following scenarios: - There are no items left to pack in the suitcase - There are items left
   * to pack, but none of them fit
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean rushedPackingBaseTest() {

    ArrayList<Item> items = new ArrayList<>();

    Suitcase s1 = new Suitcase(10, 10, items);

    // Testcase 1: their should be no items left to pack(or in the suitcase) after calling
    // rushedPacking on an empty suitcase with no items to pack
    Suitcase s2 = Packing.rushedPacking(s1);
    if (!s1.getUnpackedItems().isEmpty())
      return false;
    if (!s1.getPackedItems().equals(items))
      return false;
    if (!s2.getUnpackedItems().isEmpty())
      return false;
    if (!s2.getUnpackedItems().equals(items))
      return false;

    // Test case 2: there are items left to pack, but none of them fit. in this scenario, the
    // suitcase should still be empty after calling rushedPacking

    ArrayList<Item> items2 = new ArrayList<>();
    items2.add(new Item("A", 30, 1));
    items2.add(new Item("C", 25, 50));

    Suitcase s3 = new Suitcase(10, 10, items2);

    Suitcase s4 = Packing.rushedPacking(s3);
    if (!s3.getUnpackedItems().equals(items2))
      return false;
    if (!s4.getUnpackedItems().equals(items2)) // unpacked items in s3 and s4 should be the same as
                                               // the items stored in items2
      return false;
    if (!s4.getPackedItems().equals(items))// packed items in the suitcase should equal an empty //
                                           // array
      return false;



    return true; // default return value
  }

  /**
   * Tester method for the Packing.rushedPacking() method recursive cases. It should test at least
   * the following scenarios: - All the items remaining can fit in the suitcase - At least one item
   * remaining cannot fit in the suitcase
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean rushedPackingRecursiveTest() {

    // Test case 1: All the items remaining can fit in the suitcase
    ArrayList<Item> items1 = new ArrayList<>();
    items1.add(new Item("A", 4, 2));
    items1.add(new Item("B", 3, 3));
    items1.add(new Item("C", 2, 4));
    Suitcase suitcase1 = new Suitcase(30, 30, items1);
    Suitcase result1 = Packing.rushedPacking(suitcase1);
    if (!result1.getPackedItems().equals(items1)) // all items should be packed
      return false;
    if (!result1.getUnpackedItems().isEmpty()) // no items should be left unpacked
      return false;


    // Test case 2: At least one item remaining cannot fit in the suitcase
    ArrayList<Item> items2 = new ArrayList<>();
    ArrayList<Item> items3 = new ArrayList<>();
    ArrayList<Item> items4 = new ArrayList<>();

    items2.add(new Item("A", 4, 2));
    items3.add(new Item("A", 4, 2));
    items2.add(new Item("B", 1, 1));
    items3.add(new Item("B", 1, 1));
    items2.add(new Item("C", 10, 10));
    items4.add(new Item("C", 10, 10));

    Suitcase suitcase2 = new Suitcase(10, 10, items2);
    Suitcase result2 = Packing.rushedPacking(suitcase2);
    if (!result2.getPackedItems().equals(items3)) { // packed items should equal items3 arrayList
      return false;
    }
    if (!result2.getUnpackedItems().equals(items4)) { // unpacked items should equal items 4
                                                      // arrayList
      return false;
    }

    return true;

  }

  /**
   * Tester method for the Packing.greedyPacking() method base cases. It should test at least the
   * following scenarios: - There are no items left to pack in the suitcase - There are items left
   * to pack, but none of them fit
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean greedyPackingBaseTest() {

    ArrayList<Item> items = new ArrayList<>();

    Suitcase s1 = new Suitcase(10, 10, items);

    // Testcase 1: their should be no items left to pack(or in the suitcase) after calling
    // rushedPacking on an empty suitcase with no items to pack
    Suitcase s2 = Packing.greedyPacking(s1);
    if (!s1.getUnpackedItems().isEmpty())
      return false;
    if (!s1.getPackedItems().equals(items))
      return false;
    if (!s2.getUnpackedItems().isEmpty())
      return false;
    if (!s2.getUnpackedItems().equals(items))
      return false;

    // Test case 2: there are items left to pack, but none of them fit. in this scenario, the
    // suitcase should still be empty after calling rushedPacking

    ArrayList<Item> items2 = new ArrayList<>();
    items2.add(new Item("A", 30, 1));
    items2.add(new Item("C", 25, 50));

    Suitcase s3 = new Suitcase(10, 10, items2);

    Suitcase s4 = Packing.greedyPacking(s3);
    if (!s3.getUnpackedItems().equals(items2))
      return false;
    if (!s4.getUnpackedItems().equals(items2)) // unpacked items in s3 and s4 should be the same as
                                               // the items stored in items2
      return false;
    if (!s4.getPackedItems().equals(items))// packed items in the suitcase should equal an empty //
                                           // array
      return false;



    return true; // default return value
  }

  /**
   * Tester method for the Packing.greedyPacking() method recursive cases. It should test at least
   * the following scenarios: - At least one item is packed out of order (an item with a higher
   * index is packed before an item with a lower index) - A scenario where the greedy packing method
   * packs more of the suitcase than the rushed packing (you can use the example given in the
   * writeup)
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean greedyPackingRecursiveTest() {

    // make sure base case works
    if (!PackingTester.greedyPackingBaseTest())
      return false;

    // Test case 1: At least one item is packed out of order
    // Create a suitcase with items [A(4x2), B(6x3), C(7x4), D(4x5)]
    // The greedy packing method should pack items in the order C, D, B, A

    ArrayList<Item> items = new ArrayList<>();

    items.add(new Item("A", 4, 2));
    items.add(new Item("B", 6, 3));
    items.add(new Item("C", 7, 4));
    items.add(new Item("D", 4, 5));
    Suitcase suitcase1 = new Suitcase(50, 50, items);

    Suitcase results = Packing.greedyPacking(suitcase1);

    ArrayList<Item> expectedPack = new ArrayList<>();
    expectedPack.add(new Item("C", 7, 4));
    expectedPack.add(new Item("D", 4, 5));
    expectedPack.add(new Item("B", 6, 3));
    expectedPack.add(new Item("A", 4, 2));

    // packed items in results should equal expectedPack
    if (!results.getPackedItems().equals(expectedPack)) {
      return false;
    }


    // unpacked items should be an empty arrayList
    if (!results.getUnpackedItems().isEmpty()) {
      return false;
    }


    // Test case 2: Greedy packing method packs more of the suitcase than the rushed packing
    // Create a suitcase with items [A(4x2), B(6x3), C(7x4), D(4x5)]
    // The greedy packing method should pack items in the order C, D, E, F, G

    ArrayList<Item> items1 = new ArrayList<>();
    items1.add(new Item("A", 4, 2));
    items1.add(new Item("B", 6, 3));
    items1.add(new Item("C", 7, 4));
    items1.add(new Item("D", 4, 5));
    items1.add(new Item("E", 4, 5));
    items1.add(new Item("F", 5, 4));
    items1.add(new Item("G", 2, 6));

    Suitcase rushedPacking = new Suitcase(10, 10, items1);
    Suitcase greedy = new Suitcase(10, 10, items1);

    rushedPacking = Packing.rushedPacking(rushedPacking);
    greedy = Packing.greedyPacking(greedy);

    // greedyPacking should pack more area than rushedPacking
    if (greedy.areaPacked() < rushedPacking.areaPacked())
      return false;



    return true; // default return value
  }

  /**
   * Tester method for the Packing.optimalPacking() method. This tester should test the
   * optimalPacking() method by randomly generating at least TEN (10) different scenarios, and
   * randomly generating at least ONE-HUNDRED (100) different packing solutions for EACH of the
   * scenarios. Each scenario should have at least FIVE (5) random items, and the suitcases should
   * be of size at least 5x5. If any random solution is better than the optimal packing then it is
   * not actually optimal, so the method does not pass the test. You should use the Utilities method
   * to generate random lists of items, and to randomly pack the suitcases.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean optimalPackingRandomTest() {

    ArrayList<Item> items = new ArrayList<>(); // initialize items



    // Loop through each scenario
    for (int s = 0; s < 15; s++) {// testing 15 random scenarios

      // Generate random items for the scenario
      items = Utilities.randomItems(7); // testing 7 random items


      // initialize suitcase
      Suitcase suitcase = new Suitcase(6, 11, items);



      // Perform optimal packing
      Suitcase optimalSolution = Packing.optimalPacking(suitcase);

      // Generate packing solutions and compare with the optimal solution
      for (int p = 0; p < 100; p++) { // generate 100 random packing solutions
        // Generate a random packing solution
        Suitcase randomSolution = Utilities.randomlyPack(new Suitcase(6, 11, items));

        // Compare areas
        if (randomSolution.areaPacked() > optimalSolution.areaPacked()) {
          return false; // test fails if Random solution is better than optimal solution
        }
      }
    }



    return true; // All tests pass


  }

  public static void main(String[] args) {
    boolean allPass = true;
    String printFormat = "%-29s %s\n";

    boolean rushedBase = rushedPackingBaseTest();
    allPass &= rushedBase;
    System.out.printf(printFormat, "rushedPackingBaseTest():", rushedBase);

    boolean rushedRecur = rushedPackingRecursiveTest();
    allPass &= rushedRecur;
    System.out.printf(printFormat, "rushedPackingRecursiveTest():", rushedRecur);

    boolean greedyBase = greedyPackingBaseTest();
    allPass &= greedyBase;
    System.out.printf(printFormat, "greedyPackingBaseTest():", greedyBase);

    boolean greedyRecur = greedyPackingRecursiveTest();
    allPass &= greedyRecur;
    System.out.printf(printFormat, "greedyPackingRecursiveTest():", greedyRecur);

    boolean optimalRandom = optimalPackingRandomTest();
    allPass &= optimalRandom;
    System.out.printf(printFormat, "optimalPackingRandomTest():", optimalRandom);

    System.out.printf(printFormat, "All tests:", allPass);
  }
}
