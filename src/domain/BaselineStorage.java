package domain;
import domain.Baseline;
import java.io.*;

public class BaselineStorage {
    
    //Make baseline with random values for each sensor for each user
    //This is based off of the normal ranges for Ontario
    public Baseline makeBaseline(int userId) {
        double waterFlow = Math.random()*(3-0.5) + 0.5;
        double waterPressure = Math.random()*(75-45) + 45;
        double voltage = Math.random()*(123-117) + 117;
        double current = Math.random()*(20-5) + 5;

        return new Baseline(userId, waterFlow, waterPressure, voltage, current);
    }

    //Store the baselines in baseline.txt
    public void storeBaseline(Baseline baseline) throws IOException {
        File file = new File("baseline.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        //Write the info in format: userId|waterFlowBaseline|waterPressureBaseline|voltageBaseline|currentBaseline
        try(FileWriter writer = new FileWriter(file, true)) {
            writer.write(baseline.getUserId() + "|" + baseline.getWaterFlowBaseline() + "|" +
            baseline.getWaterPressureBaseline() + "|" + baseline.getVoltageBaseline() + "|" + 
            baseline.getCurrentBaseline() + "\n"
        );
        }
    }

    //Get the baseline for a given usedId from baseline.txt
    public Baseline getBaseline(int userId) throws IOException {
        File file = new File("baseline.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        //Read baseline.txt line by line to find the line with the userId
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {

            //Skip empty lines
            if (line.trim().isEmpty()) {
                continue;
            }

            //Split the lines into parts from the format userId|waterFlowBaseline|waterPressureBaseline|voltageBaseline|currentBaseline to get them separately.
            String[] parts = line.split("\\|"); 

            //Check if the stored userId matches the given userId
            int storedUserId = Integer.parseInt(parts[0]);
            //If it does, return the stored baselines
            if (storedUserId == userId) {
                double waterFlow = Double.parseDouble(parts[1]);
                double waterPressure = Double.parseDouble(parts[2]);
                double voltage = Double.parseDouble(parts[3]);
                double current = Double.parseDouble(parts[4]);

                br.close();
                return new Baseline(userId, waterFlow, waterPressure, voltage, current);
            }
        }
        //If the userId isn't found, return null
        br.close();
        return null;
    }

}
