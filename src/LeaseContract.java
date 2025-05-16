public class LeaseContract extends Contract {

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();
        double endingValue = price * 0.5;
        double leaseFee = price * 0.07;
        return endingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double total = getTotalPrice();
        return (total * 1.04) / 36; // 4% over 36 months
    }
}