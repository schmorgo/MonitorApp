package data;
import java.io.*;
import java.util.*;
import ui.viewApp;
import ui.controlApp;
import domain.Alert;

public class alertStorage {
    private String line;

    // Constructor
    public alertStorage() {
    }

    // Store alert in a file - alertHistory.txt
    public void storeAlert(int userId, Alert alert) throws IOException {
        File file = new File("alertHistory.txt");
        // Create file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
        }

        //Write the info in format: userId|time|sensor|severity
        try(FileWriter writer = new FileWriter(file, true)) {
            writer.write(userId + "|" + alert.getTime() + "|" + alert.getSensor() + "|" + alert.getSeverity() + "\n");
        }
    }

    //Get all alerts for the given user as an ArrayList String[]
    public ArrayList<String[]> getAlerts(int userId) throws IOException {
        ArrayList<String[]> alertInfo = new ArrayList<String[]>();
        File file = new File("alertHistory.txt");
        System.out.println(file.getAbsolutePath());

        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            //Read alertHistory.txt line by line to find all lines with the userId
            while ((line = br.readLine()) != null) {

                //Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                //Split the lines into parts from the format userId|time|sensor|severity to get them separately.
                String[] parts = line.split("\\|", 4);

                //If the line doesn't have all 4 parts, skip
                if (parts.length != 4) {
                    continue;
                }

                try {
                    //Check if the userId matches currently logged in user
                    int storedUserId = Integer.parseInt(parts[0]);

                    //If it does match, add the time, sensor, and severity to the ArrayList alertInfo as one String[]
                    if (storedUserId == userId) {
                        String time = parts[1];
                        String sensor = parts[2];
                        String severity = parts[3];
                        
                        alertInfo.add(new String[]{time, sensor, severity});
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            
        }
        //Return the ArrayList
        return alertInfo;
    }

}
