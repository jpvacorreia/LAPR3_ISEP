package lapr.project.controller;

import lapr.project.data.ClientOrderDB;
import lapr.project.model.*;

import java.util.ArrayList;

public class ClientProductViewController {

    ClientProductView cpv;
    ClientOrderDB cdb;

    public ClientProductViewController(){
        cpv = ApplicationPOT.getInstance().getPlatform().getCpv();
        cdb = ApplicationPOT.getInstance().getPlatform().getClientOrderDB();
    }

    public ArrayList<Product> getProducts(){
        return cpv.getProducts();
    }

    public ArrayList<Order> getClientOrders(int id){
        return cdb.getClientOrders(id);
    }
}
