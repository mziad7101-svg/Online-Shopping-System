package model;

import java.util.*;

public class ShoppingSystem {
    private LinkedHashMap<Integer, Product> products = new LinkedHashMap<>();
    private LinkedHashMap<Integer, Customer> customers = new LinkedHashMap<>();
    private LinkedHashMap<Integer, Order> orders = new LinkedHashMap<>();
    private HashSet<String> emails = new HashSet<>();
    private Queue<Order> pendingOrders = new ArrayDeque<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (products.containsKey(product.getProductId())) {
            throw new IllegalArgumentException("Product ID cannot be duplicated.");
        }

        products.put(product.getProductId(), product);
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Customer ID cannot be duplicated");

        }
        if (emails.contains(customer.getEmail())) {
            throw new IllegalArgumentException("Customer Email cannot be duplicated");
        }
        emails.add(customer.getEmail());
        customers.put(customer.getId(), customer);
    }

    public void addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        if (orders.containsKey(order.getOrderId())) {
            throw new IllegalArgumentException("Order ID cannot be duplicated");
        }

        orders.put(order.getOrderId(), order);
    }

    public void addOrderToQueue(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        pendingOrders.offer(order);
    }


    public void processNextOrder() {
        Order nextOrder = pendingOrders.peek();

        if (nextOrder == null) {
            System.out.println("No pending orders.");
            return;
        }

        if (nextOrder.getOrderStatus() != OrderStatus.PAID) {
            System.out.println("The order hasn't been paid.");
            return;
        }

        pendingOrders.poll();

        nextOrder.setOrderStatus(OrderStatus.PROCESSING);
        System.out.println("Order ID: " + nextOrder.getOrderId()
                + " | Status: " + nextOrder.getOrderStatus());

        nextOrder.setOrderStatus(OrderStatus.COMPLETED);
        System.out.println("Order ID: " + nextOrder.getOrderId()
                + " | Status: " + nextOrder.getOrderStatus());
    }


    public Product findProduct(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            return null;
        }
        String searchName = productName.trim().toLowerCase();

        for (Product p : products.values()) {
            if (p.getProductName().trim().toLowerCase().equals(searchName)) {
                return p;
            }
        }

        for (Product p : products.values()) {
            if (p.getProductName().trim().toLowerCase().contains(searchName)) {
                return p;
            }
        }
        return null;
    }

    public Product findProductById(int productID) {
        if (productID == 0 || productID < 0) {
            throw new IllegalArgumentException("Enter a Positive Product ID.");
        }
        return products.get(productID);
    }

    public ArrayList<Product> getProductsSortedByPrice() {
        ArrayList<Product> productArrayListByPrice = new ArrayList<>(products.values());
        Comparator<Product> byPrice = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getPriceOfProduct(), p1.getPriceOfProduct());
            }
        };
        Collections.sort(productArrayListByPrice, byPrice);
        return productArrayListByPrice;
    }

    public ArrayList<Product> getProductsSortedByName() {
        ArrayList<Product> productArrayListByName = new ArrayList<>(products.values());
        Comparator<Product> byName = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getProductName().compareToIgnoreCase(p2.getProductName());
            }
        };
        Collections.sort(productArrayListByName, byName);
        return productArrayListByName;
    }

    public Customer findCustomerByEmail(String email) {
        if (email == null) {
            return null;
        }
        for (Customer customer : customers.values()) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }
        return null;

    }

    public ArrayList<Product> getTop3ExpensiveProducts() {
        PriorityQueue<Product> productPriorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Product::getPriceOfProduct));
        for (Product p : products.values()) {
            if (productPriorityQueue.size() < 3) {
                productPriorityQueue.offer(p);
            } else {
                if (productPriorityQueue.peek().getPriceOfProduct() < p.getPriceOfProduct()) {
                    productPriorityQueue.poll();
                    productPriorityQueue.offer(p);
                }
            }
        }
        ArrayList<Product> result = new ArrayList<>(productPriorityQueue);
        Comparator<Product> byPrice = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getPriceOfProduct(), p1.getPriceOfProduct());
            }
        };
        result.sort(byPrice);
        return result;
    }

    public ArrayList<Product> getTop3CheapestProducts() {
        PriorityQueue<Product> productPriorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Product::getPriceOfProduct).reversed());
        for (Product p : products.values()) {
            if (productPriorityQueue.size() < 3) {
                productPriorityQueue.offer(p);
            } else {
                if (productPriorityQueue.peek().getPriceOfProduct() > p.getPriceOfProduct()) {
                    productPriorityQueue.poll();
                    productPriorityQueue.offer(p);
                }
            }
        }
        ArrayList<Product> result = new ArrayList<>(productPriorityQueue);
        Comparator<Product> byPrice = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPriceOfProduct(), p2.getPriceOfProduct());
            }
        };
        result.sort(byPrice);
        return result;
    }
}
