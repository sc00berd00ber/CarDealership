import java.util.Date;

public class SalesContract extends BusinessContract {
    private double salesTax = 0.05;
    private double recordingFee = 100;
    private double processingFee = 295.0;
    private boolean isFinanced = true;

    SalesContract(Date date,
                  String customerName,
                  String customerEmail,
                  boolean isSold,
                  double totalPrice,
                  //------NEW--------
                  double salesTax,
                  double recordingFee,
                  double processingFee,
                  boolean isFinanced){
        super(date, customerName, customerEmail, isSold, totalPrice);
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee =  processingFee;
        this.isFinanced = isFinanced;
    }

    public double getSalesTax() {
        return this.salesTax;
    }
    public double getRecordingFee() {
        return this.recordingFee;
    }
    public double getProcessingFee() {
        return this.processingFee;
    }
    public boolean isFinanced() {
        return this.isFinanced;
    }

    /*
                • Monthly payment (if financed) based on:
                • All loans are at 4.25% for 48 months if the price is $10,000 or more
                • Otherwise they are at 5.25% for 24 month
                 */
    public double getMonthlyPayment(){
        return 0.0;
    }
    public double getTotalPrice(){
        return 0.0;
    }
}
