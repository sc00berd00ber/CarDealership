import java.util.ArrayList;
import java.util.Date;

public class ContractFileManager {

    public ArrayList<BusinessContract> load() {
        ArrayList<BusinessContract> list = new ArrayList<>();

        String line = "";
        BusinessContract bc = parseContract(line);
        list.add(bc);

        return list;
    }
    public void save(ArrayList<BusinessContract> list) {
        for (BusinessContract c : list){
            System.out.println("SAVING: " + c);
            String line = getContractString(c);
            System.out.println(line);
        }
    }
    BusinessContract parseContract(String data){
        return new SalesContract(new Date(),
                new Vehicle(123,2019,"Toyota", "Camry", "Sedan", "Black", 0, 25000),
                "Nat Tessema",
                "him@himothy.com",
                true,
                25000,
                250,
                0,
                0,
                true);
    }
    String getContractString(BusinessContract bs){
        return "";
    }
}
