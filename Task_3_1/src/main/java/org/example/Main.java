package org.example;

import java.io.IOException;
import java.util.Arrays;

import static org.example.Find.find;
import static org.example.Find.root;

public class Main {
    public static void main(String[] args) throws IOException {
        String subSequence = "a";
        Integer[] result = find(subSequence, root + "src/main/java/org/example/sample.txt");
        System.out.println(Arrays.toString(result));

    }
}
