package ru.dvfu.mrcpk.quiz;

/**
 * Класс-структура для обозначения тестов
 */
public class QuizA {

    static int quizId = 0;

    String quizName;

    private int questionNums;

    public QuizA(String quizName) {
        this.quizId++;
        this.quizName = quizName;
    }

    public QuizA(int quizId, String quizName) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.questionNums = questionNums;
    }

    public QuizA(String quizName, int questionNums) {
        this.quizId++;
        this.quizName = quizName;
        this.questionNums = questionNums;
    }

    public QuizA(int quizId, String quizName, int questionNums) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.questionNums = questionNums;
    }

    public void setQuizA(int quizId, String quizName, int questionNums) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.questionNums = questionNums;
    }

    public int getQuestionNums() {
        return questionNums;
    }

    public String getQuizName() {
        return quizName;
    }

    public int getQuizId() {
        return quizId;
    }

    @Override
    public String toString() {
        return "Quiz: id = " + quizId + ", name = " + quizName + ", number of questions = " + questionNums;
    }
}
