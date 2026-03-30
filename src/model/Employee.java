package model;

import java.util.Scanner;

public class Employee {
  private String name;
  private int id;
  private String emptype;
  private int workhour;

  //Getters allows to read the private data
  public String getName(){return name; }
  public int getId() { return id; }
  public String getEmptype() {return emptype; }
  public int getWorkhour() { return workhour; }

  //setters allows to set the private data
  public void setName(String name) { this.name = name; }
  public void setId(int id) { this.id = id; }
  public void setEmptype(String emptype) { this.emptype = emptype; }
  public void setWorkhour(int workhour) { this.workhour = workhour; }

  public void inputDetails(Scanner sc) {
    //subclasses inputted information
  }
  public void display() {
    System.out.println("\n--- Employee Record ---");
    System.out.println("ID: " + id);
    System.out.println("Name: " + name);
    System.out.println("Type: " + emptype);
    System.out.println("Hours: " + workhour);
  }
}
