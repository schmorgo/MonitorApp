package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import ui.viewApp;
import data.storage;

public class controlApp {

    private viewApp view;
    private storage store;
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

    public controlApp(viewApp view, storage store) {
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
                    if (store.checkLogin(enteredUsername, enteredPassword)) {
                        view.showSeniorPanel();
                    }
                    
                } catch(IOException exception) {
                    exception.printStackTrace();
                }

            }
        });
    }
}
