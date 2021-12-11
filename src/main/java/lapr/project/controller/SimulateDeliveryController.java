package lapr.project.controller;

import lapr.project.model.DeliveryRun;
import lapr.project.model.DeliveryRunRegistry;
import lapr.project.model.SimulateDelivery;
import lapr.project.model.Order;

import java.util.List;

public class SimulateDeliveryController {
    SimulateDelivery sm;
    DeliveryRunRegistry drr;
    List<Order> toDeliver;
    int IDchoiceOfDeliveryMethod;
    int choiceOfDelivery;
    int choiceOfSimulationCriteria;
    DeliveryRun simulated;

    public SimulateDeliveryController(){
        this.sm = new SimulateDelivery();
        this.drr = new DeliveryRunRegistry();
    }

    public List<Order> getAllReadyOrder(){
        List<Order> orders = sm.getAllReadyOrders();
        return orders;
    }

    public void generateDeliveryList(List<Order> chosen){
        this.toDeliver = chosen;
    }

    public void setChoiceOfDeliveryMethod(int choice){
        this.choiceOfDelivery = choice;
        this.simulated = drr.createDeliveryRun(choice, toDeliver);
        this.IDchoiceOfDeliveryMethod = simulated.getiDDeliverer();
    }

    public DeliveryRun setChoiceOfSimulationCriteria(int choice) throws RuntimeException{
        if(choiceOfDelivery == 1){
            this.simulated = drr.generateDeliveryRoute(choice, simulated);
        }else{
            this.simulated = drr.generateDeliveryRouteDrone(choice, simulated);
        }
        return this.simulated;
    }
}
