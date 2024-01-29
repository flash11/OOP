package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Testing my methods.
 */
public class StudentBookTest {


    @Test
    public void averageMarkTest() {
        StudentBook studentBook1 = new StudentBook("Vlad");
        Semester semester1 = new Semester();
        semester1.add("Math", 5);
        semester1.add("Physics", 4);
        Semester semester2 = new Semester();
        semester2.add("Chemistry", 5);
        semester2.add("Biology", 3);
        semester2.add("Physics", 5);
        studentBook1.addSemester(semester1);
        studentBook1.addSemester(semester2);

        assertEquals(4.4, studentBook1.averageMark());
    }

    
    @Test
    public void noThreeTest() {

        Semester semester1 = new Semester();
        semester1.add("Math", 5);
        semester1.add("Physics", 4);

        assertTrue(semester1.noThree());

        semester1.add("History", 3);

        assertFalse(semester1.noThree());

    }


    @Test
    public void isRedDiplomaTest() {

        StudentBook studentBook1 = new StudentBook("Vlad");
        Semester semester1 = new Semester();
        semester1.add("Math", 5);
        semester1.add("Physics", 4);
        studentBook1.addSemester(semester1);
        studentBook1.setFinalTask(3);

        assertFalse(studentBook1.isRedDiploma());

        StudentBook studentBook2 = new StudentBook("Vlad");
        Semester semester2 = new Semester();
        semester2.add("Math", 5);
        semester2.add("Physics", 5);
        studentBook1.addSemester(semester2);
        studentBook1.setFinalTask(5);

        assertTrue(studentBook1.isRedDiploma());
    }

    
    @Test
    public void moneyBonusTest() {

        Semester semester1 = new Semester();
        semester1.add("Math", 5);
        semester1.add("Physics", 5);

        assertTrue(semester1.moneyBonus());

        semester1.add("History", 3);

        assertFalse(semester1.moneyBonus());

    }
}
