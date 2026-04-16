package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ui.viewApp;

public class controlApp {

    private viewApp view;

    public controlApp(viewApp view) {
        this.view = view;
        
        //Switch to Sign Up Panel
        view.getSignUpButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.showSignUpPanel();
            }
        });
    }
}
