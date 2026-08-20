import model.ShoppingSystem;
import java.util.Scanner;
import model.Product;
import model.Customer;
import model.Order;
import model.CartItem;
import Payment.CreditCardPayment;
import Payment.CashOnDelivery;
public class Main { 
    public static void main(String [] args) {

        Scanner input = new Scanner(System.in);
        ShoppingSystem shoppingSystem = new ShoppingSystem();

        Product p1 = new Product(1, "Laptop", 50000, 5);
        Product p2 = new Product(2, "Mouse", 500, 20);
        Product p3 = new Product(3, "Keyboard", 1000, 15);
        Product p4 = new Product(4, "Headphones", 2000, 10);
        Product p5 = new Product(5, "Monitor", 12000, 8);
        Product p6 = new Product(6, "Webcam", 1500, 12);
        Product p7 = new Product(7, "USB Cable", 200, 30);
        Product p8 = new Product(8, "Charger", 800, 18);
        Product p9 = new Product(9, "Phone", 25000, 7);
        Product p10 = new Product(10, "Tablet", 18000, 6);
        Product p11 = new Product(11, "Smart Watch", 5000, 14);
        Product p12 = new Product(12, "Speaker", 3000, 10);
        Product p13 = new Product(13, "Microphone", 2500, 9);
        Product p14 = new Product(14, "Printer", 7000, 4);
        Product p15 = new Product(15, "SSD", 3500, 16);
        Product p16 = new Product(16, "Hard Drive", 2800, 11);
        Product p17 = new Product(17, "RAM", 2200, 25);
        Product p18 = new Product(18, "Power Bank", 1500, 20);
        Product p19 = new Product(19, "Router", 1800, 13);
        Product p20 = new Product(20, "Game Controller", 2500, 8);

        shoppingSystem.addProduct(p1);
        shoppingSystem.addProduct(p2);
        shoppingSystem.addProduct(p3);
        shoppingSystem.addProduct(p4);
        shoppingSystem.addProduct(p5);
        shoppingSystem.addProduct(p6);
        shoppingSystem.addProduct(p7);
        shoppingSystem.addProduct(p8);
        shoppingSystem.addProduct(p9);
        shoppingSystem.addProduct(p10);
        shoppingSystem.addProduct(p11);
        shoppingSystem.addProduct(p12);
        shoppingSystem.addProduct(p13);
        shoppingSystem.addProduct(p14);
        shoppingSystem.addProduct(p15);
        shoppingSystem.addProduct(p16);
        shoppingSystem.addProduct(p17);
        shoppingSystem.addProduct(p18);
        shoppingSystem.addProduct(p19);
        shoppingSystem.addProduct(p20);

        System.out.println("Welcome to Online Shopping System");

        for(int i = 0 ; i <=15 ; i++ ){
            System.out.print("*");
        }

        System.out.println();

        System.out.println("Enter your name:");
        String name = input.nextLine();

        System.out.println("Enter your Address:");
        String address = input.nextLine();

        int customerId = 1;
        String email;

        while(true){
            System.out.println("Enter your Email:");
            email = input.nextLine();

            if(email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
                break;
            }

            System.out.println("Invalid email!");
            System.out.println("Please enter a valid email.");
        }

        Customer customer = new Customer(address, customerId++, name, email);
        shoppingSystem.addCustomer(customer);

        int addAnother;
        boolean productAdded = false;

        do{
            System.out.println("Enter product name:");
            String productName = input.nextLine();

            Product product = shoppingSystem.findProduct(productName);

            if(product == null){
                System.out.println("The product is not available.");

                if(!productAdded){
                    System.out.println("You must add at least one product.");
                    addAnother = 1;
                    continue;
                }
            }
            else{
                System.out.println("Product found.");
                System.out.println("Available stock: " + product.getStock());
                System.out.println("Enter quantity:");

                int quantity = input.nextInt();
                input.nextLine();

                if(customer.getCart().addProductToCart(product, quantity)){
                    System.out.println("Product added to cart.");
                    productAdded = true;
                }
                else{
                    System.out.println("Not enough stock or invalid quantity.");

                    if(!productAdded){
                        addAnother = 1;
                        continue;
                    }
                }
            }

            System.out.println("Do you want to add another product?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            addAnother = input.nextInt();
            input.nextLine();

        }while(addAnother == 1 || !productAdded);

        int orderId = 1;

        Order order = new Order(orderId++, customer);
        shoppingSystem.addOrder(order);

        System.out.println("========== ORDER SUMMARY ==========");

        System.out.println("Order Id: " + order.getOrderId());

        System.out.println(customer);

        System.out.println("Products:");

        for(CartItem item : order.getCartItems()){
            System.out.println(
                item.getProduct().getProductName()
                + " - Quantity: "
                + item.getQuantity()
                + " - Price: "
                + item.getProduct().getPriceOfProduct()
            );
        }

        System.out.println("Total Price: " + order.calculateTotalPrice());
        System.out.println("Status: " + order.getOrderStatus());

        System.out.println("Choose payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash On Delivery");

        int choice = input.nextInt();
        input.nextLine();

        switch(choice){

            case 1:
                System.out.println("Enter your card number:");
                String cardNumber = input.nextLine();

                CreditCardPayment creditCardPayment =
                        new CreditCardPayment(cardNumber);

                boolean creditResult =
                        order.processPayment(creditCardPayment);

                if(creditResult){
                    System.out.println("Payment successful.");
                }
                else{
                    System.out.println("Payment failed.");
                }

                break;

            case 2:
                CashOnDelivery cashOnDelivery =
                        new CashOnDelivery();

                boolean cashResult =
                        order.processPayment(cashOnDelivery);

                if(cashResult){
                    System.out.println("Payment successful.");
                }
                else{
                    System.out.println("Payment failed.");
                }

                break;

            default:
                System.out.println("Invalid payment choice.");
        }

        System.out.println("========== FINAL ORDER ==========");

        System.out.println("Order Id: " + order.getOrderId());
        System.out.println(customer);

        System.out.println("Products:");

        for(CartItem item : order.getCartItems()){
            System.out.println(
                item.getProduct().getProductName()
                + " - Quantity: "
                + item.getQuantity()
            );
        }

        System.out.println("Total Price: " + order.calculateTotalPrice());
        System.out.println("Payment Status: " + order.getOrderStatus());
        System.out.println("=================================");
        input.close();
    }
} 