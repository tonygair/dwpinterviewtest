package uk.gov.dwp.uc.pairtest.exception;

public class zeroTicketsException extends InvalidPurchaseException{
    public zeroTicketsException (String message) {
        super(message);
    }
}
