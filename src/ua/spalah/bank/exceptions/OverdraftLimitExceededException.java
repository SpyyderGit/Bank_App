package ua.spalah.bank.exceptions;

/**
 * Created by Oleg on 14.01.2017.
 */
public class OverdraftLimitExceededException extends NotEnoughFundsException {

    public OverdraftLimitExceededException(double message) {
        super(message);
        System.err.println(" money is not enough");
    }
}
