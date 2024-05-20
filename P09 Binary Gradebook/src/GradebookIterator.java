//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 - GradebookIterator
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
// Online Sources: NA
//
///////////////////////////////////////////////////////////////////////////////




import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Iterator for traversing the records in a Gradebook in increasing order without skipping any
 * element.
 * 
 */
public class GradebookIterator implements Iterator<StudentRecord> {

  // Current StudentRecord reference. It also referenced the next StudentRecord to be returned by
  // this iterator.
  private StudentRecord current;


  // Gradebook to iterate over
  private Gradebook gradebook;


  /**
   * Creates a new GradebookIterator to iterate over the given Gradebook and initializes current to
   * references the minimum studentRecord in the gradebook.
   * 
   * @param gradebook - Gradebook to iterate over.
   * 
   */
  public GradebookIterator(Gradebook gradebook) {
    this.gradebook = gradebook;
    this.current = gradebook.getMin();

  }

  /**
   * Returns true if the iteration has more elements (if current is not null). (In other words,
   * returns true if next() would return an element rather than throwing an exception.)
   * 
   * Specified by: hasNext in interface Iterator<StudentRecord>
   * 
   * @return true if iteration has more elements
   * 
   * 
   */
  public boolean hasNext() {
    return current != null;
  }

  /**
   * Returns the next element in the iteration (meaning the current StudentRecord from the
   * Gradebook), and advances the current pointer to the next StudentRecord in the gradebook in the
   * increasing order.
   * 
   * Specified by: next in interface Iterator<StudentRecord>
   * 
   * @return the next element in the iteration (current StudentRecord)
   * 
   * @throws NoSuchElementException - if the iteration has no more elements (meaning if hasNext()
   *                                returns false)
   * 
   */
  public StudentRecord next() {
    if (current == null) {
      throw new NoSuchElementException("No more elements in the iteration");
    }
    StudentRecord nextRecord = current;
    current = gradebook.successor(current);
    return nextRecord;

  }



}
