import java.util.Iterator;

public class MyTester {


  public static void main(String[] args) {


    //
    // Gradebook gradebook = new Gradebook("Math", 60.0);
    // StudentRecord record1 = new StudentRecord("John", "john@example.com", 75.0);
    // StudentRecord record2 = new StudentRecord("Alice", "alice@example.com", 80.0);
    // gradebook.addStudent(record1);
    // gradebook.addStudent(record2);
    // StudentRecord record3 = new StudentRecord("bob", "bob@example.com", 20.0);
    //
    // gradebook.addStudent(record3);
    //
    // StudentRecord record4 = new StudentRecord("Jake", "jake@example.com", 20.0);
    // gradebook.addStudent(record4);
    // StudentRecord record5 = new StudentRecord("Zach", "zach@example.com", 20.0);
    // gradebook.addStudent(record5);

    // System.out.println(gradebook.prettyString());
    // System.out.println(gradebook.successor(record3));
    // gradebook.removeStudent("john@example.com");
    // System.out.println(gradebook.successor(record3));
    // gradebook.removeStudent("bob@example.com");
    // System.out.println(gradebook.successor(record1));



    //
    //
    //
    // System.out.print("\n\nbl\n\n");
    //
    // Gradebook gradebook2 = new Gradebook("Math", 60.0);
    //
    // StudentRecord record44 = new StudentRecord("Eve", "eve@example.com", 79.0); // Alice
    // gradebook2.addStudent(record44);
    // StudentRecord record55 = new StudentRecord("Carl", "carl@example.com", 76.0);
    // gradebook2.addStudent(record55);
    // gradebook2.addStudent(record1);
    // gradebook2.addStudent(record3);
    // gradebook2.addStudent(record2);
    //
    // System.out.println(gradebook2.prettyString());
    // System.out.println(gradebook2.toString());
    //
    //
    // gradebook2.enablePassingGradeIterator();
    // Iterator<StudentRecord> iterator = gradebook2.iterator();
    // gradebook2.enablePassingGradeIterator();
    //
    // System.out.print(iterator.next());
    //
    //
    //
    //// gradebook2.removeStudent("eve@example.com");
    //
    //// System.out.println(gradebook2.prettyString());
    //// System.out.println(gradebook2.toString());
    ////
    //// System.out.println(gradebook2.toString().split("\n")[0]);
    //// System.out.println(gradebook2.toString().split("\n")[1]);
    //// System.out.println(gradebook2.toString().split("\n")[2]);
    //// System.out.println(gradebook2.toString().split("\n")[3]);
    ////
    ////
    ////
    //// System.out.println(gradebook2.toString().split("\n")[1].equals(record3.toString()));
    //// System.out.println(gradebook2.toString().split("\n")[0]);
    //
    //


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

    System.out.println(iterator.next() != record2);
    System.out.println(record2);
    //
    // System.out.println(iterator.hasNext());
    //
    // System.out.println(iterator.next());
    // System.out.println(iterator.hasNext());
    // System.out.println(iterator.next());
    // System.out.println(iterator.next());



    /**
     * testing remove by making a BST, making a gradebook with same structure, and using .equalsBST
     * to verify thegradebook is as expected
     */
    // StudentRecord record1 = new StudentRecord("max", "max", 75.0);
    // StudentRecord record2 = new StudentRecord("fred", "fred", 75.0);
    // StudentRecord record3 = new StudentRecord("al", "al", 75.0);
    // StudentRecord record4 = new StudentRecord("gr", "gr", 75.0);
    // StudentRecord record5 = new StudentRecord("za", "za", 75.0);
    // BSTNode<StudentRecord> max = new BSTNode<>(record1);
    // BSTNode<StudentRecord> al = new BSTNode<>(record3);
    // BSTNode<StudentRecord> gr = new BSTNode<>(record4);
    // BSTNode<StudentRecord> za = new BSTNode<>(record5);
    // max.setLeft(gr);
    // max.setRight(za);
    // gr.setLeft(al);
    // Gradebook gradebook = new Gradebook("Math", 60.0);
    // gradebook.addStudent(record1);
    // gradebook.addStudent(record2);
    // gradebook.addStudent(record3);
    // gradebook.addStudent(record4);
    // gradebook.addStudent(record5);
    // gradebook.removeStudent("fred");
    // System.out.println(gradebook.equalBST(max));

  }



}
