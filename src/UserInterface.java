
import java.util.*;

    public class UserInterface {
        private Dealership dealership;
        private Scanner scanner = new Scanner(System.in);

        public void display() {
            dealership = new DealershipFileManager().getDealership();
            boolean running = true;

            while (running) {
                displayMenu();
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1": searchByPrice(); break;
                    case "2": searchByMakeModel(); break;
                    case "3": searchByYear(); break;
                    case "4": searchByColor(); break;
                    case "5": searchByMileage(); break;
                    case "6": searchByType(); break;
                    case "7": showAllVehicles(); break;
                    case "8": if (authenticateEmployee()) addVehicle(); break;
                    case "9": if (authenticateEmployee()) removeVehicle(); break;
                    case "10": if (authenticateEmployee()) sellOrLeaseVehicle(); break;
                    case "11": if (authenticateEmployee()) showAllContracts(); break;
                    case "99": new DealershipFileManager().saveDealership(dealership); running = false; break;
                    default: System.out.println("Invalid option.");
                }
            }
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
        String phone = dealership.getPhone();

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
                "🔟  - Sell / Lease vehicle 📄\n" +
                "1️⃣1️⃣  - View all contracts ⬜\n" +
                "💥 99 - Quit 👋" + RESET);
        System.out.print(GREEN + "\n👉 Enter your choice: " + RESET);
    }

        private boolean authenticateEmployee() {
            System.out.print("Enter employee PIN: ");
            return scanner.nextLine().equals("6969");
        }

        private void showAllVehicles() {
            displayVehicles(dealership.getAllVehicles());
        }

        private void searchByPrice() {
            double min = promptDouble("Min price");
            double max = promptDouble("Max price");
            displayVehicles(dealership.getVehiclesByPrice(min, max));
        }

        private void searchByMakeModel() {
            String make = prompt("Make");
            String model = prompt("Model");
            displayVehicles(dealership.getVehiclesByMakeModel(make, model));
        }

        private void searchByYear() {
            int min = promptInt("Min year");
            int max = promptInt("Max year");
            displayVehicles(dealership.getVehiclesByYear(min, max));
        }

        private void searchByColor() {
            String color = prompt("Color");
            displayVehicles(dealership.getVehiclesByColor(color));
        }

        private void searchByMileage() {
            int min = promptInt("Min mileage");
            int max = promptInt("Max mileage");
            displayVehicles(dealership.getVehiclesByMileage(min, max));
        }

        private void searchByType() {
            String type = prompt("Vehicle type");
            displayVehicles(dealership.getVehiclesByType(type));
        }

        private void addVehicle() {
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

        private void removeVehicle() {
            int vin = promptInt("Enter VIN to remove");
            dealership.removeVehicle(vin);
            System.out.println("Vehicle removed (if it existed).");
        }

        private void sellOrLeaseVehicle() {
            int vin = promptInt("Enter VIN");
            Vehicle v = dealership.getVehicleByVin(vin);
            if (v == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            String name = prompt("Customer name");
            String email = prompt("Customer email");
            String date = prompt("Contract date (YYYYMMDD)");
            String type = prompt("Sale or Lease").toUpperCase();

            Contract contract = null;
            if (type.equals("SALE")) {
                boolean financed = prompt("Financed? (yes/no)").equalsIgnoreCase("yes");
                contract = new SalesContract(date, name, email, v, financed);
            } else if (type.equals("LEASE")) {
                if (v.getYear() < java.time.LocalDate.now().getYear() - 3) {
                    System.out.println("Can't lease vehicles older than 3 years.");
                    return;
                }
                contract = new LeaseContract(date, name, email, v);
            }

            if (contract != null) {
                new ContractFileManager().saveContract(contract);
                dealership.removeVehicle(vin);
                System.out.println("Contract saved and vehicle removed.");
            }
        }

        private void showAllContracts() {
            List<Contract> contracts = new ContractFileManager().loadContracts();
            for (Contract c : contracts) {
                Vehicle v = c.getVehicle();
                System.out.printf("%s | %s | %s | %s %s | VIN: %d | Total: $%.2f | Monthly: $%.2f\n",
                        c instanceof SalesContract ? "SALE" : "LEASE",
                        c.getDate(), c.getCustomerName(),
                        v.getMake(), v.getModel(), v.getVin(),
                        c.getTotalPrice(), c.getMonthlyPayment());
            }
        }

        private void displayVehicles(List<Vehicle> vehicles) {
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles found.");
                return;
            }

            for (Vehicle v : vehicles) {
                System.out.printf("%d | %d %s %s | %s | %s | %,d mi | $%,.2f\n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getType(), v.getColor(), v.getOdometer(), v.getPrice());
            }
        }

        private String prompt(String msg) {
            System.out.print(msg + ": ");
            return scanner.nextLine();
        }

        private int promptInt(String msg) {
            return Integer.parseInt(prompt(msg));
        }

        private double promptDouble(String msg) {
            return Double.parseDouble(prompt(msg));
        }
    }