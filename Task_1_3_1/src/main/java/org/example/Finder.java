package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Finder {
    final int BUFFER_SIZE = 1024;
    BufferedReader bufferedPartOfFile;

    // чтение из ресурса и будут относительные пути
    public void openFileFromResources(String filename) {
        // мы обращаемся к объету класса Finder, далее мы получаем информацию о Ресурсах, делаем из этого поток ввода
        InputStream resourceInputStream =
                Finder.class.getClassLoader().getResourceAsStream(filename);

        // теперь он будет принимать char
        InputStreamReader inputStreamReader = new InputStreamReader(
                resourceInputStream, StandardCharsets.UTF_8);

        // читает и загружает в буфер
        bufferedPartOfFile = new BufferedReader(inputStreamReader);

    }

    // чтение из любого пути
    public void openFileFromRoot(String filename) throws IOException {
        bufferedPartOfFile = new BufferedReader(
                new FileReader(filename, StandardCharsets.UTF_8));
    }

    // алгоритм поиска
    public ArrayList<Integer> find(String filename, String subSequence, boolean useResource) throws IOException {

        if (useResource) {
            openFileFromResources(filename);
        }
        else {
            openFileFromRoot(filename);
        }

        char[] tmpBuffer = new char[BUFFER_SIZE];
        int currLen = 0; // проходится по подстроке и считать вхождение
        int expectedPos = -1; // индекс в масштабах одного файла
        int readCounter = -1; // сколько буферов прочитали
        ArrayList<Integer> result = new ArrayList<>();

        while (bufferedPartOfFile.ready()) {
            bufferedPartOfFile.read(tmpBuffer, 0, tmpBuffer.length);
            readCounter++;
            for (int i = 0; i < tmpBuffer.length; i++) {
                if (tmpBuffer[i] == subSequence.charAt(currLen)) {
                    if (currLen == 0) {
                        expectedPos = i + readCounter * BUFFER_SIZE;
                    }
                    int k = 1; // сдвиг внутри буфера от первого попадания
                    currLen++;
                    if (currLen == subSequence.length()) {
                        result.add(expectedPos);
                        expectedPos = -1;
                        currLen = 0;
                        continue; // возвращение к следующему for
                    }

                    // i + k не должно превышать буфер
                    while (expectedPos != -1 && (i + k != tmpBuffer.length)) {
                        if (tmpBuffer[i + k] == subSequence.charAt(currLen)) {
                            k++;
                            currLen++;
                        } else {
                            currLen = 0;
                            break; // выход их while к последнему if
                        }
                        if (currLen == subSequence.length()) {
                            result.add(expectedPos);
                            expectedPos = -1;
                            currLen = 0;
                        }
                    }
                    if (i + k != tmpBuffer.length) {
                        expectedPos = -1;
                    }
                }
            }
        }
        bufferedPartOfFile.close();
        return result;
    }

}
