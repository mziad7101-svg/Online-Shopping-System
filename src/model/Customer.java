package model;

import java.util.*;

public class Customer extends User {
    private String address;
    private Cart cart;
    private Deque<Product> recentlySearchedProducts = new ArrayDeque<>();
    private ArrayList<Order> orderHistoryArrayList = new ArrayList<>();

    public Customer(String address, int id, String name, String email) {
        super(id, name, email);
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }
        this.address = address;
        this.cart = new Cart();
    }

    public String getAddress() {
        return address;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n"
                + "Email: " + getEmail() + "\n"
                + "Address: " + address;
    }

    public void addRecentlySearchedProduct(Product product) {
        if (product == null) {
            return;
        }
        recentlySearchedProducts.removeIf(p -> p.getProductId() == product.getProductId());
        recentlySearchedProducts.addLast(product);
        if (recentlySearchedProducts.size() > 5) {
            recentlySearchedProducts.pollFirst();
        }
    }

    public Deque<Product> getRecentlySearchedProduct() {
        return new ArrayDeque<>(recentlySearchedProducts);
    }

    public void addOrderToHistory(Order order) {
        if (order == null) {
            return;
        }
        if (order.getCustomer().getId() != this.getId()) {
            return;
        }
        orderHistoryArrayList.add(order);
    }

    public ArrayList<Order> getOrderHistoryArrayList() {
        return new ArrayList<>(orderHistoryArrayList);
    }
}
