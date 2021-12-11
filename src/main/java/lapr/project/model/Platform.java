package lapr.project.model;


import lapr.project.data.*;
import lapr.project.data.api.SendEmail;

public class Platform {
    private final ParkingSlotDronesDB parkingSlotDronesDB;
    private RegisterClient rclnt;
    private RegisterCourier rCour;
    private ClientProductView cpv;
    private RegisterScooter rScoot;
    private RegisterDrone rDrone;
    private RegisterUser rUser;
    private ParkingSlotsScootersDB parkingSlotsScootersDB;
    private CourierScooterDB courierScooterDB;
    private AddressDB addressDB;
    private ClientDB clientDB;
    private ClientOrderDB clientOrderDB;
    private CourierDB courierDB;
    private CreditCardDB creditCardDB;
    private DeliveryRunDB deliveryRunDB;
    private DeliveryRunLineDB deliveryRunLineDB;
    private DeliveryRunRegistry deliveryRunRegistry;
    private DroneDB droneDB;
    private InvoiceDB invoiceDB;
    private InvoiceLineDB invoiceLineDB;
    private ParkingSlotDB parkingSlotDB;
    private OrderLineDB orderLineDB;
    private PharmacyDB pharmacyDB;
    private PostalCodeDB postalCodeDB;
    private ProductDB productDB;
    private ScooterDB scooterDB;
    private UserDB userDB;
    private SendEmail sendEmail;
    private StockDB stockDB;
    private StockLineDB stockLineDB;
    private ParkingSlotScootersUsageDB parkingSlotScootersUsageDB;
    private LogIn logIn;
    private UpdatePharmacy updatePharmacy;
    private CreateOrder createOrder;
    private ScooterCharging scooterCharging;
    private CourierOrderView cov;


    public Platform() {
        this.cpv = new ClientProductView();
        this.rclnt = new RegisterClient();
        this.rCour = new RegisterCourier();
        this.rScoot = new RegisterScooter();
        this.rDrone = new RegisterDrone();
        this.rUser = new RegisterUser();
        this.addressDB = new AddressDB();
        this.courierDB = new CourierDB();
        this.clientDB = new ClientDB();
        this.clientOrderDB = new ClientOrderDB();
        this.creditCardDB = new CreditCardDB();
        this.deliveryRunDB = new DeliveryRunDB();
        this.deliveryRunLineDB = new DeliveryRunLineDB();
        this.deliveryRunRegistry = new DeliveryRunRegistry();
        this.invoiceDB = new InvoiceDB();
        this.invoiceLineDB = new InvoiceLineDB();
        this.orderLineDB = new OrderLineDB();
        this.pharmacyDB = new PharmacyDB();
        this.postalCodeDB = new PostalCodeDB();
        this.productDB = new ProductDB();
        this.scooterDB = new ScooterDB();
        this.droneDB = new DroneDB();
        this.parkingSlotDB = new ParkingSlotDB();
        this.userDB = new UserDB();
        this.courierScooterDB = new CourierScooterDB();
        this.sendEmail = new SendEmail();
        this.stockDB = new StockDB();
        this.stockLineDB = new StockLineDB();
        this.parkingSlotsScootersDB = new ParkingSlotsScootersDB();
        this.parkingSlotScootersUsageDB = new ParkingSlotScootersUsageDB();
        this.logIn = new LogIn();
        this.updatePharmacy = new UpdatePharmacy();
        this.createOrder = new CreateOrder();
        this.scooterCharging = new ScooterCharging();
        this.parkingSlotDronesDB = new ParkingSlotDronesDB();
        this.cov = new CourierOrderView();
    }

    public ParkingSlotDronesDB getParkingSlotDronesDB() {
        return parkingSlotDronesDB;
    }

    public ScooterCharging getScooterCharging() {
        return scooterCharging;
    }

    public void setScooterCharging(ScooterCharging scooterCharging) {
        this.scooterCharging = scooterCharging;
    }

    public CreateOrder getCreateOrder() {
        return createOrder;
    }

    public void setCreateOrder(CreateOrder createOrder) {
        this.createOrder = createOrder;
    }


    public void setDeliveryRunRegistry(DeliveryRunRegistry deliveryRunRegistry) {
        this.deliveryRunRegistry = deliveryRunRegistry;
    }

    public UpdatePharmacy getUpdatePharmacy() {
        return updatePharmacy;
    }

    public void setUpdatePharmacy(UpdatePharmacy updatePharmacy) {
        this.updatePharmacy = updatePharmacy;
    }

    public LogIn getLogIn() {
        return logIn;
    }

    public void setLogIn(LogIn logIn) {
        this.logIn = logIn;
    }

    public ParkingSlotsScootersDB getParkingSlotsScootersDB() {
        return parkingSlotsScootersDB;
    }

    public ParkingSlotScootersUsageDB getParkingSlotScootersUsageDB() {
        return parkingSlotScootersUsageDB;
    }

    public void setParkingSlotScootersUsageDB(ParkingSlotScootersUsageDB parkingSlotScootersUsageDB) {
        this.parkingSlotScootersUsageDB = parkingSlotScootersUsageDB;
    }
    public void setAddressDB(AddressDB addressDB) {
        this.addressDB = addressDB;
    }
    public void setClientDB(ClientDB clientDB) {
        this.clientDB = clientDB;
    }

    public void setClientOrderDB(ClientOrderDB clientOrderDB) {
        this.clientOrderDB = clientOrderDB;
    }

    public void setCourierDB(CourierDB courierDB) {
        this.courierDB = courierDB;
    }
    
    public void setCourierScooterDB(CourierScooterDB courierScooterDB){
        this.courierScooterDB = courierScooterDB;
    }
    public void setCreditCardDB(CreditCardDB creditCardDB) {
        this.creditCardDB = creditCardDB;
    }

    public void setDeliveryRunDB(DeliveryRunDB deliveryRunDB) {
        this.deliveryRunDB = deliveryRunDB;
    }

    public void setInvoiceDB(InvoiceDB invoiceDB) {
        this.invoiceDB = invoiceDB;
    }

    public void setOrderLineDB(OrderLineDB orderLineDB) {
        this.orderLineDB = orderLineDB;
    }

    public void setPharmacyDB(PharmacyDB pharmacyDB) {
        this.pharmacyDB = pharmacyDB;
    }

    public void setPostalCodeDB(PostalCodeDB postalCodeDB) {
        this.postalCodeDB = postalCodeDB;
    }

    public void setProductDB(ProductDB productDB) {
        this.productDB = productDB;
    }

    public void setRclnt(RegisterClient rclnt) {
        this.rclnt = rclnt;
    }

    public void setrCour(RegisterCourier rCour) {
        this.rCour = rCour;
    }


    public void setScooterDB(ScooterDB scooterDB) {
        this.scooterDB = scooterDB;
    }

    public void setUserDB(UserDB userDB) {
        this.userDB = userDB;
    }

    public RegisterClient getRegisterClient() {
        return rclnt;
    }

    public RegisterCourier getRegisterCourier() {
        return rCour;
    }

    public AddressDB getAddressDB() {
        return addressDB;
    }

    public ClientDB getClientDB() {
        return clientDB;
    }

    public ClientOrderDB getClientOrderDB() {
        return clientOrderDB;
    }

    public CourierDB getCourierDB() {
        return courierDB;
    }
    
    public CourierScooterDB getCourierScooterDB(){
       return courierScooterDB;
    }

    public CreditCardDB getCreditCardDB() {
        return creditCardDB;
    }

    public DeliveryRunDB getDeliveryRunDB() {
        return deliveryRunDB;
    }

    public InvoiceDB getInvoiceDB() {
        return invoiceDB;
    }

    public OrderLineDB getOrderLineDB() {
        return orderLineDB;
    }

    public PharmacyDB getPharmacyDB() {
        return pharmacyDB;
    }

    public PostalCodeDB getPostalCodeDB() {
        return postalCodeDB;
    }

    public ProductDB getProductDB() {
        return productDB;
    }

    public ScooterDB getScooterDB() {
        return scooterDB;
    }

    public UserDB getUserDB() {
        return userDB;
    }

    public SendEmail getSendEmail() {
        return sendEmail;
    }

    public void setParkingSlotDB(ParkingSlotDB parkingSlotDB) {
        this.parkingSlotDB = parkingSlotDB;
    }

    public ClientProductView getCpv() {
        return cpv;
    }

    public ParkingSlotDB getParkingSlotDB() {
        return parkingSlotDB;
    }

    public void setSendEmail(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    public RegisterScooter getRegisterScooter() {
        return rScoot;
    }

    public void setRegisterScooter(RegisterScooter rScoot) {
        this.rScoot = rScoot;
    }

    public RegisterDrone getRegisterDrone() {
        return rDrone;
    }

    public void setRegisterDrone(RegisterDrone rDrone) {
        this.rDrone = rDrone;
    }

    public RegisterUser getRegisterUser() {
        return rUser;
    }

    public void setRegisterUser(RegisterUser rUser) {
        this.rUser = rUser;
    }

    public StockDB getStockDB() {
        return stockDB;
        }
    public DroneDB getDroneDB() {
        return droneDB;
    }

    public void setStockDB(StockDB stockDB) {
        this.stockDB = stockDB;
        }
    public void setDroneDB(DroneDB droneDB) {
        this.droneDB = droneDB;
    }

    public StockLineDB getStockLineDB() {
        return stockLineDB;
        }

    public void setStockLineDB(StockLineDB stockLineDB) {
        this.stockLineDB = stockLineDB;
        }

    public InvoiceLineDB getInvoiceLineDB() {
        return invoiceLineDB;
        }
    public DeliveryRunRegistry getDeliveryRunRegistry() {
            return deliveryRunRegistry;
    }

    public void setInvoiceLineDB(InvoiceLineDB invoiceLineDB) {
        this.invoiceLineDB = invoiceLineDB;
    }

    public CourierOrderView getCov() {
        return cov;
    }

}
