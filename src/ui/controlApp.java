package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import ui.viewApp;
import data.storage;
import data.alertStorage;
import domain.Alert;

public class controlApp {

    private viewApp view;
    private storage store;
    private alertStorage alertStore;
    private String enteredUsername;
    private char[] userUsername;
    public char[] getUserUsername() {
        userUsername = enteredUsername.toCharArray();
        return userUsername;
    }
    private char[] enteredPassword;
    private char[] userPassword;
    public char[] getUserPassword() {
        userPassword = enteredPassword;
        return userPassword;
    }

    public controlApp(viewApp view, storage store, alertStorage alertStore) {
        this.view = view;
        this.store = store;
    
        //Switch to Sign Up Panel
        view.getSignUpButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.showSignUpPanel();
            }
        });

        //Switch to Login Panel
        view.getReturnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.showLoginPanel();
            }
        });
        
        //Store Username and Password
        view.getNewEnterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enteredUsername = view.newUsernameInput.getText();
                enteredPassword = view.newPasswordInput.getPassword();

                boolean usernameEmpty = enteredUsername.isEmpty();
                boolean passwordEmpty = enteredPassword.length == 0;

                if (usernameEmpty || passwordEmpty) {
                    return;
                }
                try {
                    controlApp.this.store.storeLogin(enteredUsername, enteredPassword);
                } 
                catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        //Sign In to enter alert panel - Default to Senior Panel
        view.getEnterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enteredUsername = view.usernameInput.getText();
                enteredPassword = view.passwordInput.getPassword();

                try {
                    int userId = store.checkLogin(enteredUsername, enteredPassword);

                    if (userId != -1) {
                        view.showSeniorPanel();
                    }
                    
                } catch(IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        view.getToFamilyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int userId = store.checkLogin(enteredUsername, enteredPassword);
                    view.loadAlerts(alertStore.getAlerts(userId));
                    view.showFamilyPanel();
                }   catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        view.getToSeniorButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    view.showSeniorPanel();
                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        view.getSimulateSenior().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean[] statusBoolean = {false, false, false, false};
                    
                    for (int i = 0; i < 4; i++) {
                        int tempInt = (int)(Math.random()*2);
                        if (tempInt == 0) {
                            statusBoolean[i] = false;
                        }
                        else if (tempInt == 1) {
                            statusBoolean[i] = true;
                        }
                    }
                
                    view.getWaterPressureStatus().setSelected(statusBoolean[0]);
                    view.getWaterFlowStatus().setSelected(statusBoolean[1]);
                    view.getVoltageStatus().setSelected(statusBoolean[2]);
                    view.getCurrentStatus().setSelected(statusBoolean[3]);

                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        view.getSimulateFamily().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String type = "";
                    String sensor;
                    String severity = "";

                    int sensorType = (int)(Math.random()*4) + 1;
                    if (sensorType == 1) {
                        type = "Water Flow Sensor";
                    }
                    else if (sensorType == 2) {
                        type = "Water Pressure Sensor";
                    }
                    else if (sensorType == 3) {
                        type = "Voltage Sensor";
                    }
                    else if (sensorType == 4) {
                        type = "Current Sensor";
                    }

                    int sensorNum = (int)(Math.random()*3) + 1;
                    sensor = type + sensorNum;

                    int severityNum = (int)(Math.random()*3) + 1;
                    if (severityNum == 1) {
                        severity = "Low";
                    }
                    else if (severityNum == 2) {
                        severity = "Medium";
                    }
                    else if (severityNum == 3) {
                        severity = "High";
                    }
    
                    Alert alert = new Alert(sensor, severity);
                    int userId = store.checkLogin(enteredUsername, enteredPassword);

                    alertStore.storeAlert(userId, alert);
                    view.loadAlerts(alertStore.getAlerts(userId));

                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
