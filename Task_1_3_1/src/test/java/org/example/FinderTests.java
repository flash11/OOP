package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
public class FinderTests {

    @Test
    public void russianLangTest() {
        Finder finder = new Finder();
        ArrayList<Integer> result;
        result = finder.find("russianLang.txt", "вет", true);
        ArrayList<Integer> predictedList = new ArrayList<>();
        predictedList.add(3);
        Assertions.assertEquals(predictedList, result);
    }

    @Test
    public void koreanLangTest() {
        Finder finder = new Finder();
        ArrayList<Integer> result;
        result = finder.find("specialKoreanSymbols.txt", "녕하", true);
        ArrayList<Integer> predictedList = new ArrayList<>();
        predictedList.add(1);
        Assertions.assertEquals(predictedList, result);
    }

    @Test
    public void newLineTest() {
        Finder finder = new Finder();
        ArrayList<Integer> result;
        result = finder.find("newLine.txt", "e", true);
        ArrayList<Integer> predictedList = new ArrayList<>();
        predictedList.add(6);
        predictedList.add(19);
        predictedList.add(22);
        predictedList.add(28);
        Assertions.assertEquals(predictedList, result);
    }

    static void write2mbTestFile() {
        // 1 UTF-8 english char = 1 byte
        try (FileWriter writer = new FileWriter("large.txt")) {
            int twoMbSize = 1048576 * 2; // 1024 * 1024
            StringBuilder twoMbString = new StringBuilder("a");
            // Быстрое добавление символов на 2 мегабайта.
            for (int i = twoMbSize; i > 1; i /= 2) {
                twoMbString.append(twoMbString);
            }

            twoMbString.replace(10000, 10003, "bbb");
            writer.write(twoMbString.toString());
            writer.flush();
        } catch (Exception e) {
            Assertions.fail("Big file couldn't be created");
        }
    }

    static void delete2mbTestFile() {
        try {
            Files.delete(Paths.get("large.txt"));
        } catch (IOException e) {
            return;
        }
    }

    @Test
    void insaneLargeTest() {
        Finder finder = new Finder();
        write2mbTestFile();
        ArrayList<Integer> result;
        result = finder.find("large.txt", "bbb", false);
        ArrayList<Integer> predictedList = new ArrayList<>();
        predictedList.add(10000);
        Assertions.assertEquals(predictedList, result);
        delete2mbTestFile();
    }
}
