package data;
import java.io.*;
import java.util.*;
import ui.viewApp;
import ui.controlApp;
import domain.Alert;

public class alertStorage {

    private String line;

    public alertStorage() {
    }

    public void storeAlert(int userId, Alert alert) throws IOException {
        try(FileWriter writer = new FileWriter("alertHistory.txt", true)) {
            writer.write(userId + "|" + alert.getTime() + "|" + alert.getSensor() + "|" + alert.getSeverity() + "\n");
        }
    }

    public ArrayList<String[]> getAlerts(int userId) throws IOException {
        ArrayList<String[]> alertInfo = new ArrayList<String[]>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("alertHistory.txt"))) {
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", 4);

                if (parts.length != 4) {
                    continue;
                }

                try {
                    int storedUserId = Integer.parseInt(parts[0]);

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
        return alertInfo;
    }


}
