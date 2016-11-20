package ru.dvfu.mrcpk.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Абстрактный класс для определения основных полей теста
 */
public abstract class QuizAb extends QuizA{

    //Объявление массива вопросов
    private List<Question> questions;

    public QuizAb(String quizName) {
        super(quizName);
    }

    public abstract void runTest(BufferedReader bufferedReader) throws IOException;

    public abstract void getResult();

    public abstract void parseQuestions(BufferedReader bufferedReader) throws IOException;

}
