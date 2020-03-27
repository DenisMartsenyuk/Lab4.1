package commands;

import collection.ProductList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды remove_all_by_manufacture_cost
 */
public class RemoveByManufactCost implements Command {

    private int quantityParameters = 1;
    private ArrayList<String> parameters;
    private ProductList productList;

    public RemoveByManufactCost(ProductList productList) {
        this.productList = productList;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Удалить элементы из коллекции, значение поля \"manufactureCost\" которого эквивалентно заданному. Параметры: manufactureCost.";
    }

    @Override
    public void execute() throws Exception {
        if (parameters.size() != quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
        try {
            productList.removeAllByManufactureCost(Long.parseLong(parameters.get(0)));
        }
        catch (NumberFormatException e) {
            throw new NullPointerException("Некорректный числа!");
        }
    }
}
