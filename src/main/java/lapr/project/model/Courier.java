package lapr.project.model;


public class Courier {

    int id;
    String name;
    double niss;
    int nif;
    double weight;
    String email;
    int pharmacyId;
    int scooterId;

    public Courier(String name, double niss, int nif, double weight, int scooterId, int pharmacyId, String email) {
        this.name = name;
        this.niss = niss;
        this.nif = nif;
        this.weight = weight;
        this.email = email;
        this.pharmacyId = pharmacyId;
        this.scooterId = scooterId;
    }
    
    public Courier(int id, String name, double niss, int nif, double weight, int scooterId, int pharmacyId, String email) {
        this.id = id;
        this.name = name;
        this.niss = niss;
        this.nif = nif;
        this.weight = weight;
        this.email = email;
        this.pharmacyId = pharmacyId;
        this.scooterId = scooterId;
    }
    
    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }
    
    public double getNiss() {
        return niss;
    }
    
    public int getNif() {
        return nif;
    }
    
    public double getWeight() {
        return weight;
    }

    public String getEmail() {
        return email;
    }
    
    public int getPharmacyId(){
        return pharmacyId;
    }
    
    public int getScooterId(){
        return scooterId;
    }

    
}
