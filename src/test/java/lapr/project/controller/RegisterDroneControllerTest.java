package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;
import lapr.project.model.Platform;
import lapr.project.model.RegisterDrone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class RegisterDroneControllerTest {

    RegisterDroneController rDroneC;
    Platform plat;
    RegisterDrone rDrone;
    DroneDB dDB;
    Drone drone;
    Drone drone2;

    @BeforeEach
    void setUp() {
        rDroneC = new RegisterDroneController();
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setRegisterDrone(mock(RegisterDrone.class));
        rDrone = plat.getRegisterDrone();
        plat.setDroneDB(mock(DroneDB.class));
        dDB = plat.getDroneDB();

        drone = new Drone(202121, 3500, 48.9, 75, 0.65, 2, 1);
        drone.setLocked(false);
        drone2 = new Drone(202122, 3500, 48.9, 75, 0.65, 2, 1);
        drone2.setLocked(false);

        rDroneC.rdrone = rDrone;
        rDroneC.drone = drone;
    }

    @Test
    void newDrone() {
        assertTrue(rDroneC.newDrone(202121, 3500, 48.9, 75, 0.65, 2, 1));

        when(rDrone.newDrone(202121, 3500, 48.9, 75, 0.65, 2, 1)).thenThrow(new IllegalArgumentException());
        assertFalse(rDroneC.newDrone(202121, 3500, 48.9, 75, 0.65, 2, 1));
    }

    @Test
    void saveDrone() {
        when(dDB.getDroneById(anyInt())).thenThrow(new IllegalArgumentException());
        when(dDB.addDrone(drone)).thenReturn(true);
        when(rDrone.saveDrone(drone)).thenReturn(true);

        assertTrue(rDroneC.saveDrone());

        rDroneC.drone = drone2;
        when(rDrone.saveDrone(drone2)).thenReturn(false);
        assertFalse(rDroneC.saveDrone());
    }

    @Test
    void removeDrone() {
        when(dDB.removeDrone(anyInt())).thenReturn(true);
        when(rDrone.removeDrone(drone)).thenReturn(true);

        assertTrue(rDroneC.removeDrone());
    }

    @Test
    void updateDrone() {
        when(dDB.getDroneById(anyInt())).thenReturn(drone);
        when(dDB.removeDrone(anyInt())).thenReturn(true);
        when(dDB.addDrone(drone)).thenReturn(true);

        when(rDrone.updateDrone(drone)).thenReturn(true);

        assertTrue(rDroneC.updateDrone());
    }


}