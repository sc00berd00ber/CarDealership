import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);


    public void display(){
        init();
        boolean running = true;
        while(running){
            displayMenu();
            String choice = scanner.nextLine();
            switch (choice){
                case "1": displayVehicles(dealership.findByPrice(promptDouble("Min price"), promptDouble("Max price"))); break;
                case "2": displayVehicles(dealership.findByMakeAndModel(prompt("Make"), prompt("Model"))); break;
                case "3": displayVehicles(dealership.findByYearRange(promptInt("Min year"), promptInt("Max year"))); break;
                case "4": displayVehicles(dealership.findByColor(prompt("Color"))); break;
                case "5": displayVehicles(dealership.findByMileageRange(promptInt("Min mileage"), promptInt("Max mileage"))); break;
                case "6": displayVehicles(dealership.findByVehicleType(prompt("Type"))); break;
                case "7": displayVehicles(dealership.listAllVehicles()); break;
                case "8":
                    if (authenticateEmployee()) addVehicle();
                    break;
                case "9":
                    if (authenticateEmployee()) removeVehicle();
                    break;
                case "99":
                    new DealershipFileManager().saveDealership(dealership);
                    running = false;
                    break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        dealership = dfm.getDealership();
    }
    //MAIN MENU
    public void displayMenu() {
        final String RESET = "\033[0m";
        final String BOLD = "\033[1m";
        final String CYAN = "\033[36m";
        final String YELLOW = "\033[33m";
        final String GREEN = "\033[32m";

        String name = dealership.getName();
        String address = dealership.getAddress();
        String phone = dealership.getPhoneNumber();

        // Limit dealership name to 35 characters for box formatting
        if (name.length() > 35) {
            name = name.substring(0, 32) + "...";
        }

        System.out.println("\n" + CYAN + "╔═════════════════════════════════╗");
        System.out.printf (" %s%-58s%s \n", "", "🚗 Welcome to " + BOLD + name + RESET + CYAN, "");
        System.out.println("╠═════════════════════════════════╣" + RESET);
        System.out.printf ("  📍 Address: %s\n", address);
        System.out.printf ("  ☎️  Phone:   %s\n", phone);
        System.out.println(CYAN + "╚═════════════════════════════════╝" + RESET);

        System.out.println("\n" + BOLD + "--- 🛠️ Dealership Menu ---" + RESET);
        System.out.println(YELLOW +
                "1️⃣  - Find vehicles within a price range 💰\n" +
                "2️⃣  - Find vehicles by make / model 🚘\n" +
                "3️⃣  - Find vehicles by year range 📆\n" +
                "4️⃣  - Find vehicles by color 🎨\n" +
                "5️⃣  - Find vehicles by mileage range 🛣️\n" +
                "6️⃣  - Find vehicles by type (car, truck, SUV, van) 🚙\n" +
                "7️⃣  - List ALL vehicles 📋\n" +
                "8️⃣  - Add a vehicle ➕\n" +
                "9️⃣  - Remove a vehicle ❌\n" +
                "💥 99 - Quit 👋" + RESET);
        System.out.print(GREEN + "\n👉 Enter your choice: " + RESET);
    }

//    public void displayMenu(){
//
//        System.out.println("\n===============================");
//        System.out.println("  Welcome to " + dealership.getName());
//        System.out.println("  Address: " + dealership.getAddress());
//        System.out.println("  Phone:   " + dealership.getPhoneNumber());
//        System.out.println("===============================\n");
//        System.out.println("\n--- Dealership Menu ---");
//        System.out.println(
//                "1 - Find vehicles within a price range\n" +
//                "2 - Find vehicles by make / model\n" +
//                "3 - Find vehicles by year range\n" +
//                "4 - Find vehicles by color\n" +
//                "5 - Find vehicles by mileage range\n" +
//                "6 - Find vehicles by type (car, truck, SUV, van)\n" +
//                "7 - List ALL vehicles\n" +
//                "8 - Add a vehicle\n" +
//                "9 - Remove a vehicle\n" +
//                "99 - Quit\n" +
//                "Enter your choice: ");
//    }

    //DISPLAY FORMATTING
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.printf("%-10s | %-4s | %-10s | %-10s | %-7s | %-10s | %-10s | %-10s\n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Vehicle v : vehicles) {
            System.out.printf("%-10d | %-4d | %-10s | %-15s | %-7s | %-10s | %,10d | $%,9.2f\n",
                    v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(),
                    v.getColor(), v.getOdometer(), v.getPrice());
        }
    }

    private void addVehicle(){
        int vin = promptInt("VIN");
        int year = promptInt("Year");
        String make = prompt("Make");
        String model = prompt("Model");
        String type = prompt("Type");
        String color = prompt("Color");
        int odometer = promptInt("Odometer");
        double price = promptDouble("Price");
        dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));
        System.out.println("Vehicle added.");
    }
    private void removeVehicle(){
        int vin = promptInt("Enter VIN # to remove: ");
        dealership.removeVehicle(vin);
        System.out.printf("Vehicle #%d removed.", vin);
    }

    private boolean authenticateEmployee() {
        System.out.print("Enter employee PIN: ");
        String input = scanner.nextLine();
        if (input.equals("6969")) {
            return true;
        } else {
            System.out.println("Access denied. Invalid PIN.");
            return false;
        }
    }

    //COLLECTS USER INPUT
    private String prompt(String msg) {
        System.out.print(msg + ": ");
        return scanner.nextLine();
    }

    private int promptInt(String msg) {
        System.out.print(msg + ": ");
        return Integer.parseInt(scanner.nextLine());
    }

    private double promptDouble(String msg) {
        System.out.print(msg + ": ");
        return Double.parseDouble(scanner.nextLine());
    }
}
