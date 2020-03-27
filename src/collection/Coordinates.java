package collection;

/**
 * Класс Coordinates
 */
public class Coordinates {
    private double x;
    private int y; //Максимальное значение поля: 999

    public Coordinates(double x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates [x=" + x + ", y=" + y + "]";
    }
}