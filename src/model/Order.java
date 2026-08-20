package model;
import java.util.ArrayList;
import Payment.Payment;
public class Order {
    private int orderId;
    private Customer customer;
    private ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    private OrderStatus status;
    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.status = OrderStatus.PENDING;
        this.cartItems = new ArrayList<>(customer.getCart().getCartItems());
    }
    public double calculateTotalPrice() {

        double total = 0;

        for (CartItem item : cartItems) {

            total += item.getProduct().getPriceOfProduct()
                    * item.getQuantity();
        }

        return total;
    }
    public boolean processPayment(Payment payment) {
        for (CartItem item : cartItems) {

            if (!item.getProduct().hasStock(item.getQuantity())) {

                status = OrderStatus.PENDING;
                return false;
            }
        }
        if (payment.processPayment(calculateTotalPrice())) {

            // Reduce stock only after successful payment
            for (CartItem item : cartItems) {

                item.getProduct().reduceStock(item.getQuantity());
            }

            status = OrderStatus.PAID;
            return true;
        }

        status = OrderStatus.PENDING;
        return false;
    }
    public int getOrderId() {
        return orderId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public OrderStatus getOrderStatus() {
        return status;
    }
    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }
}