
import java.util.ArrayList;


public class MyTester {

  public static void main(String[] args) {

    // ArrayList<Item> items = new ArrayList<>();
    // items.add(new Item("A", 4, 2));
    // items.add(new Item("B", 6, 3));
    // items.add(new Item("C", 7, 4));
    // items.add(new Item("D", 4, 5));
    // items.add(new Item("E", 4, 5));
    // items.add(new Item("F", 5, 4));
    // items.add(new Item("G", 2, 6));
    //
    // // Create a suitcase with width 10 and height 10
    // Suitcase initialSuitcase = new Suitcase(10, 10, items);
    //
    // // Call rushedPacking method to pack items
    // Suitcase packedSuitcase = Packing.rushedPacking(initialSuitcase);
    //
    // // Print the resulting packed suitcase
    // System.out.println("Packed Suitcase:");
    // System.out.println(packedSuitcase);
    //
    //
    // ArrayList<Suitcase> Permutations =Packing.generatePermutations(initialSuitcase,
    // initialSuitcase.getUnpackedItems().size());
    // System.out.println(Permutations.size());
    //
    // ArrayList<Item> items2 = new ArrayList<>();
    //
    // Suitcase s2 = new Suitcase(10, 10, items2);
    // System.out.print(s2.getUnpackedItems());

    ArrayList<Item> items = new ArrayList<>();

    items.add(new Item("A", 4, 2));
    items.add(new Item("B", 6, 3));
    items.add(new Item("C", 7, 4));
    items.add(new Item("D", 4, 5));
    Suitcase suitcase1 = new Suitcase(50, 50, items);

    Suitcase results = Packing.greedyPacking(suitcase1);
    System.out.print(results.getPackedItems());



    //
    // System.out.println("initial s1:\n" + s1);
    // Suitcase s2 = s1.packItem(phone);
    // System.out.println("added s1:\n" + s1);
    // System.out.println("s2:\n" + s2);
    // Suitcase s3 = s2.packItem(shirt);
    // System.out.println("s3:\n" + s3);
    //



  }

}
