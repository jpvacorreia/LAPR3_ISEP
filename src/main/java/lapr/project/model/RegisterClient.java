package lapr.project.model;


import lapr.project.controller.ApplicationPOT;
import lapr.project.utils.Constants;
import lapr.project.utils.PasswordGeneration;

public class RegisterClient{

    /**
     *  Creates an object of Client from the information passed as paramenter
     *
     * @param name                  Client's name
     * @param email                 Client's email
     * @param address               Client's address
     * @param postalCode            Client's postal code
     * @param coordinateX           Client's X coordinate in decimal degrees
     * @param coordinateY           Client's Y coordinate in decimal degrees
     * @param nif                   Client's NIF
     * @param creditCardNumber      Client's Credit card Number
     * @param validity_year         Client's Credit card year of validity
     * @param validity_month        Client's Credit card month of validity
     * @param ccv                   Client's Credit card verification code
     * @param elevation             Client's address elevation from sea level.
     *
     * @return client               object of Client class.
     */
    public Client newClient(String name, String email, String address, String postalCode, double coordinateX, double coordinateY,
                            int nif, int creditCardNumber, int validity_year, int validity_month, int ccv, double elevation) {
        return new Client(name, email, address, postalCode, coordinateX, coordinateY, nif,
                creditCardNumber, validity_year, validity_month, ccv, elevation);
    }

    /**
     * Registers a new Client into the Data Base
     *
     * @param clnt      Object of Client class
     * @return True     True if the client is successfully added into the Data Base
     */
    public boolean registerClient(Client clnt) {
        return save(clnt);
    }


    /**
     * Registers a new Client as a Platform User
     *
     * @param clnt      Object of client class
     * @return True     True if the Platform User is successfully created, false otherwise
     */
    public User registerClientAsUser(Client clnt) {
        String pwd = PasswordGeneration.generatePassword();
        String subject = "Your password is here!";
        String body = "Hello user! \n Your password is: " + pwd;
        ApplicationPOT.getInstance().getPlatform().getSendEmail().sendEmail(clnt.getEmail(), subject, body);
        User created = ApplicationPOT.getInstance().getPlatform().getRegisterUser().newUser(clnt.getEmail(),  pwd, Constants.CLIENT);
        ApplicationPOT.getInstance().getPlatform().getUserDB().addUser(created);
        return created;
    }

    /**
     * Retrieves a Client identification number from the Data Base using their email as identifier.
     *
     * @param email     Client's email
     * @return id       Client's identification number
     */
    public int getClientIdByEmail(String email) {
        return ApplicationPOT.getInstance().getPlatform().getClientDB().getClientIdByEmail(email); }


    /**
     * Retrieves a Client from the DataBase using their NIF as identifier.
     *
     * @param clientNIF     Client NIF number.
     */
    public static Client getClient(int clientNIF) {
        return ApplicationPOT.getInstance().getPlatform().getClientDB().getClient(clientNIF);
    }

    /**
     * Saves a Client passed as parameter into the DataBase
     *
     * @param clnt      Object of Client class
     * @return True     True if Client is successfully added to the Data Base
     **/
    private boolean save(Client clnt) {
        try {
            getClient(clnt.getNif());
        } catch (IllegalArgumentException ex) {
            //Of the record does not exist, save it
            return ApplicationPOT.getInstance().getPlatform().getClientDB().addClient(clnt);
        }
        return false;
    }

    /**
     * Removes a Client passed as parameter from the DataBase
     *
     * @param clnt      Object of Client class
     * @return True     True if Client is successfully removed from the Data Base
     **/
    public boolean remove(Client clnt){
        try {
            getClient(clnt.getNif());
            return ApplicationPOT.getInstance().getPlatform().getClientDB().removeClient(clnt.getNif());
        } catch (IllegalArgumentException ex){
            System.out.println("Client does not exist in the database");
        }
        return false;
    }


}
