package ru.dvfu.mrcpk.quiz;

/*
Основной класс системы тестирования
 */

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuizApp {

//    Map<Integer,String> questionss, addons, optionQA;
//    List<Byte> comparisonQA, validAnswers;

    public static void main(String[] args) throws IOException {

        //Массив с вопросами и ответами
        List<Question> questions = null;

        BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/gorynych/java-questions.txt"));

        int i=0;
        //Список вопросов
        questions = ParseQuestions(bufferedReader);

        System.out.println("Всего "+ questions.size() + " вопросов.");

        System.out.println(questions.get(4));

        bufferedReader.close();
    }

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
                addons.append(s + "\n");
                s = bufferedReader.readLine();
            }

//            System.out.println("Подробность вопроса: " + addons);

            //Объявление массива для вопросов
            List<OptionQA> optionQAs = new ArrayList<>();

            int k = 0;
            while (s.matches("^\\s*(\\-|\\+).*") && bufferedReader.ready()) {
                OptionQA optionQA = null;
                if (s.matches("^\\s*(\\-).*")) {
//                    System.out.println("неверно! " + s);
                    optionQA = new OptionQA(s.substring(1), (byte) 0);
                } else if (s.matches("^\\s*(\\+).*")) {
//                    System.out.println("верно! " + s);
                    optionQA = new OptionQA(s.substring(1), (byte) 1);
                }
                //Добавление ответа
                optionQAs.add(optionQA);
                k++;
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
