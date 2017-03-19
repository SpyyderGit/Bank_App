package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.services.ClientService;

import java.io.IOException;

/**
 * Created by Oleg on 15.01.2017.
 */
public class FindClientCommand extends AbstractCommand implements Command {
    private ClientService clientService;


    public FindClientCommand(ClientService clientService, IO io) {
        super(io);
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        System.out.println("Enter name of client: ");

        try {
            BankCommander.currentClient = clientService.findClientByName(read());
            write(BankCommander.currentClient.toString());
            write("Current client: " + BankCommander.currentClient.getName());
        } catch (ClientNotFoundException e) {
            write("Client not found. Click '1' from repeat");
        } catch (IOException | NullPointerException e) {
            System.err.println("Client not found. Click 1 for repeat or 11 for exit");
        }
    }

    @Override
    public void printCommandInfo() {
        write("1. FindClientCommand");
    }
}
