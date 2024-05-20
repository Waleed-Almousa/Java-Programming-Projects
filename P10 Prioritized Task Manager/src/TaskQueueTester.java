//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 - TaskQueueTester
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
 * Tester class to verify that the methods in TaskQueue work as intended
 */
public class TaskQueueTester {

  /**
   * Tests the correctness of a Task compareTo() method implementation when the criteria parameter
   * is TIME.
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testCompareToTime() {

    Task task1 = new Task("Cook", "Dinner", 30, PriorityLevel.HIGH);
    Task task2 = new Task("Cook", "Dinner", 45, PriorityLevel.HIGH);

    Task task3 = new Task("Eat", "nothing", 30, PriorityLevel.LOW);

    // Task task4 - new Task("")



    // Task 1 has low completion time; should return -1
    if (task1.compareTo(task2, CompareCriteria.TIME) >= 0)
      return false;

    // Task 2 how higher completion time than task1; should return 1
    if (task2.compareTo(task1, CompareCriteria.TIME) <= 0)
      return false;

    // Task1 and Task3 have same completion time; should return 0
    if (task1.compareTo(task3, CompareCriteria.TIME) != 0)
      return false;



    return true;

  }



  /**
   * Tests the correctness of a Task compareTo() method implementation when the criteria parameter
   * is TITLE.
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testCompareToTitle() {

    Task task1 = new Task("Cook", "Dinner", 30, PriorityLevel.HIGH);
    Task task2 = new Task("Drive", "home", 10, PriorityLevel.HIGH);
    Task task3 = new Task("Cook", "Dinner", 30, PriorityLevel.LOW);
    Task task4 = new Task("lowercase", "Dinner", 30, PriorityLevel.LOW);
    Task task5 = new Task("Cooking", "Dinner", 30, PriorityLevel.LOW);
    Task task6 = new Task("loWer", "Dinner", 30, PriorityLevel.LOW);
    Task task7 = new Task("zzzz", "Dinner", 30, PriorityLevel.LOW);
    Task task8 = new Task("aaaa", "Dinner", 30, PriorityLevel.LOW);
    Task task9 = new Task("ZZZZ", "Dinner", 30, PriorityLevel.LOW);

    if (task7.compareTo(task8, CompareCriteria.TITLE) >= 0)
      return false;

    if (task8.compareTo(task7, CompareCriteria.TITLE) <= 0)
      return false;

    if (task7.compareTo(task9, CompareCriteria.TITLE) >= 0)
      return false;

    if (task9.compareTo(task7, CompareCriteria.TITLE) <= 0)
      return false;

    if (task8.compareTo(task9, CompareCriteria.TITLE) >= 0)
      return false;


    if (task9.compareTo(task8, CompareCriteria.TITLE) <= 0)
      return false;

    if (task1.compareTo(task2, CompareCriteria.TITLE) <= 0)
      return false;

    if (task4.compareTo(task1, CompareCriteria.TITLE) >= 0)
      return false;

    if (task2.compareTo(task4, CompareCriteria.TITLE) <= 0)
      return false;

    if (task4.compareTo(task5, CompareCriteria.TITLE) >= 0)
      return false;

    if (task2.compareTo(task1, CompareCriteria.TITLE) >= 0)
      return false;

    if (task1.compareTo(task3, CompareCriteria.TITLE) != 0)
      return false;

    if (task1.compareTo(task5, CompareCriteria.TITLE) <= 0)
      return false;

    if (task5.compareTo(task1, CompareCriteria.TITLE) >= 0)
      return false;

    if (task6.compareTo(task4, CompareCriteria.TITLE) <= 0)
      return false;

    if (task4.compareTo(task6, CompareCriteria.TITLE) >= 0)
      return false;

    return true;

  }


  /**
   * Tests the correctness of a Task compareTo() method implementation when the criteria parameter
   * is LEVEL.
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testCompareToLevel() {

    Task task1 = new Task("Cook", "Dinner", 30, PriorityLevel.OPTIONAL);
    Task task2 = new Task("Drive", "home", 10, PriorityLevel.LOW);
    Task task3 = new Task("Cook", "Dinner", 30, PriorityLevel.MEDIUM);
    Task task4 = new Task("Cook", "Dinner", 30, PriorityLevel.URGENT);
    Task task5 = new Task("Cook", "Dinner", 30, PriorityLevel.URGENT);

    if (task1.compareTo(task2, CompareCriteria.LEVEL) >= 0)
      return false;

    if (task2.compareTo(task4, CompareCriteria.LEVEL) >= 0)
      return false;

    if (task5.compareTo(task3, CompareCriteria.LEVEL) <= 0)
      return false;

    if (task5.compareTo(task4, CompareCriteria.LEVEL) != 0)
      return false;

    return true;

  }

  /**
   * Tests the correctness of a TaskQueue enqueue() method implementation including exceptions and
   * edge cases (if applicable).
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testEnqueue() {


    // Testing priority level enqueue
    Task task1 = new Task("DDD", "Dinner", 10, PriorityLevel.OPTIONAL);
    Task task2 = new Task("CCC", "home", 20, PriorityLevel.LOW);
    Task task3 = new Task("BBB", "Dinner", 30, PriorityLevel.MEDIUM);
    Task task4 = new Task("EEE", "Dinner", 40, PriorityLevel.URGENT);
    Task task5 = new Task("AAA", "Dinner", 50, PriorityLevel.URGENT);

    TaskQueue taskQueue = new TaskQueue(3, CompareCriteria.LEVEL);
    taskQueue.enqueue(task1);
    taskQueue.enqueue(task2);

    if (!taskQueue.getHeapData()[0].equals(task2))
      return false;

    task4.markCompleted();

    // test that enqueue throw IllegalArgumentException when attempting to queue a task that is
    // already completed
    try {
      taskQueue.enqueue(task4);
      return false;
    } catch (IllegalArgumentException e) {
    }

    taskQueue.enqueue(task5);

    if (!taskQueue.getHeapData()[0].equals(task5))
      return false;

    if (!taskQueue.getHeapData()[1].equals(task1))
      return false;

    if (taskQueue.size() != 3)
      return false;

    // queue is full; enqueue should throw an illegal state exception
    try {
      taskQueue.enqueue(task3);
      return false;
    } catch (IllegalStateException e) {
    }

    // Testing time
    TaskQueue taskQueue2 = new TaskQueue(5, CompareCriteria.TIME);
    taskQueue2.enqueue(task1);
    taskQueue2.enqueue(task2);
    taskQueue2.enqueue(task3);

    if (!taskQueue2.getHeapData()[0].equals(task3))
      return false;

    taskQueue2.enqueue(task5);


    if (!taskQueue2.getHeapData()[0].equals(task5))
      return false;

    // Testing title
    TaskQueue taskQueue3 = new TaskQueue(5, CompareCriteria.TITLE);
    taskQueue3.enqueue(task3);
    taskQueue3.enqueue(task2);

    if (!taskQueue3.getHeapData()[0].equals(task3))
      return false;

    taskQueue3.enqueue(task1);
    taskQueue3.enqueue(task5);

    if (!taskQueue3.getHeapData()[0].equals(task5))
      return false;

    // Extra test for edge cases

    TaskQueue taskQueue4 = new TaskQueue(10, CompareCriteria.TITLE);


    Task task11 = new Task("Cook", "Dinner", 30, PriorityLevel.HIGH);
    Task task22 = new Task("Drive", "home", 10, PriorityLevel.HIGH);
    Task task33 = new Task("Cook", "Dinner", 30, PriorityLevel.LOW);
    Task task44 = new Task("lowercase", "Dinner", 30, PriorityLevel.LOW);
    Task task55 = new Task("Cooking", "Dinner", 30, PriorityLevel.LOW);
    Task task66 = new Task("loWer", "Dinner", 30, PriorityLevel.LOW);
    Task task77 = new Task("zzzz", "Dinner", 30, PriorityLevel.LOW);
    Task task88 = new Task("aaaa", "Dinner", 30, PriorityLevel.LOW);
    Task task99 = new Task("ZZZZ", "Dinner", 30, PriorityLevel.LOW);

    taskQueue4.enqueue(task44);

    if (!taskQueue4.peekBest().equals(task44))
      return false;

    taskQueue4.enqueue(task66);

    if (!taskQueue4.peekBest().equals(task66))
      return false;

    taskQueue4.enqueue(task77);

    if (!taskQueue4.peekBest().equals(task66))
      return false;

    taskQueue4.enqueue(task11);
    taskQueue4.enqueue(task22);
    taskQueue4.enqueue(task33);

    if (!taskQueue4.peekBest().equals(task11))
      return false;

    taskQueue4.enqueue(task55);
    taskQueue4.enqueue(task88);
    taskQueue4.enqueue(task99);

    if (!taskQueue4.peekBest().equals(task11))
      return false;

    return true;
  }


  /**
   * Tests the correctness of a TaskQueue dequeue() method implementation including exceptions and
   * edge cases (if applicable).
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testDequeue() {

    Task task1 = new Task("Cook", "Dinner", 30, PriorityLevel.OPTIONAL);
    Task task2 = new Task("Drive", "home", 10, PriorityLevel.LOW);
    Task task3 = new Task("Cook", "Dinner", 30, PriorityLevel.MEDIUM);
    Task task4 = new Task("Cook", "Dinner", 30, PriorityLevel.URGENT);
    Task task5 = new Task("Cook", "Dinner", 30, PriorityLevel.URGENT);

    TaskQueue taskQueue = new TaskQueue(5, CompareCriteria.LEVEL);

    // Queue is empty; dequeue should throw nosuchelement exception
    try {
      taskQueue.dequeue();
      return false;
    } catch (NoSuchElementException e) {
    }

    taskQueue.enqueue(task2);
    taskQueue.enqueue(task5);
    taskQueue.enqueue(task3);
    taskQueue.enqueue(task4);

    if (!taskQueue.dequeue().equals(task5))
      return false;

    if (!taskQueue.dequeue().equals(task4))
      return false;

    taskQueue.enqueue(task1);

    if (!taskQueue.dequeue().equals(task3))
      return false;

    if (!taskQueue.dequeue().equals(task2))
      return false;

    if (!taskQueue.dequeue().equals(task1))
      return false;


    // Queue is empty; dequeue should throw nosuchelement exception
    try {
      taskQueue.dequeue();
      return false;
    } catch (NoSuchElementException e) {
    }


    Task task11 = new Task("Cook", "Dinner", 30, PriorityLevel.HIGH);
    Task task22 = new Task("Drive", "home", 10, PriorityLevel.HIGH);
    Task task33 = new Task("Cook", "Dinner", 30, PriorityLevel.LOW);
    Task task44 = new Task("lowercase", "Dinner", 30, PriorityLevel.LOW);
    Task task55 = new Task("Cooking", "Dinner", 30, PriorityLevel.LOW);
    Task task66 = new Task("loWer", "Dinner", 30, PriorityLevel.LOW);
    Task task77 = new Task("zzzz", "Dinner", 30, PriorityLevel.LOW);
    Task task88 = new Task("aaaa", "Dinner", 30, PriorityLevel.LOW);
    Task task99 = new Task("ZZZZ", "Dinner", 30, PriorityLevel.LOW);

    TaskQueue taskQueue4 = new TaskQueue(10, CompareCriteria.TITLE);

    taskQueue4.enqueue(task11);
    taskQueue4.enqueue(task22);
    taskQueue4.enqueue(task33);
    taskQueue4.enqueue(task44);
    taskQueue4.enqueue(task55);
    taskQueue4.enqueue(task66);
    taskQueue4.enqueue(task77);
    taskQueue4.enqueue(task88);
    taskQueue4.enqueue(task99);

    if (!taskQueue4.dequeue().equals(task11))
      return false;


    if (!taskQueue4.dequeue().equals(task33))
      return false;


    if (!taskQueue4.dequeue().equals(task55))
      return false;


    if (!taskQueue4.dequeue().equals(task22))
      return false;


    if (!taskQueue4.dequeue().equals(task99))
      return false;


    if (!taskQueue4.dequeue().equals(task88))
      return false;


    if (!taskQueue4.dequeue().equals(task66))
      return false;


    if (!taskQueue4.dequeue().equals(task44))
      return false;


    if (!taskQueue4.dequeue().equals(task77))
      return false;

    try {
      taskQueue4.dequeue();
      return false;
    } catch (NoSuchElementException e) {
    }

    return true;
  }


  /**
   * Tests the correctness of a TaskQueue peek() method implementation including exceptions and edge
   * cases (if applicable).
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testPeek() {


    Task task1 = new Task("Cook", "Dinner", 30, PriorityLevel.OPTIONAL);
    Task task2 = new Task("Drive", "home", 10, PriorityLevel.LOW);
    Task task3 = new Task("Cook", "Dinner", 30, PriorityLevel.MEDIUM);
    Task task4 = new Task("Cook", "Dinner", 30, PriorityLevel.URGENT);
    Task task5 = new Task("Cook", "Dinner", 30, PriorityLevel.URGENT);

    TaskQueue taskQueue = new TaskQueue(5, CompareCriteria.LEVEL);

    // Queue is empty; dequeue should throw nosuchelement exception
    try {
      taskQueue.dequeue();
      return false;
    } catch (NoSuchElementException e) {
    }

    taskQueue.enqueue(task2);
    taskQueue.enqueue(task5);
    taskQueue.enqueue(task3);
    taskQueue.enqueue(task4);


    if (!taskQueue.peekBest().equals(task5))
      return false;

    if (taskQueue.size() != 4)
      return false;

    taskQueue.dequeue();

    if (!taskQueue.peekBest().equals(task4))
      return false;

    taskQueue.dequeue();


    taskQueue.enqueue(task1);

    if (!taskQueue.peekBest().equals(task3))
      return false;

    taskQueue.dequeue();

    if (!taskQueue.peekBest().equals(task2))
      return false;

    taskQueue.dequeue();

    if (!taskQueue.peekBest().equals(task1))
      return false;

    taskQueue.dequeue();


    // Queue is empty; dequeue should throw nosuchelement exception
    try {
      taskQueue.dequeue();
      return false;
    } catch (NoSuchElementException e) {
    }

    return true;



  }

  /**
   * 
   * Tests the correctness of a TaskQueue reprioritizee() method implementation including exceptions
   * and edge cases (if applicable).
   * 
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testReprioritize() {


    Task task1 = new Task("CCC", "Dinner", 50, PriorityLevel.OPTIONAL);
    Task task2 = new Task("BBB", "home", 40, PriorityLevel.LOW);
    Task task3 = new Task("AAA", "Dinner", 30, PriorityLevel.MEDIUM);
    Task task4 = new Task("DDD", "Dinner", 20, PriorityLevel.URGENT);
    Task task5 = new Task("EEE", "Dinner", 10, PriorityLevel.URGENT);

    TaskQueue taskQueue = new TaskQueue(5, CompareCriteria.LEVEL);

    taskQueue.enqueue(task1);
    taskQueue.enqueue(task2);
    taskQueue.enqueue(task3);
    taskQueue.enqueue(task4);
    taskQueue.enqueue(task5);


    if (!taskQueue.peekBest().equals(task4))
      return false;

    taskQueue.reprioritize(CompareCriteria.TIME);


    if (!taskQueue.peekBest().equals(task1))
      return false;

    taskQueue.reprioritize(CompareCriteria.TITLE);



    if (!taskQueue.peekBest().equals(task3))
      return false;

    taskQueue.dequeue();



    if (!taskQueue.peekBest().equals(task2))
      return false;

    taskQueue.reprioritize(CompareCriteria.TIME);



    if (!taskQueue.peekBest().equals(task1))
      return false;

    taskQueue.dequeue();



    if (!taskQueue.peekBest().equals(task2))
      return false;



    return true;
  }



  public static void main(String[] args) {

    System.out.println("Testing compare to TIME: " + (testCompareToTime() ? "Passed" : "Failed"));
    System.out.println("Testing compare to TITLE: " + (testCompareToTitle() ? "Passed" : "Failed"));
    System.out.println("Testing compare to LEVEL: " + (testCompareToLevel() ? "Passed" : "Failed"));
    System.out.println("Testing enqueue(): " + (testEnqueue() ? "Passed" : "Failed"));
    System.out.println("Testing dequeue(): " + (testDequeue() ? "Passed" : "Failed"));
    System.out.println("Testing peek(): " + (testPeek() ? "Passed" : "Failed"));
    System.out.println("Testing reprioritize(): " + (testReprioritize() ? "Passed" : "Failed"));


  }

}
