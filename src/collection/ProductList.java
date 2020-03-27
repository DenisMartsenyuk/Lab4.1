package collection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Класс ProductList, который производит действия над коллекцией
 */
public class ProductList {

    private ArrayList<Product> products;
    private HandlerId handlerId;

    public ProductList() {
        products = new ArrayList<>();
        handlerId = new HandlerId();
    }

    /**
     * Метод, проверяющий наличие id в коллекции
     */
    public boolean contains(Integer id) {
        return handlerId.contains(id);
    }

    /**
     * Метод, возвращающий ArrayList из Product
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * Метод, который выводит информацию о колекции
     */
    public void printInfo() {
        System.out.println("Тип коллекции: " + products.getClass() + ", Размер: " + products.size());
    }

    /**
     * Метод, добавляющий Product в коллекцию
     */
    public void add(Product item) {
        item.setId(handlerId);
        products.add(item);
    }

    /**
     * Метод, добавляющий Product в коллекцию, если он станет наименьшим
     */
    public void addIfMin(Product item) {
        double price = Collections.min(products, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return Double.compare(product.getPrice(), t1.getPrice());
            }
        }).getPrice();
        if (price > item.getPrice()) {
            add(item);
        }
    }

    /**
     * Метод, выставляющий коллекцию в обратном порядке
     */
    public void reverse() {
        Collections.reverse(products);
    }

    /**
     * Метод, очищающий коллекцию
     */
    public void clear() {
        handlerId.clear();
        products.clear();
    }

    /**
     * Метод, удаляющий Product из коллекции по id
     */
    public void removeById(int id) throws Exception{
        if (!handlerId.contains(id))
            throw new IOException("Такой id не найден.");
        for (Product product : products) {
            if(product.getId() == id) {
                remove(product);
                return;
            }
            if(product.getManufacturer().getId() == id) {
                System.out.println("Вы не можете удалить \"manufacturer\", потому что оно не может быть \"Null\".");
                return;
            }
        }
    }

    /**
     * Метод, удаляющий Product из коллекции по индексу в коллекции
     */
    public void remove(int index) {
        handlerId.removeId(products.get(index).getId());
        products.remove(index);
    }

    /**
     * Метод, удаляющий Product из коллекции
     */
    public void remove(Product product) {
        handlerId.removeId(product.getId());
        products.remove(product);
    }

    /**
     * Метод, удаляющий первый Product из коллекции
     */
    public void removeFirst() throws Exception{
        try {
            remove(0);
        }
        catch (IndexOutOfBoundsException e) {
            throw new Exception("Коллекция пуста, первого элемента нет!");
        }
    }

    /**
     * Метод, заменяющий Product из коллекции по id
     */
    public void updateById(int id, Product item) {
        for (Product product : products) {
            if(product.getId() == id) {
                product.updateProduct(item);
                return;
            }
            if(product.getManufacturer().getId() == id) {
                System.out.println("Вы не можете заменить этот элемент, потому что \"Product\" это не \"manufacturer\".");
                return;
            }
        }
    }

    /**
     * Метод, удаляющий все Product из коллекции, значения поля manufactureCost которого эквивалентно заданному
     */
    public void removeAllByManufactureCost(Long manufactureCost) {
        for (int i = 0; i < products.size(); ++i)
        {
            if (products.get(i).getManufactureCost().equals(manufactureCost)) {
                remove(products.get(i));
                i--;
            }
        }
    }

    /**
     * Метод, выводящий элементы коллекции в строковом представлении, значение поля unitOfMeasure которых меньше заданного
     */
    public void printLessThanUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == UnitOfMeasure.SQUARE_METERS || unitOfMeasure == UnitOfMeasure.MILLIGRAMS || unitOfMeasure == UnitOfMeasure.MILLILITERS)
            return;
        for (Product product : products) {
            if (unitOfMeasure.ordinal() > product.getUnitOfMeasure().ordinal() && unitOfMeasure.ordinal() - product.getUnitOfMeasure().ordinal() == 1) {
                System.out.println(product.toString());
            }
        }
    }

    /**
     * Метод, сортирующий коллекцию по возрастанию Price
     */
    public void sort() {
        this.sort((product, t1) -> Double.compare(product.getPrice(), t1.getPrice()));
    }

    /**
     * Метод, сортирующий коллекцию по возрастанию заданному Comparator
     */
    public void sort(Comparator<Product> comparator) {
        products.sort(comparator);
    }

    /**
     * Метод, выводящий в строковом представлении коллекцию по возрастанию Price
     */
    public void printAscending() {
        ArrayList<Product> result = new ArrayList<>(products);
        result.sort((product, t1) -> Double.compare(product.getPrice(), t1.getPrice()));
        for (Product product : result) {
            System.out.println(product.toString());
        }
    }

    /**
     * Метод, выводящий коллекцию в строковом представлении
     */
    public void print() {
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
}
