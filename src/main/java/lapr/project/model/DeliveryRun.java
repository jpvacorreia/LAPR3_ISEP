package lapr.project.model;

import lapr.project.data.api.DeliveryRoute;
import lapr.project.utils.Constants;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeliveryRun {
    private int iDDeliverer;
    private List<Order> toDeliver;
    private Date date;
    private Pharmacy startingPoint;
    private DeliveryRoute deliveryRoute;


    public DeliveryRun(int iDCourier, List<Order> toDeliver, Pharmacy startingPoint) {
        this.iDDeliverer = iDCourier;
        this.toDeliver = toDeliver;
        this.date = Date.from(Instant.now());
        this.startingPoint = startingPoint;
    }

    public int getiDDeliverer() {
        return iDDeliverer;
    }

    public List<Order> getToDeliver() {
        return new ArrayList<>(toDeliver);
    }

    public Date getDate() {
        return date;
    }

    public Pharmacy getStartingPoint() {
        return startingPoint;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setiDDeliverer(int iDDeliverer) {
        this.iDDeliverer = iDDeliverer;
    }

    public void setStartingPoint(Pharmacy startingPoint) {
        this.startingPoint = startingPoint;
    }

    public void setToDeliver(ArrayList<Order> toDeliver) {
        this.toDeliver = toDeliver;
    }


    public DeliveryRoute getDeliveryRoute() {
        return deliveryRoute;
    }

    public void setDeliveryRoute(DeliveryRoute deliveryRoute) {
        this.deliveryRoute = deliveryRoute;
    }


    /**
     * Updates all orders contained in the delivery run to Delivered state
     *
     */
    public void updateOrderState(){
        for (Order o : toDeliver){
            o.setState(Constants.DELIVERED);
        }
    }

    @Override
    public String toString() {
        return "DeliveryRun{" +
                "iDCourier=" + iDDeliverer +
                ", toDeliver=" + toDeliver +
                ", date=" + date +
                ", startingPoint=" + startingPoint +
                '}';
    }
}
