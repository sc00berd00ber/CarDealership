import java.util.Date;

 public abstract class BusinessContract {
    private Date date;
    private String customerName;
    private String customerEmail;
    private boolean isSold;
    private double totalPrice;
    private double monthlyPayment;
    Vehicle vehicle;

    BusinessContract(Date date, Vehicle vehicle,
                     String customerName,
                     String customerEmail,
                     boolean isSold,
                     double totalPrice) {
        this.date = date;
        this.vehicle = vehicle;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.isSold = isSold;
        this.totalPrice = totalPrice;
    }

     @Override
     public String toString() {
         return String.format("%s " +
                 "%s " +
                 "%s " +
                 "%s " +
                 "%.2f " +
                 "%.2f ",this.date, this.customerName, this.customerEmail, this.isSold, this.totalPrice, this.monthlyPayment);
     }

     public Date getDate() {
         return date;
     }
     public String getCustomerName() {
         return customerName;
     }
     public String getCustomerEmail() {
         return customerEmail;
     }
     public boolean isSold() {
         return isSold;
     }
     abstract double getTotalPrice();
     abstract double getMonthlyPayment();

//     public static void main(String[] args) {
//        BusinessContract bc = new BusinessContract(new Date(), "Nat Tessema", "him@himothy.com", true, 23000, 199) {
//        };
//        System.out.println(bc);
//    }
}
