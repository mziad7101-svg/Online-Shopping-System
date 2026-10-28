# Online Shopping System

A console-based Online Shopping System built using Java and Object-Oriented Programming (OOP) concepts.

The application allows customers to register, browse and search products, manage their shopping carts, undo and redo cart additions, and place orders using simulated payment methods.

## Features

- Register customers and log in using email
- Browse products sorted by price or name
- Search for products by name
- View the top 3 most expensive products
- View the top 3 cheapest products
- View recently searched products
- Add products to the shopping cart
- Check product availability and stock
- Calculate cart and order totals
- Undo and redo cart additions
- Create orders from shopping carts
- Choose between Credit Card and Cash On Delivery
- Update product stock after successful simulated payment
- Track customer order history during the current session

## OOP Concepts Used

- **Encapsulation:** Protecting object state and providing controlled access through methods.
- **Inheritance:** `Customer` and `Admin` extend the abstract `User` class.
- **Polymorphism:** Different payment implementations are used through the `Payment` interface.
- **Abstraction:** The abstract `User` class provides shared user information.
- **Interfaces:** The `Payment` interface defines the payment operation.
- **Composition:** Classes such as `Customer`, `Cart`, `CartItem`, and `Order` work together to represent shopping relationships.
- **Method Overriding:** Used in methods such as `toString()` and payment implementations.
- **Enums:** `OrderStatus` represents order states.

## Data Structures Used

| Data Structure | Used In | Why It Was Chosen | Typical Time Complexity |
|---|---|---|---|
| `ArrayList` | Cart items, order items, order history, and sorted product lists | Dynamic storage and easy iteration | Access: O(1), Search: O(n), Append: O(1) amortized |
| `Stack` | Cart undo and redo | Follows LIFO (Last In, First Out) to reverse recent cart additions | Push / Pop / Peek: O(1) |
| `LinkedHashMap` | Products, customers, and orders | Stores objects by unique IDs and preserves insertion order | Put / Get / Contains: O(1) average |
| `HashSet` | Registered customer emails | Efficient duplicate checks | Add / Contains: O(1) average |
| `Deque` (`ArrayDeque`) | Recently searched products | Efficient operations at both ends and recent-search management | Add / Remove at ends: O(1) amortized |
| `Queue` (`ArrayDeque`) | Pending orders | Maintains FIFO (First In, First Out) processing order | Offer / Peek / Poll: O(1) amortized |
| `PriorityQueue` | Top 3 most expensive and cheapest products | Maintains the best candidates while scanning products | Offer / Poll: O(log k), Peek: O(1) |

*Note: Complexities are typical average or amortized bounds. The queue-processing methods are implemented but are not connected to the main application flow.*

## Iterators and Sorting

- **Iterator:** Used to traverse recently searched products.
- **`descendingIterator()`:** Displays recent searches from newest to oldest.
- **`Comparator`:** Defines product ordering by price and name, and ordering for top-three product selection.
- **`Collections.sort()`:** Sorts products by price and name.
- **`StringBuilder`:** Used to build the order summary in `Order.toString()`.

## Payment Methods

### Credit Card

Simulates credit card payment. The implementation checks the card number format and length but does not connect to a real payment provider.

### Cash On Delivery

Simulates successful payment confirmation for Cash On Delivery.

**Important:** All payment processing is simulated for learning purposes. No real financial transactions are performed.

## Order Processing

1. The customer adds products to the cart.
2. The system checks product availability and requested quantities.
3. The system calculates the total order price.
4. An order is created with the `PENDING` status.
5. The customer selects a payment method.
6. The payment method simulates the payment result.
7. After successful payment, product stock is reduced.
8. The order status changes to `PAID`.
9. The order is added to the customer's order history.
10. The cart is cleared after successful payment.

A FIFO queue for pending-order processing is implemented in `ShoppingSystem`, but it is not yet integrated into the main console flow.

## Project Structure

```text
OSS/
├── README.md
├── .gitignore
├── src/
│   ├── Main.java
│   ├── model/
│   │   ├── Admin.java
│   │   ├── Cart.java
│   │   ├── CartAction.java
│   │   ├── CartItem.java
│   │   ├── Customer.java
│   │   ├── Order.java
│   │   ├── OrderStatus.java
│   │   ├── Product.java
│   │   ├── ShoppingSystem.java
│   │   └── User.java
│   └── Payment/
│       ├── CashOnDelivery.java
│       ├── CreditCardPayment.java
│       └── Payment.java
└── lib/
```

## Technologies Used

- Java
- Java Collections Framework
- Object-Oriented Programming
- Data Structures
- IntelliJ IDEA
- Visual Studio Code

**Configured Java version:** Temurin JDK 25 in the inspected IntelliJ project settings.

## How to Run

1. Install and configure a compatible JDK.
2. Open the project in IntelliJ IDEA.
3. Open `Main.java` inside the `src` directory.
4. Run the `main()` method.
5. Follow the console menu to register, browse products, manage the cart, and place an order.

The project currently uses IDE configuration rather than a documented Maven or Gradle build setup.

## Known Limitations

- Data is stored in memory and is reset when the application restarts.
- Payment methods are simulations, not real payment integrations.
- Login uses email lookup and does not use passwords.
- The pending-order queue is implemented but not connected to the main application flow.
- `Admin` exists as a class, but administrative operations are not available through the console.
- Email validation is basic and does not verify that an email address exists.
- Cart undo and redo support additions only.
- No automated Java test files were found in the inspected source.

## Learning Objectives

This project was developed to practice:

- Object-Oriented Programming in Java
- Java Collections Framework
- Data Structures and their time complexities
- Searching and sorting operations
- Stack-based undo and redo
- Queue and Deque operations
- PriorityQueue for top-three product selection
- Interfaces and polymorphism
- Order and payment flow management

## Future Improvements

- Add database persistence.
- Improve email validation and normalize email addresses.
- Add password-based authentication.
- Integrate a real payment gateway.
- Add automated unit tests.
- Connect the pending-order queue to the main application.
- Implement administrative features.
- Improve checkout error handling and allow order cancellation.

## Author

Developed as a Java learning project to practice Object-Oriented Programming, Java Collections, and Data Structures.
