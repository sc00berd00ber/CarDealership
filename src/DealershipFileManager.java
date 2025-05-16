
import java.io.*;
import java.util.*;

public class DealershipFileManager {
    private static final String FILE_NAME = "inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String[] dealershipInfo = reader.readLine().split("\\|");
            dealership = new Dealership(dealershipInfo[0], dealershipInfo[1], dealershipInfo[2]);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                Vehicle vehicle = new Vehicle(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        parts[2], parts[3], parts[4],
                        parts[5],
                        Integer.parseInt(parts[6]),
                        Double.parseDouble(parts[7])
                );
                dealership.addVehicle(vehicle);
            }

        } catch (IOException e) {
            System.out.println("Error loading dealership: " + e.getMessage());
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());

            for (Vehicle v : dealership.getAllVehicles()) {
                writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getType(), v.getColor(), v.getOdometer(), v.getPrice());
            }

        } catch (IOException e) {
            System.out.println("Error saving dealership: " + e.getMessage());
        }
    }
}
