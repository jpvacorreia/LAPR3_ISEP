
package lapr.project.utils;

public class EnergyCalculations {
    
    private EnergyCalculations(){}
    
    public static double getTimeSpentS(double distance) {
        return (distance / Constants.SCOOTER_VELOCITY);
    }
    
    public static double getTotalPowerS(double totalRoadLoad, double angle) {
        return (totalRoadLoad * (Constants.SCOOTER_VELOCITY * Constants.WIND_VELOCITY * Math.cos(angle)));
    }
    
    public static double getTotalRoadLoadS(double weight, double roadSlopeAngle, double angle) {
        double totalRoadLoad = 0;
        double rsa = Math.toRadians(roadSlopeAngle);
        double roadSlopeForce = (weight * Constants.GRAVITATIONAL_ACCELERATION * (Math.sin(rsa)));
        double roadLoadForce = (weight * Constants.GRAVITATIONAL_ACCELERATION * Constants.RRRC * (Math.cos(rsa)));
        double dragForce = (0.5 * Constants.AIR_DENSITY * Constants.AIR_DRAG_COEFICIENT * Constants.SCOOTER_FRONTAL_AREA * (Math.pow((Constants.SCOOTER_VELOCITY * Constants.WIND_VELOCITY * Math.cos(angle)), 2.0)));
        totalRoadLoad = roadSlopeForce + roadLoadForce + dragForce;
        return totalRoadLoad;
    }
    
    public static double convertJoulesToWH(double energy){
        double energyConverted = (energy / 3600);
        return energyConverted;
    }
    
    public static double energyWastedBetweenTwoPointsYD(double weight){
        double power = ((Math.pow((Constants.DRONE_TAKEOFF_WEIGHT * Constants.GRAVITATIONAL_ACCELERATION), 1.5))/(Math.sqrt(2 * Constants.AIR_DENSITY * Constants.DRONE_FACING_AREA)));
        double time = (float)((Constants.DRONE_MAX_ALTITUDE - Constants.DRONE_MIN_ALTITUDE) / (Constants.DRONE_UPANDDOOWN_VELOCITY));
        double energyWastedInJoules =  (power * time);
        double energyWastedInWH = convertJoulesToWH(energyWastedInJoules);
        double finalEnergyInWH = (2 * energyWastedInWH);
        return finalEnergyInWH;
    }
}