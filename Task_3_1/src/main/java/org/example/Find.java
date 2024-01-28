package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.sql.Types.NULL;

//camel case
//interface : implements
//classes : extends

public class Find {

    public static String root = "C:/Users/1/Desktop/OOP/Task_3_1/";

    public static Integer[] find(String filename, String subSequence) throws FileNotFoundException {


        File file = new File(filename);

        if (file.exists() == false) {
            throw new FileNotFoundException();
        }

        if (file.length() == 0 || subSequence.equals("")) {
            Integer[] result = new Integer[]{};
            return result;
        }

        char[] tmpBuffer = new char[256];
        StringBuilder partOfSequence = new StringBuilder();

        int bytesRead = 0;

        try {
            BufferedReader bufferedPartOfFile = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8));

            List<Integer> result = new ArrayList<>();

            int quantSymb = 0;
            int prevK = 0;
            boolean flNewLine = false;

            while ((bytesRead = bufferedPartOfFile.read(tmpBuffer, 0, tmpBuffer.length)) != NULL) {
                for (char c : tmpBuffer) {
                    int cInt = c;

                    if (cInt != 0 && cInt != 13 && c != '\n') {
                        partOfSequence.append(c);
                    }
                }


                String prefixLine = subSequence + '#' + partOfSequence;

                int[] prefixFunc = new int[subSequence.length()];
                int prefixFuncPrev = 0;

                Arrays.fill(prefixFunc, 0);
                for (int i = 1; i < prefixLine.length(); i++) {

                    int k = prefixFuncPrev;
                    while (k > 0 && prefixLine.charAt(k) != prefixLine.charAt(i)) {
                        k = prefixFunc[k - 1];
                    }
                    if (prefixLine.charAt(k) == prefixLine.charAt(i)) {
                        k++;
                    }
                    if (i < subSequence.length()) {
                        prefixFunc[i] = k;
                    }
                    prefixFuncPrev = k;
                    if (k == subSequence.length()) {
                        result.add(i + quantSymb - 2 * subSequence.length());
                    }
                    if (prevK != 0 && k != 0 && k >= subSequence.length() - prevK && flNewLine && i > subSequence.length() + 1 &&
                            !result.contains(quantSymb - 1)) {
                        result.add(quantSymb - 1);
                    }
                    if (i == prefixLine.length() - 1) {
                        prevK = k;
                    }
                    if (i == subSequence.length() + 1 && k == 0) {
                        flNewLine = false;
                    }

                }

                quantSymb += partOfSequence.length();
                partOfSequence.delete(0, partOfSequence.toString().length());
                flNewLine = true;
                char[] newBuff = new char[256];
                tmpBuffer = newBuff.clone();
            }
            Integer[] arr = new Integer[0];
            arr = result.toArray(arr);
            return arr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}