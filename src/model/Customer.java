package model;

public class Customer extends User {
    private String address;
    private Cart cart;

    public Customer(String address, int id, String name, String email) {
        super(id, name, email);
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
}