package lapr.project.model;

import lapr.project.controller.ApplicationPOT;


import java.util.ArrayList;

public class ClientProductView {


    /**
     * Gets all products listed in the DataBase.
     *
     */
    public ArrayList<Product> getProducts() {
        return ApplicationPOT.getInstance().getPlatform().getProductDB().getAllProducts();
    }
}
