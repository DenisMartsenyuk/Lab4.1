package collection;

import application.HandlerInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс Organization
 */
public class Organization {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long annualTurnover; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null

    public Organization(String name, long annualTurnover, OrganizationType type) {
        this.name = name;
        this.annualTurnover = annualTurnover;
        this.type = type;
    }

    /**
     * Конструктор класса, который считывает Organization из потока ввода
     */
    public Organization(HandlerInput handlerInput) throws IOException {
        boolean isSout = handlerInput.isSIN();
        boolean reading = true;
        boolean flagAnnualTurnover = true;
        while (reading){
            try {
                if (name == null) {
                    if(isSout)
                        System.out.println("Введите значение поля \"name\" для \"manufacturer\" одним словом.");
                    ArrayList<String> data = handlerInput.getData();
                    setName(data);
                }

                if (flagAnnualTurnover) {
                    if(isSout)
                        System.out.println("Введите значение поля \"annualTurnover\" для \"manufacturer\". Оно ЦЕЛОЕ!");
                    ArrayList<String> data = handlerInput.getData();
                    setAnnualTurnover(data);
                    flagAnnualTurnover = false;
                }

                if (type == null) {
                    if(isSout){
                        System.out.println("Введите значение поля \"type\" для \"manufacturer\". Принимаемые значения:");
                        System.out.println(Arrays.toString(OrganizationType.values()));
                    }
                    ArrayList<String> data = handlerInput.getData();
                    setType(data);
                }

                reading = false;

            }
            catch (NullPointerException e) {
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
    }

    private void setName(ArrayList<String> data) throws IOException {
        if (data.size() != 1) {
            throw new IOException();
        }
        this.name = data.get(0);
    }

    private void setAnnualTurnover(ArrayList<String> data) throws IOException {
        if (data.size() != 1) {
            throw new IOException();
        }
        this.annualTurnover = Long.parseLong(data.get(0));
    }

    private void setType(ArrayList<String> data) throws IOException {
        if (data.size() != 1) {
            throw new IOException();
        }
        this.type = OrganizationType.valueOf(data.get(0).toUpperCase());
    }

    public void setId(HandlerId handlerId) {
        id = handlerId.provideId();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getAnnualTurnover() {
        return annualTurnover;
    }

    public OrganizationType getOrganizationType() {
        return type;
    }

    @Override
    public String toString() {
        return "Organization [id=" + id + ", name=" + name + ", annualTurnover=" + annualTurnover + ", type=" +type + " ]";
    }
}