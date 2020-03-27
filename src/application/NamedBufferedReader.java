package application;

import java.io.BufferedReader;

/**
 * Класс, хранящий InputStream и его имя
 */
public class NamedBufferedReader {
    private String name;
    private BufferedReader bufferedReader;

    public NamedBufferedReader(String name, BufferedReader bufferedReader) {
        this.name = name;
        this.bufferedReader = bufferedReader;
    }

    public String getName() {
        return name;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public boolean isSout() {
        return name == null;
    }
}
