package lapr.project.model;

public class PostalCode {
    int city;
    int local;

    public PostalCode(String postalCode) {
        String[] post = postalCode.split("-");
        this.city = Integer.parseInt(post[0]);
        this.local = Integer.parseInt(post[1]);
    }

    public int getCity() {
        return city;
    }

    public int getLocal() {
        return local;
    }

    @Override
    public String toString() {
        return "PostalCode{" +
                "city=" + city +
                ", local=" + local +
                '}';
    }
}
