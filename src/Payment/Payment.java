package Payment;

@FunctionalInterface
public interface Payment {

    boolean processPayment(double price);

}