import theme.TUITheme;
import output.PayslipRenderer;
import timekeeping.Timekeeping;
import model.ContractualEmployee;
import model.PartTimeEmployee;
import model.Employee;
import model.RegularEmployee;
import model.ProbationaryEmployee;
import computation.DeductionsCalculator;
import computation.GrossPayCalculator;

import java.util.Scanner;

public class PayrollSystem {
    private final Scanner scanner;
    private final int width = 45; // Width of our menu box

    public PayrollSystem() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        // 1. We declare these OUTSIDE the loop so they remember the data between menu options!
        Employee myEmployee = null;
        Timekeeping timeData = null;
        String cutoffPeriod = "Not Set";

        while (running) {
            TUITheme.clearScreen();
            drawHeader("ABC Company Payroll");

            // Menu Options
            printMenuOption("1", "Manage Employees");
            printMenuOption("2", "Input Timekeeping");
            printMenuOption("3", "Generate Payslips");
            System.out.println(TUITheme.ORANGE + TUITheme.VERT + " " + TUITheme.MUTED_TEXT + TUITheme.line(width - 2) + " " + TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET);
            printMenuOption("0", "Exit System");

            drawFooter();

            // Prompt
            System.out.print(TUITheme.LIGHT_ORANGE + " ❯ Select option: " + TUITheme.RESET);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(" Loading Employee Module...");
                    System.out.println("Select Employment Type: [1] Regular [2] Part Time [3] Probationary [4] Contractual");
                    System.out.print("Choice: ");
                    String employmenttype = scanner.nextLine();

                    // We assign it to the 'myEmployee' we created at the top
                    switch (employmenttype){
                        case "1": myEmployee = new RegularEmployee(); break;
                        case "2": myEmployee = new PartTimeEmployee(); break;
                        case "3": myEmployee = new ProbationaryEmployee(); break;
                        default: myEmployee = new ContractualEmployee(); break; // Defaulted to Contractual to avoid raw Employee class
                    }

                    // Scans input
                    myEmployee.inputDetails(scanner);

                    // Display input
                    myEmployee.display();
                    pause();
                    break;

                case "2":
                    System.out.println(" Loading Timekeeping...");
                    if (myEmployee == null) {
                        System.out.println(TUITheme.ORANGE + " Error: Please create an employee in Option 1 first!" + TUITheme.RESET);
                        pause();
                        break;
                    }

                    timeData = new Timekeeping();

                    System.out.print("Enter cut-off period (e.g., Nov 1-15): ");
                    cutoffPeriod = scanner.nextLine();

                    // Prevent the Perfect Attendance Illusion
                    System.out.print("How many days are required for this cutoff? (e.g., 11): ");
                    int requiredDays = scanner.nextInt();

                    System.out.print("How many days of logs did the employee actually submit? ");
                    int loggedDays = scanner.nextInt();
                    scanner.nextLine();

                    // Automatically penalize any days they didn't show up for
                    int missingDays = requiredDays - loggedDays;
                    for (int i = 0; i < missingDays; i++) {
                        timeData.addDailyRecord("Absent", "Absent");
                    }

                    System.out.println("\n(Format HH:MM in 24hr time. Type 'Absent' for unpaid, or 'Leave' for paid leave)");
                    for (int i = 1; i <= loggedDays; i++) {
                        System.out.print("Day " + i + " Time In: ");
                        String timeIn = scanner.nextLine();
                        System.out.print("Day " + i + " Time Out: ");
                        String timeOut = scanner.nextLine();

                        timeData.addDailyRecord(timeIn, timeOut);
                    }

                    // Let the Timekeeping class crunch the minutes and hours
                    timeData.calculateHours();
                    System.out.println(TUITheme.LIGHT_ORANGE + " Timekeeping successfully saved! (" + missingDays + " unlogged days marked as absent)." + TUITheme.RESET);
                    pause();
                    break;

                case "3":
                    System.out.println(" Generating Payslips...");
                    if (myEmployee == null || timeData == null) {
                        System.out.println(TUITheme.ORANGE + "Please complete Option 1 and Option 2 first!" + TUITheme.RESET);
                        pause();
                        break;
                    }

                    // Run Gross Pay
                    GrossPayCalculator grossCalc = new GrossPayCalculator();
                    grossCalc.calculategrosspay(myEmployee, timeData);

                    double finalGross = grossCalc.getGrossPay();

                    // CRITICAL FIX: Pull overtime directly from the calculator instead of subtracting base rate!
                    double overtimePay = grossCalc.getOvertimepay();

                    // Run Deductions
                    DeductionsCalculator dedCalc = new DeductionsCalculator();
                    dedCalc.calculateAllDeductions(myEmployee, timeData, finalGross);

                    // Prepare final math for Renderer
                    double totalAbsentUndertimeHrs = (timeData.getTotalAbsences() * 8.0) + timeData.getTotalUndertime();

                    double totalDeductions = dedCalc.getUndertimeDeduction() +
                            dedCalc.getAbsenceDeduction() +
                            dedCalc.getSssContribution() +
                            dedCalc.getWithholdingTax() +
                            dedCalc.getPagibigContribution() +
                            dedCalc.getPhilhealthContribution();

                    double netPay = finalGross - totalDeductions;

                    // Render it
                    PayslipRenderer renderer = new PayslipRenderer();
                    renderer.printPayslip(
                            String.valueOf(myEmployee.getId()),
                            myEmployee.getName(),
                            myEmployee.getEmptype(),
                            String.format("₱%,.2f", myEmployee.getBaseRate()),
                            cutoffPeriod,
                            timeData.getTotalHours(),
                            totalAbsentUndertimeHrs,
                            timeData.getTotalOvertime(),
                            (finalGross - overtimePay), // Safe Base Pay Calculation for the Earnings section (Works for both Part-time & Regular!)
                            overtimePay,
                            dedCalc.getUndertimeDeduction(),
                            dedCalc.getAbsenceDeduction(),
                            dedCalc.getSssContribution(),
                            dedCalc.getWithholdingTax(),
                            dedCalc.getPagibigContribution(),
                            dedCalc.getPhilhealthContribution(),
                            0.0, // Loans
                            netPay
                    );

                    pause();
                    break;

                case "0":
                    System.out.println(TUITheme.ORANGE + " Exiting system. Goodbye!" + TUITheme.RESET);
                    running = false;
                    break;

                default:
                    System.out.println(" Invalid selection. Please try again.");
                    pause();
                    break;
            }
        }
    }

    /* UI Drawing Helpers */

    private void drawHeader(String title) {
        System.out.println(TUITheme.ORANGE + TUITheme.TOP_LEFT + TUITheme.line(width) + TUITheme.TOP_RIGHT + TUITheme.RESET);

        // Centering the title dynamically
        int padding = (width - title.length()) / 2;
        String leftPad = " ".repeat(padding);
        String rightPad = " ".repeat(width - title.length() - padding);

        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.BOLD + leftPad + title + rightPad + TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET);
        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.line(width) + TUITheme.VERT + TUITheme.RESET);
    }

    private void printMenuOption(String key, String text) {
        String paddedText = String.format(" %s%-2s%s %-39s ", TUITheme.ORANGE, "[" + key + "]", TUITheme.RESET, text);
        System.out.println(TUITheme.ORANGE + TUITheme.VERT + paddedText + TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET);
    }

    private void drawFooter() {
        System.out.println(TUITheme.ORANGE + TUITheme.BOTTOM_LEFT + TUITheme.line(width) + TUITheme.BOTTOM_RIGHT + TUITheme.RESET);
    }

    private void pause() {
        System.out.print(TUITheme.MUTED_TEXT + "\n Press Enter to continue..." + TUITheme.RESET);
        scanner.nextLine();
    }
}