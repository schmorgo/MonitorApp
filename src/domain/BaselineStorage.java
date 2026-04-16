package domain;
import domain.Baseline;
import java.io.*;

public class BaselineStorage {
    
    public Baseline makeBaseline(int userId) {
        double waterFlow = Math.random()*(3-0.5) + 0.5;
        double waterPressure = Math.random()*(75-45) + 45;
        double voltage = Math.random()*(123-117) + 117;
        double current = Math.random()*(20-5) + 5;

        return new Baseline(userId, waterFlow, waterPressure, voltage, current);
    }

    public void storeBaseline(Baseline baseline) throws IOException {
        File file = new File("baseline.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        try(FileWriter writer = new FileWriter(file, true)) {
            writer.write(baseline.getUserId() + "|" + baseline.getWaterFlowBaseline() + "|" +
            baseline.getWaterPressureBaseline() + "|" + baseline.getVoltageBaseline() + "|" + 
            baseline.getCurrentBaseline() + "\n"
        );
        }
    }

    public Baseline getBaseline(int userId) throws IOException {
        File file = new File("baseline.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|"); 

            int storedUserId = Integer.parseInt(parts[0]);
            if (storedUserId == userId) {
                double waterFlow = Double.parseDouble(parts[1]);
                double waterPressure = Double.parseDouble(parts[2]);
                double voltage = Double.parseDouble(parts[3]);
                double current = Double.parseDouble(parts[4]);

                br.close();
                return new Baseline(userId, waterFlow, waterPressure, voltage, current);
            }
        }
        br.close();
        return null;
    }

}
