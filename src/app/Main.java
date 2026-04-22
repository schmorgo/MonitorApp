package app;
import javax.swing.SwingUtilities;
import ui.ViewApp;
import ui.ControlApp;
import data.AlertStorage;
import data.Storage;
import domain.BaselineStorage;

// Main Class
public class Main {
    public static void main(String[] args) {
        //Initialize GUI, Storage, Control
        ViewApp view = new ViewApp();
        Storage store = new Storage();
        AlertStorage alertStore = new AlertStorage();
        BaselineStorage baselineStore = new BaselineStorage();
        
        //Run all components
        new ControlApp(view, store, alertStore, baselineStore);
        System.out.println(System.getProperty("user.dir"));
    }
}
