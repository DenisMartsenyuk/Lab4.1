package commands;

import application.HandlerInput;
import collection.Product;
import collection.ProductList;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Класс команды add
 */
public class Add implements Command {
    private int quantityParameters = 0;
    private ArrayList<String> parameters;
    private HandlerInput handlerInput;
    private ProductList productList;

    public Add(HandlerInput handlerInput, ProductList productList) {
        this.handlerInput = handlerInput;
        this.productList = productList;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Добавить новый элемент в коллекцию. Параметры: {element}.";
    }

    @Override
    public void execute() throws Exception {
        if (parameters.size() != quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
        productList.add(new Product(handlerInput));
    }
}
