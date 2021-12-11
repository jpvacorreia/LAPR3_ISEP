package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.data.ClientDB;
import lapr.project.data.DataHandler;
import lapr.project.data.api.DeliveryRoute;
import lapr.project.data.api.SendEmail;
import lapr.project.model.*;
import lapr.project.utils.*;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;


/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    private static Scanner input = new Scanner(System.in);

    private static CreateDeliveryRunDroneController cdrrc;

    private static ClientLogInController clic;

    private static ClientProductViewController cpvc;

    private static CourierOrderViewController covc;

    private static CreateDeliveryRunController cdrc;

    private static DeliveryRunRegistry drr;

    private static RegisterCourierController rcc;

    private static RegisterCourier rcour;

    private static RegisterClient rclnt;

    private static RegisterUnregisteredClientController rucc;

    private static ScooterCharging scc;

    private static UpdatePharmacyController upc;

    private static RegisterScooterController rscootc;

    private static RegisterScooter rs;

    private static RegisterDroneController rdc;

    private static SimulateDeliveryController sdc;

    private static RegisterDrone rdrone;

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }


    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        //load database properties
        try {
            Properties properties =
                    new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }


        //Initial Database Setup
        DataHandler dh = new DataHandler();
        dh.scriptRunner("target/test-classes/demo_jdbc.sql");

        initializeControllers();

        //Fazer
        /*scc = new ScooterCharging();
        rs = new RegisterScooter();
        Scooter sc = ApplicationPOT.getInstance().getPlatform().getScooterDB().getScooterByID(1);
        sc.setStateOfCharge(60);
        sc.setLocked(true);
        rs.updateScooter(sc);
        System.out.println(sc.getStateOfCharge());
        Courier cour = ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourierById(1);
        Date date = scc.placeScooterToCharge(cour, sc);
        System.out.println(scc.getChargingTimeAndLockingStatus(cour,sc, date));
        sc = ApplicationPOT.getInstance().getPlatform().getScooterDB().getScooterByID(2);
        sc.setStateOfCharge(30);
        sc.setLocked(true);
        rs.updateScooter(sc);
        cour = ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourierById(2);
        date = scc.placeScooterToCharge(cour , sc);
        System.out.println(scc.getChargingTimeAndLockingStatus(cour,sc,date));*/

        /*CalculatorExample calculatorExample = new CalculatorExample();
        int value = calculatorExample.sum(3, 5);

        if (LOGGER.isLoggable(Level.INFO))
            LOGGER.log(Level.INFO, String.valueOf(value));*/

        System.out.println("Hello! Welcome to our platform BEAN 3.1!");
        int option = -1;
        while (option != 0) {
            System.out.println("Do you want to register on our platform or log in?");
            System.out.println("(1) - Register          (2) - Log in            (0) - Exit");
            option = input.nextInt();
            switch (option) {
                case 1:
                    //if (option == 1) {
                    System.out.println("Please insert the client's data as follows:");
                    System.out.println("name,email,address,postalCode,coordinateX,coordinateY,nif,creditCardNumber,validity_year,validity_month,ccv,elevation");
                    input.nextLine();
                    String line = null;
                    Client client = null;
                    String password = PasswordGeneration.generatePassword();
                    String[] data = null;
                    try {
                        line = input.nextLine();
                        data = line.split(",");
                        client = rucc.newClient(data[0], data[1], password, data[2], data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5]), Integer.parseInt(data[6]),
                                (int) Long.parseLong(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9]), Integer.parseInt(data[10]), Double.parseDouble(data[11]));
                        if (client != null) {
                            System.out.println(client.getName() + " " + client.getCredCard());
                            //ver esta parte do cast (int) do long com o pessoal
                            System.out.println("\nClient successfully registered!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nUser not registered, please check input data!");

                    } catch (InputMismatchException ime) {
                        System.out.println("Incorrect option!");
                    }
                    waitEnter();
                    break;
                case 2:
                    System.out.println("User email:");
                    String emailParameter = input.next();
                    System.out.println();
                    System.out.println("Password:");
                    String userPassword = input.next();
                    int userType = clic.logIn(emailParameter, userPassword);
                    if (userType > 0) {
                        System.out.printf("\nLogged In as %s.\n", userType);
                        switch (userType) {
                            case Constants.CLIENT:
                                int id = clic.getClientIdByEmail(emailParameter);
                                int optionClientChoice = -1;
                                while (optionClientChoice != 0) {
                                    System.out.println("What do you want to do?");
                                    System.out.println("(0) - Exit");
                                    System.out.println("(1) - Make an Order ");
                                    System.out.println("(2) - View placed Orders");
                                    //input.next();
                                    optionClientChoice = input.nextInt();
                                    switch (optionClientChoice) {
                                        case 0:
                                            System.out.println("Exited successfully");
                                            break;
                                        case 1:
                                            ArrayList<Product> arrayList = cpvc.getProducts();
                                            if (arrayList == null) {
                                                System.out.println("No products yet");
                                                break;
                                            }
                                            String name = StringUtils.center("NAME", 30);
                                            System.out.println("(ID)->" + name + " - PRICE");
                                            for (Product ph : arrayList) {
                                                name = StringUtils.center(ph.getName(), 30);
                                                System.out.printf("(%d) ->%s - %s\n", ph.getId(), name, ph.getPrice());
                                            }
                                            CreateOrderController coc = new CreateOrderController();
                                            ArrayList<OrderLine> cart = new ArrayList<>();
                                            List<OrderLine> aux = new ArrayList<>();
                                            ArrayList<Product> productsChosen = new ArrayList<>();
                                            Order order = coc.createOrder(id, cart);
                                            int optionProduct = -1;

                                            while (optionProduct != 0) {
                                                int j = -1;
                                                boolean bool = false;
                                                System.out.println("Select product to order -> 0 to exit");
                                                optionProduct = input.nextInt();
                                                if (optionProduct > 0) {
                                                    for (Product prod : arrayList) {
                                                        if (optionProduct == prod.getId()) {
                                                            System.out.println("Insert Quantity");
                                                            int quantity = input.nextInt();
                                                            if (quantity > 0) {
                                                                if (productsChosen.size() > 0) {
                                                                    for (OrderLine ols : cart) {
                                                                        if (ols.getIdProduct() == prod.getId()) {
                                                                            ols.addQuantity(quantity);
                                                                            bool = true;
                                                                        }
                                                                    }
                                                                    if (!bool) {
                                                                        productsChosen.add(prod);
                                                                        OrderLine ol = new OrderLine(prod.getId(), quantity);
                                                                        cart.add(ol);
                                                                    }
                                                                } else {
                                                                    productsChosen.add(prod);
                                                                    OrderLine ol = new OrderLine(prod.getId(), quantity);
                                                                    cart.add(ol);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (productsChosen.size() > 0) {
                                                for (Product prod : productsChosen) {
                                                    for (OrderLine ol : cart) {
                                                        if (ol.getIdProduct() == prod.getId())
                                                            System.out.printf("(%d) -> %s - %s - %s\n", prod.getId(), prod.getName(), prod.getPrice() * ol.getQuantity(), ol.getQuantity());
                                                    }
                                                }
                                                boolean confirmationBool = false;
                                                while (!confirmationBool) {
                                                    System.out.println("Select an option: ");
                                                    System.out.println("(1) - Confirm Order");
                                                    System.out.println("(2) - Remove Product from Order");
                                                    System.out.println("(3) - Cancel Order");

                                                    int confirmation = input.nextInt();
                                                    switch (confirmation) {
                                                        case 1:
                                                            System.out.println("Confirm order? (1)-Yes / (2)-No");
                                                            if (input.nextInt() == 1) {
                                                                boolean useCredits = false;
                                                                client = ApplicationPOT.getInstance().getPlatform().getClientDB().getClientByID(id);
                                                                if (client.getCredits() >= Constants.MINIMUM_CREDITS_FOR_FREE_SHIPPING) {
                                                                    System.out.println("Do you want to use your credits? (1)-Yes / (2)-No");
                                                                    if (input.nextInt() == 1)
                                                                        useCredits = true;
                                                                }
                                                                coc.confirmedOrder(order, cart);
                                                                order.makePayment(useCredits);
                                                                confirmationBool = true;
                                                            }
                                                            waitEnter();
                                                            break;
                                                        case 2:
                                                            System.out.println("Select which product you want to remove: ");
                                                            for (Product prod : productsChosen) {
                                                                for (OrderLine ol : cart) {
                                                                    if (ol.getIdProduct() == prod.getId())
                                                                        System.out.printf("(%d) -> %s - %s - %s\n", prod.getId(), prod.getName(), prod.getPrice(), ol.getQuantity());
                                                                }
                                                            }
                                                            int prodToRemove = input.nextInt();
                                                            System.out.println("Are you sure you want to remove the product with the ID:" + prodToRemove + "? (1)-Yes / (2)-No");
                                                            if (input.nextInt() == 1)
                                                                order.removeProduct(prodToRemove);
                                                            waitEnter();
                                                            break;
                                                        case 3:
                                                            System.out.println("Order canceled");
                                                            confirmationBool = true;
                                                            waitEnter();
                                                            break;
                                                    }
                                                }
                                            }
                                            break;
                                        case 2:
                                            List<Order> orders = cpvc.getClientOrders(id);
                                            if (orders.isEmpty()) {
                                                System.out.println("No orders placed yet.");
                                                break;
                                            } else {
                                                String datePlaced = StringUtils.center("DATE PLACED", 30);
                                                System.out.println("ID -" + datePlaced + "- STATE");
                                                for (Order o : orders) {
                                                    System.out.printf("%d  - %s  -   %d %n", o.getOrderID(), o.getOrderDate(), o.getState());
                                                }
                                                waitEnter();
                                            }
                                            break;
                                        default:
                                            break;

                                    }
                                }
                                break;
                            case Constants.COURIER:
                                RegisterCourier rc = new RegisterCourier();
                                Courier c = RegisterCourier.getCourier(rc.getCourierNIFByEmail(emailParameter));
                                System.out.println("What do you want to do?");
                                System.out.println("(1) - Choose Orders To Deliver");
                                option = input.nextInt();
                                if (option == 1) {
                                    ArrayList<Order> orders = covc.getAllOrders();
                                    ArrayList<Order> ordersToDeliver = new ArrayList<>();
                                    int optionCourier = -1;
                                    if (!orders.isEmpty()) {
                                        while (optionCourier != 0) {
                                            System.out.println("Select order to deliver -> 0 to exit");
                                            System.out.println();
                                            String datePlaced = StringUtils.center("DATE PLACED", 30);
                                            System.out.println("ID -" + datePlaced + "- STATE");
                                            for (Order order : orders) {
                                                if (order.getPharmacyID() == c.getPharmacyId()) {
                                                    System.out.printf("%d  - %s  -   %.2s %n", order.getOrderID(), order.getOrderDate(), order.getCart().size());
                                                }
                                            }
                                            optionCourier = input.nextInt();
                                            for (Order order : orders) {
                                                if (optionCourier == order.getOrderID()) {
                                                    if (!ordersToDeliver.contains(order)) {
                                                        ordersToDeliver.add(order);
                                                    } else {
                                                        System.out.println("Order already selected.");
                                                        waitEnter();
                                                    }
                                                }
                                            }
                                        }
                                        boolean valid = false;
                                        while (!valid)
                                            try {
                                                DeliveryRun dr = cdrc.createDeliveryRun(c.getEmail(), ordersToDeliver, c.getPharmacyId());
                                                valid = true;
                                                System.out.println("Confirm Delivery Run? (1)-Yes / (2)-No");
                                                System.out.println();
                                                String datePlaced = StringUtils.center("DATE PLACED", 30);
                                                System.out.println("ID -" + datePlaced + "- STATE");
                                                for (Order order : ordersToDeliver) {
                                                    System.out.printf("%d  - %s  -   %.2s %n", order.getOrderID(), order.getOrderDate(), order.getCart().size());
                                                }
                                                optionCourier = input.nextInt();
                                                if (optionCourier == 1) {
                                                    cdrc.confirmDeliveryRun();
                                                    System.out.println("Distance or Energy Saving? (1)-Distance / (2)-Energy Saving");
                                                    DeliveryRoute ds = cdrc.generateDeliveryRoute(input.nextInt());
                                                    System.out.println("Delivery route: \n Path to follow: \n");
                                                    for (int i = 1; i < ds.getRoute().size(); i++) {
                                                        System.out.printf("%s --> %s \n", ds.getRoute().get(i - 1).getName(), ds.getRoute().get(i).getName());
                                                    }
                                                    System.out.printf("Total distance: %s km \n", ds.getDistance());
                                                    System.out.printf("Total energy spent: %s Wh \n", ds.getEnergyCost());
                                                    for (Order order : ordersToDeliver) {
                                                        ApplicationPOT.getInstance().getPlatform().getClientOrderDB().updateOrderStatus(order.getOrderID(), Constants.DELIVERED);
                                                    }
                                                    sendEmailDeliveries(ordersToDeliver);
                                                    System.out.println("Placing Scooter To charge...");
                                                    System.out.println("Lock Scooter? (1)-Yes / (2)-No");
                                                    Courier cour = ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourierById(dr.getiDDeliverer());
                                                    Scooter scooter = ApplicationPOT.getInstance().getPlatform().getScooterDB().getScooterByID(cour.getScooterId());
                                                    if (input.nextInt() == 1) {
                                                        scooter.setLocked(true);
                                                    }
                                                    Date date = scc.placeScooterToCharge(cour, scooter);
                                                    scc.getChargingTimeAndLockingStatus(cour, scooter, date);

                                                    System.out.println("Fast Charge? (1)-Yes / (2)-No");
                                                    if (input.nextInt() == 1) {
                                                        scooter.setStateOfCharge(100);
                                                        rs.updateScooter(scooter);
                                                        rs.scooterDoneCharging(scooter.getId());
                                                    }
                                                } else {
                                                    System.out.println("Your scooter wasn't locked, so it isn't charging.");
                                                }
                                            } catch (IllegalArgumentException iae) {
                                                System.out.println("Orders exceed maximum payload! \n Restart the process and delete one or more orders");
                                                valid = true;
                                                String datePlaced = StringUtils.center("DATE PLACED", 30);
                                                System.out.println("ID -" + datePlaced + "- STATE");
                                            } catch (RuntimeException re) {
                                                System.out.println(re.getMessage());
                                            }
                                    } else {
                                        System.out.println("No orders to deliver!");
                                    }
                                }
                                break;
                            case Constants.ADMIN:
                                while (option != 0) {
                                    System.out.println("What do you want to do?");
                                    System.out.println("(0)  -  Exit");
                                    System.out.println("(1)  -  Register a Courier ");
                                    System.out.println("(2)  -  Remove a Pharmacy");
                                    System.out.println("(3)  -  Add a Pharmacy");
                                    System.out.println("(4)  -  Remove a Scooter");
                                    System.out.println("(5)  -  Add a Scooter");
                                    System.out.println("(6)  -  Remove a drone");
                                    System.out.println("(7)  -  Add a drone");
                                    System.out.println("(8)  -  Update Pharmacy");
                                    System.out.println("(9)  -  Update Scooter");
                                    System.out.println("(10) -  Update Drone");
                                    System.out.println("(11) -  Deliver by Drone");
                                    System.out.println("(12) -  Simulate delivery run");

                                    option = input.nextInt();
                                    switch (option) {
                                        case 0:
                                            System.out.println("Exited successfully.");
                                            break;
                                        case 1:
                                            System.out.println("Please insert the courier´s data as follows:");
                                            System.out.println("name,niss,nif,weight,scooterId,pharmacyId,email");
                                            line = input.next();
                                            try {
                                                data = line.split(",");
                                                password = PasswordGeneration.generatePassword();
                                                Courier courier = rcc.newCourier(data[0], Double.parseDouble(data[1]), Integer.parseInt(data[2]), Double.parseDouble(data[3])
                                                        , Integer.parseInt(data[4]), Integer.parseInt(data[5]), data[6], password);

                                                if (courier == null) {
                                                    System.out.println("Please check input information an try again!");
                                                } else {
                                                    System.out.println("Courier successfully registered!");
                                                }

                                            } catch (InputMismatchException ime) {
                                                System.out.println("Please check input information an try again!");
                                            } catch (ArrayIndexOutOfBoundsException aioobe) {
                                                System.out.println("Insufficient/Excessive data input");
                                            }
                                            waitEnter();
                                            break;
                                        case 2:
                                            System.out.println("Please enter the Pharmacy NIF:");
                                            int nif = input.nextInt();
                                            try {
                                                if (upc.removePharmacy(nif))
                                                    System.out.println("Pharmacy Successfully Removed!");
                                            } catch (IllegalArgumentException ex) {
                                                System.out.println(ex.getMessage());
                                            } catch (ArrayIndexOutOfBoundsException aioobe) {
                                                System.out.println("Insufficient/Excessive data input");
                                            }
                                            waitEnter();
                                            break;
                                        case 3:
                                            System.out.println("Please insert the pharmacy´s data as follows:");
                                            System.out.println("name,nif,idAdmin,postalcode(xxxx-xxx),address,x,y,voltageInput,currentInput,maxScooters,elevation");
                                            input.nextLine();
                                            line = input.nextLine();
                                            try {
                                                data = line.split(",");
                                                Pharmacy ph = new Pharmacy(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3], data[4], Double.parseDouble(data[5]), Double.parseDouble(data[6]), Integer.parseInt(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9]), Double.parseDouble(data[10]));
                                                if (upc.addPharmacy(ph)) {
                                                    System.out.println("Pharmacy Successfully Added!");
                                                } else {
                                                    System.out.println("Incorrect or duplicated data, pharmacy not added!");
                                                }
                                            } catch (InputMismatchException ex) {
                                                System.out.println("Pharmacy not added, please check input data!");
                                            } catch (ArrayIndexOutOfBoundsException aioobe) {
                                                System.out.println("Insufficient/Excessive data input");
                                            }
                                            waitEnter();
                                            break;
                                        case 4:
                                            System.out.println("Please enter the Scooter ID:");
                                            input.nextLine();
                                            id = input.nextInt();
                                            rs = rscootc.getRscoot();
                                            try {
                                                Scooter scoot = RegisterScooter.getScooterByID(id);
                                                rs.removeScooter(scoot);
                                                System.out.println("Scooter Successfully Removed!");
                                            } catch (IllegalArgumentException ex) {
                                                System.out.println(ex.getMessage());
                                            } catch (ArrayIndexOutOfBoundsException aioobe) {
                                                System.out.println("Insufficient/Excessive data input");
                                            }
                                            waitEnter();
                                            break;
                                        case 5:
                                            System.out.println("Please insert the scooter's data as follows:");
                                            System.out.println("ID,maxBatteryCapacity,stateOfCharge,powerOutput,weight,maxPayload,pharmacyID");
                                            input.nextLine();
                                            try {
                                                line = input.nextLine();
                                                data = line.split(",");
                                                rscootc.newScooter(Integer.parseInt(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3])
                                                        , Double.parseDouble(data[4]), Double.parseDouble(data[5]), Integer.parseInt(data[6]));
                                                if (rscootc.saveScooter()) {
                                                    System.out.println("Scooter Successfully Added!");
                                                } else {
                                                    System.out.println("Incorrect or duplicated data, Scooter not added!");
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("Scooter not added, please check input data!");
                                            } catch (ArrayIndexOutOfBoundsException aioobe) {
                                                System.out.println("Insufficient/Excessive data input");
                                            }
                                            waitEnter();
                                            break;
                                        case 6:
                                            System.out.println("Please enter the Drone ID:");
                                            id = input.nextInt();
                                            try {
                                                Drone drone = rdrone.getDroneByID(id);
                                                rdrone.removeDrone(drone);
                                                System.out.println("Drone Successfully Removed!");
                                            } catch (IllegalArgumentException ex) {
                                                System.out.println(ex.getMessage());
                                            } catch (ArrayIndexOutOfBoundsException aioobe) {
                                                System.out.println("Insufficient/Excessive data input");
                                            }
                                            waitEnter();
                                            break;
                                        case 7:
                                            System.out.println("Please insert the drone's data as follows:");
                                            System.out.println("ID,maxBatteryCapacity,stateOfCharge,powerOutput,weight,maxPayload,pharmacyID");
                                            input.nextLine();
                                            try {
                                                line = input.nextLine();
                                                data = line.split(",");
                                                rdc.newDrone(Integer.parseInt(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]),
                                                        Double.parseDouble(data[4]), Double.parseDouble(data[5]), Integer.parseInt(data[6]));
                                                if (rdc.saveDrone()) {
                                                    System.out.println("Drone Successfully Added!");
                                                } else {
                                                    System.out.println("Incorrect or duplicated data, Drone not added!");
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("Drone not added, please check input data!");
                                            } catch (ArrayIndexOutOfBoundsException aioobe) {
                                                System.out.println("Insufficient/Excessive data input");
                                            }
                                            waitEnter();
                                            break;
                                        case 8:
                                            System.out.println("Please enter the Pharmacy NIF:");
                                            try {
                                                nif = input.nextInt();
                                                Pharmacy pharma = upc.getPharmacy(nif);
                                                System.out.println("Please insert the pharmacy´s data as follows:");
                                                System.out.println("name,nif,voltageInput,currentInput,maxScooters");
                                                input.nextLine();
                                                line = input.nextLine();
                                                data = line.split(",");
                                                pharma.setName(data[0]);
                                                pharma.setNif(Integer.parseInt(data[1]));
                                                pharma.setVoltageInput(Integer.parseInt(data[2]));
                                                pharma.setCurrentInput(Integer.parseInt(data[3]));
                                                pharma.setMaxScooters(Integer.parseInt(data[4]));
                                                upc.updatePharmacy(pharma);
                                                System.out.println("Pharmacy updated successfully!");
                                            } catch (IllegalArgumentException ex) {
                                                System.out.println(ex.getMessage());
                                            } catch (InputMismatchException ime) {
                                                System.out.println("Pharmacy not updated, please check input data!");
                                            } catch (ArrayIndexOutOfBoundsException aioobe) {
                                                System.out.println("Insufficient/Excessive data input");
                                            }
                                            waitEnter();
                                            break;
                                        case 9:
                                            System.out.println("Please enter the Scooter ID:");
                                            try {
                                                id = input.nextInt();
                                                Scooter scootUpdate = RegisterScooter.getScooterByID(id); //can be mocked
                                                System.out.println("Please insert the scooter's data as follows:");
                                                System.out.println("maxBatteryCapacity,stateOfCharge,powerOutput,weight,maxPayload");
                                                input.nextLine();
                                                line = input.nextLine();
                                                data = line.split(",");
                                                rscootc.newScooter(scootUpdate.getId(), Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), scootUpdate.getPharmacyId());
                                                rscootc.updateScooter();
                                                System.out.println("Scooter updated successfully!");
                                            } catch (IllegalArgumentException ex) {
                                                System.out.println(ex.getMessage());
                                            } catch (InputMismatchException ime) {
                                                System.out.println("Scooter not updated, please check input data!");
                                            } catch (ArrayIndexOutOfBoundsException aioobe) {
                                                System.out.println("Insufficient/Excessive data input");
                                            }
                                            waitEnter();
                                            break;
                                        case 10:
                                            System.out.println("Please enter the Drone ID:");
                                            try {
                                                id = input.nextInt();
                                                Drone droneUpdate = rdrone.getDroneByID(id); //can be mocked
                                                System.out.println("Please insert the drone's data as follows:");
                                                System.out.println("maxBatteryCapacity,stateOfCharge,powerOutput,weight,maxPayload");
                                                input.nextLine();
                                                line = input.nextLine();
                                                data = line.split(",");
                                                rdc.newDrone(droneUpdate.getID(), Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), droneUpdate.getPharmacyID());
                                                rdc.updateDrone();
                                                System.out.println("Drone updated successfully!");
                                            } catch (IllegalArgumentException ex) {
                                                System.out.println(ex.getMessage());
                                            } catch (InputMismatchException ime) {
                                                System.out.println("Drone not updated, please check input data!");
                                            } catch (ArrayIndexOutOfBoundsException aioobe) {
                                                System.out.println("Insufficient/Excessive data input");
                                            }
                                            waitEnter();
                                            break;
                                        case 11:
                                            int flag = 0;
                                            ArrayList<Order> orders = covc.getAllOrders();
                                            ArrayList<Order> ordersToDeliver = new ArrayList<>();
                                            int optionCourier = -1;
                                            int optionPharmacy = 0;
                                            List<Pharmacy> allPharmacies = new ArrayList<>();
                                            allPharmacies = ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getAllPharmacies();
                                            System.out.println("Choose which pharmacy to deliver from: ");
                                            for (Pharmacy p : allPharmacies) {
                                                System.out.printf("(%d) -> %s \n", p.getId(), p.getName());
                                            }
                                            optionPharmacy = input.nextInt();
                                            System.out.println();
                                            for (Order order : orders) {
                                                if (order.getPharmacyID() == optionPharmacy) {
                                                    flag++;
                                                }
                                            }
                                            if (flag > 0) {
                                                while (optionCourier != 0) {
                                                    System.out.println("Select order to deliver -> 0 to exit");
                                                    System.out.println();
                                                    String datePlaced = StringUtils.center("DATE PLACED", 30);
                                                    System.out.println("ID -" + datePlaced + "- STATE");
                                                    for (Order order : orders) {
                                                        if (order.getPharmacyID() == optionPharmacy) {
                                                            System.out.printf("%d  - %s  -   %.2s %n", order.getOrderID(), order.getOrderDate(), order.getCart().size());
                                                        }
                                                    }
                                                    optionCourier = input.nextInt();
                                                    for (Order order : orders) {
                                                        if (optionCourier == order.getOrderID()) {
                                                            if (!ordersToDeliver.contains(order)) {
                                                                ordersToDeliver.add(order);
                                                            } else {
                                                                System.out.println("Order already selected.");
                                                            }
                                                        }
                                                    }
                                                }
                                                if (optionCourier != -1) {
                                                    System.out.println("Choose Drone to Make the deliver.");
                                                    ArrayList<Drone> arrayList = ApplicationPOT.getInstance().getPlatform().getDroneDB().getAllDrones();
                                                    for (Drone d : arrayList) {
                                                        if (d.getPharmacyID() == optionPharmacy) {
                                                            System.out.printf("(%d) -> %s - %s \n", d.getID(), d.getMaxPayload(), d.getStateOfCharge());
                                                        }
                                                    }
                                                    int droneChosen = 0;
                                                    int optionDrone = input.nextInt();
                                                    for (Drone d : arrayList) {
                                                        if (optionDrone == d.getID())
                                                            droneChosen = d.getID();
                                                    }
                                                    boolean valid = false;
                                                    while (!valid)
                                                        try {
                                                            DeliveryRun dr = cdrrc.createDeliveryRun(droneChosen, ordersToDeliver, Constants.DEFAULT_PHARMACY_ID);
                                                            valid = true;
                                                            System.out.println("Confirm Delivery Run? (1)-Yes / (2)-No");
                                                            for (Order order : ordersToDeliver) {
                                                                System.out.printf("(%d) -> %.10s - %s \n", order.getOrderID(), order.getOrderDate(), order.getCart().size());
                                                            }
                                                            optionCourier = input.nextInt();
                                                            if (optionCourier == 1) {
                                                                cdrrc.confirmDeliveryRun();
                                                                System.out.println("Distance or Energy Saving? (1)-Distance / (2)-Energy Saving");
                                                                DeliveryRoute ds = cdrrc.generateDeliveryRoute(input.nextInt());
                                                                for (int i = 1; i < ds.getRoute().size(); i++) {
                                                                    System.out.printf("%s --> %s \n", ds.getRoute().get(i - 1).getName(), ds.getRoute().get(i).getName());
                                                                }
                                                                System.out.printf("Total distance: %s km \n", ds.getDistance());
                                                                System.out.printf("Total energy spent: %s Wh \n", ds.getEnergyCost());
                                                                for (Order order : ordersToDeliver) {
                                                                    ApplicationPOT.getInstance().getPlatform().getClientOrderDB().updateOrderStatus(order.getOrderID(), Constants.DELIVERED);
                                                                }
                                                                Drone used = ApplicationPOT.getInstance().getPlatform().getDroneDB().getDroneById(droneChosen);
                                                                used.setStateOfCharge(Constants.SCOOTER_FULLY_CHARGED);
                                                                sendEmailDeliveries(ordersToDeliver);
                                                            }
                                                        } catch (IllegalArgumentException iae) {
                                                            System.out.println("Orders exceed maximum payload! \n Restart the process and delete one or more orders");
                                                            valid = true;
                                                            String datePlaced = StringUtils.center("DATE PLACED", 30);
                                                            System.out.println("ID -" + datePlaced + "- STATE");
                                                        } catch (RuntimeException re) {
                                                            System.out.println(re.getMessage());
                                                        }
                                                } else {
                                                    System.out.println("Process cancelled");
                                                }
                                            } else {
                                                System.out.println("No orders to deliver in chosen pharmacy!");
                                            }
                                            waitEnter();
                                            break;
                                        case 12:
                                            flag = 0;
                                            System.out.println("Delivery run simulation");
                                            int optionAdmin = -1;
                                            List<Order> ordersSimul = sdc.getAllReadyOrder();
                                            ArrayList<Order> ordersToDeliverSimul = new ArrayList<>();
                                            allPharmacies = ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getAllPharmacies();
                                            System.out.println("Choose which pharmacy to deliver from: ");
                                            for (Pharmacy p : allPharmacies) {
                                                System.out.printf("(%d) -> %s \n", p.getId(), p.getName());
                                            }
                                            optionPharmacy = input.nextInt();
                                            for (Order order : ordersSimul) {
                                                if (order.getPharmacyID() == optionPharmacy) {
                                                    flag++;
                                                }
                                            }
                                            if (flag > 0) {
                                                try {
                                                    while (optionAdmin != 0) {
                                                        System.out.println("Select order to deliver -> 0 to exit");
                                                        System.out.println();
                                                        String datePlaced = StringUtils.center("DATE PLACED", 30);
                                                        System.out.println("ID -" + datePlaced + "- STATE");
                                                        for (Order order : ordersSimul) {
                                                            if (order.getPharmacyID() == optionPharmacy) {
                                                                System.out.printf("%d  - %s  -   %.2s %n", order.getOrderID(), order.getOrderDate(), order.getCart().size());
                                                            }
                                                        }
                                                        optionAdmin = input.nextInt();
                                                        for (Order order : ordersSimul) {
                                                            if (optionAdmin == order.getOrderID()) {
                                                                if (!ordersToDeliverSimul.contains(order)) {
                                                                    ordersToDeliverSimul.add(order);
                                                                } else {
                                                                    System.out.println("Order already selected.");
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (optionAdmin != -1) {
                                                        System.out.println("Confirm Order? (1)-Yes / (2)-No");
                                                        optionAdmin = input.nextInt();
                                                        if (optionAdmin == 1) {
                                                            sdc.generateDeliveryList(ordersToDeliverSimul);
                                                            System.out.println("Choose delivery method? (1)-Courier / (2)-Drone");
                                                            optionAdmin = input.nextInt();
                                                            sdc.setChoiceOfDeliveryMethod(optionAdmin);
                                                            System.out.println("Choose simulation criteria? (1)-Distance / (2)-Energy");
                                                            optionAdmin = input.nextInt();
                                                            DeliveryRun dr = sdc.setChoiceOfSimulationCriteria(optionAdmin);
                                                            for (int i = 1; i < dr.getDeliveryRoute().getRoute().size(); i++) {
                                                                System.out.printf("%s --> %s \n", dr.getDeliveryRoute().getRoute().get(i - 1).getName(), dr.getDeliveryRoute().getRoute().get(i).getName());
                                                                System.out.println();
                                                                String datePlaced = StringUtils.center("DATE PLACED", 30);
                                                                System.out.println("ID -" + datePlaced + "- STATE");
                                                            }
                                                            System.out.printf("Total distance: %s km \n", dr.getDeliveryRoute().getDistance());
                                                            System.out.printf("Total energy spent: %s Wh \n", dr.getDeliveryRoute().getEnergyCost());
                                                        }
                                                    } else {
                                                        System.out.println("Process cancelled");
                                                    }
                                                } catch (RuntimeException re) {
                                                    System.out.println(re.getMessage());
                                                } catch (Exception e) {
                                                    System.out.println("Orders exceed maximum payload! \n Restart the process and delete one or more orders");
                                                }

                                            } else {
                                                System.out.println("No orders to simulate delivery in chosen pharmacy!");
                                            }
                                            waitEnter();
                                            break;
                                        default:
                                            break;

                                    }
                                }
                                break;
                            default:
                                break;
                        }

                    }
                    break;
                case 0:
                    System.out.println("You left the application");
                    break;
                default:
                    System.out.println("No option selected.");
                    break;
            }
            waitEnter();
        }

    }

    private static void sendEmailDeliveries(ArrayList<Order> ordersToDeliver) {
        String subject = "Your order is on its way!";
        String body = "The order has just left the pharmacy and will be delivered to you soon!";
        ClientDB cdb = ApplicationPOT.getInstance().getPlatform().getClientDB();
        SendEmail se = ApplicationPOT.getInstance().getPlatform().getSendEmail();
        for (Order o : ordersToDeliver) {
            se.sendEmail(cdb.getClientByID(o.getClientID()).getEmail(), subject, body);
        }
    }
/*

        System.out.println("\nVerificar se existe Sailor 100...");
        try {
            Sailor.getSailor(100);
            System.out.println("Nunca deve aparecer esta mensagem");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("\nAdicionar Sailor ...");


        long sailorID = 100;
        String sailorName = "Popeye";
        long sailorRating = 11;
        int sailorAge = 85;

        Sailor sailor = new Sailor(sailorID, sailorName);
        sailor.setAge(sailorAge);
        sailor.setRating(sailorRating);
        sailor.save();

        System.out.println("\t... Sailor Adicionado.");

        System.out.println("\nVerificar se existe Sailor 100...");
        try {
            sailor = Sailor.getSailor(100);
            System.out.println("\nSailor 100 existe...: " + sailor.getName());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
*/

/*
        RegisterUnregisteredClientController reg = new RegisterUnregisteredClientController();
        Client clnt = reg.newClient("client1", "oldfdsjb", "boaPass" ,"rua estranha", "1234-567",
                1561.28651, 15651.513, 123456789, 12356489, 2022, 9, 124);
        System.out.println("Client name: " + clnt.getName());

        ClientDB clntDB = new ClientDB();
        Client novo = clntDB.getClient(123456789);
        System.out.println(novo.getName());
*/

    public static void waitEnter() {
        System.out.println("Press ENTER to continue.");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println();
        }
        System.out.println();
    }

    public static void initializeControllers() {
        cdrrc = new CreateDeliveryRunDroneController();
        clic = new ClientLogInController();
        cpvc = new ClientProductViewController();
        covc = new CourierOrderViewController();
        cdrc = new CreateDeliveryRunController();
        drr = new DeliveryRunRegistry();
        rcc = new RegisterCourierController();
        rcour = new RegisterCourier();
        rclnt = new RegisterClient();
        rucc = new RegisterUnregisteredClientController();
        scc = new ScooterCharging();
        upc = new UpdatePharmacyController();
        rscootc = new RegisterScooterController();
        rs = new RegisterScooter();
        rdc = new RegisterDroneController();
        sdc = new SimulateDeliveryController();
        rdrone = new RegisterDrone();
    }

}


