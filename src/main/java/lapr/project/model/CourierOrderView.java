package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.utils.Constants;

import java.util.ArrayList;

public class CourierOrderView {

    public CourierOrderView(){

    }

    /**
     * Gets all orders listed in the DataBase with a "processing" state.
     *
     */
    public ArrayList<Order> getAllOrders(){
        return ApplicationPOT.getInstance().getPlatform().getClientOrderDB().getAllOrdersWithState(Constants.PROCESSING);
    }
}
