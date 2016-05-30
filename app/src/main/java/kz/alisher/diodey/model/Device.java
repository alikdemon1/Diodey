package kz.alisher.diodey.model;

/**
 * Created by Alikdemon on 30.05.2016.
 */
public class Device {
    private String name;
    private String address;

    public Device(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
