//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 - PassingGradeIterator
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



import java.util.NoSuchElementException;


/**
 * Iterator for traversing the records in a Gradebook in increasing order, while also skipping over
 * StudentRecords who do not have a passing grade. This iterator iterates through the StudentRecord
 * objects with passing grades, only
 */
public class PassingGradeIterator extends GradebookIterator {

  // Reference to the current StudentRecord with a passing grade to be returned by this iterator
  private StudentRecord next;

  // Passing grade
  private double passingGrade;

  // fields inherited form gradebook iteraror: current


  /**
   * Constructs a new PassingGradeIterator to iterate over the StudentRecords with passing grades in
   * a given Gradebook (StudentRecords with NO passing grades are skipped by this iterator). This
   * iterator sets the passing grade to the gradebook passing grade and advances the iterator to the
   * first student record with passing grade in the iteration by calling the
   * advanceToNextPassingGrade() helper method.
   * 
   * @param gradebook - Gradebook to iterate over.
   * 
   */
  public PassingGradeIterator(Gradebook gradebook) {
    super(gradebook);
    this.passingGrade = gradebook.PASSING_GRADE;
    advanceToNextPassingGrade();


  }

  /**
   * Private helper method that advances this iterator to the next StudentRecord with a passing
   * grade. Then, it stores it into next. If no more StudentRecord with a passing grade are
   * available in the iteration, this method sets next to null. This method uses super.hasNext() and
   * super.next() in a while loop to operate.
   */
  private void advanceToNextPassingGrade() {
    while (super.hasNext()) {
      StudentRecord nextPassing = super.next();

      if (nextPassing.getGrade() >= this.passingGrade) {
        this.next = nextPassing;
        return;
      }
    }
    this.next = null;


  }


  /**
   * Returns true if the iteration has more elements (if next is not null). (In other words, returns
   * true if next() would return an element rather than throwing an exception.)
   * 
   * Specified by: hasNext in interface Iterator<StudentRecord>
   * 
   * Overrides: hasNext in class GradebookIterator
   * 
   * @return true if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return next != null;

  }


  /**
   * Returns the next StudentRecord object with a passing grade in the iteration and advances the
   * iteration to the next record with passing grade.
   * 
   * Specified by: next in interface Iterator<StudentRecord>
   * 
   * Overrides: next in class GradebookIterator
   * 
   * @return the next StudentRecord with a passing grade in the iteration
   * 
   * 
   * @throws NoSuchElementException - if the iteration has no more elements (meaning has no more
   *                                StudentRecord objects with a passing grade)
   * 
   */
  @Override
  public StudentRecord next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more elements in the iteration");
    }
    StudentRecord current = next;
    advanceToNextPassingGrade();
    return current;

  }

}
