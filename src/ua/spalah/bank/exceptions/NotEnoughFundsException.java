package ua.spalah.bank.exceptions;

/**
 * Created by User on 13.01.2017.
 */
public class NotEnoughFundsException extends BankException {
    public NotEnoughFundsException(double available) {
        super("Not enough funds on account . Only $" + available + " available to withdraw.");
    }
}
