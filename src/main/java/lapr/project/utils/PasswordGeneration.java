package lapr.project.utils;

import java.util.Random;

public class PasswordGeneration {

    private PasswordGeneration(){}

    static public String generatePassword(){
        final String ALPHANUMERICAL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int ALPHANUMERICALSIZE = ALPHANUMERICAL.length();
        String pwd = "";
        Random random = new Random();
        for(int i = 0; i < 7; i++){
            int n = random.nextInt(ALPHANUMERICALSIZE);
            pwd = pwd + ALPHANUMERICAL.charAt(n);
        }
        return pwd;
    }
}
