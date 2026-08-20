package model;
import java.util.ArrayList ;
public class ShoppingSystem {
    private ArrayList<Product> products = new ArrayList<Product>() ;
        private ArrayList<Customer> customers = new ArrayList<Customer>() ;
    private ArrayList<Order> orders = new ArrayList<Order>() ;
public void addProduct(Product product){
    products.add(product) ;
}
public void addCustomer(Customer customer){
    customers.add(customer) ;
}
public void addOrder(Order order){
    orders.add(order) ;
}
public Product findProduct(String productName){
    for( Product p : products){
if(p.getProductName().equals(productName)) {
return p ;
}
    }
    return null ;
}
}
