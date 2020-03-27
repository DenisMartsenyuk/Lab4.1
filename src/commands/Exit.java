package commands;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды exit
 */
public class Exit implements Command {
    private int quantityParameters = 0;
    private ArrayList<String> parameters;

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Завершить программу (без сохранения в файл).";
    }

    @Override
    public void execute() throws Exception{
        if (parameters.size() != quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
        System.exit(0);
    }
}
