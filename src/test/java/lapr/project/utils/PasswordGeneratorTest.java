package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordGeneratorTest {

    @Test
    void generatePasswordTest(){
        String expResult = "1234567";
        String result = PasswordGeneration.generatePassword();
        assertEquals(expResult.length(), result.length());
    }
}
