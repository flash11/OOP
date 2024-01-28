package org.example;

public class Subject {

    private String nameSubject;
    private int mark;


    public String getNameSubject() {
        return nameSubject;
    }

    public int getMark() { return mark; }

    public void setMark(int mark) {
        this.mark = this.mark;
    }


    public Subject(String nameSubject, int mark) {
        this.nameSubject = nameSubject;
        this.mark = mark;
    }


}