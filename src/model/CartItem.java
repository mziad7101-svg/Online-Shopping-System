package model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product p, int quantity) {
        this.product = p;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
}