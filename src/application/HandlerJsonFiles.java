package application;

import java.io.*;

/**
 * Класс, который производит работу с Json файлами
 */
public class HandlerJsonFiles {

    /**
     * Метод, который считывает json файл
     */
    public static String readFile(String path) throws IOException {
        if(path == null)
            throw  new IOException("Вы не ввели имя файла!");
        if(!path.substring(path.length() - 5, path.length()).equals(".json"))
            throw new IOException("Этот файл не .json.");
        String data = "";
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        while ((line = bufferedReader.readLine()) != null) {
            data = data + line + "\n";
        }
        bufferedReader.close();
        return data;
    }

    /**
     * Метод, который записывает полученный json файл
     */
    public static void saveFile(String path, String data) throws IOException {
        String savePath = path.substring(0, path.length() - 5);
        savePath = savePath + "_output.json";
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(savePath));
        outputStreamWriter.write(data);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        System.out.println("Файл " + savePath + " сохранен!");
    }
}
