//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 - TaskQueue
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
 * 
 * 
 */
public class TaskQueue {


  // oversized array that holds all of Tasks in the heap
  private Task[] heapData;

  // the number of items in the TaskQueue
  private int size;

  // the criteria used to determine how to prioritize Tasks in the queue
  private CompareCriteria priorityCriteria;


  /**
   * Creates an empty TaskQueue with the given capacity and priority criteria.
   * 
   * @param capacity       - the max number of Tasks this priority queue can hold
   * 
   * @param priorityCriter - the criteria for the queue to use to determine a Task's priority
   * @throws IllegalArgumentException - with a descriptive message if the capacity is non-positive
   * 
   */
  public TaskQueue(int capacity, CompareCriteria priorityCriteria) {

    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }

    this.priorityCriteria = priorityCriteria;
    this.heapData = new Task[capacity];

  }


  /**
   * Reports if a TaskQueue is empty.
   * 
   * @return true if this TaskQueue is empty, false otherwise
   * 
   */
  public boolean isEmpty() {

    if (this.size == 0) {
      return true;
    }
    return false;
  }


  /**
   * Reports the size of a TaskQueue.
   * 
   * @return the number of Tasks in this TaskQueue
   * 
   */
  public int size() {
    return this.size;
  }


  /**
   * Gets the Task in a TaskQueue that has the highest priority WITHOUT removing it. The Task that
   * has the highest priority may differ based on the current priority criteria.
   * 
   * @return the Task in this queue with the highest priority
   * 
   * @throws NoSuchElementException - with descriptive message if this TaskQueue is empty
   * 
   */
  public Task peekBest() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("TaskQueue is empty");
    }
    return this.heapData[0];

  }


  /**
   * Adds the newTask to this priority queue.
   * 
   * @param newTask - the task to add to the queue
   * 
   * @throws IllegalArgumentException - with a descriptive message if the Task is already completed
   * 
   * @throws IllegalStateException    - with a descriptive message if the priority queue is full
   */
  public void enqueue(Task newTask) {

    if (newTask.isCompleted()) {
      throw new IllegalArgumentException("Task is already completed");
    }
    if (this.size == heapData.length) {
      throw new IllegalStateException("TaskQueue is full");
    }

    this.heapData[size] = newTask;

    this.size++;

    this.percolateUp(size - 1);

    this.percolateDown(0);


  }


  /**
   * Fixes one heap violation by moving it up the heap.
   * 
   * @param index - the of the element where the violation may be
   * 
   */
  protected void percolateUp(int index) {
    if (index <= 0) {
      return;
    }
    int parentIndex = (index - 1) / 2;
    Task current = heapData[index];
    Task parent = heapData[parentIndex];
    if (current.compareTo(parent, priorityCriteria) > 0) {
      swap(parentIndex, index);
      percolateUp(parentIndex);
    }
    percolateUpHelper();
  }

  private void percolateUpHelper() {

    for (int i = size; i <= 0; --i) {
      this.percolateUp(i);
    }
  }


  /**
   * @author Mouna Kacem
   * 
   *         ALL CREDITS FOR THIS METHOD GO TO THE INSTRUCTOR WHO IMPLEMENTED THIS IN LECTURE
   * 
   *         Helper Method: Swaps the elements at indices i and j
   * 
   * 
   * @param i index to swap with
   * @param j index to swap with
   * @throws IndexOutOfBoundsException if i or j is not in the range 0..size-1
   */
  private void swap(int i, int j) throws IndexOutOfBoundsException {
    Task temp = this.heapData[i];
    this.heapData[i] = this.heapData[j];
    this.heapData[j] = temp;
  }

  /**
   * Gets and removes the Task that has the highest priority. The Task that has the highest priority
   * may differ based on the current priority criteria.
   * 
   * @return the Task in this queue with the highest priority
   * 
   * @throws NoSuchElementException - with descriptive message if this TaskQueue is empty
   * 
   * 
   */
  public Task dequeue() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("TaskQueue is empty");
    }
    Task removedTask = this.heapData[0];
    heapData[0] = heapData[size - 1];
    heapData[size - 1] = null;
    size--;
    percolateDown(0);
    return removedTask;


  }

  /**
   * Fixes one heap violation by moving it down the heap.
   * 
   * @param index - the of the element where the violation may be
   * 
   */
  protected void percolateDown(int index) {

    int leftIndex = 2 * index + 1;
    int rightIndex = 2 * index + 2;
    int maxIndex = index;

    // Find the index of the maximum priority child
    if (leftIndex < this.size && heapData[leftIndex] != null
        && heapData[leftIndex].compareTo(heapData[maxIndex], priorityCriteria) > 0) {
      maxIndex = leftIndex;
    }
    if (rightIndex < size && heapData[rightIndex] != null
        && heapData[rightIndex].compareTo(heapData[maxIndex], priorityCriteria) > 0) {
      maxIndex = rightIndex;
    }

    if (maxIndex != index) {
      Task temp = heapData[index];
      heapData[index] = heapData[maxIndex];
      heapData[maxIndex] = temp;
      percolateDown(maxIndex);
    }

  }


  /**
   * Changes the priority criteria of this priority queue and fixes it so that is is a proper
   * priority queue based on the new criteria.
   * 
   * @param priorityCriteria - the (new) criteria that should be used to prioritize the Tasks in
   *                         this queue
   * 
   */
  public void reprioritize(CompareCriteria priorityCriteria) {
    this.priorityCriteria = priorityCriteria;
    // Rebuild the heap according to the new priority criteria
    for (int i = size / 2 - 1; i >= 0; i--) {
      percolateDown(i);
    }
  }


  /**
   * Gets the criteria use to prioritize tasks in this a TaskQueue.
   * 
   * @return the prioritization criteria of this TaskQueue
   * 
   */
  public CompareCriteria getPriorityCriteria() {
    return this.priorityCriteria;
  }



  /**
   * Creates and returns a deep copy of the heap's array of data.
   * 
   * @return the deep copy of the array holding the heap's data
   * 
   */
  public Task[] getHeapData() {
    Task[] copy = new Task[size];
    System.arraycopy(heapData, 0, copy, 0, size);
    return copy;

  }



}
