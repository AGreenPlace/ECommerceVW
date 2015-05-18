package controller;

/**
 * Created by Andrea on 07/05/15.
 */
public class MainController {
    private DatabaseController databaseController;

    public MainController() {
        this.databaseController = new DatabaseController();
    }

    public String consultCatalog(){
        return databaseController.describeCatalog();
    }
}
