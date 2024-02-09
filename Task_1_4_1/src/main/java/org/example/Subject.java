package org.example;

/**
 * Subject class.
 */
public class Subject {

    private String nameSubject;
    private int mark;


    public String getNameSubject() {
        return nameSubject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }


    public Subject(String nameSubject, int mark) {
        this.nameSubject = nameSubject;
        this.mark = mark;
    }

    @Override
    public boolean equals(Object obj) {
        //только ссылка проверяется
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Subject subject = (Subject) obj;
        return this.nameSubject.equals(subject.nameSubject);
    }

    @Override
    public int hashCode() {
        int total = 31;
        total = total * 31 * (nameSubject == null ? 0 : nameSubject.hashCode());
        return total;
    }
}
