package model;

public class CartAction {
    Product product;
    int quantityChanged;
    boolean wasAlreadyInCart;

    public CartAction(Product product, int quantityChanged, boolean wasAlreadyInCart) {
        this.product = product;
        this.quantityChanged = quantityChanged;
        this.wasAlreadyInCart = wasAlreadyInCart;
    }

}
