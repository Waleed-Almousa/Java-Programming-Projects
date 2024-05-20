//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 - Recursive Suitcase Packing
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
// Partner Lecturer's Name: Mounda Kacem
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
 * Class used for packing a 2D suitcase with items using various strategies.
 */
public class Packing {


  /**
   * Tries to pack each item in the items list in-order. If an item can fit, it must be packed.
   * Otherwise, it should be skipped. Must be a recursive method.
   *
   * @param suitcase current Suitcase object
   * @return a Suitcase representing the outcome of a strategy in which the items were attempted to
   *         be packed in-order.
   */
  public static Suitcase rushedPacking(Suitcase suitcase) {


    // BASE CASE: if there are no items to unpack, return the suitcase
    if (suitcase.numItemsUnpacked() == 0) {
      return suitcase;
    }

    // initialize the list of items to unpack to loop through
    ArrayList<Item> unpacked = suitcase.getUnpackedItems();

    // RECURSIVE CASE: loop through the items to unpack until the list is empty
    while (unpacked.size() > 0) {
      // get the first item
      Item firstItem = unpacked.get(0);

      // If the item can be packed, create a new suitcase with the item packed in it
      if (suitcase.canPackItem(firstItem)) {
        // create a new suitcase with the item packed in it
        Suitcase packedSuitcase = suitcase.packItem(firstItem);
        // remove the item from list of unpacked items
        unpacked.remove(0);
        // Recursively call rushedPacking on the updated suitcase
        return rushedPacking(packedSuitcase);
      } else {
        // Remove the first item from the list of remaining items
        unpacked.remove(0);
        // skip over this item and continue
      }

    }

    // BASE CASE: if no more items fit or no items left to pack, return the suitcase
    return suitcase;// default return statement

  }

  /**
   * Packs items by greedily packing the largest item which can currently be packed. Must be a
   * recursive method.
   *
   * @param suitcase current Suitcase object
   * @return a Suitcase representing the outcome of a greedy strategy in which at each point the
   *         largest item that can fit is packed.
   */
  public static Suitcase greedyPacking(Suitcase suitcase) {

    // BASE CASE: if there are no items to unpack, return the suitcase
    if (suitcase.numItemsUnpacked() == 0) {
      return suitcase;
    }

    ArrayList<Item> unpacked = suitcase.getUnpackedItems(); // initialize items to loop through and
                                                            // unpack

    // loop through all the items that need to be unpacked
    while (!unpacked.isEmpty()) {


      // initialize maxArea - the largest area of the current items to be unpacked
      int maxArea = 0;

      // initialize the largest index as the first item in the list- this will store the index of
      // the
      // largest item
      int largestIndex = 0;

      // initialize the largest Item as the first item in the list
      Item largestItem = unpacked.get(0);



      // loop through all items to find the largest
      for (int i = 0; i < unpacked.size(); i++) {

        // calculate the current items Area
        int currArea = unpacked.get(i).width * unpacked.get(i).height;

        // if the current item's area is greater than the max Area so far, set the maxArea to the
        // current items area
        if (currArea > maxArea) {
          maxArea = currArea;

          // store the largest item to be unpacked next
          largestItem = unpacked.get(i);

          // store the index of the current largest item
          largestIndex = i;

        }
      } // end of for loop

      // If the largest item can be packed, create a new suitcase with the item packed in it
      if (suitcase.canPackItem(largestItem)) {
        // create a new suitcase with the item packed in it
        Suitcase packedSuitcase = suitcase.packItem(largestItem);

        // remove the item from list of unpacked items
        unpacked.remove(largestIndex);

        // Recursively call greedyPacking on the updated suitcase
        return greedyPacking(packedSuitcase);
      } else {

        // Remove this item from the list of remaining items since this item cannot be packed
        unpacked.remove(largestIndex);
        // skip over this item and continue since it can't be packed
      }

    } // end of while loop

    // BASE CASE: if no more items fit or no items left to pack, return the suitcase
    return suitcase;

  }

  /**
   * Finds the optimal packing of items by trying all packing orders. Must be a recursive method.
   *
   * @param suitcase current Suitcase
   * @return a Suitcase representing the optimal outcome.
   */
  public static Suitcase optimalPacking(Suitcase suitcase) {


    // initialize the best area optimization to the current suitcase
    Suitcase bestSuitcase = suitcase;



    // initialize maxArea- this will store the area that the best permutation covers
    int maxArea = 0;

    // Call the helper function that recursively generates all permutations of the suitcase
    ArrayList<Suitcase> perms = generatePermutations(suitcase, suitcase.getUnpackedItems().size());

    // loop through all permutations to find the one that maximizes the area
    for (int i = 0; i < perms.size(); ++i) {
      if (perms.get(i).areaPacked() > maxArea) {
        maxArea = perms.get(i).areaPacked(); // store area if it is larger than current maxArea
        bestSuitcase = perms.get(i);// store best suitcase
      }
    }

    return bestSuitcase; // return best suitcase


  }


  /**
   * Helper function for OptimalPacking that generates all possible permutations for a suitcase
   * 
   * @param suitcase -the suitcase to generate all permutations for
   * @param toPack   - the number of items left to pack in the suitcase
   * @return an arraylist of suitcases of all the permutations of the suitcase based on what's left
   *         to pack
   */
  public static ArrayList<Suitcase> generatePermutations(Suitcase suitcase, int toPack) {

    // initialize the arraylist of suitcase permutations that will store suitcase permutations
    ArrayList<Suitcase> perms = new ArrayList<Suitcase>();

    // call helperfunction which recursively generates all permutations of the suitcase
    permutationHelper(suitcase, toPack, perms);

    return perms;

  }


  /**
   * Helper function for generatePermutation that recursively generates all permutations for a
   * suitcase
   * 
   * 
   * 
   * @param suitcase - the suitcase to generate all permutations for
   * @param toPack   - the number of items left to pack in the suitcase
   * @param perms    - arraylist of permutations
   */
  private static void permutationHelper(Suitcase suitcase, int toPack, ArrayList<Suitcase> perms) {


    // BASE CASE 1: when no items left to unpack return the suitcase
    if (toPack == 0) {
      perms.add(suitcase);
    }

    // BASE CASE 2: when 1 item is left, attempt to add it and then add suitcase to perms
    if (toPack == 1) {
      Item currItem = suitcase.getUnpackedItems().get(0);
      if (suitcase.canPackItem(currItem)) {
        Suitcase updatedSuit = suitcase.packItem(currItem);
        perms.add(updatedSuit);

      }
      perms.add(suitcase);
    }

    // RECURSIVE CASE: loop through all items to unpack, generating all permutations by recursively
    // calling permutationHelper
    for (int i = 0; i < toPack; i++) {
      // initialize current item to add
      Item currItem = suitcase.getUnpackedItems().get(i);

      // add it to the suitcase if it can be added
      if (suitcase.canPackItem(currItem)) {
        Suitcase updatedSuit = suitcase.packItem(currItem);
        // Recursively call helper function on updated suitcase and decrement toPack
        permutationHelper(updatedSuit, toPack - 1, perms);
      }



    } // end of for loop

    // base case: add suitcase to permutation after going through for loop
    perms.add(suitcase);

  }



}


