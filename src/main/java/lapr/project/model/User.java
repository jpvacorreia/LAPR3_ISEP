package lapr.project.model;


public class User {
    String email;
    String password;
    int userType;

    public User(String email, String password, int userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }

}
