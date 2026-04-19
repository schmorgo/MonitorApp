package data;
import java.io.*;
import ui.viewApp;
import ui.controlApp;

public class storage {

    private String line;
    private alertStorage alertStore;

    public storage() {
    }

    //Get new userId when user signs up
    private int getNewUserId() throws IOException {
        //Assume that the login.txt doesn't have any users, therefore the maxId is -1
        int maxId = -1;
        File file = new File("login.txt");

        //Create file if it doesn't exist and return 0 as the new userId since no file = no users
        if (!file.exists()) {
            file.createNewFile();
            return 0;
        }

        //Read login.txt to find current max userId
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {

                //Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                //Split the lines into parts from the format: userId|username|password to get them separately.
                String[] half = line.split("\\|", 3);
                
                //If the line doesn't have all 3 parts, skip
                if (half.length != 3) {
                    continue;
                }

                try {
                    //Go through the lines to find max current userId
                    int id = Integer.parseInt(half[0]);
                    if (id > maxId) {
                        maxId = id;
                    }
                } catch(NumberFormatException exception) {
                    exception.printStackTrace();
                }
            }
            //Return the new registered user's Id which is the maxId + 1
            return maxId + 1;
        }
    }

    //Store new user's login into login.txt
    public void storeLogin(String username, char[] password) throws IOException {
        //Get new userId for the new registered user
        int newId = getNewUserId();

        //Put the user's info into login.txt with format: userId|username|password
        try(FileWriter writer = new FileWriter("login.txt", true)) {
            writer.write(newId + "|" + username + "|" + new String(password) + "\n");
        }
    }

    //Check if input username and password match any user stored in login.txt
    public int checkLogin(String inputUsername, char[] inputPassword) throws IOException {
        File file = new File("login.txt");

        if (!file.exists()) {
            file.createNewFile();
            return -1;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            //Read login.txt line by line
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                //Split the lines into parts from the format: userId|username|password to get them separately.
                String[] half = line.split("\\|", 3);
                
                //If the line doesn't have all 3 parts, skip
                if (half.length != 3) {
                    continue;
                }

                //Check if the username inputted into the textfield and the password inputted into the password field 
                //match the username and password stored in login.txt for a given line.
                String storedUsername = half[1];
                String storedPassword = half[2];

                //If they match, return the userId of the logged in user to use it find the alerts and baseline for the user
                if (storedUsername.equals(inputUsername) && storedPassword.equals(new String(inputPassword))) {
                    return Integer.parseInt(half[0]);
                }
            }
        }
        return -1;
    }
}
