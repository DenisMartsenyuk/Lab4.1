package commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Класс обработчика команд
 */
public class HandlerCommands {
    private HashMap<String, Command> commands;

    public HandlerCommands() {
        commands = new HashMap<>();
    }

    /**
     * Метод добавления команды
     */
    public void setCommand(String key, Command command) {
        commands.put(key, command);
    }

    /**
     * Метод вызова команды
     */
    public void executeCommand(ArrayList<String> data) throws Exception{
        String name = data.get(0);
        data.remove(0);
        commands.get(name).setParameters(data);
        System.out.println(name + ": ");
        commands.get(name).execute();
        System.out.println("Команда " + name + " выполнена!\n");
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

}
