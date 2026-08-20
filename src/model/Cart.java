package model;
import java.util.ArrayList;
public class Cart {
    private ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    public boolean addProductToCart(Product p, int quantity) {
        if (quantity <= 0) {
            return false;
        }
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductId() == p.getProductId()) {
                int newQuantity =
                        cartItem.getQuantity() + quantity;
                if (newQuantity > p.getStock()) {
                    return false;
                }
                cartItem.increaseQuantity(quantity);
                return true;
            }
        }
        if (quantity > p.getStock()) {
            return false;
        }
        CartItem cartItem = new CartItem(p, quantity);
        cartItems.add(cartItem);

        return true;
    }
    public double calculateTotalPrice() {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getProduct().getPriceOfProduct()
                    * cartItem.getQuantity();
        }
        return total;
    }
    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }
}