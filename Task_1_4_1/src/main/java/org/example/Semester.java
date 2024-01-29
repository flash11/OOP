package org.example;

import java.util.ArrayList;

/**
* class to create semester.
*/

public class Semester {

    private ArrayList<Subject> listSubjectsOfSemester;

    /**
     * getter.
     * return subjects. 
     */

    public ArrayList<Subject> getListSubjectsOfSemester() {
        return listSubjectsOfSemester;
    }


    /**
     * constructor.
     */
    public Semester() {
        this.listSubjectsOfSemester = new ArrayList<Subject>();
    }

    /**
     * method which add subjects and marks.
     * param1 is nameOfSubject.
     * param2 is mark.
     */
    public void add(String nameOfSubject, int mark) {
        var subj = new Subject(nameOfSubject, mark);
        this.listSubjectsOfSemester.add(subj);
    }


    public boolean noThree() {
        return listSubjectsOfSemester.stream().allMatch(subject -> subject.getMark() > 3 );
    }

    public boolean moneyBonus() {
        return listSubjectsOfSemester.stream().allMatch(mark -> mark.getMark() == 5);
    }
}
