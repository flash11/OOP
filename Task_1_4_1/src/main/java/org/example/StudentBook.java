package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.OptionalDouble;
import java.util.stream.Stream;

/**
 * class which has all semesters with subjects and marks.
 */

public class StudentBook {

    private ArrayList<Semester> semesters;
    private String name;

    private int finalTask;

    /**
     * create setter and getter.
     * return list of semesters.
     */
    public ArrayList<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(ArrayList<Semester> semesters) {
        this.semesters = semesters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * initialized mark of final task.
     * parameter mark.
     */
    public void setFinalTask(int mark) { 
        this.finalTask = mark; 
    }

    public int getFinalTask() { 
        return finalTask; 
    }

    /**
     * constructor of class. write who own this book.
     * parameter name.
     */
    public StudentBook(String name) {
        this.name = name;
        this.semesters = new ArrayList<Semester>();

    }

    /**
     * add semester to all semesters.
     * parameter sem.
     */
    public void addSemester (Semester sem) {
        semesters.add(sem);
    }

    /**
     * method which calculate average mark.
     * return average mark.
     */
    public double averageMark() {

        Stream<Subject> listOfSubjects = Stream.empty();

        for (var semester : semesters ) {
            listOfSubjects = Stream.concat(listOfSubjects, semester.getListSubjectsOfSemester().stream());
        }

        OptionalDouble avMark = listOfSubjects
                .mapToInt(x -> x.getMark())
                .average();
        if (avMark.isEmpty()){
            return 0.0;
        }
        return Math.ceil(avMark.getAsDouble() * 10) / 10;

    }


    /**
     * read last mark of subject to set red diploma.
     * return true or false.
     */
    public boolean isRedDiploma() {

        Collections.reverse(semesters);

        int count5 = (int) semesters.stream()
                .flatMap(semester -> semester.getListSubjectsOfSemester().stream())
                .distinct()
                /**
                 * combine all streams to one.
                 */
                .mapToInt(Subject::getMark)
                .filter(mark -> mark == 5)
                .count();

        int count4 = (int) semesters.stream()
                .flatMap(semester -> semester.getListSubjectsOfSemester().stream())
                .distinct()
                .mapToInt(Subject::getMark)
                .filter(mark -> mark == 4)
                .count();

        boolean noThreeAtAll = semesters.stream().allMatch(semester -> semester.noThree());

        return finalTask == 5 && ((double) count5 / (count4 + count5)) >= 0.75 && noThreeAtAll;

    }

}
