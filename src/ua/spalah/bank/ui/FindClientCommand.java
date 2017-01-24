package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.models.Bank;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.BankReportServiceImpl;

import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class FindClientCommand implements Command {
    private ClientService clientService;


    public FindClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute() {


        System.out.println("Enter name of client: ");
        Scanner find = new Scanner(System.in);
        try {
            BankCommander.currentClient = clientService.findClientByName(BankCommander.currentBank, find.nextLine());
            System.out.println(BankCommander.currentClient);
            System.out.println("Current client: " + BankCommander.currentClient.getName());
        } catch (ClientNotFoundException e) {
            System.err.println("Client not found. Click '1' from repeat");
        }

    }

    @Override
    public void printCommandInfo() {

        System.out.println("1. FindClientCommand");
    }
}
