package app;
import javax.swing.SwingUtilities;
import ui.viewApp;
import ui.controlApp;
import data.alertStorage;
import data.storage;
import domain.BaselineStorage;

// Main Class
public class Main {
    public static void main(String[] args) {
        //Initialize GUI, Storage, Control
        viewApp view = new viewApp();
        storage store = new storage();
        alertStorage alertStore = new alertStorage();
        BaselineStorage baselineStore = new BaselineStorage();
        
        //Run all components
        new controlApp(view, store, alertStore, baselineStore);
        System.out.println(System.getProperty("user.dir"));
    }
}
