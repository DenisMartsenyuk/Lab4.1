package commands;

import application.HandlerInput;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды execute_script
 */
public class ExecuteScript implements Command {
    private int quantityParameters = 1;
    private ArrayList<String> parameters;
    private HandlerInput handlerInput;

    public ExecuteScript(HandlerInput handlerInput) {
        this.handlerInput = handlerInput;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Считать и выполнить скрипт из указанного файла. Параметры: file_name.";
    }

    @Override
    public void execute() throws Exception {
        if (parameters.size() != quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
        handlerInput.pushFileBufferedStream(parameters.get(0));
    }
}
