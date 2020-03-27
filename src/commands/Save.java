package commands;

import application.HandlerJsonFiles;
import collection.ProductList;
import parser.Parser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс команды save
 */
public class Save implements Command {
    private int quantityParameters = 0;
    private ArrayList<String> parameters;
    private ProductList productList;
    private String path;

    public Save(ProductList productList, String path) {
        this.productList = productList;
        this.path = path;
    }

    @Override
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getManual() {
        return "Сохранить коллекцию в файл. (Название файла будет inputFile_output.json).";
    }

    @Override
    public void execute() throws Exception {
        if (parameters.size() != quantityParameters) {
            throw new IOException("Неверное количество параметров!");
        }
        HandlerJsonFiles.saveFile(path, Parser.serialize(productList));
    }
}
