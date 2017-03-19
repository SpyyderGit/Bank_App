import org.junit.Before;
import org.junit.Test;
import ua.spalah.bank.models.Gender;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.services.AccountDao;
import ua.spalah.bank.services.ClientDao;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.AccountDaoImpl;
import ua.spalah.bank.services.impl.ClientDaoImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;
import ua.spalah.bank.ui.BankCommander;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 24.01.2017.
 */

public class TestClientService {
    private ClientService clientService = null;
    private Client john = null;
    private Client katya = null;
    private Client petya = null;
    private Client sofia = null;
    private Client anya = null;


    @Before
    public void init() {

        AccountDao accountDao = new AccountDaoImpl();
        ClientDao clientDao = new ClientDaoImpl(accountDao);

        clientService = new ClientServiceImpl(clientDao, new AccountDaoImpl());


        john = new Client("John", Gender.MALE);
        katya = new Client("Katya", Gender.FAMALE);
        petya = new Client("Petya", Gender.MALE);
        sofia = new Client("Sofia", Gender.FAMALE);
        anya = new Client("Anya", Gender.FAMALE);

//================== Регистрируем клиентов в банке ==========

        clientService.saveClient(john);
        clientService.saveClient(katya);
        clientService.saveClient(petya);
        clientService.saveClient(sofia);
        clientService.saveClient(anya);

        john.setPhone("(092)123-12-12");
        john.setEmail("oleg@mail.ua");
        john.setCity("Dnepr");
    }

    //======================= Test of findClientByName =============================

    @Test(expected = IllegalArgumentException.class)
    public void testFindClientByName_EmptyName() throws ClientNotFoundException {
        clientService.findClientByName("");
        String excepted = john.getName();
        String actual = "";
        assertEquals(excepted, actual);
    }


    @Test(expected = NullPointerException.class)
    public void testFindClientByName_Null() throws ClientNotFoundException {
        clientService.findClientByName(null);
        String excepted = john.getName();
        String actual = null;
        assertEquals(excepted, actual);
    }


    @Test
    public void testFindClientByName_Name() throws ClientNotFoundException {
        clientService.findClientByName("John");
        String excepted = john.getName();
        String actual = "John";
        assertEquals(excepted, actual);
    }

    @Test
    public void testFindClientByName_Phone() throws ClientNotFoundException {
        clientService.findClientByName("John");
        String excepted = john.getPhone();
        String actual = "(092)123-12-12";
        assertEquals(excepted, actual);
    }

    @Test
    public void testFindClientByName_Email() throws ClientNotFoundException {
        clientService.findClientByName("John");
        String excepted = john.getEmail();
        String actual = "oleg@mail.ua";
        assertEquals(excepted, actual);
    }

    @Test
    public void testFindClientByName_City() throws ClientNotFoundException {
        clientService.findClientByName("John");
        String excepted = john.getCity();
        String actual = "Dnepr";
        assertEquals(excepted, actual);
    }

//======================= Test of findAllClients =====================================

    @Test
    public void testFindAllClients_Empty() {
        int excepted = 0;
        int actual = 0;
        assertEquals(excepted, actual);
    }


    @Test
    public void testFindAllClients_oneClient() {

        clientService.saveClient(new Client("Eugeny", Gender.MALE));
        int excepted = 0;
        int actual = 1;
        assertEquals(excepted, actual);
    }

    @Test
    public void testFindAllClients_Many() {

        clientService.saveClient(new Client("Valya", Gender.FAMALE));
        clientService.saveClient(new Client("Olya", Gender.FAMALE));
        clientService.saveClient(new Client("Alya", Gender.FAMALE));
        clientService.saveClient(new Client("Bolya", Gender.FAMALE));
        clientService.saveClient(new Client("Salya", Gender.FAMALE));
        clientService.saveClient(new Client("Pasha", Gender.MALE));
        clientService.saveClient(new Client("Ralya", Gender.FAMALE));
        clientService.saveClient(new Client("Gav", Gender.MALE));
        clientService.saveClient(new Client("Galya", Gender.FAMALE));
        clientService.saveClient(new Client("Kalya", Gender.FAMALE));

        int excepted = 0;
        int actual = 15;
        assertEquals(excepted, actual);
    }

    @Test
    public void testSaveClient_null() {

        clientService.saveClient(new Client(null, Gender.MALE));
        int expected = 0;
        int actual = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testSaveClient_Empty() {

        clientService.saveClient(new Client("EE", Gender.MALE));
        int expected = 0;
        int actual = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testSaveClient_AddMenyClients() {
        clientService.saveClient(new Client("1Valy", Gender.FAMALE));
        clientService.saveClient(new Client("2Olya", Gender.FAMALE));
        clientService.saveClient(new Client("3Qaly", Gender.FAMALE));
        clientService.saveClient(new Client("4dfd", Gender.FAMALE));
        clientService.saveClient(new Client("5lya", Gender.FAMALE));
        clientService.saveClient(new Client("6ash", Gender.MALE));
        clientService.saveClient(new Client("7alya", Gender.FAMALE));
        clientService.saveClient(new Client("8av", Gender.MALE));
        clientService.saveClient(new Client("9aly", Gender.FAMALE));
        clientService.saveClient(new Client("10lya", Gender.FAMALE));
        clientService.saveClient(new Client("11Raly", Gender.FAMALE));
        clientService.saveClient(new Client("12fff", Gender.FAMALE));
        clientService.saveClient(new Client("13ya", Gender.FAMALE));
        clientService.saveClient(new Client("14dala", Gender.FAMALE));
        clientService.saveClient(new Client("15Vly", Gender.FAMALE));
        clientService.saveClient(new Client("16Pas", Gender.MALE));
        clientService.saveClient(new Client("19Vay", Gender.FAMALE));
        clientService.saveClient(new Client("17Gav", Gender.MALE));
        clientService.saveClient(new Client("20Gay", Gender.FAMALE));
        clientService.saveClient(new Client("21Kly", Gender.FAMALE));
        clientService.saveClient(new Client("22Val", Gender.FAMALE));
        clientService.saveClient(new Client("23Ola", Gender.FAMALE));
        clientService.saveClient(new Client("24aly", Gender.FAMALE));
        clientService.saveClient(new Client("25aya", Gender.FAMALE));
        clientService.saveClient(new Client("Vla", Gender.FAMALE));
        clientService.saveClient(new Client("26Psh", Gender.MALE));
        clientService.saveClient(new Client("27Valy", Gender.FAMALE));
        clientService.saveClient(new Client("29Ga", Gender.MALE));
        clientService.saveClient(new Client("28lfya", Gender.FAMALE));
        clientService.saveClient(new Client("Klyadfg", Gender.FAMALE));
        clientService.saveClient(new Client("Vafyawerw", Gender.FAMALE));
        clientService.saveClient(new Client("Olydsf", Gender.FAMALE));
        clientService.saveClient(new Client("Valydsfs", Gender.FAMALE));
        clientService.saveClient(new Client("Vayadsf", Gender.FAMALE));
        clientService.saveClient(new Client("Vly", Gender.FAMALE));
        clientService.saveClient(new Client("ashsdfsf", Gender.MALE));
        clientService.saveClient(new Client("Vala", Gender.FAMALE));
        clientService.saveClient(new Client("Gav", Gender.MALE));
        clientService.saveClient(new Client("Gly", Gender.FAMALE));
        clientService.saveClient(new Client("Kal", Gender.FAMALE));

        int expected = 0;
        int actual = 45;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteClient_One() throws ClientNotFoundException {
        clientService.deleteClient(john);
        int expected = 0;
        int actual = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteClient_Meny() throws ClientNotFoundException {
        clientService.deleteClient(john);
        clientService.deleteClient(anya);
        clientService.deleteClient(katya);
        int expected = 0;
        int actual = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteClient_All() throws ClientNotFoundException {
        clientService.deleteClient(john);
        clientService.deleteClient(anya);
        clientService.deleteClient(katya);
        clientService.deleteClient(sofia);
        clientService.deleteClient(petya);

        int expected = 0;
        int actual = 0;
        assertEquals(expected, actual);
    }
}