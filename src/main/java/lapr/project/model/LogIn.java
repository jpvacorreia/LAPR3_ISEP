package lapr.project.model;

import lapr.project.controller.ApplicationPOT;

public class LogIn {

    /**
     * Returns the user type associated with the email and password passed as parameters if they exist.
     *
     * @param email         Platform user email.
     * @param pwd           Platform user password.
     *
     * @return userType     type of user associated with the email
     */
    public int logIn(String email, String pwd){
        try {
            User user = RegisterUser.getUserByEmail(email);
            if (user != null && user.password.equals(pwd)) {
                return user.getUserType();
            } else {
                throw new IllegalAccessException("Incorrect password");
            }
        } catch (IllegalArgumentException iae){
            System.out.println("No User with email: " + email);
        }catch (IllegalAccessException npe){
            System.out.println(npe.getMessage());
        }
        return -1;
    }


    /**
     * Returns the identification number of a Client from the email passed as paramenter.
     *
     * @param email         Platform user email.
     *
     * @return clientId     Client identification number.
     */
    public int getClientIdByEmail(String email){
        return ApplicationPOT.getInstance().getPlatform().getRegisterClient().getClientIdByEmail(email);
    }
}
