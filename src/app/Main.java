package app;
import javax.swing.SwingUtilities;
import ui.viewApp;
import ui.controlApp;
import data.storage;

public class Main {
    public static void main(String[] args) {
        viewApp view = new viewApp();
        storage store = new storage();
        new controlApp(view, store);
    }
}
