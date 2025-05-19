public class SalesContract extends Contract {
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicle);
        this.isFinanced = isFinanced;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();
        double tax = price * 0.05;
        double recordingFee = 100.0;
        double processingFee = price < 10000 ? 295.0 : 495.0;
        return price + tax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) return 0.0;

        double total = getTotalPrice();
        double interestRate = getVehicle().getPrice() >= 10000 ? 0.0425 : 0.0525;
        int months = getVehicle().getPrice() >= 10000 ? 48 : 24;

        return (total * (1 + interestRate)) / months;
    }
}