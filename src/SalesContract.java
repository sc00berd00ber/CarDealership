
import java.util.Date;

public class SalesContract extends BusinessContract {
    private double salesTaxAmount = 0.05;
    private double recordingFee = 100.00;
    private double processingFee = 295.00;
    private boolean isFinanced = true; //the NO LOAN OPTION?
    SalesContract(
            /* ******** BASE ********* */
            Vehicle vehicle,
            Date date,
            String customerName,
            String customerEmail,
            boolean isSold,
            double totalPrice,
            /* ******** NEW ********* */
            double salesTaxAmount,
            double recordingFee,
            double processingFee,
            boolean isFinanced
    ){
        super(vehicle, date, customerName, customerEmail, isSold, totalPrice);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinanced = isFinanced;
    }
    public String toString(){
        return super.toString() + String.format("""
                Sales Tax Amount: %.2f
                Recording Fee:    %.2f
                Processing Fee:   %.2f
                Is Financed:      %s
                """,
                this.salesTaxAmount,
                this.recordingFee,
                this.processingFee,
                this.isFinanced ? "YES" : "NO"
        );
    }
    public double getSalesTaxAmount(){
        return this.salesTaxAmount;
    }
    public double getRecordingFee(){
        return this.recordingFee;
    }
    public double getProcessingFee(){
        return this.processingFee;
    }
    public boolean isFinanced(){
        return this.isFinanced;
    }

    /* Monthly payment (if financed) based on:
        • All loans are at 4.25% for 48 months if the price is $10,000 or more
        • Otherwise they are at 5.25% for 24 month
    */
    public double getMonthlyPayment(){
        return 0.0; //STUB
    }

    /* It is possible that getMonthlyPayment() would return
        0 if they chose the NO loan option.
     */
    public double getTotalPrice(){
        return 0.0; //STUB
    }
}
