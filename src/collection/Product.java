package collection;

import application.HandlerInput;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс Product
 */
public class Product {
    private Integer id; //Поле не может быть null, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private String partNumber; //Поле может быть null
    private Long manufactureCost; //Поле не может быть null
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Organization manufacturer; //Поле может быть null

    public Product(String name, Coordinates coordinates, double price, String partNumber, Long manufactureCost, UnitOfMeasure unitOfMeasure, Organization manufacturer){
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    /**
     * Конструктор класса, который считывает Product из потока ввода
     */
    public Product(HandlerInput handlerInput) throws Exception {
        boolean isSout = handlerInput.isSIN();
        boolean reading = true;
        boolean flagPrice = true;
        while (reading){
            try {
                if (name == null) {
                    if(isSout)
                        System.out.println("Введите значение поля \"name\" одним словом.");
                    ArrayList<String> data = handlerInput.getData();
                    setName(data);
                }

                if (coordinates == null) {
                    if(isSout)
                        System.out.println("Введите значение поля \"coordinates\" -  X и Y через пробел. " +
                                           "X - дробное, его надо записать через ТОЧКУ! Y - целое.");
                    ArrayList<String> data = handlerInput.getData();
                    setCoordinates(data);
                }

                if (flagPrice) {
                    if(isSout)
                        System.out.println("Введите значение поля \"price\". Его значение должно быть > 0. Если число дробное, то его надо записать через ТОЧКУ!");
                    ArrayList<String> data = handlerInput.getData();
                    setPrice(data);
                    flagPrice = false;
                }

                if (partNumber == null) {
                    if(isSout)
                        System.out.println("Введите значение поля \"partNumber\" одним словом.");
                    ArrayList<String> data = handlerInput.getData();
                    setPartNumber(data);
                }

                if (manufactureCost == null) {
                    if(isSout)
                        System.out.println("Введите значение поля \"manufactureCost\" - Оно ЦЕЛОЕ!");
                    ArrayList<String> data = handlerInput.getData();
                    setManufactureCost(data);
                }

                if (unitOfMeasure == null) {
                    if(isSout) {
                        System.out.println("Введите значение поля \"unitOfMeasure\". Принимаемые значения:");
                        System.out.println(Arrays.toString(UnitOfMeasure.values()));
                    }
                    ArrayList<String> data = handlerInput.getData();
                    setUnitOfMeasure(data);
                }

                manufacturer = new Organization(handlerInput);
                reading = false;

            }
            catch (NullPointerException e)  {
                if (!isSout)
                    throw new  IOException("Неверные значения полей в команде!");
                System.out.println("Вы ничего не ввели!");
            }
            catch (NumberFormatException e) {
                if (!isSout)
                    throw new  IOException("Неверные значения полей в команде!");
                System.out.println("Некорректный ввод числа!");
            }
            catch (IllegalArgumentException e) {
                if (!isSout)
                    throw new  IOException("Неверные значения полей в команде!");
                System.out.println("Введенное значение не подходит под перечень!");
            }
            catch (IOException e) {
                if (!isSout)
                    throw new  IOException("Неверные значения полей в команде!");
                System.out.println("Некорректный ввод!");
            }
            catch (Exception e) {
                if (!isSout)
                    throw new  IOException("Неверные значения полей в команде!");
                System.out.println("Вы выявили новый баг! Ну что же, будем фиксить) " + e.getMessage());
            }
        }
        this.creationDate = ZonedDateTime.now();
    }

    private void setName(ArrayList<String> data) throws IOException {
        if (data.size() != 1) {
            throw new IOException();
        }
        this.name = data.get(0);
    }

    private void setCoordinates(ArrayList<String> data) throws IOException {
        if (data.size() != 2){
            throw new IOException();
        }
        this.coordinates = new Coordinates(Double.parseDouble(data.get(0)), Integer.parseInt(data.get(1)));
    }

    private void setPrice(ArrayList<String> data) throws IOException {
        if (data.size() != 1) {
            throw new IOException();
        }
        if (Double.parseDouble(data.get(0)) < 0) {
            throw new NullPointerException();
        }
        this.price = Double.parseDouble(data.get(0));
    }

    private void setPartNumber(ArrayList<String> data) throws IOException {
        if (data.size() != 1) {
            throw new IOException();
        }
        this.partNumber = data.get(0);
    }

    private void setManufactureCost(ArrayList<String> data) throws IOException {
        if (data.size() != 1) {
            throw new IOException();
        }
        this.manufactureCost = Long.parseLong(data.get(0));
    }

    private void setUnitOfMeasure(ArrayList<String> data) throws IOException {
        if (data.size() != 1) {
            throw new IOException();
        }
        this.unitOfMeasure = UnitOfMeasure.valueOf(data.get(0).toUpperCase());
    }

    public void setId(HandlerId handlerId) {
        id = handlerId.provideId();
        manufacturer.setId(handlerId);
    }

    public void updateProduct(Product product) {
        name = product.name;
        coordinates = product.coordinates;
        price = product.price;
        partNumber = product.partNumber;
        manufactureCost = product.manufactureCost;
        unitOfMeasure = product.unitOfMeasure;
        manufacturer = product.manufacturer;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public double getPrice() {
        return price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public Long getManufactureCost() {
        return manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return  "Product [id=" + id + ", \n" +
                "         name=" + name + ", \n" +
                "         coordinates=" + coordinates.toString() + ", \n" +
                "         creationDate=" + creationDate.toString() + ", \n" +
                "         price=" + price + ", \n" +
                "         partNumber=" + partNumber + ", \n" +
                "         manufactureCost=" + manufactureCost + ", \n" +
                "         unitOfMeasure=" + unitOfMeasure + ", \n" +
                "         manufacturer= " + manufacturer.toString() + "]";
    }
}