package ru.dvfu.mrcpk.quiz;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuizTest {

    static List<Student> students;

    public static void main(String[] args) throws IOException {


        //Поток для ввода данных с консоли
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        //Объект управления сущностями Студент и Тесты
        QuizManager quizManager = new QuizManager();

        //Выбор пользователя
        quizManager.choozeStudent(bufferedReader);

        //Выбор теста
        quizManager.choozeTest(bufferedReader);


        // Наименование файла с тестами
        String fileName = "/home/gorynych/java-questions.txt";
        BufferedReader bufferedReaderFile1 = new BufferedReader(new FileReader(fileName));

        // Загрузка вопросов теста из файла
        quizManager.loadTest(new BufferedReader(new FileReader(fileName)));

        bufferedReaderFile1.close();


        // 2 часть - запуск теста
        quizManager.runTest(bufferedReader);

        //Получение результатов
        quizManager.quiz.getResult();

        bufferedReader.close();

        String fileForSave = "/home/gorynych/userResult.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(fileForSave);

        //Сохранение результатов
        quizManager.saveResultToFile(fileOutputStream);

        fileOutputStream.close();
    }
}