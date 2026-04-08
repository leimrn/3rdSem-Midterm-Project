import model.Employee;
import model.RegularEmployee;
import model.PartTimeEmployee;
import model.ProbationaryEmployee;
import model.ContractualEmployee;
import theme.TUITheme;


import java.util.Scanner;

public class PayrollSystem {
    private final Scanner scanner;
    private final int width = 45; // Width of our menu box

    public PayrollSystem() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

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
                    // TODO: Call employee management logic
                    System.out.println(" Loading Employee Module...");
                    System.out.println("Select Employment Type: [1] Regular [2] Part Time [3] Prbitionary [4] Contractual");
                    System.out.print("Choice: ");
                    String employmenttype = scanner.nextLine();
                    //Calls out Employee
                    Employee myEmployee;

                    switch (employmenttype){
                      case "1": myEmployee = new RegularEmployee();
                                break;
                      case "2": myEmployee = new PartTimeEmployee();
                                break;
                      case "3": myEmployee = new ProbationaryEmployee();
                                break;
                      default: myEmployee = new Employee();
                               break;
                    }

                    //Scans input
                    myEmployee.inputDetails(scanner);

                    //Display input
                    myEmployee.display();



                    pause();
                    break;
                case "2":
                    // TODO: Call timekeeping logic
                    System.out.println(" Loading Timekeeping...");
                    pause();
                    break;
                case "3":
                    // TODO: Call payslip rendering
                    System.out.println(" Generating Payslips...");
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
        System.out.print(TUITheme.MUTED_TEXT + " Press Enter to continue..." + TUITheme.RESET);
        scanner.nextLine();
    }
}
