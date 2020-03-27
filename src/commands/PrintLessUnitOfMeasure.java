package commands;

import collection.ProductList;
import collection.UnitOfMeasure;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды filter_less_than_unit_of_measure
 */
public class PrintLessUnitOfMeasure implements Command {

    private int quantityParameters = 1;
    private ArrayList<String> parameters;
    private ProductList productList;

    public PrintLessUnitOfMeasure(ProductList productList) {
        this.productList = productList;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Вывести элементы, значения поля  \"unitOfMeasure\" которых меньше заданного. Параметры: unitOfMeasure";
    }

    @Override
    public void execute() throws Exception {
        if (parameters.size() != quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
        productList.printLessThanUnitOfMeasure(UnitOfMeasure.valueOf(parameters.get(0).toUpperCase()));
    }
}
