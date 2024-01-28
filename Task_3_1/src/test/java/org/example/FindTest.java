package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;


public class FindTest {

    @ParameterizedTest
    @MethodSource("subSequence")
    void testSearchSubString(String subSequence, String filename, Integer[] expectedResult) throws IOException {
        Integer[] result = Find.find(subSequence, filename);
        Assertions.assertArrayEquals(result, expectedResult);
    }

    @ParameterizedTest
    @MethodSource("NoFileException")
    void testNoFile(String subSequence, String filename, Exception eExpected) {
        try {
            Integer[] result = Find.find(subSequence, filename);
        }
        catch (Exception eReal) {
            Assertions.assertEquals(eReal.getClass(), eExpected.getClass());
        }

    }

    private static Stream<Arguments> find() {
        return Stream.of(
                Arguments.of("a", "src/test/java/org/example/sample2.txt", new Integer[] {0, 4, 5, 6, 7}),

                Arguments.of("b", "src/test/java/org/example/sample3.txt", new Integer[] {3, 4}),
        );
    }

    private static Stream<Arguments> NoFileException() {
        return Stream.of(
                Arguments.of("a", "src/main/java/org/example/noExist.txt",
                        new FileNotFoundException()),
                Arguments.of("a", "src/main/java/org/example/noExist2.txt",
                        new FileNotFoundException()),
                Arguments.of("a", "src/main/java/org/example/noExist3.txt",
                        new FileNotFoundException())
        );
    }

}
