package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


public class FinderTests {

    @Test
    public void russianLangtest() {
        Finder finder = new Finder();
        ArrayList<Integer> result;
        result = finder.find("russianLang.txt", "вет", true);
        ArrayList<Integer> predictedList = new ArrayList<>();
        predictedList.add(3);
        Assertions.assertEquals(predictedList, result);
    }

    @Test
    public void koreanLangtest() {
        Finder finder = new Finder();
        ArrayList<Integer> result;
        result = finder.find("specialKoreanSymbols.txt", "녕하", true);
        ArrayList<Integer> predictedList = new ArrayList<>();
        predictedList.add(1);
        Assertions.assertEquals(predictedList, result);
    }

    static void write16gbTestFile() {
        try (FileWriter writer = new FileWriter("large.txt")) {
            int twoMbSize = 1048576 * 2;
            StringBuilder twoMbString = new StringBuilder("w");
            for (int i = twoMbSize; i > 1; i /= 2) {
                twoMbString.append(twoMbString);
            }
            String twoMbBs = twoMbString.toString();
            twoMbString.replace(1000000, 1000002, "qq");
            writer.write(twoMbString.toString());
            writer.flush();
        } catch (Exception e) {
            Assertions.fail("Big file couldn't be created");
        }
    }

    static void delete16gbTestFile() {
        try {
            Files.delete(Paths.get("large.txt"));
        } catch (IOException e) {
            return;
        }
    }

    @Test
    void insaneLargeTest() {
        Finder finder = new Finder();
        write16gbTestFile();
        ArrayList<Integer> result;
        result = finder.find("large.txt", "qq", false);
        ArrayList<Integer> predictedList = new ArrayList<>();
        predictedList.add(1000000);
        Assertions.assertEquals(predictedList, result);
        delete16gbTestFile();
    }
}
