package ru.dvfu.mrcpk.quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for Quizs - tests
 */
public class Quiz {

    //Имя теста
    private String name;

    //Объявление массива вопросов
    private List<Question> questions;

    //Конструктор теста
    public Quiz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void runTest() throws IOException {
        System.out.println("Запуск теста: " + name);


        // Поток на получение ответов пользователя
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Последовательный вывод вопросов с вариантами ответов. В этом же цикле ввод результатов.
        for(Question question: questions){

            System.out.println(question);

            System.out.println("Введите последовательно номера правильных ответов, разделенные пробелом или запятой: ");

            question.setUserAnswers(bufferedReader.readLine());

            //Вызов метода расчета оценки
            question.markEst();
        }

        bufferedReader.close();

        //Вывод результата пользователя
        System.out.println("Ответы пользователя:");

        int sumMarks = 0;
        for(Question question: questions){
            System.out.print(question.getMark() + "\t");
            sumMarks += question.getMark();
        }

        System.out.println("\nОбщая оценка: " + (5*(float)sumMarks/questions.size()/100));

    }

    //Метод для распознавания текста файла в формате вопроса
    //Не хватает распознавания картинки
    public void parseQuestions (String pathToFile) throws IOException {

        //Открытие потока на считывание файла
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile));

        //Инициализация массива вопросов
        List<Question> questions = new ArrayList<>();

        String s = bufferedReader.readLine();

        // Организация цикла. 1 условие - до конца файла, 2 условие - шаблон поиска знака вопроса, (может быть последовательность пробельных символов или один спецсимвол (в начале файла))
        while (bufferedReader.ready() || s.matches("^(\\s*|.)(\\?).*")){

            //Считывается вопрос
            String question1  = bufferedReader.readLine();
//            System.out.println("Вопрос: " + question1);
            s = bufferedReader.readLine();

            // Шаблон "^\\s*(\\-|\\+).*" - отыскивает знак + или - с начала строки (могут быть пробелы)
            StringBuilder addons = new StringBuilder();
            while (!s.matches("^\\s*(\\-|\\+).*")){
                addons.append(s);
                addons.append("\n");
                s = bufferedReader.readLine();
            }
//            System.out.println("Подробность вопроса: " + addons);

            //Объявление массива для вопросов
            List<OptionQA> optionQAs = new ArrayList<>();
            // Заполнение массива с вопросами
            while (s.matches("^\\s*(\\-|\\+).*") && bufferedReader.ready()) {
                OptionQA optionQA = null;
                if (s.matches("^\\s*(\\-).*")) {
//                    System.out.println("неверно! " + s);
                    optionQA = new OptionQA(s.substring(1), (byte) 0, false);
                } else if (s.matches("^\\s*(\\+).*")) {
//                    System.out.println("верно! " + s);
                    optionQA = new OptionQA(s.substring(1), (byte) 1, true);
                }

                //Добавление ответа в список
                optionQAs.add(optionQA);

                s = bufferedReader.readLine();
            }

            //Добавить вопрос в массив вопросов через создание нового объекта в конструкторе
            questions.add(new Question(question1,addons,optionQAs));

            //Счетчик вопросов
//            System.out.println(i++);
        }

        bufferedReader.close();

        System.out.println("Файл: " + pathToFile);
        System.out.println("Всего распознано "+ questions.size() + " вопроса(-ов). \n");

        //Возврат
        this.questions=questions;
    }
}
