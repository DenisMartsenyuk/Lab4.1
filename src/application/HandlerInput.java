package application;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * Класс, обрабатывающий ввод в приложении
 */
public class HandlerInput {
    private BufferedReader bufferedReader;
    private Stack<NamedBufferedReader> bufferedReaderStack;
    private HashSet<String> paths;

    public HandlerInput() throws IOException{
        bufferedReaderStack = new Stack<>();
        paths = new HashSet<>();
        updateSystemInReader();
    }

    /**
     * Метод, который возвращает true - если чтение из System.in, false - из файла
     */
    public boolean isSIN() {
        return bufferedReaderStack.isEmpty();
    }


    /**
     * Метод, который добавляет новый NamedBufferedReader в Stack и открывает его
     */
    private void pushBufferedReader(NamedBufferedReader namedBufferedReader) throws IOException {
        bufferedReaderStack.push(namedBufferedReader);
        bufferedReader = namedBufferedReader.getBufferedReader();
    }

    /**
     * Метод, который убирает отработанный NamedBufferedReader из Stack и открывает следующий
     */
    private void popBufferedReader() throws IOException {
        paths.remove(bufferedReaderStack.peek().getName());
        bufferedReader = bufferedReaderStack.pop().getBufferedReader();
    }

    /**
     * Метод, устанавливает чтение из System.in
     */
    private void updateSystemInReader() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Метод, который открывает файл и добавляет новый NamedBufferedReader
     */
    public void pushFileBufferedStream(String path) throws Exception {
        path = new File(path).getCanonicalPath();
        if (paths.contains(path)) {
            throw new Exception("Вы вызываете команду, из-за которого произойдет рекурсия! Остановка программы!");
        }
        pushBufferedReader(new NamedBufferedReader(path, new BufferedReader(new InputStreamReader(new FileInputStream(path)))));
        paths.add(path);
    }

    /**
     * Метод, считывает данные с активного BufferedReader
     */
    public ArrayList<String> getData() throws IOException {
        String srcData;
        ArrayList<String> data = new ArrayList<>();
        if ((srcData = bufferedReader.readLine()) != null) {
            srcData = srcData.trim();
            srcData = srcData + " ";
            if (srcData.equals(" ")) {
                return null;
            }
            char[] str = srcData.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < str.length; ++i) {
                if(str[i] != ' ') {
                    stringBuilder.append(str[i]);
                }
                else if (str[i - 1] != ' ') {
                    data.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            }
        }
        else {
            data = null;
            if(bufferedReaderStack.size() == 0) {
                updateSystemInReader();
            }
            if(bufferedReaderStack.size() != 0) {
                popBufferedReader();
            }
        }
        return data;
    }
}
