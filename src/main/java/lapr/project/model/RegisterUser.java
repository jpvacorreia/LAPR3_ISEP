package lapr.project.model;

import lapr.project.controller.ApplicationPOT;

public class RegisterUser {

    /**
     * Creates new User in java
     *
     * @param email
     * @param password
     * @param userType
     * @return
     */
    public User newUser(String email, String password, int userType) {
        return new User(email, password, userType);
    }


    /**
     * Saves User in database
     *
     * @param user - User to be saved
     * @return true when no exceptions are lifted
     */
    public boolean registerUser(User user) {
        return save(user);
    }



    /**
     * Retrieves a User from the DataBase
     *
     * @param email     User's email.
     * @return true when no exceptions are lifted
     */
    public static User getUserByEmail(String email) {
        return ApplicationPOT.getInstance().getPlatform().getUserDB().getUser(email);
    }

    /**
     * Saves this User into the DataBase
     *
     * @param user - User to be saved
     * @return true when no exceptions are lifted
     **/
    private boolean save(User user) {

        try {
            getUserByEmail(user.getEmail());
        } catch (IllegalArgumentException ex) {
            //Of the record does not exist, save it
            return ApplicationPOT.getInstance().getPlatform().getUserDB().addUser(user);
        }
        return false;
    }

    /**
     * Deletes this User from the DataBase
     *
     * @param user - User to be eliminated
     * @return true when no exceptions are lifted
     **/
    public boolean delete(User user){
        return ApplicationPOT.getInstance().getPlatform().getUserDB().removeUser(user.getEmail());
    }


}
