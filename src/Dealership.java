import java.util.ArrayList;

public class Dealership {
    private final String name;
    private final String address;
    private final String phoneNumber;

    //CONSTRUCTOR
    public Dealership(String name,
                      String address,
                      String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    ArrayList<Vehicle> inventory = new ArrayList<>();


    public void findByPrice(double min, double max){}
    public void findByMakeAndModel(String make, String model){}
    public void findByYearRange(int min, int max){}
    public void findByColor(String color){}
    public void findByMileageRange(int min, int max){}
    public void findByVehicleType(String type){}
    public void listAllVehicles(){}
    public void addVehicle(){}
    public void removeVehicle(){}

}
