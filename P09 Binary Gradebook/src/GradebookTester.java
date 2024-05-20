//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 - GradebookTester
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

public class GradebookTester {


  /**
   * tests functionality of gradebook constructor
   * 
   * @return true if works all test cases pass, false otherwise
   */
  public static boolean constructorTester() {
    // Test constructor with valid parameters
    Gradebook gradebook = new Gradebook("Math", 60.0);
    if (!gradebook.course.equals("Math") || gradebook.PASSING_GRADE != 60.0 || !gradebook.isEmpty()
        || gradebook.size() != 0)
      return false;

    // test constructor w/ invalid course name:
    try {
      Gradebook badName = new Gradebook("", 60.0);
      return false;
    } catch (IllegalArgumentException e) {
    }
    // test w/ invalid grade:
    try {
      Gradebook badGrade = new Gradebook("name", -60.0);
      return false;
    } catch (IllegalArgumentException e) {
    }

    // ensure the course is correct
    if (!gradebook.course.equals("Math"))
      return false;


    return true;
  }


  /**
   * tests functionality of isEmpty, size, and add methods from gradebook class
   * 
   * @return true if works all test cases pass, false otherwise
   */
  public static boolean isEmptySizeAddTester() {
    // Test isEmpty() on an empty gradebook
    Gradebook gradebook = new Gradebook("Math", 20.0);
    if (!gradebook.isEmpty())
      return false;

    if (gradebook.size() != 0)
      return false;

    // Test adding a student record
    StudentRecord record = new StudentRecord("bob", "bob@wisc.com", 75.0);
    gradebook.addStudent(record);
    if (gradebook.isEmpty() || gradebook.size() != 1)
      return false;

    return true;
  }


  /**
   * tests functionality of gradebook lookup method
   * 
   * @return true if works all test cases pass, false otherwise
   */
  public static boolean lookupTester() {
    // Test lookup() with an empty gradebook
    Gradebook gradebook = new Gradebook("Math", 60.0);
    if (gradebook.lookup("john@example.com") != null)
      return false;

    // Test lookup() with a non-empty gradebook
    StudentRecord record = new StudentRecord("John", "john@example.com", 75.0);
    StudentRecord record2 = new StudentRecord("bob", "bob@example.com", 2.0);

    gradebook.addStudent(record);
    gradebook.addStudent(record2);

    if (gradebook.lookup("john@example.com") != record)
      return false;
    if (gradebook.lookup("bob@example.com") != record2)
      return false;
    // make sure it still returns false for emails not in the gradebook
    if (gradebook.lookup("john@.com") != null)
      return false;

    StudentRecord record4 = new StudentRecord("Jake", "jake@example.com", 20.0);
    gradebook.addStudent(record4);
    StudentRecord record5 = new StudentRecord("Zach", "zach@example.com", 20.0);
    gradebook.addStudent(record5);

    if (gradebook.lookup("bob@example.com") != record2)
      return false;
    if (gradebook.lookup("zach@example.com") != record5)
      return false;
    if (gradebook.lookup("jake@example.com") != record4)
      return false;

    return true;
  }


  /**
   * tests functionality of gradebook toString method
   * 
   * @return true if works all test cases pass, false otherwise
   */
  public static boolean toStringTester() {
    // Test toString() on an empty gradebook
    Gradebook gradebook = new Gradebook("Math", 60.0);
    if (!gradebook.toString().equals(""))
      return false;

    // Test toString() with a non-empty gradebook
    StudentRecord record1 = new StudentRecord("John", "john@example.com", 75.0);
    StudentRecord record2 = new StudentRecord("Alice", "alice@example.com", 80.0);
    StudentRecord record3 = new StudentRecord("bob", "bob@example.com", 20.0);

    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    gradebook.addStudent(record3);
    String expected =
        "Alice (alice@example.com): 80.0\nbob (bob@example.com): 20.0\nJohn (john@example.com): 75.0\n";

    if (!gradebook.toString().equals(expected))
      return false;

    return true;
  }


  /**
   * tests functionality of gradebook prettyString method
   * 
   * @return true if works all test cases pass, false otherwise
   */
  public static boolean prettyStringTester() {
    // Test prettyString() on an empty gradebook
    Gradebook gradebook = new Gradebook("Math", 60.0);
    if (!gradebook.prettyString().equals(""))
      return false;

    // Test prettyString() with a non-empty gradebook
    StudentRecord record1 = new StudentRecord("John", "john@example.com", 75.0);
    StudentRecord record2 = new StudentRecord("Alice", "alice@example.com", 80.0);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    StudentRecord record3 = new StudentRecord("bob", "bob@example.com", 20.0);

    gradebook.addStudent(record3);
    String expected = "John\n" + "  bob\n" + " Alice\n";
    if (!gradebook.prettyString().equals(expected))
      return false;

    return true;
  }

  /**
   * tests functionality of gradebook getMin method
   * 
   * @return true if works all test cases pass, false otherwise
   */
  public static boolean getMinTester() {
    // Test getMin() on an empty gradebook
    Gradebook gradebook = new Gradebook("Math", 60.0);
    if (gradebook.getMin() != null)
      return false;

    // Test getMin() with a non-empty gradebook
    StudentRecord record1 = new StudentRecord("John", "john@example.com", 75.0);
    StudentRecord record2 = new StudentRecord("Alice", "alice@example.com", 80.0);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    StudentRecord record3 = new StudentRecord("bob", "bob@example.com", 20.0);

    gradebook.addStudent(record3);
    if (gradebook.getMin() != record2)
      return false;

    return true;
  }


  /**
   * tests functionality of gradebook successor method
   * 
   * @return true if works all test cases pass, false otherwise
   */
  public static boolean successorTester() {
    // Test successor() with an empty gradebook
    Gradebook gradebook = new Gradebook("Math", 60.0);
    if (gradebook.successor(null) != null)
      return false;

    // Test successor() with a non-empty gradebook
    StudentRecord record1 = new StudentRecord("John", "john@example.com", 75.0);
    StudentRecord record2 = new StudentRecord("Alice", "alice@example.com", 80.0);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    StudentRecord record3 = new StudentRecord("bob", "bob@example.com", 20.0);

    gradebook.addStudent(record3);

    if (gradebook.successor(record1) != null || gradebook.successor(record2) != record3
        || gradebook.successor(record3) != record1)
      return false;

    return true;
  }


  /**
   * tests functionality of gradebook removeStudent method
   * 
   * @return true if works all test cases pass, false otherwise
   */
  public static boolean removeStudentTester() {
    // Test removeStudent() with an empty gradebook
    Gradebook gradebook = new Gradebook("Math", 60.0);
    try {
      gradebook.removeStudent("john@example.com");
      return false; // return false if it doesn't throw NoSuchElementException
    } catch (NoSuchElementException e) {
      // Expected exception(good)
    }

    // Test removeStudent() with a non-empty gradebook
    StudentRecord record1 = new StudentRecord("John", "john@example.com", 75.0);
    StudentRecord record2 = new StudentRecord("Alice", "alice@example.com", 80.0);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    StudentRecord record3 = new StudentRecord("bob", "bob@example.com", 20.0);
    gradebook.addStudent(record3);
    StudentRecord record4 = new StudentRecord("Jake", "jake@example.com", 20.0);
    gradebook.addStudent(record4);
    StudentRecord record5 = new StudentRecord("Zach", "zach@example.com", 20.0);
    gradebook.addStudent(record5);

    gradebook.removeStudent("john@example.com");

    // Testing if succesors are as expected after removing nodes
    if (gradebook.size() != 4 || gradebook.lookup("john@example.com") != null
        || gradebook.successor(record3) != record4)
      return false;
    gradebook.removeStudent("bob@example.com");
    if (gradebook.size() != 3 || gradebook.lookup("bob@example.com") != null
        || gradebook.successor(record1) != record5)
      return false;
    gradebook.removeStudent("alice@example.com");
    if (gradebook.size() != 2 || gradebook.lookup("alice@example.com") != null)
      return false;

    // Additional test for removing a node with 2 children:
    Gradebook gradebook2 = new Gradebook("Math", 60.0);
    StudentRecord record44 = new StudentRecord("Eve", "eve@example.com", 79.0); // Alice
    gradebook2.addStudent(record44);
    StudentRecord record55 = new StudentRecord("Carl", "carl@example.com", 76.0);
    gradebook2.addStudent(record55);
    gradebook2.addStudent(record1);
    gradebook2.addStudent(record3);
    gradebook2.addStudent(record2);

    gradebook2.removeStudent("eve@example.com");
    if (gradebook2.size() != 4 || gradebook2.lookup("eve@example.com") != null) {
      return false;
    }
    // test that everyone is where they should be in the tree:
    if (!gradebook2.toString().split("\n")[0].equals(record2.toString()))
      return false;
    if (!gradebook2.toString().split("\n")[1].equals(record3.toString()))
      return false;
    if (!gradebook2.toString().split("\n")[2].equals(record55.toString()))
      return false;
    if (!gradebook2.toString().split("\n")[3].equals(record1.toString()))
      return false;


    // FINAL TEST: testing if the BST is as expected after removing an internal node with 2
    // children

    // initialize student records to be used in BST and gradebook
    StudentRecord record11 = new StudentRecord("max", "max", 75.0);
    StudentRecord record22 = new StudentRecord("fred", "fred", 75.0);
    StudentRecord record33 = new StudentRecord("al", "al", 75.0);
    StudentRecord recordgr = new StudentRecord("gr", "gr", 75.0);
    StudentRecord recordza = new StudentRecord("za", "za", 75.0);

    // set up expected BST, which we will compare to a gradebook with the same nodes after removing
    // fred
    BSTNode<StudentRecord> max = new BSTNode<>(record11);
    BSTNode<StudentRecord> al = new BSTNode<>(record33);
    BSTNode<StudentRecord> gr = new BSTNode<>(recordgr);
    BSTNode<StudentRecord> za = new BSTNode<>(recordza);

    max.setLeft(gr);
    max.setRight(za);
    gr.setLeft(al);

    // initialize gradebook and add students
    Gradebook gradebookFINAL = new Gradebook("Math", 60.0);
    gradebookFINAL.addStudent(record11);
    gradebookFINAL.addStudent(record22);
    gradebookFINAL.addStudent(record33);
    gradebookFINAL.addStudent(recordgr);
    gradebookFINAL.addStudent(recordza);

    // remove internal node that has 2 children
    gradebookFINAL.removeStudent("fred");

    // ensure gradebook equals expect BST
    if (!gradebookFINAL.equalBST(max))
      return false;

    return true;
  }


  /**
   * tests functionality of gradebook iterator method
   * 
   * @return true if works all test cases pass, false otherwise
   */
  public static boolean iteratorTester() {
    // Test iterator() with an empty gradebook
    Gradebook gradebook = new Gradebook("Math", 60.0);
    Iterator<StudentRecord> iterator = gradebook.iterator();
    if (iterator.hasNext())
      return false;

    // Test iterator() with a non-empty gradebook
    StudentRecord record1 = new StudentRecord("John", "john@example.com", 75.0);
    StudentRecord record2 = new StudentRecord("Alice", "alice@example.com", 80.0);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    StudentRecord record3 = new StudentRecord("bob", "bob@example.com", 20.0);
    gradebook.addStudent(record3);
    iterator = gradebook.iterator();
    if (!iterator.hasNext() || iterator.next() != record2 || iterator.next() != record3)
      return false;

    return true;
  }


  /**
   * tests functionality of gradebook iterator method when passing is enabled
   * 
   * @return true if works all test cases pass, false otherwise
   */
  public static boolean passingIteratorTester() {
    // Test passingGradeIterator() with an empty gradebook

    StudentRecord record1 = new StudentRecord("max", "max", 75.0);
    StudentRecord record2 = new StudentRecord("fred", "fred", 70.0);
    StudentRecord record3 = new StudentRecord("al", "al", 20.0);
    StudentRecord record4 = new StudentRecord("gr", "gr", 72.0);
    StudentRecord record5 = new StudentRecord("za", "za", 30.0);

    Gradebook gradebook = new Gradebook("Math", 60.0);
    gradebook.addStudent(record1);
    gradebook.addStudent(record2);
    gradebook.addStudent(record3);
    gradebook.addStudent(record4);
    gradebook.addStudent(record5);

    gradebook.enablePassingGradeIterator();
    Iterator<StudentRecord> iterator = gradebook.iterator();


    // test passinggradeiterator with nonempty book
    if (!iterator.next().toString().equals(record2.toString()))
      return false;

    if (!iterator.next().toString().equals(record4.toString()))
      return false;

    if (!iterator.hasNext())
      return false;

    if (!iterator.next().toString().equals(record1.toString()))
      return false;

    if (iterator.hasNext())
      return false;

    return true;
  }
  
  //Outputs of tester:
//
//  public static void main(String[] args) {
//    System.out.println("constructorTester: " + constructorTester());
//    System.out.println("isEmptySizeAddTester: " + isEmptySizeAddTester());
//    System.out.println("lookupTester: " + lookupTester());
//    System.out.println("toStringTester: " + toStringTester());
//    System.out.println("prettyStringTester: " + prettyStringTester());
//    System.out.println("getMinTester: " + getMinTester());
//    System.out.println("successorTester: " + successorTester());
//    System.out.println("removeStudentTester: " + removeStudentTester());
//    System.out.println("iteratorTester: " + iteratorTester());
//    System.out.println("passingIteratorTester: " + passingIteratorTester());
//  }
}


