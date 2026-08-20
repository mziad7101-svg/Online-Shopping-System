# Online Shopping System

A console-based Online Shopping System built using Java and Object-Oriented Programming (OOP) concepts.

## Features

- Add customers
- Add products
- Search for products
- Add products to cart
- Check product stock
- Add multiple products to the cart
- Calculate total order price
- Create orders
- Credit Card payment
- Cash On Delivery payment
- Update order status after payment
- Reduce product stock after successful payment

## OOP Concepts Used

- Encapsulation
- Inheritance
- Polymorphism
- Abstraction
- Interfaces
- Composition
- ArrayList
- Enums

## Project Structure

```text
src
│
├── Main.java
│
├── model
│   ├── User.java
│   ├── Customer.java
│   ├── Admin.java
│   ├── Product.java
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Order.java
│   ├── OrderStatus.java
│   └── ShoppingSystem.java
│
└── Payment
    ├── Payment.java
    ├── CreditCardPayment.java
    └── CashOnDelivery.java
```

## How It Works

1. The user enters their name, address, and email.
2. The user searches for a product.
3. The system checks if the product exists.
4. The user enters the quantity.
5. The system checks if enough stock is available.
6. The user can add multiple products to the cart.
7. An order is created from the cart.
8. The system calculates the total price.
9. The user chooses a payment method.
10. The payment is processed.
11. After successful payment, the stock is reduced.
12. The order status is updated to PAID.

## Payment Methods

- Credit Card
- Cash On Delivery

## Technologies Used

- Java
- Object-Oriented Programming
- Java Collections
- Git
- GitHub

