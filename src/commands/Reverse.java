package commands;

import collection.ProductList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды reorder
 */
public class Reverse implements Command {
    private int quantityParameters = 0;
    private ArrayList<String> parameters;
    private ProductList productList;

    public Reverse(ProductList productList) {
        this.productList = productList;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Отсортировать коллекцию в порядке, обратном нынешнему.";
    }

    @Override
    public void execute() throws Exception {
        if (parameters.size() > quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
        productList.reverse();
    }
}
