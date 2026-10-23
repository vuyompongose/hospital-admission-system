package hospitaladmissionsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vuyolwethumpongose
 */

// Manages the hospital ward's 20 beds, arranged in a 4x5 grid
public class Ward {
    
    private static final int ROWS = 4;
    private static final int COLS = 5;
    private Inpatient[][] beds;

    public Ward() {
        beds = new Inpatient[ROWS][COLS];
    }
    
    // Converts a bed number (e.g. 7) into its row position in the grid
        private int getRow(int bedNumber) {
        return (bedNumber - 1) / COLS;
    }

    // Converts a bed number (e.g. 7) into its column position in the grid    
    private int getCol(int bedNumber) {
        return (bedNumber - 1) % COLS;
    }
    
    // Assigns a patient to the given bed, if it's free
        public boolean allocateBed(int bedNumber, Inpatient patient) {
        int row = getRow(bedNumber);
        int col = getCol(bedNumber);

        if (beds[row][col] != null) {
            System.out.println("Bed B" + bedNumber + " is already occupied.");
            return false;
        }

        beds[row][col] = patient;
        System.out.println("Bed B" + bedNumber + " allocated successfully.");
        return true;
    }
        
        // Frees up the given bed, if it's currently occupied
            public boolean releaseBed(int bedNumber) {
        int row = getRow(bedNumber);
        int col = getCol(bedNumber);

        if (beds[row][col] == null) {
            System.out.println("Bed B" + bedNumber + " is already empty.");
            return false;
        }

        beds[row][col] = null;
        System.out.println("Bed B" + bedNumber + " released successfully.");
        return true;
    }
        public void displayWardLayout() {
        System.out.println("Ward Layout:");
        int bedNumber = 1;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (beds[r][c] == null) {
                    System.out.print("B" + String.format("%02d", bedNumber) + "[Empty] ");
                } else {
                    System.out.print("B" + String.format("%02d", bedNumber) + "[Occupied] ");
                }
                bedNumber++;
            }
            System.out.println();
        }
    }

    public void displayAvailableBeds() {
        System.out.println("Available Beds:");
        int bedNumber = 1;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (beds[r][c] == null) {
                    System.out.println("B" + String.format("%02d", bedNumber));
                }
                bedNumber++;
            }
        }
    }

    public void displayOccupiedBeds() {
        System.out.println("Occupied Beds:");
        int bedNumber = 1;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (beds[r][c] != null) {
                    System.out.println("B" + String.format("%02d", bedNumber) + " - " + beds[r][c].getFirstName() + " " + beds[r][c].getLastName());
                }
                bedNumber++;
            }
        }
    }
            
        public int getOccupiedCount() {
        int count = 0;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (beds[r][c] != null) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getTotalBeds() {
        return ROWS * COLS;
    }
}
