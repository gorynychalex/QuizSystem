package ru.dvfu.mrcpk.quiz;

/*
Основной класс системы тестирования
 */

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class QuizApp {

    // Массивы для получения данных в императивном стиле
    // Массив с вопросами, массив с подробностями вопросов, массив с вариантами ответов
//    Map<Integer,String> questionss, addons, optionQA;
    // Массив с соответствиями вопросов и вариантами ответов, массив с правильными вариантами ответов, массив с ответами пользователя.
//    List<Byte> comparisonQA, validAnswers, userAnswers;



    public static void main(String[] args) throws IOException {


        //Массив с вопросами и ответами
        List<Question> questions = null;

        //Массив с ответами пользователя
        List<Byte> answerUser = new ArrayList<>();

        //Счетчик неверных ответов
        int wrongAnswers=0;

        // 1 часть - распознавание вопросов из файла и сохранение в виде объектов.
        String fileName = "/home/gorynych/java-questions.txt";

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        // Запуск метода для считывания данных из файла, открытого в предыдущем потоке. Результат возвращается в виде списка вопросов.
        questions = ParseQuestions(bufferedReader);

        bufferedReader.close();
        System.out.println("Файл: " + fileName);
        System.out.println("Всего распознано "+ questions.size() + " вопросов. \n");


        // 2 часть - опрос.

        // Объект List<Questions> questions - получен из файла. Нужно из БД!!!

        Collections.shuffle(questions);

        // Поток на получение ответов пользователя
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));

        // Последовательный вывод вопросов с вариантами ответов. В этом же цикле ввод результатов.
        for(Question question: questions){
            System.out.println(question);
            System.out.println("Введите последовательно ответы, разделенные пробелом или запятой: ");
            question.setUserAnswers(bufferedReader1.readLine());
            }

        bufferedReader1.close();

        System.out.println("Ответы пользователя:");

        for (int j = 0; j < answerUser.size(); j++) {
            System.out.print(j + "\t");
        }

        System.out.println();

        int sum = 0;
        for(Byte a: answerUser){
            System.out.print(a + "\t");
            sum += a;
        }
        try {
            System.out.println("Общий бал: " + (float)(sum/(2*answerUser.size())));
        } catch (ArithmeticException e){
            System.out.println("ответов не зафиксировано");

        }

    }

    //Метод для распознавания текста файла в формате вопроса
    //Не хватает распознавания картинки
    public static List<Question> ParseQuestions (BufferedReader bufferedReader) throws IOException {

        // Объявление и инициализация массива
        List<Question> questions = new ArrayList<>();

        //Счетчик списка вопросов
        int i = 0;

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
            //Объявление ассоциативного массива
            Map<String,OptionQA> optionQAMap = new HashMap<>();

            int k = 0;
            while (s.matches("^\\s*(\\-|\\+).*") && bufferedReader.ready()) {
                OptionQA optionQA = null;
                if (s.matches("^\\s*(\\-).*")) {
//                    System.out.println("неверно! " + s);
                    optionQA = new OptionQA(s.substring(1), (byte) 0, false);
                } else if (s.matches("^\\s*(\\+).*")) {
//                    System.out.println("верно! " + s);
                    optionQA = new OptionQA(s.substring(1), (byte) 1, true);
                }
                //Добавление ответа
                optionQAs.add(optionQA);
                k++;
                optionQAMap.put(String.valueOf(k),optionQA);
                s = bufferedReader.readLine();
            }

            //Добавить вопрос в массив вопросов через создание нового обеъкта в конструкторе
            questions.add(new Question(question1,addons,optionQAs));

            //Счетчик вопросов
//            System.out.println(i++);
        }

        return questions;
    }
}
