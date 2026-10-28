package model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product p, int quantity) {
        if (p == null){
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (quantity<=0){
            throw new IllegalArgumentException("Quantity must be positive.");
        }
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
        if (quantity <= 0){
            return;
        }
        this.quantity = Math.addExact(this.quantity, quantity);
    }
    public void decreaseQuantity(int quantity) {
        if (quantity <= 0|| quantity > this.quantity) {
            return;
        }
        this.quantity -= quantity;
    }
}
