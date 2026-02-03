package app;
import javax.swing.SwingUtilities;
import ui.viewApp;
import ui.controlApp;

public class Main {
    public static void main(String[] args) {
        viewApp view = new viewApp();
        new controlApp(view);
    }
}
