
import java.io.*;
import java.util.*;

public class ContractFileManager {
    private static final String CONTRACT_FILE = "contracts.csv";

    public void saveContract(Contract contract) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONTRACT_FILE, true))) {
            Vehicle v = contract.getVehicle();
            StringBuilder sb = new StringBuilder();

            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract;
                sb.append("SALE|")
                        .append(contract.getDate()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(v.getVin()).append("|").append(v.getYear()).append("|")
                        .append(v.getMake()).append("|").append(v.getModel()).append("|")
                        .append(v.getType()).append("|").append(v.getColor()).append("|")
                        .append(v.getOdometer()).append("|").append(v.getPrice()).append("|")
                        .append(v.getPrice() * 0.05).append("|")
                        .append("100.00|")
                        .append(v.getPrice() < 10000 ? "295.00" : "495.00").append("|")
                        .append(contract.getTotalPrice()).append("|")
                        .append(sc.isFinanced() ? "YES" : "NO").append("|")
                        .append(contract.getMonthlyPayment());
            } else if (contract instanceof LeaseContract) {
                sb.append("LEASE|")
                        .append(contract.getDate()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(v.getVin()).append("|").append(v.getYear()).append("|")
                        .append(v.getMake()).append("|").append(v.getModel()).append("|")
                        .append(v.getType()).append("|").append(v.getColor()).append("|")
                        .append(v.getOdometer()).append("|").append(v.getPrice()).append("|")
                        .append(v.getPrice() * 0.5).append("|")
                        .append(v.getPrice() * 0.07).append("|")
                        .append(contract.getTotalPrice()).append("|")
                        .append(contract.getMonthlyPayment());
            }

            writer.println(sb);
        } catch (IOException e) {
            System.out.println("Error writing to contracts file: " + e.getMessage());
        }
    }

    public List<Contract> loadContracts() {
        List<Contract> contracts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONTRACT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String type = parts[0];
                String date = parts[1];
                String name = parts[2];
                String email = parts[3];

                int vin = Integer.parseInt(parts[4]);
                int year = Integer.parseInt(parts[5]);
                String make = parts[6];
                String model = parts[7];
                String vehicleType = parts[8];
                String color = parts[9];
                int odometer = Integer.parseInt(parts[10]);
                double price = Double.parseDouble(parts[11]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                if (type.equals("SALE")) {
                    boolean financed = parts[16].equalsIgnoreCase("YES");
                    contracts.add(new SalesContract(date, name, email, vehicle, financed));
                } else if (type.equals("LEASE")) {
                    contracts.add(new LeaseContract(date, name, email, vehicle));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading contracts: " + e.getMessage());
        }
        return contracts;
    }
}
