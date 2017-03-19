import org.junit.Before;
import org.junit.Test;
import ua.spalah.bank.models.Gender;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.services.AccountDao;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientDao;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.AccountDaoImpl;
import ua.spalah.bank.services.impl.AccountServiceImpl;
import ua.spalah.bank.services.impl.ClientDaoImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 25.01.2017.
 */
public class TestAccountService {
    private ClientService clientService = null;

    private Client john = null;
    private Client katya = null;
    private Client petya = null;
    private Client sofia = null;
    private Client anya = null;
    private Account accDepositKatya;
    private Account accCreditKatya;
    private AccountService accountService;
    private Account accCreditKatya2;
    private Account accDepositJohn;
    private Account accCreditJohn;


    @Before
    public void init() throws OverdraftLimitExceededException {
        Connection con = null;
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:~/test";
            con = DriverManager.getConnection(url, "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        AccountDao accountDao = new AccountDaoImpl();
        ClientDao clientDao = new ClientDaoImpl(accountDao);
        clientService = new ClientServiceImpl(clientDao, accountDao);
        accountService = new AccountServiceImpl(accountDao);

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

//=================== Добавляем счета для Кати и тестим Катю ====================

        accDepositKatya = new SavingAccount(150);
        accCreditKatya = new CheckingAccount(250, 10);
        accCreditKatya2 = new CheckingAccount(250, 0);
        clientService.addAcc(katya, accCreditKatya);
        clientService.addAcc(katya, accDepositKatya);


//=================== Добавляем счета для Джона ====================

        accDepositJohn = new SavingAccount(150);
        accCreditJohn = new CheckingAccount(250, 10);

        clientService.addAcc(john, accCreditJohn);
        clientService.addAcc(john, accDepositJohn);
    }

//============================== тестим deposit ==================================

    @Test
    public void testDeposit_depositZero() {
        accountService.deposit(accDepositKatya, 0);
        double expected = accDepositKatya.getBalance();
        double actual = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeposit_creditZero() {
        accountService.deposit(accCreditKatya, 0);
        double expected = accCreditKatya.getBalance();
        double actual = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeposit_depositMenyMoneyInt() {
        accountService.deposit(accDepositKatya, 10000000);
        double expected = accDepositKatya.getBalance();
        double actual = 10000000;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeposit_creditMenyMoneyInt() {
        accountService.deposit(accCreditKatya, 10000000);
        double expected = accCreditKatya.getBalance();
        double actual = 10000000;
        assertEquals(expected, actual);
    }


    @Test
    public void testDeposit_depositMenyMoneyDouble() {
        accountService.deposit(accDepositKatya, 10000000.5656);
        double expected = accDepositKatya.getBalance();
        double actual = 10000000.5656;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeposit_creditMenyMoneyDouble() {
        accountService.deposit(accCreditKatya, 10000000.5656);
        double expected = accCreditKatya.getBalance();
        double actual = 10000000.5656;
        assertEquals(expected, actual);
    }

// ============= тестим setActiveAccService =================

    @Test
    public void testSetActiveAccService() {
        clientService.setActiveAccService(accCreditKatya, katya);
        Account expected = katya.getActiveAcc();
        Account actual = accCreditKatya;
        assertEquals(expected, actual);
    }

// ============ тестим withdraw =====================

    // ------------------- Credit --------------------
    @Test
    public void testWithdraw_drawZero() throws NotEnoughFundsException {
        accountService.withdraw(accCreditKatya, 0);
        double expected = accCreditKatya.getBalance();
        double actual = 250;
        assertEquals(expected, actual);
    }

    @Test(expected = OverdraftLimitExceededException.class)
    public void testWithdraw_MoreThenOver() throws NotEnoughFundsException {
        accountService.withdraw(accCreditKatya, 241);
    }

    @Test(expected = OverdraftLimitExceededException.class)
    public void testWithdraw_MoreThenOverZero() throws NotEnoughFundsException {
        accountService.withdraw(accCreditKatya, 250);
    }


    @Test
    public void testWithdraw_drawAll() throws NotEnoughFundsException {
        accountService.withdraw(accCreditKatya2, 250);
        double expected = accCreditKatya2.getBalance();
        double actual = 0;
        assertEquals(expected, actual);
    }

// --------------------- Deposit --------------------------

    @Test
    public void testWdDepos_drawZero() throws NotEnoughFundsException {
        accountService.withdraw(accCreditKatya, 0);
        double expected = accDepositKatya.getBalance();
        double actual = 150;
        assertEquals(expected, actual);
    }

    @Test(expected = NotEnoughFundsException.class)
    public void testWdDepos_SubZero() throws NotEnoughFundsException {
        accountService.withdraw(accDepositKatya, 450);
    }


    @Test
    public void testWdDepos_drawAll() throws NotEnoughFundsException {
        accountService.withdraw(accDepositKatya, 150);
        double expected = accDepositKatya.getBalance();
        double actual = 0;
        assertEquals(expected, actual);
    }

// ======================= test transfer ===========================

// ==================== Тестим перевод между своими счетами ====================

    // -------------------- проверка отправителя ---------------------
    @Test
    public void testTransferSender() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditKatya, 10);
        double expected = accDepositKatya.getBalance();
        double actual = 140;
        assertEquals(expected, actual);
    }

// ------------------ проверка получателя --------------------------

    @Test
    public void testTransferRecipient() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditKatya, 10);
        double expected = accCreditKatya.getBalance();
        double actual = 260;
        assertEquals(expected, actual);
    }

// *********** переводим ноль между своими счетами с депозита ********

    // -------------------- проверка отправителя ---------------------
    @Test
    public void testTransferSenderZero() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditJohn, 0);
        double expected = accDepositKatya.getBalance();
        double actual = 150;
        assertEquals(expected, actual);
    }

// ------------------ проверка получателя --------------------------

    @Test
    public void testTransferRecipientZero() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditKatya, 0);
        double expected = accCreditKatya.getBalance();
        double actual = 250;
        assertEquals(expected, actual);
    }

    // ************* переводим копейки между своими счетами **************

    // -------------------- проверка отправителя ---------------------
    @Test
    public void testTransferSenderM() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditKatya, 0.1);
        double expected = accDepositKatya.getBalance();
        double actual = 149.99;
        assertEquals(expected, actual);
    }

// ------------------ проверка получателя --------------------------

    @Test
    public void testTransferRecipientM() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditKatya, 0.1);
        double expected = accCreditKatya.getBalance();
        double actual = 250.01;
        assertEquals(expected, actual);
    }


// ==================== Тестим перевод на другой счет ====================

    // -------------------- проверка отправителя ---------------------
    @Test
    public void testTransferSendTo() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditJohn, 10);
        double expected = accDepositKatya.getBalance();
        double actual = 140;
        assertEquals(expected, actual);
    }

// ------------------ проверка получателя --------------------------

    @Test
    public void testTransferRecipientTo() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditJohn, 10);
        double expected = accCreditJohn.getBalance();
        double actual = 260;
        assertEquals(expected, actual);
    }

// ***************** переводим ноль с депозита на кредит *************

    // -------------------- проверка отправителя ---------------------
    @Test
    public void testTransferSendZeroTo() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditJohn, 0);
        double expected = accDepositKatya.getBalance();
        double actual = 150;
        assertEquals(expected, actual);
    }

// ------------------ проверка получателя --------------------------

    @Test
    public void testTransferRecipientZeroTo() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditJohn, 0);
        double expected = accCreditJohn.getBalance();
        double actual = 250;
        assertEquals(expected, actual);
    }


// ***************** переводим ноль с депозита на депозит *************

    // -------------------- проверка отправителя ---------------------
    @Test
    public void testTransferSendZeroDtoD() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accDepositJohn, 0);
        double expected = accDepositKatya.getBalance();
        double actual = 150;
        assertEquals(expected, actual);
    }

// ------------------ проверка получателя --------------------------

    @Test
    public void testTransferRecipientZeroDtoD() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accDepositJohn, 0);
        double expected = accDepositJohn.getBalance();
        double actual = 150;
        assertEquals(expected, actual);
    }

// ***************** переводим копейки  ***********************

    // -------------------- проверка отправителя ---------------------
    @Test
    public void testTransferSenderMTo() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditJohn, 0.1);
        double expected = accDepositKatya.getBalance();
        double actual = 149.99;
        assertEquals(expected, actual);
    }

// ------------------ проверка получателя --------------------------

    @Test
    public void testTransferRecipientMTo() throws NotEnoughFundsException {
        accountService.transfer(accDepositKatya, accCreditJohn, 0.1);
        double expected = accCreditJohn.getBalance();
        double actual = 250.01;
        assertEquals(expected, actual);
    }
}