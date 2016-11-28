package ru.dvfu.mrcpk.quiz;

//Класс для управления тестами и студентами

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuizManager {

    //Объявление переменных, с которыми работает класс

    //Студент, студенты
    private Student student;
    private List<Student> students;

    //Тест-id, список тестов
    private QuizA quizChooze;
    List<QuizA> quizAList;

    // тест
    Quiz quiz;

    //Конструкторы


    public QuizManager() {
    }

    public QuizManager(List<Student> students, List<QuizA> quizAList) {
        this.students = students;
        this.quizAList = quizAList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    //Метод по выбору студента
    public void choozeStudent(BufferedReader bufferedReader) throws IOException {

        //Если пустой список студентов, то добавляем фиктивные ФИО
        if(students == null) {
            students = new ArrayList<>();
            students.add(new Student("Алексей", "Борисович", "Попович"));
            students.add(new Student("Иван", "Иванович", "Иванов"));
        }

        System.out.println("Выберите имя пользователя:");
        int i = 1;
        for(Student student : students){
            System.out.print("[" + i + "] ");
            System.out.print(student.getFirstname() + " ");
            System.out.print(student.getMiddlename() + " ");
            System.out.print(student.getLastname());
            System.out.println();
            i++;
        }
        System.out.println("Или введите 'r', если нужно ввести новое имя: ");

        String input = bufferedReader.readLine();
        if(input.matches(".*\\d.*")){
            student = students.get(Integer.parseInt(input)-1);
            System.out.print(student.getLastname() + ", Вам доступен тест");
        } else {
            System.out.println("Введите Имя Отчество Фамилию");
            String userName = bufferedReader.readLine();

            if(userName.split("\\p{Blank}").length > 1 && userName.split("\\p{Blank}").length < 4 ) {
                student = new Student(userName.split("\\p{Blank}")[0],userName.split("\\p{Blank}")[1],userName.split("\\p{Blank}")[2]);
                students.add(student);
            }
            else {
                System.out.println("Введены некорректные данные. Тест будете проходить анонимно.");
            }
        }
    }

    //Метод выбора теста
    public void choozeTest(BufferedReader bufferedReader) throws IOException {

        if(quizAList == null){
            quizAList = new ArrayList<>();
            quizAList.add(new QuizA("Java"));
            quizAList.add(new QuizA("Android"));
        }

        System.out.println("\nВыберите тест:");
        int i = 1;
        for(QuizA quizA: quizAList){
            System.out.print("[" + i + "] ");
            System.out.println(quizA.getName());
            i++;
        }

        int quizId = Integer.parseInt(bufferedReader.readLine());

        quizChooze = quizAList.get(quizId-1);
    }

    //Загрузка теста
    public void loadTest(BufferedReader bufferedReader) throws IOException {
        quiz = new Quiz(quizChooze.name);

        //Запуск метода, который парсит из файла данные
        quiz.parseQuestions(bufferedReader);
    }


    //Метод-декоратор запуска теста
    public void runTest(BufferedReader bufferedReader) throws IOException {
        quiz.runTest(bufferedReader);
    }

    // Сохранение результата в файл
    public void saveResultToFile(OutputStream outputStream){

        //Инициализация интерфейса с потоком вывода в файл
        PrintWriter printWriter = new PrintWriter(outputStream);

        printWriter.println(new Date());

        printWriter.println("Студент: ");
        printWriter.print(student.getLastname() + " " + student.getFirstname() + "\n");

        // Запуск сохранения данных: вопрос, варианты ответов, правильный ответ, ответ пользователя
        if(quiz != null) {
            //Сохранение шапки
            printWriter.println("Тест: " + quiz.getName());
            printWriter.println();

            //Описание полей
            printWriter.println("Поля:\nN)\t[уст]\t[польз]\t Описание ответа\n");

            int i = 1;
            //Сохранение вопросов
            for (Question question : quiz.getQuestions()) {

                printWriter.println("Вопрос №" + i);
                printWriter.println(question.getQuestion());
                printWriter.println();

                //Сохранение результатов ответов

                for (OptionQA option : question.getOptionQAs()) {
                    printWriter.print(i + ")\t");
                    printWriter.print("[" + option.isCorrectOpt() + "]\t");
                    //Внесение ответа пользователя
                    printWriter.print("[" + option.isUserAnswerB() + "]\t");
                    printWriter.println(option.getOption());
                    i++;
                }

                printWriter.println("Балов: " + question.getMark() + "\n");
            }
            printWriter.println("Количество баллов: " + quiz.getScore());
            printWriter.println("Общая оценка за тест: " + quiz.getMark());

        }

        printWriter.close();
    }

    public QuizA getQuizChooze() {
        return quizChooze;
    }
}