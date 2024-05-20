//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 - Markov Model
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
// Online Sources:
// https://learn.zybooks.com/zybook/WISCCOMPSCI300Spring2024?selectedPanel=student-activity
// Zybook Chapters 10.13 and 10.14 helped me with the processText function by familiarizing
// me with the syntax of hashmaps
//
///////////////////////////////////////////////////////////////////////////////



import java.util.HashMap;

// CITATION:
// https://learn.zybooks.com/zybook/WISCCOMPSCI300Spring2024?selectedPanel=student-activity
// Zybook Chapters 10.13 and 10.14 helped me with the processText function by familiarizing
// me with the syntax of hashmaps

public class MarkovModel {


  // A map of substrings of length windowWidth to stacks containing the observed characters which
  // follow that substring of characters.
  private HashMap<String, MyStack<Character>> model;


  // The current windowWidth number of characters that the model will use to predict the next
  // character. Should always be maintained at length windowWidth using methods from MyQueue.
  private MyQueue<Character> currentQueue;


  // The number of characters to consider in a substring when generating new text.
  private int windowWidth;


  // A boolean value indicating whether to shuffle the stacks during text generation.
  private boolean shuffleStacks;


  /**
   * Constructs a MarkovModel with a specified order. This model will predict the next character in
   * the generated text based on strings of length k.
   * 
   * @param k       - the order of the Markov model (length of substrings to consider)
   * 
   * @param shuffle - whether this model should shuffle the stacks during the text generation phase.
   */
  public MarkovModel(int k, boolean shuffle) {
    windowWidth = k;
    shuffleStacks = shuffle;  
    model = new HashMap<>();
    currentQueue = new MyQueue<>();

  }


  /**
   * Reads in the provided text and builds a model, which maps each k-length substring of the text
   * to a stack containing all of the characters that follow that substring in the text. (See the
   * writeup for more details.)
   * 
   * @param text - the text to be processed to build the model.
   * 
   */
  public void processText(String text) {

    // Loop through text to build/train model
    for (int i = 0; i <= text.length() - windowWidth - 1; i++) {
      String textsub = text.substring(i, i + windowWidth);
      char nextChar = text.charAt(i + windowWidth);
      // Update/create new stack for the given substring and add the next char to it
      model.computeIfAbsent(textsub, k -> new MyStack<>()).push(nextChar); // CITE: Zybook sections
                                                                           // 10.13 and 10.14
    }

  }

  /**
   * 
   * Initializes the current queue with the first k-letter substring from the text, setting the
   * initial state for text generation.
   * 
   * @param text - the text from which to derive the initial queue state.
   */
  public void initializeQueue(String text) {

    if (text.length() >= windowWidth) {
      for (int i = 0; i < windowWidth; i++) {
        currentQueue.enqueue(text.charAt(i));
      }
    }

  }


  /**
   * 
   * Generates text of a specified length based on the model. You will need to do the following:
   * 
   * 1. Start with an empty string, for concatenating the results
   * 
   * 2. Add the current state of the queue to the string (you may assume initializeQueue() has
   * already been called)
   * 
   * 3. Until the produced string is of the desired length:
   * 
   * 3a. Get the current queue state, see if the HashMap has an entry for it, and add the next
   * character from its stack to the output string
   * 
   * 3b. If shuffleStacks is true, shuffle the stack corresponding to the current queue state before
   * continuing
   * 
   * 3c. Add the new char into the current queue, and maintain its length appropriately
   * 
   * 3d. If the model doesn't have the current queue state, OR if the current queue state's stack
   * has been emptied out:
   * 
   * - re-seed the model using the argument string text
   * 
   * - add a newline to the output
   * 
   * - and continue!
   * 
   * @param length - the desired length of the generated text
   * @param text   - the text to use for re-seeding the model if necessary.
   * @return the generated text.
   * 
   */
  public String generateText(int length, String text) {

    StringBuilder generatedText = new StringBuilder();
    for (int i = 0; i < length; i++) {

      StringBuilder sb = new StringBuilder();
      for (Character ch : currentQueue.getList()) {
        sb.append(ch);
      }

      // Get the current queue state
      String currentSubstring = sb.toString();


      // Check if the model has an entry for the current queue state
      if (model.containsKey(currentSubstring) && !model.get(currentSubstring).isEmpty()) {
        // Get the next character from the stack associated with the current queue state
        char nextChar = model.get(currentSubstring).pop();
        generatedText.append(nextChar);
        // If shuffleStacks is true, shuffle the stack
        if (shuffleStacks) {
          model.get(currentSubstring).shuffle();
        }
        // Add the new char into the current queue
        currentQueue.dequeue();
        currentQueue.enqueue(nextChar);
      } else {
        // If the model doesn't have the current queue state or if the stack is empty
        // re-seed the model using the argument string text
        processText(text);
        generatedText.append('\n'); // Add a newline to the output
        // Reinitialize the queue with the next k-letter substring from the text
        currentQueue.dequeue();
        currentQueue.enqueue(text.charAt(i % (text.length() - windowWidth)));
      }
    }
    return generatedText.toString();


  }



}
