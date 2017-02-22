package ua.spalah.bank.ui;

import ua.spalah.bank.models.Gender;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.services.ClientService;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class AddClientCommand extends AbstractCommand implements Command {

    private ClientService clientService;

    public AddClientCommand(ClientService clientService, IO io) {
        super(io);

        this.clientService = clientService;
    }

    @Override
    public void execute() throws IOException {


        Gender gender = null;
        write("Client name: ");
        String newClient = read();
        write("Gander: 1 - MALE 2 - FAMALE: ");


        int selectGender = Integer.parseInt(read());


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
        write("Client E-Mail: ");
        String email = read();

        boolean emailCorrect = email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");


        if (emailCorrect == true) {
            client.setEmail(email);
        } else {
            System.err.println("email is not valid, for repeat click '7'");
            return;
        }

        write("Client phone (xxx)xxx-xx-xx): ");
        String phone = read();
        boolean phoneCorrect = phone.matches("^\\(([0-9]){3}\\)[0-9]{3}\\-[0-9]{2}\\-[0-9]{2}$");

        if (phoneCorrect == true) {
            client.setPhone(phone);
        } else {
            write("phone number is not valid, for repeat click '7'");
            return;
        }


        write("Client city: ");
        String city = read();
        client.setCity(city);


        write("Choose id of acc for activate");
        long activeAccId = Integer.parseInt(read());
        client.setActiveAccId(activeAccId);


        clientService.saveClient(client);
        BankCommander.currentClient = client;
        write("Client " + client.getName() + " add and activate\n");
        write(client.toString());
    }

    @Override
    public void printCommandInfo() {

        write("7. AddClientCommand ");
    }
}
