package commands;

import application.HandlerInput;
import collection.Product;
import collection.ProductList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды update
 */
public class UpdateById implements Command {
    private int quantityParameters = 1;
    private ArrayList<String> parameters;
    private HandlerInput handlerInput;
    private ProductList productList;

    public UpdateById(HandlerInput handlerInput, ProductList productList) {
        this.handlerInput = handlerInput;
        this.productList = productList;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Обновить значение элемента коллекции по полю \"id\". Параметры: id \n" +
                "                                                                      {element}.";
    }

    @Override
    public void execute() throws Exception {
        if (parameters.size() != quantityParameters){
            throw new IOException("Неверное количество параметров!");
        }
        try{
            if (!productList.contains(Integer.parseInt(parameters.get(0))))
                throw new IOException("Такой id не найден.");
            productList.updateById(Integer.parseInt(parameters.get(0)), new Product(handlerInput));
        }
        catch (NumberFormatException e) {
            throw new NullPointerException("Некорректный ввод id!");
        }
    }
}
