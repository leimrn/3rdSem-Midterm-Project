package model;
import java.util.Scanner;


public class PartTimeEmployee extends Employee {
  @Override
  public void inputDetails(Scanner sc) {
    
    setEmptype("Part Time"); // Setting the private field in parent via setter
    System.out.print("Enter Name: ");
    setName(sc.nextLine());
    System.out.print("Enter ID: ");
    setId(sc.nextInt());
    System.out.print("Enter Work Hours: ");
    setWorkhour(sc.nextInt());
    sc.nextLine(); 
  }
}
