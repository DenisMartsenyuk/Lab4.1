package commands;

import collection.ProductList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды info
 */
public class Info implements Command {
    private int quantityParameters = 0;
    private ArrayList<String> parameters;
    private ProductList productList;

    public Info(ProductList productList) {
        this.productList = productList;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Вывести информацию о коллекции.";
    }

    @Override
    public void execute() throws Exception {
        if (quantityParameters != parameters.size()) {
            throw new IOException("Неверное количество параметров!");
        }
        productList.printInfo();
    }
}
