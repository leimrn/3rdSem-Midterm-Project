package output;

import theme.TUITheme;

public class PayslipRenderer {

    private final int width = 68; // Width updated to match your screenshot

    public void printPayslip(
            String empId, String empName, String empType, String basicSalaryStr, String cutoff,
            double hrsWorked, double hrsAbsentUndertime, double hrsOvertime,
            double basePay, double overtimePay,
            double undertimeDed, double absentDed, double sss, double wTax, double pagibig, double philhealth, double loans,
            double netPay
    ) {
        TUITheme.clearScreen();

        // --- HEADER ---
        System.out.println(TUITheme.ORANGE + TUITheme.TOP_LEFT + TUITheme.line(width) + TUITheme.TOP_RIGHT + TUITheme.RESET);
        printCentered("OFFICIAL PAYSLIP");
        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.line(width) + TUITheme.VERT + TUITheme.RESET);

        // --- EMPLOYEE DETAILS ---
        printRow("Employee ID:", empId);
        printRow("Employee Name:", empName);
        printRow("Employee Type:", empType);
        printRow("Basic Salary:", basicSalaryStr);
        printRow("Cut-off Period:", cutoff);
        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.line(width) + TUITheme.VERT + TUITheme.RESET);

        // --- TIMEKEEPING SUMMARY ---
        printCentered("TIMEKEEPING SUMMARY");
        printRow("Total Hours Worked:", String.format("%.2f hrs", hrsWorked));
        printRow("Absent/Undertime:", String.format("%.2f hrs", hrsAbsentUndertime));
        printRow("Overtime:", String.format("%.2f hrs", hrsOvertime));
        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.line(width) + TUITheme.VERT + TUITheme.RESET);

        // --- EARNINGS & DEDUCTIONS ---
        printCentered("FINANCIAL DETAILS");

        // Earnings
        System.out.printf("%s%s %s%-36s: %s%8.2f%s %s%n",
                TUITheme.ORANGE, TUITheme.VERT, TUITheme.RESET, "Basic Salary", TUITheme.LIGHT_ORANGE, basePay, TUITheme.RESET, " ".repeat(19) + TUITheme.ORANGE + TUITheme.VERT);
        System.out.printf("%s%s %s%-36s: %s%8.2f%s %s%n",
                TUITheme.ORANGE, TUITheme.VERT, TUITheme.RESET, "Additional (Overtime)", TUITheme.LIGHT_ORANGE, overtimePay, TUITheme.RESET, " ".repeat(19) + TUITheme.ORANGE + TUITheme.VERT);

        // Deductions
        System.out.printf("%s%s %s%-66s %s%n", TUITheme.ORANGE, TUITheme.VERT, TUITheme.MUTED_TEXT, "Deductions:", TUITheme.ORANGE + TUITheme.VERT);
        printDeductionRow("Undertime/Late", undertimeDed);
        printDeductionRow("Absences", absentDed);
        printDeductionRow("SSS", sss);
        printDeductionRow("W/Tax", wTax);
        printDeductionRow("Pag-IBIG", pagibig);
        printDeductionRow("PhilHealth", philhealth);
        printDeductionRow("Loans", loans);

        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.line(width) + TUITheme.VERT + TUITheme.RESET);

        // --- NET PAY ---
        System.out.printf("%s%s %s%-36s: %s%8.2f%s %s%n",
                TUITheme.ORANGE, TUITheme.VERT, TUITheme.BOLD, "NET PAY", TUITheme.LIGHT_ORANGE, netPay, TUITheme.RESET, " ".repeat(19) + TUITheme.ORANGE + TUITheme.VERT);

        // --- FOOTER ---
        System.out.println(TUITheme.ORANGE + TUITheme.BOTTOM_LEFT + TUITheme.line(width) + TUITheme.BOTTOM_RIGHT + TUITheme.RESET);
    }

    // --- Helper Methods ---

    private void printRow(String label, String value) {
        String formatted = String.format(" %-20s %-45s", label, value);
        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET + formatted + " " + TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET);
    }

    private void printDeductionRow(String label, double amount) {
        String formatted = String.format("    %-24s: %8.2f", label, amount);
        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET + formatted + " ".repeat(30) + TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET);
    }

    private void printCentered(String text) {
        int padding = (width - text.length()) / 2;
        String leftPad = " ".repeat(padding);
        String rightPad = " ".repeat(width - text.length() - padding);
        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.BOLD + leftPad + text + rightPad + TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET);
    }
}