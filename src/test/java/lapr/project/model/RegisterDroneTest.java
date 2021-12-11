package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.DroneDB;
import lapr.project.utils.Edge;
import lapr.project.utils.Pair;
import lapr.project.utils.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class RegisterDroneTest {

    Platform plat;
    RegisterDrone rDrone;
    DroneDB dDB;
    Drone drone;
    Drone drone2;

    @BeforeEach
    void setUp() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setRegisterDrone(new RegisterDrone());
        rDrone = plat.getRegisterDrone();
        plat.setDroneDB(mock(DroneDB.class));
        dDB = plat.getDroneDB();

        drone = new Drone(202121, 3500, 48.9, 75, 0.65, 2, 1);
        drone.setLocked(false);
        drone2 = new Drone(202122, 3500, 48.9, 75, 0.65, 2, 1);
        drone2.setLocked(false);
    }

    @Test
    void newDrone() {
        assertEquals(drone.toString(), rDrone.newDrone(202121, 3500, 48.9, 75, 0.65, 2, 1).toString());
    }

    @Test
    void getDroneById() {
        when(dDB.getDroneById(202121)).thenReturn(drone);
        assertEquals(drone.toString(), drone.toString());

        assertNotNull(rDrone.getDroneByID(202121));

        when(dDB.getDroneById(anyInt())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> {
            rDrone.getDroneByID(202123);
        });
    }

    @Test
    void saveDrone() {
        when(dDB.getDroneById(anyInt())).thenReturn(drone);
        assertFalse(rDrone.saveDrone(drone));

        when(dDB.getDroneById(anyInt())).thenThrow(new IllegalArgumentException());
        when(dDB.addDrone(drone)).thenReturn(true);
        assertTrue(rDrone.saveDrone(drone));

        when(dDB.addDrone(drone2)).thenReturn(false);
        assertFalse(rDrone.saveDrone(drone2));
    }

    @Test
    void removeDrone() {
        when(dDB.getDroneById(202122)).thenReturn(drone2);
        when(dDB.removeDrone(202122)).thenReturn(true);
        assertTrue(rDrone.removeDrone(drone2));
        when(dDB.removeDrone(drone.getID())).thenReturn(false);
        assertFalse(rDrone.removeDrone(drone));

        when(dDB.getDroneById(202121)).thenThrow(new IllegalArgumentException());
        assertFalse(rDrone.removeDrone(drone));
    }

    @Test
    void updateDrone() {
        when(dDB.getDroneById(anyInt())).thenReturn(drone);
        when(dDB.updateDrone(drone)).thenReturn(true);
        assertTrue(rDrone.updateDrone(drone));
        when(dDB.updateDrone(drone2)).thenReturn(false);
        assertFalse(rDrone.updateDrone(drone2));

        when(dDB.getDroneById(202121)).thenThrow(new IllegalArgumentException());
        assertFalse(rDrone.updateDrone(drone));
    }
    
    @Test
    void energyWastedBetweenTwoPointsD(){
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Edge e1 = new Edge("teste", 20, v1, v2);
        double weight = 20;
        int windDirection = 0;
        double expected = 344.568328;
        double actual = RegisterDrone.energyWastedBetweenTwoPointsD(e1.getWeight(), weight, windDirection);
        assertEquals(expected, actual, 0.1);
        weight = 30;
        windDirection = 60;
        Pair<Double,Double> pair1 = new Pair<>(4.15113,-8.4123);
        Pair<Double,Double> pair2 = new Pair<>(4.16113,-8.5123);
        v1.setCoordinates(pair1);
        v2.setCoordinates(pair2);
        Edge e2 = new Edge("Teste",25,v1,v2);
        assertEquals(646.061381,RegisterDrone.energyWastedBetweenTwoPointsD(e2.getWeight(),weight, windDirection), 0.1);
        assertNotEquals(684.79679,RegisterDrone.energyWastedBetweenTwoPointsD(e2.getWeight(),weight, windDirection));
    }
}