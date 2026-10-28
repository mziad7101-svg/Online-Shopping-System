package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Stack;

public class Cart {
    private ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    private Stack<CartAction> undo = new Stack<>();
    private Stack<CartAction> redo = new Stack<>();

    public boolean addProductToCart(Product p, int quantity) {
        if (p == null) {
            return false;
        }
        if (quantity <= 0) {
            return false;
        }
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductId() == p.getProductId()) {
                int remaining = p.getStock() - cartItem.getQuantity();
                if (quantity > remaining) {
                    return false;
                }
                cartItem.increaseQuantity(quantity);
                CartAction action = new CartAction(p, quantity, true);
                undo.push(action);
                redo.clear();
                return true;
            }
        }
        if (quantity > p.getStock()) {
            return false;
        }
        CartItem cartItem = new CartItem(p, quantity);
        cartItems.add(cartItem);
        CartAction action = new CartAction(p, quantity, false);
        undo.push(action);
        redo.clear();
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

    public List<CartItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }

    public boolean undoLastAction() {
        if (undo.isEmpty()) {
            return false;
        }
        CartAction action = undo.pop();
        if (action.wasAlreadyInCart) {
            boolean found = false;
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getProductId() == action.product.getProductId()) {

                    if (cartItem.getQuantity() < action.quantityChanged) {
                        undo.push(action);
                        return false;
                    }

                    cartItem.decreaseQuantity(action.quantityChanged);
                    found = true;
                    break;
                }
            }
            if (!found) {
                undo.push(action);
                return false;
            }
        } else {
            if (!cartItems.removeIf(cartItem -> cartItem.getProduct().getProductId() == action.product.getProductId())) {
                undo.push(action);
                return false;
            }
        }
        redo.push(action);
        return true;
    }

    public boolean redoLastAction() {
        if (redo.isEmpty()) {
            return false;
        }

        CartAction action = redo.pop();

        if (action.wasAlreadyInCart) {
            boolean found = false;

            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getProductId() == action.product.getProductId()) {

                    int remaining = action.product.getStock() - cartItem.getQuantity();

                    if (action.quantityChanged > remaining) {
                        redo.push(action);
                        return false;
                    }

                    cartItem.increaseQuantity(action.quantityChanged);
                    found = true;
                    break;
                }
            }

            if (!found) {
                redo.push(action);
                return false;
            }

        } else {
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getProductId() == action.product.getProductId()) {
                    redo.push(action);
                    return false;
                }
            }

            if (action.quantityChanged > action.product.getStock()) {
                redo.push(action);
                return false;
            }

            CartItem cartItem = new CartItem(action.product, action.quantityChanged);

            cartItems.add(cartItem);
        }

        undo.push(action);
        return true;
    }

    public void clearCart() {
        cartItems.clear();
        undo.clear();
        redo.clear();
    }
}
