package ua.spalah.bank.exceptions;

/**
 * Created by Oleg on 14.01.2017.
 */
public class ClientNotFoundException extends BankException {
    public ClientNotFoundException(String clientName) {
        super("Client " + clientName + " not found");
    }
}
