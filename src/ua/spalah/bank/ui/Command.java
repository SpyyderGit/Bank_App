package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.OverdraftLimitExceededException;

/**
 * Created by Oleg on 15.01.2017.
 */
public interface Command {
    // взаимодействует с клиетом читая его ввод с консоли и печатая ему ответы
    void execute() throws ClientNotFoundException, OverdraftLimitExceededException;

    // выводит информацию о команде в консоль
    void printCommandInfo();
}
