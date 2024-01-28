package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//camel case
//interface : implements
//classes : extends

public class Find {

    public static Integer[] find(String filename, String subSequence) throws FileNotFoundException {

        File file = new File(filename);

        if (file.exists() == false) {
            throw new FileNotFoundException();
        }

        try{}
        catch{}
    }

//принимаем на вход часть файла и тот кусок, который мы в нем ищем
    private static Integer[] prefixFunc(String partOfSequence, String subSequence){


        String str = subSequence + '#' + partOfSequence;

        List<Integer> result = new ArrayList<>();

        int strN = str.length();

        int subSequenceN = subSequence.length();
        Integer[] p = new Integer[subSequenceN];

        Arrays.fill(p, 0);

        for (int i = 1; i < strN; i++){
            int k = p[i - 1];

            while (k > 0 && str.charAt(k) != str.charAt(i)){
                k = p[k - 1];
            }
            if (str.charAt(k) == str.charAt(i)){
                k++;
            }
            p[i] = k;

            if (k == subSequenceN){
                result.add(i);
            }
        }
    }
}
