//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 - My Queue
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

/**
 * A generic singly-linked queue implementation, which contains some additional methods to
 * facilitate the workings of the Markov Model.
 * 
 * @param <T> the type of data contained in this queue
 */
public class MyQueue<T> implements QueueADT<T> {

  // A reference to the LinkedNode currently at the back of the queue, which contains the
  // most-recently added value in the queue.
  private LinkedNode<T> back;

  // A reference to the LinkedNode currently at the front of the queue, which contains the
  // least-recently added value in the queue.
  private LinkedNode<T> front;

  // the number of values currently present in the queue
  private int size;


  /**
   * Add a new element to the back of the queue, assumed to be non-null.
   *
   * @param value the value to add
   */
  @Override
  public void enqueue(T value) {
    LinkedNode<T> newNode = new LinkedNode<>(value);
    if (isEmpty()) {
      front = newNode;
      back = newNode;
    } else {
      back.setNext(newNode);
      back = newNode;
    }
    size++;
  }


  /**
   * Removes and returns the value added to this queue least recently
   *
   * @return the least recently-added value, or null if the queue is empty
   */
  @Override
  public T dequeue() {
    if (isEmpty()) {
      return null;
    }
    T removedValue = front.getData();
    front = front.getNext();
    size--;
    if (isEmpty()) {
      back = null;
    }
    return removedValue;
  }


  /**
   * Accesses the value added to this queue least recently, without modifying the queue
   *
   * @return the least recently-added value, or null if the queue is empty
   */
  @Override
  public T peek() {
    if (isEmpty()) {
      return null;
    }
    return front.getData();
  }

  /**
   * Returns true if this queue contains no elements.
   *
   * @return true if the queue contains no elements, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }


  /**
   * Returns the number of elements in the queue.
   *
   * @return the number of elements in the queue
   */
  @Override
  public int size() {
    return size;
  }



  /**
   * Enforces a maximum size for this queue. If the queue is already smaller than the requested
   * size, this method does nothing.
   * 
   * @param size - the maximum number of elements this queue should contain once the method has run
   */
  public void maintainSize(int size) {
    while (this.size() > size) {
      dequeue();
    }


  }

  /**
   * 
   * Creates a copy of the current contents of this queue in the order they are present here, in
   * ArrayList form. This method should traverse the queue without removing any elements, and add
   * the values (not the nodes!) to an ArrayList in the order they appear in the queue.
   * 
   * @return an ArrayList representation of the current state of this queue
   * 
   */
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<>();
    LinkedNode<T> current = front;
    while (current != null) {
      list.add(current.getData());
      current = current.getNext();
    }
    return list;
  }


  /**
   * Concatenates the string representation of all values in this queue in order, from the front of
   * the queue to the back. Does not separate values (no whitespace, no commas).
   * 
   * @Override toString in class Object
   * @return the string representation of this queue
   * 
   */
  @Override
  public String toString() {
    String builder = "";
    LinkedNode<T> current = front;
    while (current != null) {
      builder += (current.getData().toString());
      current = current.getNext();
    }
    return builder.toString();
  }



}
