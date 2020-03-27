import application.ConsoleApplication;
import com.google.gson.JsonParseException;

public class Main {
    public static void main(String[] args) {
        ConsoleApplication consoleApplication = new ConsoleApplication();
        try {
            consoleApplication.initialization();
            consoleApplication.run();
        }
        catch (JsonParseException | NullPointerException | IllegalArgumentException e) {
            System.out.println("Некорректные данные в json файле!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
