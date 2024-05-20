//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P01 - Wardrobe Manager
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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2024/spring/p1/doc/WardrobeManager.html
// Used for Javadoc comments before each method
// https://www.digitalocean.com/community/tutorials/java-array-contains-value
// Used for Contains method, showed me the syntax of .equalsignorecase() function
// https://www.digitalocean.com/community/tutorials/java-remove-character-string
// used for getMostRecent function, showed me how to remove "-" from the dates
//
///////////////////////////////////////////////////////////////////////////////



/**
 * WardbobeManager class: A collection of static methods for effectively managing the contents of a
 * wardrobe. A wardrobe entry has three components: A general description of the clothing item (e.g.
 * "purple t-shirt" or "dark jeans") A brand name, if purchased, or "handmade" The date the item was
 * last worn, formatted as "YYYY-MM-DD", or "never" This wardrobe does NOT allow duplicate items,
 * defined as having identical descriptions and brand names. Each combination of description and
 * brand name must be unique. (If you buy a five-pack of black t-shirts, you could enter them as a
 * single wardrobe item and track the most recently-worn one of them to get around this restriction,
 * if you simply MUST have duplicate clothes.) The wardrobe is maintained as an oversize array,
 * meaning it must be COMPACT (no gaps between non-null values) and the lowest-index value must
 * ALWAYS be at index 0. For the purposes of P01, you may assume the provided size value correctly
 * reflects the number of items in the wardrobe.
 * 
 */
public class WardrobeManager {

  /**
   * Checks whether the oversized array defined by the provided two-dimensional array of strings and
   * its size contains an entry with the provided description AND brand. description/brand matching
   * is NOT case sensitive
   * 
   * @param description  a general description of the clothing item
   * @param brand        - the brand of the clothing item, or "handmade"
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never"
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe, assumed to be correct
   * @return true if the description/brand combination is present in the wardrobe, false otherwise
   */
  public static boolean containsClothing(String description, String brand, String[][] wardrobe,
      int wardrobeSize) {
    int i;

    if (wardrobeSize == 0)
      return false;

    for (i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i][0].equalsIgnoreCase(description) && wardrobe[i][1].equalsIgnoreCase(brand))
        return true;
    }
    return false;
  }

  /**
   * Appends (adds at the end) a new clothing item to the given wardrobe oversize array defined by
   * the provided two-dimensional array of strings and its size, and returns the new size of the
   * oversize array. If the array is full, OR if the description/brand combination provided is
   * already present within the array, the item is NOT added and the current size is returned
   * unmodified.
   * 
   * New clothing items are added with a last-worn date of "never".
   * 
   * @param description  a general description of the clothing item
   * @param brand        - the brand of the clothing item, or "handmade"
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never"
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe, assumed to be correct
   * @return the number of items in the wardrobe after trying to add the new item
   */
  public static int addClothing(String description, String brand, String[][] wardrobe,
      int wardrobeSize) {

    // return original size if wardrobe is already full, or if trying to add duplicate
    if (wardrobeSize >= wardrobe.length)
      return wardrobeSize;

    if (WardrobeManager.containsClothing(description, brand, wardrobe, wardrobeSize))
      return wardrobeSize;

    String[] newItem = new String[] {description, brand, "never"};
    // otherwise, add the item
    wardrobe[wardrobeSize] = newItem;
    wardrobeSize++;

    return wardrobeSize;

  }


  /**
   * Finds the location (index) of a provided clothing item in an oversize array defined by the
   * provided two-dimensional array of strings and its size. If the item is NOT present in the
   * array, returns -1.
   * 
   * @param description  a general description of the clothing item
   * @param brand        the brand of the clothing item, or "handmade"
   * @param wardrobe     a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never"
   * @param wardrobeSize number of items currently stored in the wardrobe
   * @return the index of the clothing item if it is present, or -1 if it is not
   *
   */
  public static int indexOfClothing(String description, String brand, String[][] wardrobe,
      int wardrobeSize) {

    if (WardrobeManager.containsClothing(description, brand, wardrobe, wardrobeSize)) {
      for (int i = 0; i < wardrobeSize; ++i)
        if ((wardrobe[i][0]).equalsIgnoreCase(description)
            && (wardrobe[i][1]).equalsIgnoreCase(brand))
          return i;

    }

    return -1;
  }


  /**
   * 
   * Locates the clothing item matching the provided description/brand in the oversize array defined
   * by the provided two-dimensional array of strings and size, and updates the last-worn date to
   * the provided date value (assumed to be formatted as "YYYY-MM-DD"). If the wardrobe does not
   * contain a clothing item matching the description/brand combination, this method returns false.
   * 
   * @param description  a general description of the clothing item
   * @param brand        the brand of the clothing item, or "handmade"
   * @param date         - the date on which this clothing item was worn, as "YYYY-MM-DD"
   * @param wardrobe     a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never"
   * @param wardrobeSize number of items currently stored in the wardrobe
   * @return true if the item's last-worn date was successfully updated, false otherwise
   */
  public static boolean wearClothing(String description, String brand, String date,
      String[][] wardrobe, int wardrobeSize) {

    if (!WardrobeManager.containsClothing(description, brand, wardrobe, wardrobeSize))
      return false;

    int index = WardrobeManager.indexOfClothing(description, brand, wardrobe, wardrobeSize);

    wardrobe[index][2] = date;
    if (wardrobe[index][2].equals(date))
      return true;

    return false;
  }

  /**
   * Counts the number of clothing items in the oversize array defined by the provided
   * two-dimensional array of strings and size which have a brand that matches the provided brand.
   * Recall that this match ignores case; for example, "GUCCI" and "Gucci" are considered
   * equivalent.
   * 
   * @param brand        the brand of a clothing item, or "handmade"
   * @param wardrobe     a two-dimensional arrayof Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never"
   * @param wardrobeSize number of items currently stored in the wardrobe, assumed to be correct
   * @return the number of items in wardrobe which have a brand matching the provided string
   */
  public static int getBrandCount(String brand, String[][] wardrobe, int wardrobeSize) {

    int brandCount = 0;

    if (wardrobeSize == 0)
      return 0;

    for (int i = 0; i < wardrobeSize; ++i) {
      if (wardrobe[i][1].equalsIgnoreCase(brand))
        brandCount++;
    }

    return brandCount;
  }


  /**
   * Counts the number of clothing items in the oversize array defined by the provided
   * two-dimensional array of strings and size which have a last-worn date of "never".
   * 
   * @param wardrobe     a two-dimensional arrayof Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never"
   * @param wardrobeSize number of items currently stored in the wardrobe, assumed to be correct
   * @return the number of clothing items in wardrobe which have a last-worn date of "never"
   */
  public static int getNumUnwornClothes(String[][] wardrobe, int wardrobeSize) {

    if (wardrobeSize == 0)
      return 0;

    int unwornCount = 0;

    for (int i = 0; i < wardrobeSize; ++i) {
      if (wardrobe[i][2].equalsIgnoreCase("never"))
        unwornCount++;
    }

    return unwornCount;


  }

  /**
   * Finds the most recently worn item of clothing in the oversize array defined by the provided
   * two-dimensional array of strings and size. The most recently worn item of clothing will be the
   * one with the largest date value (as date values increase over time); "never" is considered the
   * smallest possible date, so if all items have a last-worn date of "never", the method should
   * return 0 (the first index at which that date occurs).
   * 
   * 
   * @param wardrobe     a two-dimensional arrayof Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never"
   * @param wardrobeSize number of items currently stored in the wardrobe, assumed to be correct
   * 
   * @return the smallest index of a clothing item in the wardrobe with the most recent last-worn
   *         date, or -1 if the wardrobe is empty
   */
  public static int getMostRecentlyWorn(String[][] wardrobe, int wardrobeSize) {
    ;;
    // define original index
    int index = 0;
    if (wardrobeSize == 0)
      return -1;
    if (wardrobeSize == 1)
      return 0;
    String ogDate = wardrobe[0][2];

    int dateInt;
    int tempDateInt;

    // cycle threw all items except first
    for (int i = 1; i < wardrobeSize; ++i) {
      // if the stored date is never, and the date being compared is not never, update "ogDate" to
      // the non-never date, and update index to its index
      if (ogDate.equalsIgnoreCase("never")) {
        if (!wardrobe[i][2].equalsIgnoreCase("never")) {
          ogDate = wardrobe[i][2];
          index = i;
        }
        // if stored date is never, and date at index[i] is never, continue to next case
        // (i.e another if statement is unnecesary)

        // if the stored date is not never, and wardrobe[i][2] is also not never, compare them
      } else {
        if (!wardrobe[i][2].equalsIgnoreCase("never")) {
          dateInt = Integer.parseInt(ogDate.replace("-", "")); // CITE: digitalocean.com, showed me
                                                               // how to convert string to int
          tempDateInt = Integer.parseInt(wardrobe[i][2].replace("-", ""));

          if (tempDateInt > dateInt) {
            index = i;
            ogDate = wardrobe[i][2];
          }
        }
      }

    }

    return index;
  }



  /**
   * 
   * Removes the single clothing item entry at the provided index of the oversize array defined by
   * the provided two-dimensional array of strings and size, updates the array to uphold the
   * oversize array requirements, and returns the new size of the array. If the index is outside of
   * the bounds of the provided oversize array, the array must NOT be modified and the previous size
   * returned.
   * 
   * 
   * @param index        the index of the clothing item to remove from the wardrobe
   * @param wardrobe     a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never"
   * @param wardrobeSize number of items currently stored in the wardrobe, assumed to be correct
   * @return the size of the oversize array after attempting to remove the item at the provided
   *         index
   */
  public static int removeClothingAtIndex(int index, String[][] wardrobe, int wardrobeSize) {

    if (index >= wardrobeSize || index < 0)
      return wardrobeSize;

    for (int i = index; i < wardrobeSize - 1; i++) {
      wardrobe[i][0] = wardrobe[i + 1][0];
      wardrobe[i][1] = wardrobe[i + 1][1];
      wardrobe[i][2] = wardrobe[i + 1][2];
    }

    wardrobe[wardrobeSize - 1] = null;

    wardrobeSize--;


    return wardrobeSize;
  }

  /**
   * Removes any clothing item from the oversize array defined by the provided two-dimensional array
   * of strings and size where the item's last-worn date is "never", and returns the updated size of
   * the oversize array.
   * 
   * @param wardrobe     a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never"
   * @param wardrobeSize number of items currently stored in the wardrobe, assumed to be correct
   * @return the size of the oversize array after all never-worn items have been removed
   */
  public static int removeAllUnworn(String[][] wardrobe, int wardrobeSize) {
    if (wardrobeSize == 0)
      return 0;

    int expectedSize = wardrobeSize - WardrobeManager.getNumUnwornClothes(wardrobe, wardrobeSize);

    while (wardrobeSize != expectedSize) {
      for (int i = 0; i < wardrobeSize; ++i) {
        if (wardrobe[i][2].equalsIgnoreCase("never")) {
          WardrobeManager.removeClothingAtIndex(i, wardrobe, wardrobeSize);
          wardrobeSize--;
        }
      }
    }


    return wardrobeSize;
  }

}
