
public class MyTester {

  public static void main(String[] args) {

    //
    // Task task1 = new Task("Cook", "Dinner", 30, PriorityLevel.HIGH);
    // Task task2 = new Task("Cook", "Dinner", 45, PriorityLevel.HIGH);
    // Task task3 = new Task("Eat", "nothing", 30, PriorityLevel.LOW);
    //

    //
    //
    // Task task1 = new Task("Cook", "Dinner", 30, PriorityLevel.HIGH);
    // Task task2 = new Task("Drive", "home", 10, PriorityLevel.HIGH);
    //
    // Task task3 = new Task("Cook", "Dinner", 30, PriorityLevel.LOW);

    //



    Task task1 = new Task("Cook", "Dinner", 30, PriorityLevel.OPTIONAL);
    Task task2 = new Task("Drive", "home", 10, PriorityLevel.LOW);

    Task task3 = new Task("Cook", "Dinner", 30, PriorityLevel.MEDIUM);
    Task task4 = new Task("Cook", "Dinner", 30, PriorityLevel.URGENT);
    Task task5 = new Task("Cook", "Dinner", 30, PriorityLevel.URGENT);


    TaskQueue taskQueue = new TaskQueue(5, CompareCriteria.LEVEL);
    taskQueue.enqueue(task1);
    taskQueue.enqueue(task2);



    System.out.println(taskQueue.getHeapData()[0].equals(task2));

    taskQueue.enqueue(task5);
    System.out.println(taskQueue.getHeapData()[0].equals(task5));
    System.out.println(taskQueue.getHeapData()[1]);



    // System.out.println(task2.compareTo(task4, CompareCriteria.LEVEL));
    //
    // System.out.println(task3.compareTo(task1, CompareCriteria.LEVEL));



  }

}
