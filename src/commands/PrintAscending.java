package commands;

import collection.ProductList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды print_ascending
 */
public class PrintAscending implements Command {
    private int quantityParameters = 0;
    private ArrayList<String> parameters;
    private ProductList productList;

    public PrintAscending(ProductList productList) {
        this.productList = productList;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Вывести элементы коллекции в порядке возрастания";
    }

    @Override
    public void execute() throws Exception {
        if (parameters.size() > quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
        productList.printAscending();
    }
}
