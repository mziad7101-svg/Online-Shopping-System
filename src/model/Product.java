package model;

public class Product {
    private int productId;
    private String productName;
    private double priceOfProduct;
    private int stock;

    public Product(int productId, String productName, double priceOfProduct, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.priceOfProduct = priceOfProduct;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPriceOfProduct() {
        return priceOfProduct;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Name : " + productName + "\n"
                + "Id : " + productId + "\n"
                + "Price of Product : " + priceOfProduct + "\n"
                + "Stock : " + stock + "\n"
                + "The remaining in Stock : " + stock;
    }

    public boolean reduceStock(int quantity) {

        if (quantity <= stock && quantity > 0) {
            stock -= quantity;
            return true;
        } else
            return false;
    }

    public boolean hasStock(int quantity) {
        return quantity > 0 && quantity <= stock;
    }

}
