/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitaladmissionsystem;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author vuyolwethumpongose
 */
public class HospitalAdmissionSystem {
    
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static Ward ward = new Ward();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // clears the leftover Enter key press

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    searchPatient();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 5:
                    allocateBed();
                    break;
                case 6:
                    releaseBed();
                    break;
                case 7:
                    viewReports();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        } while (choice != 8);
    }
    
    private static void showMenu() {
      System.out.println("\n==========================================");
        System.out.println("   MediCare Hospital Admission System");
        System.out.println("==========================================");
        System.out.println(" 1. Register Patient");
        System.out.println(" 2. Search Patient");
        System.out.println(" 3. Update Patient");
        System.out.println(" 4. Delete Patient");
        System.out.println(" 5. Allocate Bed");
        System.out.println(" 6. Release Bed");
        System.out.println(" 7. View Reports");
        System.out.println(" 8. Exit");
        System.out.println("------------------------------------------");
        System.out.print(" Enter your choice: "); 
    }
    
        private static void registerPatient() {
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        for (Patient p : patients) {
            if (p.getPatientId().equals(id)) {
                System.out.println("A patient with this ID already exists.");
                return;
            }
        }

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

                int age = getValidInt("Enter Age: ");

        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        System.out.println("Enter Patient Category (1 = Inpatient, 2 = Outpatient, 3 = Emergency): ");
        int catChoice = scanner.nextInt();
        scanner.nextLine();

        PatientCategory category;
        if (catChoice == 1) {
            category = PatientCategory.INPATIENT;
        } else if (catChoice == 2) {
            category = PatientCategory.OUTPATIENT;
        } else {
            category = PatientCategory.EMERGENCY;
        }

        if (category == PatientCategory.INPATIENT) {
            System.out.print("Enter Ward Number: ");
            String wardNum = scanner.nextLine();
            System.out.print("Enter Bed Number (1-20): ");
            int bedNum = scanner.nextInt();
            scanner.nextLine();

            Inpatient inpatient = new Inpatient(id, firstName, lastName, age, gender, condition, category, wardNum, String.valueOf(bedNum));
            boolean success = ward.allocateBed(bedNum, inpatient);
            if (success) {
                patients.add(inpatient);
                System.out.println("Inpatient registered and bed allocated.");
            }
        } else {
            Patient patient = new Patient(id, firstName, lastName, age, gender, condition, category);
            patients.add(patient);
            System.out.println("Patient registered successfully.");
        }
    }
       
       // Reads a patient ID and checks all registered patients for a match, then displays their details
       private static void searchPatient() {
        System.out.print("Enter Patient ID to search: ");
        String id = scanner.nextLine();

        for (Patient p : patients) {
            if (p.getPatientId().equals(id)) {
                p.displayDetails();
                return;
            }
        }

        System.out.println("No patient found with that ID.");
    } 

       // Finds a patient by ID and lets the user update their editable details 
       private static void updatePatient() {
        System.out.print("Enter Patient ID to update: ");
        String id = scanner.nextLine();

        for (Patient p : patients) {
            if (p.getPatientId().equals(id)) {
                System.out.print("Enter new First Name (" + p.getFirstName() + "): ");
                String firstName = scanner.nextLine();
                p.setFirstName(firstName);

                System.out.print("Enter new Last Name (" + p.getLastName() + "): ");
                String lastName = scanner.nextLine();
                p.setLastName(lastName);

                System.out.print("Enter new Age (" + p.getAge() + "): ");
                int age = scanner.nextInt();
                scanner.nextLine();
                p.setAge(age);

                System.out.print("Enter new Gender (" + p.getGender() + "): ");
                String gender = scanner.nextLine();
                p.setGender(gender);

                System.out.print("Enter new Medical Condition (" + p.getMedicalCondition() + "): ");
                String condition = scanner.nextLine();
                p.setMedicalCondition(condition);

                System.out.println("Patient updated successfully.");
                return;
            }
        }

        System.out.println("No patient found with that ID.");
    } 
        
       // Finds a patient by ID and removes them from the patient list
       private static void deletePatient() {
        System.out.print("Enter Patient ID to delete: ");
        String id = scanner.nextLine();

        for (Patient p : patients) {
            if (p.getPatientId().equals(id)) {
                patients.remove(p);
                System.out.println("Patient deleted successfully.");
                return;
            }
        }

        System.out.println("No patient found with that ID.");
    } 
            
       // Repeatedly asks for a number until valid input is given, to avoid crashing on bad input
       private static int getValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
            
               private static void allocateBed() {
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        for (Patient p : patients) {
            if (p.getPatientId().equals(id)) {
                if (!(p instanceof Inpatient)) {
                    System.out.println("Only Inpatients can be allocated a bed.");
                    return;
                }
                Inpatient inpatient = (Inpatient) p;

                                int bedNum = getValidInt("Enter Bed Number (1-20): ");

                boolean success = ward.allocateBed(bedNum, inpatient);
                if (success) {
                    inpatient.setBedNumber(String.valueOf(bedNum));
                }
                return;
            }
        }

        System.out.println("No patient found with that ID.");
    }
               
               private static void releaseBed() {
                int bedNum = getValidInt("Enter Bed Number to release (1-20): ");

        ward.releaseBed(bedNum);
    }  
               
               private static void viewReports() {
                int sortChoice = getValidInt("Sort patients by: 1 = Surname, 2 = Patient ID: ");

        if (sortChoice == 1) {
            Collections.sort(patients, new Comparator<Patient>() {
                public int compare(Patient a, Patient b) {
                    return a.getLastName().compareTo(b.getLastName());
                }
            });
        } else if (sortChoice == 2) {
            Collections.sort(patients, new Comparator<Patient>() {
                public int compare(Patient a, Patient b) {
                    return a.getPatientId().compareTo(b.getPatientId());
                }
            });
        }

        System.out.println("\n--- Patient Report ---");
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
        } else {
            for (Patient p : patients) {
                p.displayDetails();
                System.out.println("-----------------------");
            }
        }
        System.out.println("Total Registered Patients: " + patients.size());

        System.out.println("\n--- Bed Report ---");
        ward.displayAvailableBeds();
        ward.displayOccupiedBeds();

        int occupied = ward.getOccupiedCount();
        int total = ward.getTotalBeds();
        double occupancyPercentage = (double) occupied / total * 100;

        System.out.println("Total Occupied Beds: " + occupied);
        System.out.println("Ward Occupancy: " + occupancyPercentage + "%");
    }      
}
    
