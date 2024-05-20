//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 - My Stack
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
import java.util.Collections;



public class MyStack<T> implements StackADT<T> {

  // A reference to the LinkedNode currently at the top of the stack, which is null when the stack
  // is empty.
  private LinkedNode<T> top;


  /**
   * Add a new element to the top of this stack, assumed to be non-null.
   *
   * @param value the value to add
   */
  @Override
  public void push(T value) {
    LinkedNode<T> newNode = new LinkedNode<>(value);
    newNode.setNext(top);
    top = newNode;
  }



  /**
   * Removes and returns the value added to this stack most recently
   *
   * @return the most recently-added value, or null if the stack is empty
   */
  @Override
  public T pop() {
    if (isEmpty()) {
      return null;
    }
    T removedValue = top.getData();
    top = top.getNext();
    return removedValue;
  }


  /**
   * Accesses the value added to this stack most recently, without modifying the stack
   *
   * @return the most recently-added value, or null if the stack is empty
   */
  @Override
  public T peek() {
    if (isEmpty()) {
      return null;
    }
    return top.getData();
  }



  /**
   * Returns true if this stack contains no elements.
   *
   * @return true if the stack contains no elements, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return top == null;
  }


  /**
   * 
   * Creates a copy of the current contents of this stack in the order they are present here, in
   * ArrayList form. This method should traverse the stack without removing any elements, and add
   * the values (not the nodes!) to an ArrayList in the order they appear in the stack, with the top
   * of the stack at index 0.
   * 
   * @return an ArrayList representation of the current state of this stack
   */
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<>();
    LinkedNode<T> current = top;
    while (current != null) {
      list.add(current.getData());
      current = current.getNext();
    }
    return list;

  }

  /**
   * Randomly reorder the contents of this stack:
   * 
   * 1. Create an ArrayList representation of all of the elements of this stack, in order
   * 
   * 2. Use Collections.shuffle() to create a new random ordering of the contents
   * 
   * 3. REPLACE the current contents of the stack with the contents in their new order from the
   * ArrayList
   * 
   * IMPORTANT: By the conventions established in getList(), the top of the stack is at index 0 in
   * the ArrayList representation!
   * 
   */
  public void shuffle() {

    ArrayList<T> stackList = getList();
    Collections.shuffle(stackList);
    // Reconstruct the stack with shuffled elements
    top = null; // Clear the current stack
    for (T value : stackList) {
      push(value);
    }

  }



}
