package ua.spalah.bank.ui;

import java.util.Scanner;

/**
 * Created by User on 01.02.2017.
 */
public class ConsoleIO implements IO {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String s) {
        System.out.println(s);
    }
}
