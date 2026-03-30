
# 🚀 Project Alpha: Java-Based Payroll System**National University Dasmariñas** | [cite_start]**CCOBJPGL: Week 3 Lab** [cite: 1, 5]

## 📋 Project Summary
[cite_start]An automated Terminal User Interface (TUI) engineered to compute and present employee compensation based on specific labor categories[cite: 11, 20]. [cite_start]This application emphasizes core **Object-Oriented Programming (OOP)** standards, specifically focusing on strict encapsulation, class hierarchies, and modular logic[cite: 8, 88, 109].

## 👥 The Development Team
| Name | Designation | GitHub |

# Project Alpha: Java Payroll System
**National University Dasmariñas** | **CCOBJPGL: Week 3 Lab**

## Project Summary
[cite_start]An automated Terminal User Interface (TUI) designed to compute and display employee compensation based on specific labor categories[cite: 8, 11]. [cite_start]The application implements **Object-Oriented Programming (OOP)** standards, focusing on encapsulation, class hierarchies, and modular logic[cite: 8, 88, 109].

## Development Team
| Name | Role | GitHub |
>>>>>>> fbca972 (Try)
| :--- | :--- | :--- |
| **Paquito** | Project Manager | @Alexmanacan |
| **Leila** | Senior Developer | @leimrn |
| **Emman** | Logic Architect | |
| **Kiel** | UI/UX Developer | |

<<<<<<< HEAD
## 🛠️ Technical Specifications
=======
## Technical Specifications
>>>>>>> fbca972 (Try)
| Layer | Technology |
| :--- | :--- |
| **Language** | [cite_start]Java (JDK 17+) [cite: 23] |
| **Environment** | Neovim (LazyVim) on Arch Linux |
<<<<<<< HEAD
| **Interface** | [cite_start]CLI-based TUI with ANSI styling [cite: 84, 99] |
| **Tracking** | Jira & GitHub |
| **Data Storage** | [cite_start]ArrayList & Custom Objects (No File I/O) [cite: 85, 86] |

---## 📂 System Architecture```text
payroll-system/
├── src/│   ├── Main.java                 # Entry point; flow control only 
│   ├── PayrollSystem.java        # Core TUI loop and controller [cite: 86]
│   ├── TUITheme.java             # Terminal styling and box-drawing [cite: 99]│   ├── model/                    # Data Access Layer 
│   │   ├── Employee.java         # Abstract base for all staff members
│   │   ├── RegularEmployee.java  # Fixed monthly + leave benefits [cite: 25, 92]
│   │   ├── ProbationaryEmployee.java [cite: 30, 93]
│   │   ├── ContractualEmployee.java [cite: 30, 94]
│   │   └── PartTimeEmployee.java # Hourly-based, no-work-no-pay [cite: 30, 95]
│   ├── timekeeping/
│   │   └── Timekeeping.java      # Shift tracking and hour calculation [cite: 19, 63]
│   ├── computation/
│   │   ├── GrossPayCalculator.java # Primary earnings arithmetic [cite: 96]
│   │   └── DeductionsCalculator.java # Statutory and custom deductions [cite: 22, 35]
│   └── output/
│       └── PayslipRenderer.java  # Formatted console reporting [cite: 99, 124]
└── README.md
📊 Compensation Matrix
Staff CategoryPay BasisBenefitsFrequency
Regular 
+2
Monthly Rate✅ YesBi-monthly (1st-15th / 16th-30th) 

Probationary 
+1
Monthly Rate✅ YesBi-monthly (1st-15th / 16th-30th) 

Contractual 
+1
Monthly Rate❌ NoBi-monthly (1st-15th / 16th-30th) 

Part-Time 
+1
Hourly Rate❌ NoWeekly
🧮 Calculation Logic 
Earnings (Gross) 
+2
Base Pay: Derived from fixed monthly salary or total units worked.

Overtime: Additional pay for hours exceeding the standard shift.
+1
Reductions (Deductions) 
+3

Statutory: BIR Withholding Tax, SSS, PhilHealth, and Pag-IBIG.
+1

Company: Internal loans, late penalties, and unexcused absences.
+2
⏰ Standard Operations 
Operational Days: Monday to Friday.
Standard Shift: 08:00 AM to 05:00 PM (8 active hours, 1 hour unpaid break).
Metrics: Overtime starts after 17:00; Undertime applies for late starts or early exits.
📜 Compliance Checklist 
[ ] OOP Standards: 3+ user classes with private attributes and public accessors.
[ ] Logic Isolation: Main class restricted to flow control with no business logic.
[ ] Encapsulation: Proper use of constructors, getters, and setters.
[ ] Robustness: Graceful handling of invalid user inputs.
🚀 Execution Guide
Compilation
Bash

javac -d out src/**/*.java
Launch
Bash

java -cp out Main

Note: ANSI colors require a terminal emulator with escape code support (e.g., Kitty, Alacritty, or Windows Terminal).
📚 Required Research 
[ ] Current BIR Withholding Tax Gradients 
[ ] SSS Monthly Contribution Table 
[ ] PhilHealth and Pag-IBIG Premium Rates 
[ ] Holiday Pay Differentials 
=======
| **Interface** | [cite_start]Console-based TUI with ANSI styling [cite: 84, 99] |
| **Tracking** | Jira & GitHub |
| **Data Storage** | [cite_start]ArrayList & Custom Objects (No File I/O) [cite: 85, 86] |

---

## System Architecture
```text
payroll-system/
├── src/
[cite_start]│   ├── Main.java                 # Entry point; flow control only 
[cite_start]│   ├── PayrollSystem.java        # TUI controller & main menu loop 
[cite_start]│   ├── TUITheme.java             # ANSI color constants & box-drawing [cite: 99]
[cite_start]│   ├── model/                    # Data layer [cite: 89, 109]
│   │   ├── Employee.java         # Abstract base class 
[cite_start]│   │   ├── RegularEmployee.java  # Monthly rate + leave benefits [cite: 25, 92]
[cite_start]│   │   ├── ProbationaryEmployee.java [cite: 30, 93]
[cite_start]│   │   ├── ContractualEmployee.java [cite: 30, 94]
[cite_start]│   │   └── PartTimeEmployee.java # Hourly rate, no work-no pay [cite: 30, 95]
│   ├── timekeeping/
[cite_start]│   │   └── Timekeeping.java      # Hours, OT, and Undertime logic [cite: 19, 63]
│   ├── computation/
[cite_start]│   │   ├── GrossPayCalculator.java # Core salary arithmetic [cite: 96, 112]
[cite_start]│   │   └── DeductionsCalculator.java # Tax, SSS, PhilHealth, Pag-IBIG [cite: 22, 35]
│   └── output/
[cite_start]│       └── PayslipRenderer.java  # Formatted console reporting [cite: 99, 124]
└── README.md

