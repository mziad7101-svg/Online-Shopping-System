package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Payment.Payment;

public class Order {
    private int orderId;
    private Customer customer;
    private ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    private OrderStatus status;

    public Order(int orderId, Customer customer) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive.");
        }

        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        this.orderId = orderId;
        this.customer = customer;
        this.status = OrderStatus.PENDING;
        this.cartItems = new ArrayList<>();
        for (CartItem item : customer.getCart().getCartItems()) {
            CartItem newItem = new CartItem(
                    item.getProduct(),
                    item.getQuantity()
            );
            this.cartItems.add(newItem);
        }
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
        if (payment == null || status != OrderStatus.PENDING) {
            return false;
        }
        if (cartItems.isEmpty()) {
            return false;
        } else {
            for (CartItem item : cartItems) {

                if (!item.getProduct().hasStock(item.getQuantity())) {

                    status = OrderStatus.PENDING;
                    return false;
                }
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

    public void setOrderStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Order status cannot be null.");
        }

        this.status = status;
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

    public List<CartItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Order Id: ").append(orderId).append("\n");
        sb.append("Name: ").append(customer.getName()).append("\n");
        sb.append("Email: ").append(customer.getEmail()).append("\n");
        sb.append("Address: ").append(customer.getAddress()).append("\n");
        sb.append("Products:\n");

        for (CartItem item : cartItems) {
            sb.append(item.getProduct().getProductName())
                    .append(" - Quantity: ")
                    .append(item.getQuantity())
                    .append(" - Price: ")
                    .append(item.getProduct().getPriceOfProduct())
                    .append("\n");
        }

        sb.append("Total Price: ").append(calculateTotalPrice()).append("\n");
        sb.append("Status: ").append(getOrderStatus());

        return sb.toString();
    }
}
