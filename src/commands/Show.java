package commands;

import collection.ProductList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды show
 */
public class Show implements Command {
    private int quantityParameters = 0;
    private ArrayList<String> parameters;
    private ProductList productList;

    public Show(ProductList productList) {
        this.productList = productList;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Вывести все элементы коллекции в строковом представлении.";
    }

    @Override
    public void execute() throws Exception {
        if (parameters.size() != quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
        productList.print();
    }
}
