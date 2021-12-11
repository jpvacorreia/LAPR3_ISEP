package lapr.project.model;

import java.util.Date;
import lapr.project.controller.ApplicationPOT;

public class CourierScooter {

    private final Date dateIn;
    private final Date dateOut;
    private final int idScooter;
    private final int idCourier;

    public CourierScooter(Date dateIn, Date dateOut, int idScooter,int idCourier){
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.idCourier = idCourier;
        this.idScooter = idScooter;
    }

    public int getIdScooter() {
        return idScooter;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public int getIdCourier() {
        return idCourier;
    }
    
    /**
     * Retrieves a Courier from the DataBase with the id passed as parameter.
     *
     * @param scooterId     Courier Scooter id.
     */
    public static CourierScooter getCourierScooterByScooterId(int scooterId){
        return ApplicationPOT.getInstance().getPlatform().getCourierScooterDB().getCourierScooterByScooterId(scooterId);
    } 
}
