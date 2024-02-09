package org.example;

import java.util.ArrayList;

public class Semester {

    private ArrayList<Subject> listSubjectsOfSemester;

    /**
     * getter.
     * 
     * @return listSubjectsOfSemester.
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
     * 
     * @param nameOfSubject
     * @param mark
     */
    public void add(String nameOfSubject, int mark) {
        var subj = new Subject(nameOfSubject, mark);
        this.listSubjectsOfSemester.add(subj);
    }

    public void addFutureSubject(String nameOfSubject) {
        var subj = new Subject(nameOfSubject, 0);
        this.listSubjectsOfSemester.add(subj);
    }


    public boolean noThree() {
        return listSubjectsOfSemester.stream().allMatch(subject -> subject.getMark() > 3 );
    }

    public boolean moneyBonus() {
        return listSubjectsOfSemester.stream().allMatch(mark -> mark.getMark() == 5);
    }
}
