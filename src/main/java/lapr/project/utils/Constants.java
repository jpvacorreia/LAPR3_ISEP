package lapr.project.utils;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.api.ProximityMap;

public class Constants {
    public static final int CLIENT = 1;
    public static final int COURIER = 3;
    public static final int ADMIN = 2;
    public static final int PENDING = 1;
    public static final int PROCESSING = 2;
    public static final int DELIVERY = 3;
    public static final int DELIVERED = 4;
    public static final int SCOOTER_FULLY_CHARGED = 100;
    public static final double GRAVITATIONAL_ACCELERATION = 9.8;
    public static final double RRRC = 0.5; // road rolling resistance coeficient;
    public static final double AIR_DENSITY = 1.2041;
    public static final double AIR_DRAG_COEFICIENT = 1.1;
    public static final double SCOOTER_VELOCITY = 8.33333333333;
    public static final double WIND_VELOCITY = 1.38889;
    public static final double SCOOTER_FRONTAL_AREA = 0.3;
    public static final int DRONE_HORIZONTAL_VELOCITY = 16;
    public static final int DRONE_UPANDDOOWN_VELOCITY = 6;
    public static final double DRONE_TAKEOFF_WEIGHT = 4.7;
    public static final int LIFT_RATIO = 4;
    public static final double DRONE_EFFICIENCY = 0.8;
    public static final int DRONE_PAVIO = 10;
    public static final int DRONE_FACING_AREA = 1;
    public static final int DRONE_MIN_ALTITUDE = 10;
    public static final int DRONE_MAX_ALTITUDE = 110;
    public static final int MINIMUM_CREDITS_FOR_FREE_SHIPPING = 10;
    public static final double SHIPPING_RATIO = 0.32;
    public static final int DEFAULT_COURIER_ID = 1;
    public static final int DEFAULT_DRONE_ID = 1;
    public static final int DEFAULT_PHARMACY_ID = 1;
    public static final int DEFAULT_WIND_DIRECTION = 20;
    public static final int DEFAULT_WIND_DIRECTION_OPPOSITE = 180-DEFAULT_WIND_DIRECTION;
    public static final int MINIMUM_ACCEPTABLE_BATTERY = 25;
    public static final ProximityMap PROXIMITY_MAP_DRONE = new ProximityMap(true);
    public static final ProximityMap PROXIMITY_MAP_SCOOTER = new ProximityMap(false);
}
