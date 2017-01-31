package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.services.ClientService;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class RemoveClientCommand implements Command {

    private ClientService clientService;

    public RemoveClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        Scanner del = new Scanner(System.in);
        String delClient = del.nextLine();
        Client client = null;

        for (Client c : BankCommander.currentBank.getAllClients().values()) {
            if (c.getName().equals(delClient)) {
                client = (Client) c;
            }
        }
        try {
            clientService.deleteClient(BankCommander.currentBank, client);
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(client.getName() + " deleted");
    }

    @Override
    public void printCommandInfo() {
        System.out.println("9. RemoveClientCommand");
    }
}
