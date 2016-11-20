package ru.dvfu.mrcpk.quiz;

/*
Class for List Persons
 */

import java.util.List;

public class Student {

    //Идентификация пользователя
    private String firstname;
    private String middlename;
    private String lastname;

    //Список доступных тестов
    private List<QuizA> quizAListAvail;

    //Пройденные тесты
    private List<QuizA> quizAListDone;


    // Оценки за пройденные тесты
    private List<Float> markDoneQuizId;

    public Student(String firstname, String middlename, String lastname) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }


    public List<String> getTests() {

        return null;
    }
}
