package ru.dvfu.mrcpk.quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuizTest {

    static List<Student> students;

    public static void main(String[] args) throws IOException {

        // Загрузка вариантов тестов
        List<QuizA> quizAList = new ArrayList<>();
        quizAList.add(new QuizA("Java"));
        quizAList.add(new QuizA("Android"));

        for(QuizA quizA: quizAList)
            System.out.println(quizA);


        //Инициализация экзамена
        Quiz quiz = new Quiz("Java");

        // 1 часть - загрузка вопросов

        // распознавание вопросов из файла
        String fileName = "/home/gorynych/java-questions.txt";

        //Открытие потока на считывание файла
        BufferedReader bufferedReaderFile = new BufferedReader(new FileReader(fileName));

        quiz.parseQuestions(bufferedReaderFile);

        bufferedReaderFile.close();

        //Поток для работы студента с вариантами
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        students = new ArrayList<>();

        students.add(new Student("Алексей", "Борисович", "Попович"));

        students.add(new Student("Иван", "Иванович", "Иванов"));

        //Объект управления сущностями Студент и Тесты
        QuizManager quizManager = new QuizManager(students,quizAList);


        //Выбор пользователя
        quizManager.choozeStudent(bufferedReader);

        //Выбор теста
        quizManager.choozeTest(bufferedReader);


//        BufferedReader bufferedReaderFile1 = new BufferedReader(new FileReader(fileName));
//
//        quizManager.choozeTest(bufferedReaderFile1);
//
//        bufferedReaderFile1.close();



        // 2 часть - запуск теста
        quiz.runTest(bufferedReader);

        //Получение результатов
        quiz.getResult();

        bufferedReader.close();
    }
}