package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.api.FileConnectionWithC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ScooterCharging {

    public ScooterCharging(){

    }

    /**
     * Creates a CourierScooter object regarding the courier's act to place the scooter to charge
     * So we know when he used it and when he placed it to charge.
     *  - Simulates act of placing scooter in the parkingSlot to charge -
     * @param courier - Courier that places the scooter to charge
     * @param scooter - Scooter placed to charge
     * @return Date
     */
    public Date placeScooterToCharge(Courier courier, Scooter scooter) {
        Date dateOut = new Date(System.currentTimeMillis());
        Date dateIn = ApplicationPOT.getInstance().getPlatform().getDeliveryRunDB().getLastCourierDeliveryRunDate(courier.getId());
        CourierScooter cs = new CourierScooter(dateIn,dateOut,scooter.getId(),courier.getId());
        ApplicationPOT.getInstance().getPlatform().getCourierScooterDB().addCourierScooter(cs);
        return dateIn;
    }

    /**
     * Gets Scooter's charging time
     * Checks for vacant parkingSlots, logs it's usage, changes scooter's state of charge
     * Connects with C through files and sends notification to the courier's email
     * @param courier - Courier that will recieve the notification
     * @param scooter - Scooter placed to charge
     * @param date - When it was placed to charge
     * @return boolean - True when no errors/exceptions appear
     *                 - False when the scooter isn't locked
     * @throws IOException - Since files are handled
     */
    public boolean getChargingTimeAndLockingStatus(Courier courier,Scooter scooter, Date date) throws IOException {
        ParkingSlot parkingSlot = getVacantChargeableScooterParkingSlot(scooter.getPharmacyId());
        if (scooter.isLocked()) {
            ArrayList<ParkingSlot> arrayParksInUse = getParkSlotsScootersInUse(parkingSlot.getPharmacyId(),date);
            arrayParksInUse.add(parkingSlot);
            int size = arrayParksInUse.size();
            System.out.printf("\n%d scooters charging at the same time.",size);
            ParkingSlotsScooters parkingSlotsScooters = ApplicationPOT.getInstance().getPlatform().getParkingSlotsScootersDB().getParkingSlotsScooters(parkingSlot.getId());
            parkingSlotsScooters.setStateOfUse(2);
            ApplicationPOT.getInstance().getPlatform().getParkingSlotsScootersDB().updateParkingSlotsScooters(parkingSlotsScooters);
            ParkingSlotScootersUsage usage = new ParkingSlotScootersUsage(parkingSlot.getId(),scooter.getId(),date,new Date());
            saveParkingSlotScootersUsage(usage);
            for (ParkingSlot ps : arrayParksInUse){
                ParkingSlotScootersUsage lastUsage = ApplicationPOT.getInstance().getPlatform().getParkingSlotScootersUsageDB().getParkingSlotScootersLastUsage(ps.getId());
                Date dateIN =lastUsage.getDateIN();
                Date dateOUT =lastUsage.getDateOUT();
                Scooter sc = RegisterScooter.getScooterByID(lastUsage.getIdScooter());
                sc.setStateOfCharge(calculateChargedBattery(sc.getStateOfCharge(),dateOUT.getTime(),dateIN.getTime(),date.getTime()));
                ApplicationPOT.getInstance().getPlatform().getRegisterScooter().updateScooter(sc);
                Pharmacy ph = UpdatePharmacy.getPharmacyByID(ps.getPharmacyId());
                double voltageInput = ph.getVoltageInput();
                double currentInput = ph.getCurrentInput();
                double output = (voltageInput*currentInput) / size;
                double chTime = connectWithFiles(date,sc,output);
                ArrayList<Integer> actualTime = getActualTime(chTime);
                dateOUT.setTime(date.getTime() + (normalTimeToDateTime(actualTime)));
                usage.setDateOUT(dateOUT);
                updateParkingSlotScootersUsage(usage);
                String body = "Your scooter, id " +scooter.getId()+", is well locked and will take "+actualTime.get(0)
                        +"hours, "+actualTime.get(1) +"minutes and "+actualTime.get(2)+"seconds to charge.";
                ApplicationPOT.getInstance().getPlatform().getSendEmail().sendEmail(courier.getEmail(),"Charging Time and Locking Status",body);
            }
            return true;
        } else {
            String body = "Your scooter, id " +scooter.getId()+", isn't locked, therefore it won't charge";
            ApplicationPOT.getInstance().getPlatform().getSendEmail().sendEmail(courier.getEmail(),"Charging Time and Locking Status",body);
        }
        return false;
    }

    /**
     * Calculates state of the batery charged during its time on the parkingSlot
     *
     * @param stateOfCharge - Old State of Charge
     * @param time1 - date in
     * @param time2 - provisorial date out
     * @param time3 - date when charging time was altered
     * @return state charged so far
     */
    public double calculateChargedBattery(double stateOfCharge, long time1, long time2, long time3){
        double stateMissing = 100 - stateOfCharge;
        long difference = time1 - time2;
        long diff = time3 - time2;
        double stateChargedSoFar = (diff * stateMissing) / difference;
        stateChargedSoFar += stateOfCharge;
        return stateChargedSoFar;
    }

    /**
     * Coverts regular time (hh-mm-ssss) to java.util.date time
     * @param actualTime - array containg hours, minutes and seconds
     * @return time in java.util.date type
     */
    public long normalTimeToDateTime(ArrayList<Integer> actualTime){
        return ((actualTime.get(0)*3600) + (actualTime.get(1)*60) + actualTime.get(2)) * 1000;
    }

    /**
     * Method connecting to classes to handle files
     *
     * @param date - date
     * @param sc - scooter
     * @param output - parkingSlot output
     * @return time calculated in hours
     * @throws IOException - since files are handled
     */
    public double connectWithFiles(Date date, Scooter sc, double output) throws IOException {
        FileConnectionWithC.setFile(date,sc.getMaxBatteryCapacity(),sc.getStateOfCharge(),output);
        return FileConnectionWithC.getDataFromFile(date);
    }

    /**
     * Turns hours into normal time
     *
     * @param chTime - time in hours
     * @return arrayList containing hours, minutes and seconds
     */
    public ArrayList<Integer> getActualTime(double chTime){
        int chTimeInt = (int) chTime;
        if (chTimeInt > chTime){
            chTimeInt =- 1;
        }
        int minutesInt = 0;
        double minutes = chTime - chTimeInt;
        double seconds = 0;
        if (minutes > 0){
            minutes = minutes*60;
            minutesInt = (int) minutes;
            if (minutesInt > minutes){
                minutesInt =-1;
            }
            seconds = minutes - minutesInt;
            if (seconds > 0) {
                seconds = seconds * 60;
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(chTimeInt);
        arrayList.add(minutesInt);
        arrayList.add((int)seconds);
        return arrayList;
    }

    /**
     * Retrieves from the database all ParkingSlots currently in use
     *
     * @param idPharmacy - Pharmacy id number from where the ParkingSlots are needed
     * @param date - When they are needed
     * @return arrayList containing the ParkingSlots
     */
    public ArrayList<ParkingSlot> getParkSlotsScootersInUse(int idPharmacy,Date date) {
        return ApplicationPOT.getInstance().getPlatform().getParkingSlotDB().getParkingSlotsScootersWithCCInUse(idPharmacy,date);
    }

    /**
     * Saves a ParkingSlotScootersUsage into the DataBase
     *
     * @param parkingSlotScootersUsage - parkingSlotScootersUsage
     * @return true when no exceptions are lifted
     **/
    public boolean saveParkingSlotScootersUsage(ParkingSlotScootersUsage parkingSlotScootersUsage) {
        return save(parkingSlotScootersUsage);
    }

    /**
     * Deletes a ParkingSlotScootersUsage from the DataBase
     *
     * @param parkingSlotScootersUsage - parkingSlotScootersUsage
     * @return true when no exceptions are lifted
     */
    public boolean removeParkingSlotScootersUsage(ParkingSlotScootersUsage parkingSlotScootersUsage) {
        return delete(parkingSlotScootersUsage);
    }

    /**
     * Updates a ParkingSlotScootersUsage into the DataBase
     *
     * @param parkingSlotScootersUsage - parkingSlotScootersUsage
     * @return true when no exceptions are lifted
     */
    public boolean updateParkingSlotScootersUsage(ParkingSlotScootersUsage parkingSlotScootersUsage) {
        return update(parkingSlotScootersUsage);
    }

    /**
     * Retrieves a ParkingSlotScootersUsage from the DataBase
     *
     * @return true when no exceptions are lifted
     */
    public static ParkingSlotScootersUsage getParkingSlotScootersUsage(int parkingSlotID,int scooterID, Date dateIN) {
        return ApplicationPOT.getInstance().getPlatform().getParkingSlotScootersUsageDB().getParkingSlotScootersUsage(parkingSlotID,scooterID,dateIN);
    }

    /**
     * Saves a ParkingSlotScootersUsage into the DataBase
     *
     * @param parkingSlotScootersUsage - parkingSlotScootersUsage
     * @return true when no exceptions are lifted
     **/
    private boolean addParkingSlotScootersUsage(ParkingSlotScootersUsage parkingSlotScootersUsage) {
        return ApplicationPOT.getInstance().getPlatform().getParkingSlotScootersUsageDB().addParkingSlotScootersUsage(parkingSlotScootersUsage);
    }

    /**
     * Saves a ParkingSlotScootersUsage into the DataBase
     *
     * @param parkingSlotScootersUsage - parkingSlotScootersUsage
     * @return true when no exceptions are lifted
     **/
    private boolean save(ParkingSlotScootersUsage parkingSlotScootersUsage) {
        try {
            getParkingSlotScootersUsage(parkingSlotScootersUsage.getIdParkingSlot(),parkingSlotScootersUsage.getIdScooter(), parkingSlotScootersUsage.getDateIN());
        } catch (IllegalArgumentException ex) {
            return addParkingSlotScootersUsage(parkingSlotScootersUsage);
        }
        return false;
    }


    /**
     * Deletes a ParkingSlotScootersUsage from the DataBase
     *
     * @param parkingSlotScootersUsage - parkingSlotScootersUsage
     * @return true when no exceptions are lifted
     */
    private boolean delete(ParkingSlotScootersUsage parkingSlotScootersUsage) {
        try {
            getParkingSlotScootersUsage(parkingSlotScootersUsage.getIdParkingSlot(),parkingSlotScootersUsage.getIdScooter(), parkingSlotScootersUsage.getDateIN());
            return ApplicationPOT.getInstance().getPlatform().getParkingSlotScootersUsageDB().removeParkingSlotScootersUsage(parkingSlotScootersUsage.getIdParkingSlot(),parkingSlotScootersUsage.getIdScooter(), parkingSlotScootersUsage.getDateIN());
        } catch (IllegalArgumentException ex) {
            System.out.println("Scooter does not exist.");
        }
        return false;
    }

    /**
     * Updates a ParkingSlotScootersUsage into the DataBase
     *
     * @param parkingSlotScootersUsage - parkingSlotScootersUsage
     * @return true when no exceptions are lifted
     */
    private boolean update(ParkingSlotScootersUsage parkingSlotScootersUsage) {
        if (delete(parkingSlotScootersUsage)) {            //if ParkingSlotScootersUsage exists it is deleted
            return addParkingSlotScootersUsage(parkingSlotScootersUsage);   //and added
        } else return addParkingSlotScootersUsage(parkingSlotScootersUsage);       //if ParkingSlotScootersUsage does not exist, it is added anyway.
    }

    /**
     * Retrieves from the database one ParkingSlot for scooters currently vacant
     *
     * @param pharmacyId - Pharmacy id number from where the ParkingSlot is needed
     * @return the parkingSlot
     */
    public ParkingSlot getVacantChargeableScooterParkingSlot(int pharmacyId){
        return ApplicationPOT.getInstance().getPlatform().getParkingSlotDB().getVacantChargeableScooterParkingSlot(pharmacyId);
    }


}
