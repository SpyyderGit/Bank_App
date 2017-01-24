package ua.spalah.bank.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.spalah.bank.Gender;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.models.Bank;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.ClientServiceImpl;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 24.01.2017.
 */
public class TestClientService {
    private ClientService clientService = null;
    private Bank prostoBank = null;

    private Client john = null;
    private Client katya = null;
    private Client petya = null;
    private Client sofia = null;
    private Client anya = null;


    @Before
    public void init() {
        clientService = new ClientServiceImpl();
        prostoBank = new Bank();

        john = new Client("John", Gender.MALE);
        katya = new Client("Katya", Gender.FAMALE);
        petya = new Client("Petya", Gender.MALE);
        sofia = new Client("Sofia", Gender.FAMALE);
        anya = new Client("Anya", Gender.FAMALE);

//================== Регистрируем клиентов в банке ==========

        clientService.saveClient(prostoBank, john);
        clientService.saveClient(prostoBank, katya);
        clientService.saveClient(prostoBank, petya);
        clientService.saveClient(prostoBank, sofia);
        clientService.saveClient(prostoBank, anya);

        john.setPhone("(092)123-12-12");
        john.setEmail("oleg@mail.ua");
        john.setCity("Dnepr");
    }

    @Test
    public void testFindClientByName_Name() throws ClientNotFoundException {
        clientService.findClientByName(prostoBank, "John");
        String excepted = john.getName();
        String actual = "John";
        assertEquals(excepted, actual);
    }

    @Test
    public void testFindClientByName_Phone() throws ClientNotFoundException {
        clientService.findClientByName(prostoBank, "John");
        String excepted = john.getPhone();
        String actual = "(092)123-12-12";
        assertEquals(excepted, actual);

    }


    @Test
    public void testFindClientByName_Email() throws ClientNotFoundException {
        clientService.findClientByName(prostoBank, "John");
        String excepted = john.getEmail();
        String actual = "oleg@mail.ua";
        assertEquals(excepted, actual);
    }


    @Test
    public void testFindClientByName_City() throws ClientNotFoundException {
        clientService.findClientByName(prostoBank, "John");
        String excepted = john.getCity();
        String actual = "Dnepr";
        assertEquals(excepted, actual);
    }
}
