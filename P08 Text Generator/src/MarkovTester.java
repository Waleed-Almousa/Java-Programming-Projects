//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 - Markov Tester
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



import java.util.ArrayList;

public class MarkovTester {

  /**
   * verify that adding things to the stack correctly increases the stack’s size, and that the
   * ordering of all elements is correct.
   * 
   * @return true if all test cases pass and outputs are as expected, false otherwise
   */
  public static boolean testStackAdd() {

    ArrayList<Integer> exp = new ArrayList<>(); // stores expected value of stack.getlist
    MyStack<Integer> stack = new MyStack<>();
    for (int i = 0; i < 5; i++) {
      stack.push(i);
      exp.add(4 - i);
    }
    ArrayList<Integer> list = stack.getList();


    // ensures list is as expected and is correct order
    for (int i = 0; i < 5; i++) {
      if (exp.get(i) != list.get(i))
        return false;
    }


    if (list.size() != 5)
      return false;

    if (list.get(0) != 4)
      return false;

    return true;
  }

  /**
   * 
   * verify that removing things from the stack (after adding them) correctly decreases the stack’s
   * size, and that the ordering of all remaining elements is correct. Additionally, verify that a
   * stack that has had elements added to it can become empty again later.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testStackRemove() {

    ArrayList<Integer> exp = new ArrayList<>(); // stores expected value of stack.getlist

    MyStack<Integer> stack = new MyStack<>();
    for (int i = 0; i < 5; i++) {
      stack.push(i);
      exp.add(4 - i);
    }

    // removes first 3 elements from stack
    for (int i = 0; i < 3; i++) {
      stack.pop();
      exp.remove(0);
    }

    ArrayList<Integer> list = stack.getList();



    // ensure all elements of list are as expected
    for (int i = 0; i < 2; i++) {
      if (list.get(i) != exp.get(i))
        return false;

    }

    if (list.size() != 2)
      return false;

    if (list.get(0) != 1)
      return false;

    if (stack.pop() != 1)
      return false;

    stack.pop();

    // verifies stack can be emptied after having stuff added to it
    if (!stack.isEmpty())
      return false;


    return true;


  }

  /**
   * – verify that calling shuffle() on the stack results in a stack that still contains all of the
   * same elements, but in any order that is different from the original order.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testStackShuffle() {

    MyStack<Integer> stack = new MyStack<>();
    for (int i = 0; i < 5; i++) {
      stack.push(i);
    }
    ArrayList<Integer> originalList = new ArrayList<>(stack.getList());
    stack.shuffle();
    stack.shuffle(); // Shuffling again to ensure randomness
    ArrayList<Integer> shuffledList = stack.getList();

    // ensure shuffled stack still contains all original elements
    for (int j = 0; j < 5; j++) {
      if (!shuffledList.contains(j))
        return false;
    }

    // ensure shuffled list still has 5 elements
    if (shuffledList.size() != 5)
      return false;

    // ensures shuffled list does not equal original list
    if (originalList.equals(shuffledList)) {
      System.out.print("could be a coincidence?");
      return false;
    }

    return true;
  }


  /**
   * – verify that adding things to the queue correctly increases the queue’s size, and that the
   * ordering of all elements is correct.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testQueueAdd() {

    MyQueue<Integer> queue = new MyQueue<>();


    for (int i = 0; i < 5; i++) {
      queue.enqueue(i);
    }
    ArrayList<Integer> list = queue.getList();

    // ensure correct size of list
    if (list.size() != 5)
      return false;

    // ensure correct order of queue
    for (int i = 0; i < 5; i++) {
      if (i != list.get(i))
        return false;
    }



    return true;
  }



  /**
   * – verify that removing things from the queue (after adding them) correctly decreases the
   * queue’s size, and that the ordering of all remaining elements is correct. This test should also
   * verify that the custom method maintainSize(int) works as described.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testQueueRemove() {


    MyQueue<Integer> queue = new MyQueue<>();
    for (int i = 0; i < 5; i++) {
      queue.enqueue(i);
    }



    for (int i = 0; i < 3; i++) {
      queue.dequeue();
    }
    ArrayList<Integer> list = queue.getList();

    // ensure size properly decrements
    if (list.size() != 2)
      return false;

    // ensuring elemtns are in correct order
    if (list.get(1) != 4)
      return false;
    // ensure deque returns correct element
    if (queue.dequeue() != 3)
      return false;


    for (int j = 0; j < 10; j++) {
      queue.enqueue(j);
    }

    queue.maintainSize(5);

    // ensure maintainSize works as intended
    if (queue.size() != 5)
      return false;


    return true;
  }


  /**
   * verify that calling peek() on both a stack and a queue returns the correct element AND does not
   * make any modifications to the data structure.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testPeek() {

    MyStack<Integer> stack = new MyStack<>();
    for (int i = 0; i < 5; i++) {
      stack.push(i);
    }
    MyQueue<Integer> queue = new MyQueue<>();
    for (int i = 0; i < 5; i++) {
      queue.enqueue(i);
    }

    // ensure peek returns correct element
    if (stack.peek() != 4 || queue.peek() != 0)
      return false;

    // ensure peek does not change size
    if (stack.getList().size() != 5 || queue.getList().size() != 5)
      return false;

    return true;
  }


  public static void main(String[] args) {
    // Run tests
    System.out.println("testStackAdd(): " + testStackAdd());
    System.out.println("testStackRemove(): " + testStackRemove());
    System.out.println("testStackShuffle(): " + testStackShuffle());
    System.out.println("testQueueAdd(): " + testQueueAdd());
    System.out.println("testQueueRemove(): " + testQueueRemove());
    System.out.println("testPeek(): " + testPeek());

  }



}
