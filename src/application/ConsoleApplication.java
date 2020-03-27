package application;

import collection.ProductList;
import com.google.gson.JsonParseException;
import commands.*;
import parser.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс приложения
 */
public class ConsoleApplication {
    String path;

    private ProductList productList;
    private HandlerInput handlerInput;
    private HandlerCommands handlerCommands;

    private Command reverseProductList;
    private Command clearProductList;
    private Command showProductList;
    private Command exitProgram;
    private Command help;
    private Command infoAboutCollection;
    private Command printAscending;
    private Command removeFirst;
    private Command removeById;
    private Command removeByManufactCost;
    private Command save;
    private Command filterUnitOfMeasure;
    private Command addElement;
    private Command addIfMin;
    private Command updateElementById;
    private Command executeScript;

    /**
     * Инициализация приложения
     */
    public void initialization() throws IOException, JsonParseException {
        handlerCommands = new HandlerCommands();
        handlerInput = new HandlerInput();

        path = System.getenv("INPUT_FILE");
        String data = HandlerJsonFiles.readFile(path);
        productList = Parser.deserialize(data);
        System.out.println("Коллекция заполнена.");

        reverseProductList = new Reverse(productList);
        clearProductList = new Clear(productList);
        showProductList = new Show(productList);
        exitProgram = new Exit();
        help = new Help(handlerCommands);
        infoAboutCollection = new Info(productList);
        printAscending = new PrintAscending(productList);
        removeFirst = new RemoveFirst(productList);
        removeById = new RemoveById(productList);
        removeByManufactCost = new RemoveByManufactCost(productList);
        save = new Save(productList, path);
        filterUnitOfMeasure = new PrintLessUnitOfMeasure(productList);
        addElement = new Add(handlerInput, productList);
        addIfMin = new AddIfMin(handlerInput, productList);
        updateElementById = new UpdateById(handlerInput, productList);
        executeScript = new ExecuteScript(handlerInput);

        handlerCommands.setCommand("reorder", reverseProductList);
        handlerCommands.setCommand("clear", clearProductList);
        handlerCommands.setCommand("show", showProductList);
        handlerCommands.setCommand("exit", exitProgram);
        handlerCommands.setCommand("help", help);
        handlerCommands.setCommand("info", infoAboutCollection);
        handlerCommands.setCommand("print_ascending", printAscending);
        handlerCommands.setCommand("remove_first", removeFirst);
        handlerCommands.setCommand("remove_by_id", removeById);
        handlerCommands.setCommand("remove_all_by_manufacture_cost", removeByManufactCost);
        handlerCommands.setCommand("save", save);
        handlerCommands.setCommand("filter_less_than_unit_of_measure", filterUnitOfMeasure);
        handlerCommands.setCommand("add", addElement);
        handlerCommands.setCommand("add_if_min", addIfMin);
        handlerCommands.setCommand("update", updateElementById);
        handlerCommands.setCommand("execute_script", executeScript);

        System.out.println("Консольное приложение запущено.");

    }

    /**
     * Метод запуска приложения
     */
    public void run() throws IOException{
        System.out.println("Введите \"help\" для просмотра перечня команд.\nВводите команды:");
        while (true) {
            try {
                ArrayList<String> data;
                if ((data = handlerInput.getData()) != null) {
                    handlerCommands.executeCommand(data);
                }
            }
            catch (NullPointerException e) {
                System.out.println("Не найдена такая команда! Введите \"help\" для просмотра перечня команд.");
            }

            catch (FileNotFoundException e) {
                System.out.println("Ошибка доступа к файлу: " + e.getMessage());
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

}
