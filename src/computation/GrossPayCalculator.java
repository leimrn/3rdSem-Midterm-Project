package computation;

import model.Employee;
import timekeeping.Timekeeping;

public class GrossPayCalculator {
    // Variable requested: grossPay
    // Variables in private to hold gross pay components
    private double grossPay;
    private double overtimepay;
    private double hourrate;

    // Constructor: ensures a clean slate by initializing to 0.0
    public GrossPayCalculator(){
      this.grossPay = 0.0;
      this.overtimepay = 0.0;
      this.hourrate = 0.0;
    }

    public void calculategrosspay(Employee emp, Timekeeping timekeeping){
      //Uses the standard 22-day work month and 8-hour work day logic
      this.hourrate = (emp.getBaseRate() / 22.0) / 8.0;

      // Calculate OT using the total overtime hours from Timekeeping
      this.overtimepay = timekeeping.getTotalOvertime() * this.hourrate * 1.25;

      // Final total of base salary plus any extra earned overtime
      this.grossPay = emp.getBaseRate() + this.overtimepay;
    }

    // Getters to allow other classes to access the calculated money
    public double getGrossPay(){
      return grossPay;
    }

    public double getOvertimepay(){
      return overtimepay;
    }

    public double getHourrate(){
      return hourrate;
    }
}
