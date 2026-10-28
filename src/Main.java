import model.*;

import java.util.*;

import Payment.CreditCardPayment;
import Payment.CashOnDelivery;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ShoppingSystem shoppingSystem = new ShoppingSystem();
        int nextCustomerId = 1;
        int nextOrderId = 1;
        Customer customer = null;
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

        for (int i = 0; i <= 15; i++) {
            System.out.print("*");
        }

        System.out.println();
        while (true) {
            customer = null;
            while (true) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                if (input.hasNextInt()) {

                    int choice = input.nextInt();
                    input.nextLine();
                    switch (choice) {
                        case 1:
                            System.out.println("Enter your name:");
                            String name;
                            while (true) {
                                name = input.nextLine();
                                if (!name.trim().matches("[a-zA-Z ]+")) {
                                    System.out.println("Invalid name. Enter letters only:");
                                } else {
                                    break;
                                }
                            }
                            System.out.println("Enter your Address:");
                            String address;
                            while (true) {
                                address = input.nextLine();
                                if (address.trim().isEmpty()) {
                                    System.out.println("Enter your Address:");
                                } else {
                                    break;
                                }
                            }
                            String email;

                            while (true) {
                                System.out.println("Enter your Email:");
                                email = input.nextLine();

                                if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                                    break;
                                }

                                System.out.println("Invalid email!");
                                System.out.println("Please enter a valid email.");
                            }
                            while (true) {
                                customer = new Customer(address, nextCustomerId, name, email);

                                try {
                                    shoppingSystem.addCustomer(customer);
                                    System.out.println("Registration successful.");
                                    nextCustomerId++;
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("This email is already registered.");
                                    System.out.println("Please enter a different email:");
                                    while (true) {
                                        email = input.nextLine();

                                        if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                                            break;
                                        }

                                        System.out.println("Invalid email!");
                                        System.out.println("Please enter a valid email.");
                                    }
                                }

                            }
                            break;
                        case 2:
                            System.out.println("Enter your Email:");
                            String emailForLogin = input.nextLine();
                            customer = shoppingSystem.findCustomerByEmail(emailForLogin);
                            if (customer == null) {
                                System.out.println("Email cannot be find");
                            } else {
                                System.out.println("Logged in Successfully.");
                            }
                            break;
                        case 3:
                            return;
                        default:
                            System.out.println("Enter a valid number 1 or 2 or 3");
                    }
                    if (customer != null) {
                        break;
                    }
                } else {
                    System.out.println("Enter a Valid Number");
                    input.nextLine();

                }
            }

            boolean startShopping = false;

            while (!startShopping) {

                System.out.println("1. View products sorted by price");
                System.out.println("2. View products sorted by name");
                System.out.println("3. Search for a product");
                System.out.println("4. View Top 3 Most Expensive Products");
                System.out.println("5. View Top 3 Cheapest Products");
                System.out.println("6. View Recently Searched Products");
                System.out.println("7. View Order History");
                System.out.println("8. Start shopping");

                if (input.hasNextInt()) {
                    int choice2 = input.nextInt();
                    input.nextLine(); // Consume the leftover newline

                    switch (choice2) {
                        case 1:
                            for (Product p : shoppingSystem.getProductsSortedByPrice()) {
                                System.out.println(p.getProductName() + " | "
                                        + p.getPriceOfProduct() + " | Stock: " + p.getStock());
                            }
                            break;

                        case 2:
                            for (Product p : shoppingSystem.getProductsSortedByName()) {
                                System.out.println(p.getProductName() + " | "
                                        + p.getPriceOfProduct() + " | Stock: " + p.getStock());
                            }
                            break;

                        case 3:
                            System.out.println("Enter Product Name:");
                            String productName = input.nextLine();

                            Product p = shoppingSystem.findProduct(productName);

                            if (p == null) {
                                System.out.println("Product not found.");
                            } else {
                                System.out.println(p.getProductName() + " | "
                                        + p.getPriceOfProduct() + " | Stock: "
                                        + p.getStock());
                                customer.addRecentlySearchedProduct(p);
                            }
                            break;
                        case 4:
                            for (Product product : shoppingSystem.getTop3ExpensiveProducts()) {
                                System.out.println(product.getProductName() + " | "
                                        + product.getPriceOfProduct() + " | Stock: "
                                        + product.getStock());
                            }
                            break;

                        case 5:
                            for (Product product : shoppingSystem.getTop3CheapestProducts()) {
                                System.out.println(product.getProductName() + " | "
                                        + product.getPriceOfProduct() + " | Stock: "
                                        + product.getStock());
                            }
                            break;
                        case 6:
                            Deque<Product> arrayDeque = customer.getRecentlySearchedProduct();
                            if (arrayDeque.isEmpty()) {
                                System.out.println("No recently searched products.");
                            } else {
                                Iterator<Product> it = arrayDeque.descendingIterator();
                                while (it.hasNext()) {
                                    Product pp = it.next();
                                    System.out.println(pp.getProductName() + " | " + pp.getPriceOfProduct() + " | Stock:" + pp.getStock());
                                }
                            }
                            break;
                        case 7:
                            ArrayList<Order> orderArrayList = customer.getOrderHistoryArrayList();
                            if (orderArrayList.isEmpty()) {
                                System.out.println("Order history is empty.");
                            } else {
                                for (Order order : orderArrayList) {
                                    System.out.println(order.toString());
                                }
                            }
                            break;

                        case 8:
                            startShopping = true;
                            break;

                        default:
                            System.out.println("Choose a number from 1 to 7.");
                    }

                } else {
                    System.out.println("Enter a valid number.");
                    input.nextLine(); // Consume invalid input
                }
            }


            int addAnother;
            boolean productAdded = false;

            do {
                System.out.println("Enter product name:");
                String productName = input.nextLine();

                Product product = shoppingSystem.findProduct(productName);

                if (product == null) {
                    System.out.println("The product is not available.");

                    if (!productAdded) {
                        System.out.println("You must add at least one product.");
                        addAnother = 1;
                        continue;
                    }
                } else {
                    System.out.println("Product found.");
                    System.out.println("Available stock: " + product.getStock());
                    System.out.println("Enter quantity:");
                    int quantity = 0;
                    while (true) {
                        if (input.hasNextInt()) {
                            quantity = input.nextInt();
                            input.nextLine();
                            if (quantity > 0) {
                                break;
                            } else {
                                System.out.println("Enter Positive Quantity");
                            }
                        } else {
                            System.out.println("Please enter a valid number.");
                            input.nextLine();
                            System.out.println("Enter quantity again:");
                        }
                    }
                    if (customer.getCart().addProductToCart(product, quantity)) {
                        System.out.println("Product added to cart.");
                        productAdded = true;
                    } else {
                        System.out.println("Not enough stock or invalid quantity.");

                        if (!productAdded) {
                            addAnother = 1;
                            continue;
                        }
                    }
                }

                System.out.println("Do you want to add another product?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                while (true) {
                    if (input.hasNextInt()) {
                        addAnother = input.nextInt();
                        input.nextLine();
                        if (addAnother == 1 || addAnother == 2) {
                            break;
                        } else {
                            System.out.println("Please enter 1 or 2.");
                        }
                    } else {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }
                }
            } while (addAnother == 1 || !productAdded);
            if (customer.getCart().getCartItems().isEmpty()) {
                System.out.println("Your cart is empty.");
            } else {
                System.out.println("=============== YOUR CART ===============");

                for (CartItem item : customer.getCart().getCartItems()) {
                    System.out.println(
                            "Product: " + item.getProduct().getProductName()
                                    + " | Quantity: " + item.getQuantity()
                                    + " | Price: " + item.getProduct().getPriceOfProduct()
                    );
                }

                System.out.println(
                        "Total Price: " + customer.getCart().calculateTotalPrice()
                );

                System.out.println("=========================================");
            }
            boolean z = false;
            while (!z) {
                System.out.println("1. Undo Last Action.");
                System.out.println("2. Redo Last Action.");
                System.out.println("3. Continue to Checkout.");
                if (input.hasNextInt()) {
                    int choice3 = input.nextInt();
                    input.nextLine();
                    switch (choice3) {

                        case 1:
                            if (customer.getCart().undoLastAction()) {
                                System.out.println("Last action undone successfully.");
                                if (customer.getCart().getCartItems().isEmpty()) {
                                    System.out.println("Your cart is empty.");
                                } else {
                                    System.out.println("=============== YOUR CART ===============");

                                    for (CartItem item : customer.getCart().getCartItems()) {
                                        System.out.println(
                                                "Product: " + item.getProduct().getProductName()
                                                        + " | Quantity: " + item.getQuantity()
                                                        + " | Price: " + item.getProduct().getPriceOfProduct()
                                        );
                                    }

                                    System.out.println(
                                            "Total Price: " + customer.getCart().calculateTotalPrice()
                                    );

                                    System.out.println("=========================================");
                                }
                            } else {
                                System.out.println("Nothing to undo.");
                            }
                            break;
                        case 2:
                            if (customer.getCart().redoLastAction()) {
                                System.out.println("Last action redone successfully.");
                                if (customer.getCart().getCartItems().isEmpty()) {
                                    System.out.println("Your cart is empty.");
                                } else {
                                    System.out.println("=============== YOUR CART ===============");

                                    for (CartItem item : customer.getCart().getCartItems()) {
                                        System.out.println(
                                                "Product: " + item.getProduct().getProductName()
                                                        + " | Quantity: " + item.getQuantity()
                                                        + " | Price: " + item.getProduct().getPriceOfProduct()
                                        );
                                    }

                                    System.out.println(
                                            "Total Price: " + customer.getCart().calculateTotalPrice()
                                    );

                                    System.out.println("=========================================");
                                }
                            } else {
                                System.out.println("Nothing to redo.");
                            }
                            break;
                        case 3:
                            if (customer.getCart().getCartItems().isEmpty()) {
                                System.out.println("Your cart is empty. Please add a product or redo your last action.");
                            } else {
                                z = true;
                            }
                            break;
                        default:
                            System.out.println("Choose a number for 1 to 3.");
                    }
                } else {
                    System.out.println("Enter a Valid Number: ");
                    input.nextLine();
                }
            }

            Order order = new Order(nextOrderId++, customer);
            shoppingSystem.addOrder(order);

            System.out.println("========== ORDER SUMMARY BEFORE PAYMENT ==========");

            System.out.println("Order Id: " + order.getOrderId());

            System.out.println(customer);

            System.out.println("Products:");

            for (CartItem item : order.getCartItems()) {
                System.out.println(
                        item.getProduct().getProductName()
                                + " - Quantity: "
                                + item.getQuantity()
                                + " - Price: "
                                + item.getProduct().getPriceOfProduct());
            }

            System.out.println("Total Price: " + order.calculateTotalPrice());
            System.out.println("Status: " + order.getOrderStatus());

            System.out.println("Choose payment method:");
            System.out.println("1. Credit Card");
            System.out.println("2. Cash On Delivery");
            boolean paymentSuccessful = false;
            while (true) {
                int choice4;
                if (input.hasNextInt()) {
                    choice4 = input.nextInt();
                    input.nextLine();

                    switch (choice4) {

                        case 1:
                            System.out.println("Enter your card number:");
                            while (true) {
                                String cardNumber = input.nextLine();
                                if (cardNumber.matches("[0-9]{16}")) {
                                    CreditCardPayment creditCardPayment = new CreditCardPayment(cardNumber);

                                    boolean creditResult = order.processPayment(creditCardPayment);

                                    if (creditResult) {
                                        System.out.println("Payment successful.");
                                        customer.addOrderToHistory(order);
                                        customer.getCart().clearCart();
                                        paymentSuccessful = true;
                                    } else {
                                        System.out.println("Payment failed.");
                                    }
                                    break;
                                } else {
                                    System.out.println("Please enter a valid card number.");
                                }
                            }
                            break;

                        case 2:
                            CashOnDelivery cashOnDelivery = new CashOnDelivery();

                            boolean cashResult = order.processPayment(cashOnDelivery);

                            if (cashResult) {
                                System.out.println("Payment successful.");
                                customer.addOrderToHistory(order);
                                customer.getCart().clearCart();
                                paymentSuccessful = true;
                            } else {
                                System.out.println("Payment failed.");
                            }
                            break;

                        default:
                            System.out.println("Invalid payment choice.");
                    }
                    if (paymentSuccessful) {
                        break;
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    input.nextLine();

                }
            }

            System.out.println("========== FINAL ORDER ==========");

            System.out.println("Order Id: " + order.getOrderId());
            System.out.println(customer);

            System.out.println("Products:");

            for (CartItem item : order.getCartItems()) {
                System.out.println(
                        item.getProduct().getProductName()
                                + " - Quantity: "
                                + item.getQuantity());
            }

            System.out.println("Total Price: " + order.calculateTotalPrice());
            System.out.println("Payment Status: " + order.getOrderStatus());
            System.out.println("=================================");
        }

    }

}

