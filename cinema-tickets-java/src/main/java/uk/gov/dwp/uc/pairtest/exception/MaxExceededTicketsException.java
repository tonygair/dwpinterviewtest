package uk.gov.dwp.uc.pairtest.exception;

public class MaxExceededTicketsException extends InvalidPurchaseException{
    public MaxExceededTicketsException(String message){
        super(message);

    }
}
