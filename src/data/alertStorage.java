package data;
import java.io.*;
import ui.viewApp;
import ui.controlApp;
import domain.Alert;

public class alertStorage {

    public alertStorage() {
    }

    private void storeAlert(int userId, Alert alert) throws IOException {
        try(FileWriter writer = new FileWriter("alertHistory.txt", true)) {
            writer.write(userId + "|" + alert.getTime() + "|" + alert.getSensor() + "|" + alert.getSeverity() + "\n");
        }
    }
}
