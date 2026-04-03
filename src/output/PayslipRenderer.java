package output;

import theme.TUITheme;

public class PayslipRenderer {

    private final int width = 68;

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
        printFinancialLine("Basic Salary", String.format("%8.2f", basePay), TUITheme.LIGHT_ORANGE, false);
        printFinancialLine("Additional (Overtime)", String.format("%8.2f", overtimePay), TUITheme.LIGHT_ORANGE, false);

        // Deductions Header (Passing an empty string removes the colon)
        printFinancialLine("Deductions:", "", TUITheme.RESET, false);

        // Deductions
        printFinancialLine("Undertime/Late", String.format("%8.2f", undertimeDed), TUITheme.RESET, true);
        printFinancialLine("Absences", String.format("%8.2f", absentDed), TUITheme.RESET, true);
        printFinancialLine("SSS", String.format("%8.2f", sss), TUITheme.RESET, true);
        printFinancialLine("W/Tax", String.format("%8.2f", wTax), TUITheme.RESET, true);
        printFinancialLine("Pag-IBIG", String.format("%8.2f", pagibig), TUITheme.RESET, true);
        printFinancialLine("PhilHealth", String.format("%8.2f", philhealth), TUITheme.RESET, true);
        printFinancialLine("Loans", String.format("%8.2f", loans), TUITheme.RESET, true);

        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.line(width) + TUITheme.VERT + TUITheme.RESET);

        // --- NET PAY ---
        printFinancialLine("NET PAY", String.format("%8.2f", netPay), TUITheme.LIGHT_ORANGE, false);

        // --- FOOTER ---
        System.out.println(TUITheme.ORANGE + TUITheme.BOTTOM_LEFT + TUITheme.line(width) + TUITheme.BOTTOM_RIGHT + TUITheme.RESET);
    }

    // --- Bulletproof Helper Methods ---

    private void printRow(String label, String value) {
        String cleanLeft = " " + label;
        int leftWidth = 22; // Locks the value to a specific starting column

        if (cleanLeft.length() < leftWidth) {
            cleanLeft += " ".repeat(leftWidth - cleanLeft.length());
        }

        int visibleLen = leftWidth + value.length();
        String rightPad = " ".repeat(Math.max(0, width - visibleLen));

        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET
                + cleanLeft + value + rightPad
                + TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET);
    }

    private void printFinancialLine(String label, String amountStr, String amountColor, boolean isDeduction) {
        String leftPad = isDeduction ? "    " : " ";
        String cleanLeft = leftPad + label;

        int leftWidth = 40; // Pushes all financial colons to the exact same spot
        if (cleanLeft.length() < leftWidth) {
            cleanLeft += " ".repeat(leftWidth - cleanLeft.length());
        }

        // Apply special text formatting without breaking the length math
        String styledLeft;
        if (label.equals("NET PAY")) {
            styledLeft = TUITheme.BOLD + cleanLeft + TUITheme.RESET;
        } else if (label.equals("Deductions:")) {
            styledLeft = TUITheme.MUTED_TEXT + cleanLeft + TUITheme.RESET;
        } else {
            styledLeft = cleanLeft;
        }

        String colon = amountStr.isEmpty() ? "  " : ": ";

        // Calculate exact spacing needed before injecting ANSI colors
        int visibleLen = leftWidth + colon.length() + amountStr.length();
        String rightPad = " ".repeat(Math.max(0, width - visibleLen));

        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET
                + styledLeft + colon + amountColor + amountStr + TUITheme.RESET + rightPad
                + TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET);
    }

    private void printCentered(String text) {
        int padding = (width - text.length()) / 2;
        String leftPad = " ".repeat(padding);
        String rightPad = " ".repeat(width - text.length() - padding);

        System.out.println(TUITheme.ORANGE + TUITheme.VERT + TUITheme.BOLD
                + leftPad + text + rightPad
                + TUITheme.ORANGE + TUITheme.VERT + TUITheme.RESET);
    }
}