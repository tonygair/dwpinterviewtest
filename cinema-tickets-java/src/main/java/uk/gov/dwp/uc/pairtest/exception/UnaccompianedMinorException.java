package uk.gov.dwp.uc.pairtest.exception;

public class UnaccompianedMinorException extends InvalidPurchaseException{
    public UnaccompianedMinorException(String message){
        super(message);
    }
}
