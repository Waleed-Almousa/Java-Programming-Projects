import java.text.ParseException;
import java.time.LocalDate;

public class MyTester {



  public static void main(String[] args) {


 

    Wardrobe testW = new Wardrobe(10);
    Clothing toAdd = new Clothing("black T", "Nike", 1, LocalDate.of(1999, 9, 8));
    Clothing toAdd2 = new Clothing("white T", "add", 1, LocalDate.of(1, 4, 8));
    

    testW.addClothing(toAdd);
    testW.addClothing(toAdd2);
    
    testW.removeAllClothingWornBefore(2000, 10, 12);
    

    System.out.println(testW+ "wwww");
    Clothing clothes;
    try {
      clothes=testW.parseClothing("white T,Nike,10/20/2004,0");
      System.out.println(clothes);

    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    String date = "02/20/2000";
    String[] parts = date.split("/");
    System.out.println(Integer.parseInt(parts[2]));
    
    LocalDate date2=LocalDate.of(2005,  10, 2);
    System.out.print(date2);


  }

}
