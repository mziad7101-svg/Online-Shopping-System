package Payment;

public class CreditCardPayment implements Payment {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        if (cardNumber == null) {
            throw new IllegalArgumentException("Card Number cannot be null.");
        }
        if (!cardNumber.matches("[0-9]{16}")) {
            throw new IllegalArgumentException("Invalid card number.");
        }
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double price) {
        if (cardNumber.length() != 16) {
            return false;
        } else
            return true;
    }
}

