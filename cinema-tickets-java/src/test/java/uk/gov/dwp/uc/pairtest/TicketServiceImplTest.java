package uk.gov.dwp.uc.pairtest;

import org.junit.Rule;
import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.*;


public class TicketServiceImplTest {
    /**
     * test for empty ticket
     */


    @Test(expected = zeroTicketsException.class)
    public void BlankTicketThrowsException (){
        TicketTypeRequest[] blankTicketArray = new TicketTypeRequest[0];
        TicketServiceImpl ticketService = new TicketServiceImpl();
        long accountId = 1;
        ticketService.purchaseTickets(accountId,blankTicketArray);

    }

    @Test(expected = InvalidAccountException.class)
    public void AccountIdLessthan1ThrowsException(){
        TicketTypeRequest ticketOne = new TicketTypeRequest(TicketTypeRequest.Type.ADULT,4);
        TicketTypeRequest[] ticketArray = new TicketTypeRequest[1];
        ticketArray[0] = ticketOne;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        long accountId = 0;
        ticketService.purchaseTickets(accountId,ticketArray);

    }

     @Test(expected = zeroTicketsException.class)
    public void NoTicketsOrderedThrowsException(){
         TicketTypeRequest ticketOne =
                 new TicketTypeRequest(TicketTypeRequest.Type.ADULT,0);
         TicketTypeRequest[] ticketArray = new TicketTypeRequest[1];
         ticketArray[0] = ticketOne;
         TicketServiceImpl ticketService = new TicketServiceImpl();
         long accountId = 1;
         ticketService.purchaseTickets(accountId,ticketArray);
     }
     @Test(expected = MaxExceededTicketsException.class)
     public void TooManyTicketsOrderedThrowsException(){
         TicketTypeRequest ticketOne =
                 new TicketTypeRequest(TicketTypeRequest.Type.ADULT,11);
         TicketTypeRequest ticketTwo =
                 new TicketTypeRequest(TicketTypeRequest.Type.CHILD,6);
         TicketTypeRequest ticketThree =
                 new TicketTypeRequest(TicketTypeRequest.Type.INFANT,4);
         TicketTypeRequest[] ticketArray = new TicketTypeRequest[3];
         ticketArray[0] = ticketOne;
         ticketArray[1] = ticketTwo;
         ticketArray[2] = ticketThree;
         TicketServiceImpl ticketService = new TicketServiceImpl();
         long accountId = 1;
         ticketService.purchaseTickets(accountId,ticketArray);
     }

     @Test(expected = UnaccompianedMinorException.class)
    public void WheresYourAdultThrowsException (){
         TicketTypeRequest ticketOne =
                 new TicketTypeRequest(TicketTypeRequest.Type.ADULT,0);
         TicketTypeRequest ticketTwo =
                 new TicketTypeRequest(TicketTypeRequest.Type.CHILD,6);
         TicketTypeRequest ticketThree =
                 new TicketTypeRequest(TicketTypeRequest.Type.INFANT,4);
         TicketTypeRequest[] ticketArray = new TicketTypeRequest[3];
         ticketArray[0] = ticketOne;
         ticketArray[1] = ticketTwo;
         ticketArray[2] = ticketThree;
         TicketServiceImpl ticketService = new TicketServiceImpl();
         long accountId = 1;
         ticketService.purchaseTickets(accountId,ticketArray);
     }

    @Test(expected = OneInfantOneLapException.class)
    public void TooManyInfantsThrowsException (){
        TicketTypeRequest ticketOne =
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT,3);
        TicketTypeRequest ticketTwo =
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD,6);
        TicketTypeRequest ticketThree =
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT,4);
        TicketTypeRequest[] ticketArray = new TicketTypeRequest[3];
        ticketArray[0] = ticketOne;
        ticketArray[1] = ticketTwo;
        ticketArray[2] = ticketThree;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        long accountId = 1;
        ticketService.purchaseTickets(accountId,ticketArray);
    }

    @Test
    public void OkTicketsThrowNoException (){
        TicketTypeRequest ticketOne =
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT,3);
        TicketTypeRequest ticketTwo =
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD,6);
        TicketTypeRequest ticketThree =
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT,2);
        TicketTypeRequest[] ticketArray = new TicketTypeRequest[3];
        ticketArray[0] = ticketOne;
        ticketArray[1] = ticketTwo;
        ticketArray[2] = ticketThree;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        long accountId = 1;
        ticketService.purchaseTickets(accountId,ticketArray);
        assert(true);
    }
}