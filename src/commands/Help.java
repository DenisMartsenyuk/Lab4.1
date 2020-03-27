package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Класс команды help
 */
public class Help implements Command {
    private HandlerCommands handlerCommands;
    private int quantityParameters = 1;
    private ArrayList<String> parameters;

    public Help(HandlerCommands handlerCommands) {
        this.handlerCommands = handlerCommands;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Вывести справку по всем командам.\n" +
                "Вы можете ввести \"help команда\", которая выводит справку по указанной команде.";
    }

    @Override
    public void execute() throws Exception{
        if (parameters.size() == 0) {
            for (Map.Entry<String, Command> command : handlerCommands.getCommands().entrySet()){
                System.out.println(command.getKey() + ": " + command.getValue().getManual() + "\n");
            }
        }
        if (parameters.size() == quantityParameters) {
            System.out.println(parameters.get(0) + ": " + handlerCommands.getCommands().get(parameters.get(0)).getManual());
        }
        if (parameters.size() > quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
    }
}
