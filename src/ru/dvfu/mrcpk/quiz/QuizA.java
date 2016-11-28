package ru.dvfu.mrcpk.quiz;

/**
 * Класс верхнего уровня для структуры классов тестов
 */
public class QuizA {

    static int id = 0;

    String name;

    private int questionNums;

    public QuizA(String name) {
        this.id++;
        this.name = name;
    }

    public QuizA(int id, String name) {
        this.id = id;
        this.name = name;
        this.questionNums = questionNums;
    }

    public QuizA(String name, int questionNums) {
        this.id++;
        this.name = name;
        this.questionNums = questionNums;
    }

    public QuizA(int id, String name, int questionNums) {
        this.id = id;
        this.name = name;
        this.questionNums = questionNums;
    }

    public void setQuizA(int quizId, String quizName, int questionNums) {
        this.id = quizId;
        this.name = quizName;
        this.questionNums = questionNums;
    }

    public int getQuestionNums() {
        return questionNums;
    }

    public String getName() {
        return name;
    }

    public int getQuizId() {
        return id;
    }

    @Override
    public String toString() {
        return "Quiz: id = " + id + ", name = " + name + ", number of questions = " + questionNums;
    }
}
