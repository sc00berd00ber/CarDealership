import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {

    String fileName = "inventory.csv";

    //LOAD FILE
    public List<Vehicle> load() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().startsWith("date"))
                    continue;
                vehicles.add();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Couldn't read file!" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }

    //SAVE FILE
    public void save( ) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(tx.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}
