package model;
import java.util.Scanner;


public class ProbationaryEmployee extends Employee {

  // Overriding the inputDetails method to handle Probationary status
  @Override

  public void inputDetails(Scanner sc) {
    
    // Automatically set the employee type to Probationary
    setEmptype("Probationary");
    
    // Collecting user input using the setters from Employee.java
    System.out.print("Enter Name: ");
    setName(sc.nextLine());

    System.out.print("Enter ID: ");
    setId(sc.nextInt());

    System.out.print("Enter Fixed Salary or Base Rate: ");
    setBaseRate(sc.nextDouble());

    sc.nextLine(); 
  }
}
