public class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    //CONSTRUCTOR
    public Vehicle(int vin, int year, String make, String model,
                   String vehicleType, String color, int odometer, double price){
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    //GETTERS
    public int getVin(){return this.vin;}
    public int getYear(){return this.year;}
    public String getMake(){return this.make;}
    public String getModel(){return this.model;}
    public String getVehicleType(){return this.vehicleType;}
    public String getColor(){return this.color;}
    public int getOdometer(){return this.odometer;}
    public double getPrice(){return this.price;}

    //SETTERS
    public void setVin(int vin) {this.vin = vin;}
    public void setYear(int year) {this.year = year;}
    public void setMake(String make) {this.make = make;}
    public void setModel(String model) {this.model = model;}
    public void setVehicleType(String vehicleType) {this.vehicleType = vehicleType;}
    public void setColor(String color) {this.color = color;}
    public void setOdometer(int odometer) {this.odometer = odometer;}
    public void setPrice(double price) {this.price = price;}

}
