package Payment;
public class CashOnDelivery implements Payment {
    @Override
    public boolean processPayment(double price) {
        return true ;
    }
}
