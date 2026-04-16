package app;
import javax.swing.SwingUtilities;
import ui.viewApp;
import ui.controlApp;
import data.alertStorage;
import data.storage;

public class Main {
    public static void main(String[] args) {
        viewApp view = new viewApp();
        storage store = new storage();
        alertStorage alertStore = new alertStorage();
        new controlApp(view, store, alertStore);
        System.out.println(System.getProperty("user.dir"));
    }
}
