package commands;

import java.util.ArrayList;

/**
 * Интерфейс для команд
 */
public interface Command {

    /**
     * Метод, устанавливающий аргументы для команды
     */
    void setParameters(ArrayList<String> parameters);

    /**
     * Метод, возвращающий справку о команде
     */
    String getManual();

    /**
     * Метод, выполняющий саму команду
     */
    void execute() throws Exception;
}
