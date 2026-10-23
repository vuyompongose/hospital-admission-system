# 🏥 Hospital Patient Admission System

A console-based Java application for managing patient admissions and bed allocation in a hospital ward. Built as a practical assignment for my Programming module (PROG6112) at IIE Emeris.

## ✨ Features

- **Register, search, update and delete patients** using a unique Patient ID
- **Three patient categories:** Inpatient, Outpatient and Emergency
- **Bed management** for a 20-bed ward: allocate and release beds, with checks that stop two patients being given the same bed
- **Reports:** list patients sorted by surname or ID, see available and occupied beds, and view the ward occupancy percentage
- **Input validation**, so the program asks again instead of crashing when a number is expected

## 🧠 Java concepts used

| Concept | Where it's used |
|---|---|
| Classes & objects | `Patient`, `Inpatient`, `Ward` |
| Inheritance | `Inpatient` extends `Patient` and adds ward and bed details |
| Method overriding | `Inpatient.displayDetails()` builds on the parent's version |
| Encapsulation | Private fields with getters and setters |
| Enums | `PatientCategory` (INPATIENT, OUTPATIENT, EMERGENCY) |
| 2D arrays | The ward's beds are stored in a 4 × 5 grid |
| ArrayList & sorting | Patients stored in an `ArrayList`, sorted with `Comparator` |
| Unit testing | 8 JUnit tests in `test/` |

## 📁 Project structure

```
src/
├── HospitalAdmissionSystem.java   # Main menu and program flow
├── Patient.java                   # Base patient class
├── Inpatient.java                 # Admitted patient with ward & bed
├── PatientCategory.java           # Enum of patient types
└── Ward.java                      # 20-bed ward stored as a 4x5 grid
test/
└── hospitaladmissionsystem/
    └── HospitalAdmissionSystemTest.java
```

## ▶️ How to run

1. Clone this repository
2. Open the folder in **Apache NetBeans** (File → Open Project)
3. Click **Run** (or press F6) to start the menu
4. Press **Alt + F6** (or Run → Test Project) to run the JUnit tests

## 🖥️ Sample menu

```
==========================================
   MediCare Hospital Admission System
==========================================
 1. Register Patient
 2. Search Patient
 3. Update Patient
 4. Delete Patient
 5. Allocate Bed
 6. Release Bed
 7. View Reports
 8. Exit
------------------------------------------
```

## 🛠️ Built with

Java · JUnit 4 · Apache NetBeans

---
👩🏾‍💻 Built by [Vuyolwethu Mpongose](https://www.linkedin.com/in/vuyompongose)
