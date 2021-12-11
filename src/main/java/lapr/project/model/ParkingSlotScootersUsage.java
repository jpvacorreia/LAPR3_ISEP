package lapr.project.model;

import java.util.Date;

public class ParkingSlotScootersUsage {

    private int idParkingSlot;
    private int idScooter;
    private Date dateIN;
    private Date dateOUT;
    public ParkingSlotScootersUsage(int idParkingSlot, int idScooter, Date dateIN, Date dateOUT){
        this.idScooter = idScooter;
        this.idParkingSlot = idParkingSlot;
        this.dateIN = dateIN;
        this.dateOUT = dateOUT;
    }

    public void setIdScooter(int idScooter) {
        this.idScooter = idScooter;
    }

    public void setDateIN(Date dateIN) {
        this.dateIN = dateIN;
    }

    public void setDateOUT(Date dateOUT) {
        this.dateOUT = dateOUT;
    }

    public void setIdParkingSlot(int idParkingSlot) {
        this.idParkingSlot = idParkingSlot;
    }

    public int getIdParkingSlot() {
        return idParkingSlot;
    }

    public Date getDateIN() {
        return dateIN;
    }

    public Date getDateOUT() {
        return dateOUT;
    }

    public int getIdScooter() {
        return idScooter;
    }

}
