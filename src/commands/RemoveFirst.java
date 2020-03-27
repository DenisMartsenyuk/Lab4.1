package commands;

import collection.ProductList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды remove_first
 */
public class RemoveFirst implements Command {
    private int quantityParameters = 0;
    private ArrayList<String> parameters;
    private ProductList productList;

    public RemoveFirst(ProductList productList) {
        this.productList = productList;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Удалить первый элемент коллекции.";
    }

    @Override
    public void execute() throws Exception {
        if (parameters.size() != quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
        productList.removeFirst();
    }
}
