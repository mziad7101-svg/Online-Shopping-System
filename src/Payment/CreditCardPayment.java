package Payment;

public class CreditCardPayment implements Payment {
 private String cardNumber ;
 public CreditCardPayment(String cardNumber ){
this.cardNumber = cardNumber ; 
 }
 @Override
    public boolean processPayment(double price) {
    if(cardNumber.length() !=16 ){
        return false ;
    }
    else 
        return true ;
    }
}
