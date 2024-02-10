package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


/**
 * class for finding subSequence.
 */
public class Finder {
    final int BUFFER_SIZE = 1024;
    BufferedReader bufferedPartOfFile;

    /**
     * First way to read file.
     * Reading from resources, using relative path.
     *
     * @param filename any.
     */
    public void openFileFromResources(String filename) {
        // Обращаемся к объекту класса Finder, далее мы получаем информацию о Ресурсах, делаем из этого поток ввода.
        InputStream resourceInputStream =
                Finder.class.getClassLoader().getResourceAsStream(filename);

        // inputStreamReader принимать char в кодировке utf-8
        InputStreamReader inputStreamReader = new InputStreamReader(
                resourceInputStream, StandardCharsets.UTF_8);

        // Чтение и загрузка в буфер
        bufferedPartOfFile = new BufferedReader(inputStreamReader);
    }


    /**
     * Second way to read file.
     *
     * Any path possible.
     * @param filename anyName.
     * @throws IOException FileNotFound.
     */
    public void openFileFromRoot(String filename) throws IOException {
        bufferedPartOfFile = new BufferedReader(
                new FileReader(filename, StandardCharsets.UTF_8));
    }


    /**
     * Finding algorithm.
     * manually set how to read the file.
     *
     * @param filename anyName.
     * @param subSequence this we search.
     * @param useResource how to open file.
     * @return array of repetition.
     */
    public ArrayList<Integer> find(String filename, String subSequence, boolean useResource) {

        try {
            if (useResource) {
                openFileFromResources(filename);
            } else {
                openFileFromRoot(filename);
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }

        char[] tmpBuffer = new char[BUFFER_SIZE];
        int currLen = 0; // Проходится по подстроке и считает вхождения.
        int expectedPos = -1; // Индекс в масштабах одного файла.
        int readCounter = -1; // Сколько буферов прочитали.

        ArrayList<Integer> result = new ArrayList<>();

        boolean bufferReady;
        try {
            bufferReady = bufferedPartOfFile.ready();
        } catch (IOException e) {
            return null;
        }

        while (bufferReady) {
            try {
                bufferedPartOfFile.read(tmpBuffer, 0, tmpBuffer.length);
            } catch (IOException e) {
                return null;
            }

            readCounter++;

            for (int i = 0; i < tmpBuffer.length; i++) {
                if (tmpBuffer[i] == subSequence.charAt(currLen)) {
                    if (currLen == 0) {
                        expectedPos = i + readCounter * BUFFER_SIZE;
                    }
                    int k = 1; // Сдвиг внутри буфера от первого попадания.
                    currLen++;

                    if (currLen == subSequence.length()) {
                        result.add(expectedPos);
                        expectedPos = -1;
                        currLen = 0;
                        continue; // Comeback to for.
                    }

                    // i + k не должно превышать буфер.
                    while (expectedPos != -1 && (i + k != tmpBuffer.length)) {
                        if (tmpBuffer[i + k] == subSequence.charAt(currLen)) {
                            k++;
                            currLen++;
                        } else {
                            currLen = 0;
                            break; // exit while.
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
            try {
                bufferReady = bufferedPartOfFile.ready();
            } catch (IOException e) {
                return null;
            }
        }
        try {
            bufferedPartOfFile.close();
        } catch (IOException e) {
            return result;
        }
        return result;
    }
}
