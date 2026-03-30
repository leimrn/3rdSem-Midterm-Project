Project Alpha: Java Payroll System

National University Dasmariñas | CCOBJPGL: Week 3 Lab 
Project Summary

An automated Terminal User Interface (TUI) designed to compute and display employee compensation based on specific labor categories. The application implements Object-Oriented Programming (OOP) standards, focusing on encapsulation, class hierarchies, and modular logic.
Development Team
Name	Role	GitHub
Paquito	Project Manager	@Alexmanacan
Leila	Senior Developer	@leimrn
Emman	Logic Architect	
Kiel	UI/UX Developer	
Technical Specifications
Layer	Technology
Language	

Java (JDK 17+)
Environment	Neovim (LazyVim) on Arch Linux
Interface	

Console-based TUI with ANSI styling
Tracking	Jira & GitHub
Data Storage	

ArrayList & Custom Objects (No File I/O)
System Architecture
Plaintext

payroll-system/
├── src/
│   ├── Main.java                 # Entry point; flow control only [cite: 90, 111]
│   ├── PayrollSystem.java        # TUI controller & main menu loop [cite: 86]
│   ├── TUITheme.java             # ANSI color constants & box-drawing [cite: 99]
│   ├── model/                    # Data layer [cite: 89, 108]
│   │   ├── Employee.java         # Abstract base class 
│   │   ├── RegularEmployee.java  # Monthly rate + leave benefits [cite: 25, 92]
│   │   ├── ProbationaryEmployee.java [cite: 30, 93]
│   │   ├── ContractualEmployee.java [cite: 30, 94]
│   │   └── PartTimeEmployee.java # Hourly rate, no work-no pay [cite: 30, 95]
│   ├── timekeeping/
│   │   └── Timekeeping.java      # Hours, OT, and Undertime logic [cite: 19, 21, 63]
│   ├── computation/
│   │   ├── GrossPayCalculator.java # Core salary arithmetic [cite: 96, 112]
│   │   └── DeductionsCalculator.java # Tax, SSS, PhilHealth, Pag-IBIG [cite: 22, 31, 35]
│   └── output/
│       └── PayslipRenderer.java  # Formatted console reporting [cite: 99, 124]
└── README.md

Compensation Matrix
Staff Category	Pay Basis	Benefits	Frequency
Regular	Monthly Rate	Yes	

Bi-monthly (1st-15th / 16th-30th)
Probationary	Monthly Rate	Yes	

Bi-monthly (1st-15th / 16th-30th)
Contractual	Monthly Rate	No	

Bi-monthly (1st-15th / 16th-30th)
Part-Time	Hourly Rate	No	

Weekly
Calculation Logic
Gross Pay

    Base Pay: Derived from fixed monthly salary or total units worked.

    Overtime: Additional pay for hours exceeding the standard shift.

Deductions

    Statutory: BIR Withholding Tax, SSS, PhilHealth, and Pag-IBIG.

    Company: Internal loans, late penalties, and absences.

Standard Operations

    Work Days: Monday to Friday.

    Standard Shift: 08:00 to 17:00 (8 hours, 1 hour break).

    Metrics: Overtime starts after 17:00; Undertime applies for late starts or early exits.

Compliance Checklist

    [ ] At least 3 user-defined classes.

    [ ] Attributes declared as private (Encapsulation).

    [ ] Constructors, getters, and setters implemented.

    [ ] Main class restricted to flow control only.

    [ ] Objects or ArrayList used for data storage.

Execution Guide
Compilation
Bash

javac -d out src/**/*.java

Launch
Bash

java -cp out Main

Required Research

    [ ] BIR Withholding Tax Table.

    [ ] SSS Contribution Table.

    [ ] PhilHealth and Pag-IBIG Premium Rates.

    [ ] Holiday Pay Differentials.
    [ ] Current BIR Withholding Tax Gradients 

    [ ] SSS Monthly Contribution Table 

    [ ] PhilHealth and Pag-IBIG Premium Rates 

    [ ] Holiday Pay Differentials
