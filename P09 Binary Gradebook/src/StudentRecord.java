//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 - StudentRecord
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




/**
 * This class models student course records.
 */
public class StudentRecord implements Comparable<StudentRecord> {

  // This student's name
  public final String name;

  // This student's email address
  public final String email;

  // This student's grade
  private double grade;


  /**
   * Constructs a new StudentRecord given the name, email, and grade of a given student. If the name
   * or email is null or blank, the constructor throws an IllegalArgumentException. Additionally, if
   * the grade is not between the range 0.0 to 100.0 (inclusive) the constructor throws an
   * IllegalArgumentException. We do not validate the format of the email.
   * 
   * @param name  - the student's name
   * @param email - the student-s email
   * @param grade - the students grade
   * @throws IllegalArgumentException - if the name or email is null or blank or if grade is not in
   *                                  the range 0.0 .. 100.0 (inclusive)
   */
  public StudentRecord(String name, String email, double grade) {

    if (name == null || name.isBlank() || email.isBlank() || email == null) {
      throw new IllegalArgumentException("Email and name must not be null or blank");
    }

    if (grade < 0.0 || grade > 100.0) {
      throw new IllegalArgumentException("Grade must be in range [0,100] inclusive");

    }

    this.name = name;
    this.grade = grade;
    this.email = email;

  }

  /**
   * Returns this student's current grade
   * 
   * @return this student's current grade
   */
  public double getGrade() {
    return this.grade;
  }

  /**
   * Updates this student's current grade
   * 
   * @param grade - the new value of this student's grade
   */
  public void setGrade(double grade) {
    this.grade = grade;
  }


  /**
   * Returns a String representation of this StudentRecord in the following format: "name (email):
   * grade"
   * 
   * @return a String representation of this StudentRecord
   */
  @Override
  public String toString() {
    String s = this.name + " (" + email + "): " + this.grade;
    return s;
  }

  /**
   * Compares this StudentRecord to other StudentRecord passed as input. StudentRecords are compared
   * with respect to the lexicographical (alphabetical) order of the students emails.
   * 
   * 
   * Specified by: compareTo in interface Comparable<StudentRecord>
   * 
   * @param other - other StudentRecord to compare to
   * @return 0 if the email associated with the other Student is equal to the email of this Student;
   *         a value less than 0 if the email associated with this Student is lexicographically less
   *         than the email of the other Student argument; and a value greater than 0 if the email
   *         associated with this Student is lexicographically greater than the email of the other
   *         Student argument.
   * 
   */
  public int compareTo(StudentRecord other) {
    return this.email.compareTo(other.email);
  }


  /**
   * Returns true if the given Object is a StudentRecord with an email that matches the email of
   * this StudentRecord.
   * 
   * Overrides: equals in class Object
   * 
   * @param o - Object to compare with this StudentRecord.
   * @return true if the given Object is a StudentRecord with an email that matches the email of
   *         this StudentRecord
   */
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    StudentRecord S = (StudentRecord) o;

    return this.email.equals(S.email);
  }



}
