package ua.spalah.bank.ui;

import ua.spalah.bank.models.Gender;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class AddClientCommand implements Command {

    ClientService clientService;

    public AddClientCommand(ClientService clientService) {

        this.clientService = clientService;
    }

    @Override
    public void execute() {


        Gender gender = null;
        Scanner name = new Scanner(System.in);
        System.out.print("Client name: ");
        String newClient = name.nextLine();
        System.out.print("Gander: 1 - MALE 2 - FAMALE: ");


        Scanner numOfGender = new Scanner(System.in);
        int selectGender = numOfGender.nextInt();


        switch (selectGender) {
            case 1: {
                gender = Gender.MALE;
                break;
            }
            case 2: {
                gender = Gender.FAMALE;
                break;
            }
            default:
                System.err.println("Gender is not found");
        }
        Client client = new Client(newClient, gender);
        System.out.print("Client E-Mail: ");
        String email = name.nextLine();

        boolean emailCorrect = email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");


        if (emailCorrect == true) {
            client.setEmail(email);
        } else {
            System.err.println("email is not valid, for repeat click '7'");
            return;
        }

        System.out.print("Client phone (xxx)xxx-xx-xx): ");
        String phone = name.nextLine();
        boolean phoneCorrect = phone.matches("^\\(([0-9]){3}\\)[0-9]{3}\\-[0-9]{2}\\-[0-9]{2}$");

        if (phoneCorrect == true) {
            client.setPhone(phone);
        } else {
            System.err.println("phone number is not valid, for repeat click '7'");
            return;
        }


        System.out.print("Client city: ");
        String city = name.nextLine();


        client.setCity(city);

        clientService.saveClient(BankCommander.currentBank, client);
        BankCommander.currentClient = client;
        System.out.println("Client " + client.getName() + " add and activate\n");
        System.out.println(client);
    }

    @Override
    public void printCommandInfo() {
        System.out.println("7. AddClientCommand ");
    }
}
