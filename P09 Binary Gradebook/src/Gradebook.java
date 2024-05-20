//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 - Gradebook
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
import java.util.Iterator;


/**
 * This class models a grade book for a specific course used to store student records.
 * 
 */
public class Gradebook implements Iterable<StudentRecord> {

  // Name of this course
  public final String course;

  // Minimum passing grade for this course
  public final double PASSING_GRADE;

  // Root node of the BST
  private BSTNode<StudentRecord> root;

  // Total number of StudentRecords stored in this Gradebook
  private int size;

  // Indicates whether the passing grade iterator is enabled (meaning equals true if this Gradebook
  // is set to iterate through passing grades only).
  private boolean passingGradeIteratorEnabled;

  /**
   * Constructs an empty Gradebook for a given course and define its passing grade. We assume that
   * that this gradebook iterates through every stored grade (meaning that the passingGradeIterator
   * is not enabled by default).
   * 
   * @param course       - name of the course
   * @param passingGrade - passing grade of the course
   * @throws IllegalArgumentException - if the course name is null or blank, or the passing grade is
   *                                  invalid. Valid passing grades are in the range [0 .. 100.0]
   */
  public Gradebook(String course, double passingGrade) {
    if (course == null || course.equals("") || course.isBlank()) {
      throw new IllegalArgumentException("course must not be null or blank");
    }

    if (passingGrade < 0.0 || passingGrade > 100.0) {
      throw new IllegalArgumentException("Grade must be in range [0,100] inclusive");

    }

    this.passingGradeIteratorEnabled = false;
    this.course = course;
    this.PASSING_GRADE = passingGrade;
  }

  /**
   * Enables the passing grade iterator
   */
  public void enablePassingGradeIterator() {
    this.passingGradeIteratorEnabled = true;
  }

  /**
   * Disables the passing grade iterator
   */
  public void disablePassingGradeIterator() {
    this.passingGradeIteratorEnabled = false;
  }

  /**
   * Checks whether this Gradebook is empty
   * 
   * @return true if this Gradebook is empty and false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns the size of this Gradebook
   * 
   * @return the total number of StudentRecord objects stored in this Gradebook
   * 
   */
  public int size() {
    return this.size;
  }

  /**
   * Adds a new StudentRecord to this Gradebook. This method tries to add record to this tree and
   * updates its size accordingly. Be sure to update the root to the BSTNode returned by the
   * addStudentHelper() method.
   * 
   * @param record - to be added to this Gradebook
   * @throws IllegalStateException - If a match with record is already in this tree
   */
  public void addStudent(StudentRecord record) {
    if (lookup(record.email) != null) {
      throw new IllegalStateException("Duplicate record found");
    }
    this.root = addStudentHelper(record, root);
    size++;
  }


  /**
   * Recursive helper method to add a record to the subtree rooted at node
   * 
   * @param record - new Student to add
   * @param node   - root of a subtree
   * @throws IllegalStateException - if the subtree rooted at node contains a duplicate record
   * @return the new root of this BST after adding the record to this tree
   */
  protected static BSTNode<StudentRecord> addStudentHelper(StudentRecord record,
      BSTNode<StudentRecord> node) {

    if (node == null) {
      return new BSTNode<>(record);
    }

    int compare = record.compareTo(node.getData());
    if (compare < 0) {
      node.setLeft(addStudentHelper(record, node.getLeft()));
    } else if (compare > 0) {
      node.setRight(addStudentHelper(record, node.getRight()));
    } else {
      throw new IllegalStateException("Duplicate record found.");
    }
    return node;
  }

  /**
   * Finds a StudentRecord given the associated email address
   * 
   * @param email - email address of a student
   * @return the Student associated with the email argument if there is a match, or null otherwise
   */
  public StudentRecord lookup(String email) {
    StudentRecord target = new StudentRecord("Name", email, 1.0);
    return lookupHelper(target, root);

  }

  /**
   * Recursive helper method which looks for a given StudentRecord given in the BST rooted at node
   * 
   * @param target - the StudentRecord to search in the subtree rooted at node
   * 
   * @param node   - root of a subtree of this BST
   * 
   * @return the StudentRecord which matches the one passed as input if a match is found in the
   *         subtree rooted at node, or null if no match found
   * 
   */
  protected static StudentRecord lookupHelper(StudentRecord target, BSTNode<StudentRecord> node) {

    if (node == null) {
      return null;
    }

    int compare = target.compareTo(node.getData());
    if (compare < 0) {
      return lookupHelper(target, node.getLeft());
    } else if (compare > 0)
      return lookupHelper(target, node.getRight());

    return node.getData();
  }


  /**
   * Searches for the StudentRecord associated with the provided input email in this BST and checks
   * whether it has a passing grade for this course. The student with the provided email passes the
   * course if their grade is greater or equal to this Gradebook's passingGrade data field. Returns:
   * "No match found." if no match found with email in this Gradebook If a matching StudentRecord is
   * found, this method returns: matchingStudent.toString() + ": PASS" if the student has a passing
   * grade matchingStudent.toString() + ": FAIL" if the student does not have a passing grade For
   * instance, "Charlie (charlie@wisc.edu) 85: PASS" "Andy (andy@wisc.edu) 56: FAIL"
   * 
   * @param email - the email of the StudentRecord to find
   * 
   * @return A String indicating whether the student having the input email has a passing or failing
   *         grade.
   * 
   */
  public String checkPassingCourse(String email) {
    StudentRecord student = lookup(email);
    if (student == null) {
      return "No match found.";
    } else {
      return student.toString() + (student.getGrade() >= PASSING_GRADE ? ": PASS" : ": FAIL");
    }

  }

  /**
   * Returns the StudentRecord with the lexicographically smallest email in this BST, or null if
   * this Gradebook is empty.
   * 
   * @return the StudentRecord with the lexicographically smallest email in this BST
   * 
   */
  protected StudentRecord getMin() {
    return getMinHelper(root);

  }

  /**
   * Returns the smallest StudentRecord (with respect to the result of Student.compareTo() method)
   * in the subtree rooted at node
   * 
   * @param node - root of a subtree of a binary search tree
   * @return the smallest Student in the subtree rooted at node, or null if the node is null
   */
  protected static StudentRecord getMinHelper(BSTNode<StudentRecord> node) {
    if (node == null) {
      return null;
    }
    while (node.getLeft() != null) {
      node = node.getLeft();
    }
    return node.getData();
  }

  /**
   * Returns the successor of a target StudentRecord (smallest value in the BST that is larger than
   * the target), or returns null if there is no successor in this Gradebook.
   * 
   * @param target - the StudentRecord to find the successor of
   * 
   * @return the successor of the target in the Gradebook, or null if none exists
   * 
   */
  protected StudentRecord successor(StudentRecord target) {
    return successorHelper(target, root);
  }

  /**
   * Returns the successor of a target StudentRecord within the subtree (smallest value in the
   * subtree that is larger than the target), or returns null if there is no successor in this
   * subtree.
   * 
   * @param target - the StudentRecord to find the successor of
   * 
   * @param node   - the subtree to search for a successor to the target
   * 
   * @return the successor of the target in the subtree rooted at node, or null if none exists
   * 
   */
  protected static StudentRecord successorHelper(StudentRecord target,
      BSTNode<StudentRecord> node) {

    if (node == null) {
      return null;
    }
    StudentRecord succ = null;
    while (node != null) {
      int compare = target.compareTo(node.getData());
      if (compare < 0) {
        succ = node.getData();
        node = node.getLeft();
      } else if (compare > 0) {
        node = node.getRight();
      } else {
        if (node.getRight() != null) {
          return getMinHelper(node.getRight());
        }
        break;
      }
    }
    return succ;

  }

  /**
   * Deletes a StudentRecord from this Gradebook given their email, or throws a
   * NoSuchElementException if there is no StudentRecord with the given email.
   * 
   * @param email - the email of the student to delete
   * @throws NoSuchElementException - if there is no matching StudentRecord in this Gradebook
   * 
   */
  public void removeStudent(String email) {

    StudentRecord toDrop = lookup(email);
    if (toDrop == null) {
      throw new NoSuchElementException("No matching StudentRecord found");
    }
    root = removeStudentHelper(toDrop, root);
    size--;

  }

  /**
   * Deletes the matching StudentRecord with toDrop if it is found within this tree, or otherwise
   * throws a NoSuchElementException.
   * 
   * @param toDrop - the StudentRecord to be removed from this tree
   * @param node   - the root of the subtree to remove the student from
   * 
   * @return the new root of the subtree after removing the matching StudentRecord
   * @throws NoSuchElementException - if there is no matching StudentRecord in this subtree
   */
  protected static BSTNode<StudentRecord> removeStudentHelper(StudentRecord toDrop,
      BSTNode<StudentRecord> node) {

    if (node == null) {
      throw new NoSuchElementException("No matching StudentRecord in this Gradebook.");
    }
    int compare = toDrop.compareTo(node.getData());
    if (compare < 0) {
      node.setLeft(removeStudentHelper(toDrop, node.getLeft()));
    } else if (compare > 0) {
      node.setRight(removeStudentHelper(toDrop, node.getRight()));
    } else {
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      }
      node.setData(getMinHelper(node.getRight()));
      node.setRight(removeStudentHelper(node.getData(), node.getRight()));
    }
    return node;
  }


  /**
   * Returns a String representation of the contents of this Gradebook in increasing order
   * 
   * @return an in-order String representation of this Gradebook
   * 
   */
  @Override
  public String toString() {
    return toStringHelper(root);

  }

  /**
   * Returns a String representation of the subtree rooted at node in increasing order
   * 
   * @param node - the root of a subtree
   * @return an in-order String representation of the subtree rooted at node
   * 
   */
  protected static String toStringHelper(BSTNode<StudentRecord> node) {
    if (node == null) {
      return "";
    }
    StringBuilder builder = new StringBuilder();
    builder.append(toStringHelper(node.getLeft()));
    builder.append(node.getData().toString()).append("\n");
    builder.append(toStringHelper(node.getRight()));
    return builder.toString();

  }

  /**
   * Returns a String representation of the structure of this BST. The String should print the
   * StudentRecords in decreasing order (largest-to-smallest), and each StudentRecord should have an
   * indentation (space from the left side of the screen to the student names) that increases by
   * four (4) spaces for each level of depth in the tree. For instance, the root has no indentation,
   * the root's left subtree has an indentation of 4 spaces, and the root's left subtree's right
   * child has an indentation of 8 spaces.
   * 
   * @return a String representation of the structure of this BST
   * 
   */
  public String prettyString() {
    return prettyStringHelper(root, 0);

  }

  /**
   * Returns a decreasing-order String representation of the structure of this subtree, indented by
   * four spaces for each level of depth in the larger tree.
   * 
   * @author Ashley Samuelson
   * @param node  - current subtree within the larger tree
   * 
   * @param depth - depth of the current node within the larger tree
   * 
   * @return a String representation of the structure of this subtree
   * 
   */
  protected static String prettyStringHelper(BSTNode<StudentRecord> node, int depth) {

    if (node == null) {
      return "";
    }
    String indent = " ".repeat(depth);
    return prettyStringHelper(node.getRight(), depth + 1) + indent + node.getData().name + "\n"
        + prettyStringHelper(node.getLeft(), depth + 1);

    //
    // StringBuilder builder = new StringBuilder();
    // if (node != null) {
    // builder.append(prettyStringHelper(node.getRight(), depth + 1));
    // for (int i = 0; i < depth; i++) {
    // builder.append(" ");
    // }
    // builder.append(node.getData().toString()).append("\n");
    // builder.append(prettyStringHelper(node.getLeft(), depth + 1));
    // }
    // return builder.toString();
  }

  /**
   * Returns true if this BST has an identical layout (all subtrees equal) to the given tree.
   * 
   * @param node - tree to compare this Gradebook to
   * 
   * @return true if the given tree looks identical to the root of this Gradebook
   * 
   */
  public boolean equalBST(BSTNode<StudentRecord> node) {
    return root == node || (root != null && root.equals(node));

  }

  /**
   * Returns an iterator over the student records in this gradebook in the increasing order. If the
   * passing grade iterator is enabled, this method returns an iterator that iterates through
   * records with passing grades only while skipping the ones that fail to pass.
   * 
   * Specified by: iterator in interface Iterable<StudentRecord>
   * 
   * @return an Iterator over the elements in this gradebook in proper sequence.
   * 
   */
  public Iterator<StudentRecord> iterator() {

    if (this.passingGradeIteratorEnabled)
      return new PassingGradeIterator(this);

    return new GradebookIterator(this);


  }



}
