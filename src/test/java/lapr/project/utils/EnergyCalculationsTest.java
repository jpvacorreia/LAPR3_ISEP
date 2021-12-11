package lapr.project.utils;

import lapr.project.model.RegisterScooter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;


public class EnergyCalculationsTest {
    
    @Test
    void getTotalRoadLoad(){
        double weight = 100;
        double roadSlopeAngle = 0;
        double angle = 0;
        double expected = 516.614585;
        double actual = EnergyCalculations.getTotalRoadLoadS(weight, roadSlopeAngle, angle);
        assertEquals(expected, actual, 0.1);
        weight = 250;
        roadSlopeAngle = 4;
        angle = Math.toRadians(60);
        assertEquals(1399.5729,EnergyCalculations.getTotalRoadLoadS(weight,roadSlopeAngle, angle),0.1);
        assertNotEquals(300, EnergyCalculations.getTotalRoadLoadS(weight,roadSlopeAngle, angle),0.1);
    }
    
    @Test 
    void getTimeSpent(){
        double distance = 3000;
        double expected = 360.000000000000000;
        double actual = EnergyCalculations.getTimeSpentS(distance);
        assertEquals(expected, actual, 0.1);
        assertNotEquals(0,actual);
    }
    
    @Test
    void getTotalPower(){
        double totalRoadLoad = 2.5;
        double angle = Math.toRadians(45);
        double expected = 20.46028;
        double actual = EnergyCalculations.getTotalPowerS(totalRoadLoad, angle);
        assertEquals(expected, actual, 0.1);
        assertNotEquals(0,EnergyCalculations.getTotalPowerS(totalRoadLoad, angle));
    }
    
    @Test
    void convertJoulesToWH(){
        double energy = 3600;
        double expected = 1;
        double actual = EnergyCalculations.convertJoulesToWH(energy);
        assertEquals(expected, actual);
        assertNotEquals(2, actual);
    }
    
    @Test 
    void energyWastedBetweenTwoPointsYD(){
        double weight = 30;
        double expected = 1.7905526;
        double actual = EnergyCalculations.energyWastedBetweenTwoPointsYD(weight);
        assertEquals(expected, actual, 0.1);
        assertNotEquals(47.239857289375, actual);
    }
}
