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
    private int finalTask = 0;

    /**
     * create setter and getter.
     * 
     * @return list of semesters.
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
     * 
     * @param mark int.
     */
    public void setFinalTask(int mark) { 
        this.finalTask = mark; 
    }

    /**
     * constructor of class. write who own this book.
     * 
     * @param name string.
     */
    public StudentBook(String name) {
        this.name = name;
        this.semesters = new ArrayList<Semester>();
    }

    /**
     * add semester to all semesters.
     * 
     * @param sem Semester.
     */
    public void addSemester (Semester sem) {
        semesters.add(sem);
    }

    /**
     * method which calculate average mark.
     * 
     * @return average mark.
     */
    public double averageMark() {

        Collections.reverse(semesters);

        Stream<Subject> listOfSubjects = Stream.empty();

        for (var semester : semesters ) {
            listOfSubjects = Stream.concat(listOfSubjects, semester.getListSubjectsOfSemester().stream());
        }

        OptionalDouble avMark = listOfSubjects
                .distinct()
                .mapToInt(x -> x.getMark())
                .filter(mark -> mark != 0)
                .average();
        if (avMark.isEmpty()) {
            return 0.0;
        }
        return Math.ceil(avMark.getAsDouble() * 10) / 10;
    }


    /**
     * read last mark of subject to set red diploma.
     *
     * @return true or false.
     */
    public boolean isRedDiploma() {

        ArrayList<Semester> tmpSemesters = new ArrayList<>(semesters);

        Collections.reverse(tmpSemesters);

        int count5 = countFives(tmpSemesters);
        
        boolean noThreeAtAll = tmpSemesters.stream().allMatch(semester -> semester.noThree());

        int countSubjects = countSubjects(tmpSemesters);
        int countFutureSubs = countFutureSubjects(tmpSemesters);
        
        double avg5 = (count5 + countFutureSubs) / (double) countSubjects;

        if (finalTask != 0) {
            return finalTask == 5 && avg5 >= 0.75 && noThreeAtAll;
        } else {
            return  avg5 >= 0.75 && noThreeAtAll;
        }
    }

    public int max4Allowed() {
        ArrayList<Semester> tmpSemesters = new ArrayList<>(semesters);

        Collections.reverse(tmpSemesters);

        int count5 = countFives(tmpSemesters);

        int countSubjects = countSubjects(tmpSemesters);
        int countFutureSubs = countFutureSubjects(tmpSemesters);


        int min5 = (int) Math.ceil(0.75 * (double) countSubjects - count5);

        return countFutureSubs - min5;
    }

    private int countFives(ArrayList<Semester> semestersReverse) {
        return  (int) semestersReverse.stream()
                .flatMap(semester -> semester.getListSubjectsOfSemester().stream())
                .distinct()
                /**
                 * combine all streams to one.
                 */
                .mapToInt(Subject::getMark)
                .filter(mark -> mark == 5)
                .count();
    }

    private int countSubjects(ArrayList<Semester> semestersReverse) {
        return (int) semestersReverse.stream()
                .flatMap(semester -> semester.getListSubjectsOfSemester().stream())
                .distinct()
                .count();
    }

    private int countFutureSubjects(ArrayList<Semester> semestersReverse) {
        return (int) semestersReverse.stream()
                .flatMap(semester -> semester.getListSubjectsOfSemester().stream())
                .distinct()
                .filter(subject -> subject.getMark() == 0)
                .count();
    }
    
}
