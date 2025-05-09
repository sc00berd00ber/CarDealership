import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phoneNumber;
    private List<Vehicle> inventory;

    //CONSTRUCTOR
    public Dealership(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.inventory = new ArrayList<>();
    }

    //GETTERS
    public String getName(){return this.name;}
    public String getAddress(){return this.address;}
    public String getPhoneNumber(){return this.phoneNumber;}

    //SETTERS
    public void setName(String name){this.name = name;}
    public void setAddress(String address){this.address =address;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

    //SEARCH METHODS
    public List<Vehicle> findByPrice(double min, double max){
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> findByMakeAndModel(String make, String model){
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> findByYearRange(int min, int max){
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= min && v.getYear() <= max) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> findByColor(String color){
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> findByMileageRange(int min, int max){
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> findByVehicleType(String type){
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getVehicleType().equalsIgnoreCase(type)) {
                result.add(v);
            }
        }
        return result;
    }
    public List<Vehicle> listAllVehicles(){
        return inventory;
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    public void removeVehicle(int vin){
        inventory.removeIf(v -> v.getVin() == vin);
    }

}
