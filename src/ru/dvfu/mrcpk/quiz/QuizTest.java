package ru.dvfu.mrcpk.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuizTest {

    public static void main(String[] args) throws IOException {

        Quiz quiz = new Quiz("Java");

        // 1 часть - загрузка вопросов

        // распознавание вопросов из файла
        String fileName = "/home/gorynych/java-questions.txt";
        quiz.parseQuestions(fileName);

        // 2 часть - запуск теста
        quiz.runTest();


    }
}
