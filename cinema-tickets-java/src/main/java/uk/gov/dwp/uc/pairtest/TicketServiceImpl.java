package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationService;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.*;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */
    TicketPaymentServiceImpl paymentService = new TicketPaymentServiceImpl();

    SeatReservationServiceImpl seatService = new SeatReservationServiceImpl();
    /**
     * I am deliberately not hardcoding in a zero price for infants as this might change in the future
      */
    Integer adultPrice = 20;
    Integer childPrice = 10;
    Integer infantPrice = 0;
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        Boolean requestIsValid = Boolean.FALSE;
        if (accountId <= 0) {
            throw new InvalidAccountException("Invalid account number");
        }
        if (ticketTypeRequests.length < 1) {
            throw new zeroTicketsException("No tickets requested");
        }

        Integer adultTickets=0, infantTickets=0, childTickets = 0;
        for (Integer requests =0; requests < ticketTypeRequests.length ;
                requests = requests + 1) {
            switch (ticketTypeRequests[requests].getTicketType()) {
                case ADULT:
                    adultTickets = adultTickets + ticketTypeRequests[requests].getNoOfTickets();
                    break;
                case CHILD:
                    childTickets = childTickets + ticketTypeRequests[requests].getNoOfTickets();
                    break;
                case INFANT:
                    infantTickets = infantTickets + ticketTypeRequests[requests].getNoOfTickets();
                    break;
            }
        }
        Integer totalTickets = infantTickets + childTickets + adultTickets;
        if (totalTickets < 1) {
            throw new zeroTicketsException("Number of tickets amount to zero requested");
        }

        if (totalTickets > 20) {
            throw new MaxExceededTicketsException(totalTickets +
                    " have been ordered, order maximum is 20 tickets in a single request - " );
        }

        /** an adult must attend if a child attends (Infants are dealt with separately)
         *  however there is no limit to children if one adult attends i.e. A school class
         */

        if (adultTickets < 1) {
            throw new UnaccompianedMinorException("Child is not accompanied by Adult!");
        }


        /**
         * I have added a sensible assumption that an infant will take a full lap of an adult -
         * so one infant per adult
         */

        if (infantTickets > adultTickets) {
            throw new OneInfantOneLapException("Infants need a lap to sit on - you need "
                    +(infantTickets-adultTickets) + " more Adults to attend");
        }

        /** I did wonder whether to have a separate validation method. However much of the code
         * would be repeated and for simplicity and less processor usage I thought not. But in case my team leader decided
         * my decision was incorrect, I wanted it easy to change with fewer changes which is
         * why I have included the requestIsValid variable.
         */
        requestIsValid = Boolean.TRUE;

        if (requestIsValid) {
            Integer numberOfSeatsNeeded = adultTickets + childTickets;
            Integer amountToPay = (adultTickets * adultPrice) +
                                    (childTickets*childPrice) +
                                    (infantTickets*infantPrice);
            /** since payment is always made - we can reserve the seats before we pay to make
             * sure customers have a seat BEFORE they pay
             */
            seatService.reserveSeat(accountId, numberOfSeatsNeeded);
            System.out.println(numberOfSeatsNeeded + " seats were booked");
            paymentService.makePayment(accountId, amountToPay);
            System.out.println(" for a cost of " + amountToPay + " pounds ");
        }







        }






}
