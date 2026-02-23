package data;
import java.io.*;
import ui.viewApp;
import ui.controlApp;

public class storage {

    private String line;

    public storage() {
    }

    private int getNewUserId() throws IOException {
        int maxId = -1;

        try (BufferedReader br = new BufferedReader(new FileReader("login.txt"))) {
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] half = line.split("\\|", 3);
                
                if (half.length != 3) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(half[0]);
                    if (id > maxId) {
                        maxId = id;
                    }
                } catch(NumberFormatException exception) {
                    exception.printStackTrace();
                }
            }
            return maxId + 1;
        }


    }

    public void storeLogin(String username, char[] password) throws IOException {
        int newId = getNewUserId();

        try(FileWriter writer = new FileWriter("login.txt", true)) {
            writer.write(newId + "|" + username + "|" + new String(password) + "\n");
        }
    }

    public boolean checkLogin(String inputUsername, char[] inputPassword) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("login.txt"))) {

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] half = line.split("\\|", 3);
                
                if (half.length != 3) {
                    continue;
                }

                String storedUsername = half[1];
                String storedPassword = half[2];

                if (storedUsername.equals(inputUsername) && storedPassword.equals(new String(inputPassword))) {
                    return true;
                }
            }
        }
        return false;
    }
}
