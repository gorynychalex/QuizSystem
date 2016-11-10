package ru.dvfu.mrcpk.quiz;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Class Question
 */
public class Question implements Serializable{

    // Вопрос и подробности
    private String question;
    private StringBuilder addons;

    // Варианты ответа
    private List<OptionQA> optionQAs;

    // Результат ответа(ов) на вопрос
    private byte mark;

    // Для заполнения вопроса, сообщается сам вопрос, развернутое содержание и список вариантов ответов.
    public Question(String questions, StringBuilder addons, List<OptionQA> optionQAs) {
        this.question = questions;
        this.addons = addons;
        this.optionQAs = optionQAs;
    }

    public StringBuilder getAddons() {
        return addons;
    }

    public void setAddons(StringBuilder addons) {
        this.addons = addons;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<OptionQA> getOptionQAs() {
        return optionQAs;
    }

    public void setOptionQA(List<OptionQA> optionQAs) {
        this.optionQAs = optionQAs;
    }


    // Метод для регистрации и занесения ответов пользователя
    // Строка с ответами: разделенные запятыми или пробелами варианты ответов 1,3
    public void setUserAnswers(String answers) {

        for (int i = 0; i < answers.split("(\\p{Punct}|\\p{Blank})").length; i++) {

            optionQAs.get(Integer.parseInt(answers.split("(\\p{Punct}|\\p{Blank})")[i])-1).setUserAnswer((byte) 1);

            optionQAs.get(Integer.parseInt(answers.split("(\\p{Punct}|\\p{Blank})")[i])-1).setUserAnswerB(true);
        }
    }

    //Расчет оценки
    public byte markEst(){
        int sumOptionsTrue=0,sumAnsTrue=0,sunAnsFalse=0;
        for(int i = 0; i < optionQAs.size(); i++) {
            if(optionQAs.get(i).isCorrectOpt()) sumOptionsTrue++;
            if(optionQAs.get(i).isCorrectOpt() & optionQAs.get(i).isUserAnswerB()) sumAnsTrue++;
            if( (optionQAs.get(i).isCorrectOpt() ^ optionQAs.get(i).isUserAnswerB()) & !optionQAs.get(i).isUserAnswerB()) {sunAnsFalse++;
                System.out.println("i = " + i);}
        }
//        System.out.println("Sum of all true options = " + sumOptionsTrue);
//        System.out.println("Sum of true answers = " + sumAnsTrue);
//        System.out.println("Sum of false answers = " + sunAnsFalse);
//        System.out.println("mark = КВП/ОКП/(КВН + 1)");

        float mark1 = (float)sumAnsTrue/(float)sumOptionsTrue/((float)sunAnsFalse+1);
        mark = (byte) (mark1*100);

//        System.out.println("Баллов за ответ на вопрос: " + mark);
//        System.out.println("Оценка за ответ на вопрос: " + (float)(5*(float)mark/100));

        return mark;
    }

    public byte getMark(){ return mark; };

    // Метод вывода вариантов ответов
    public String getStringOptionQAs(){

        //Встряска вариантов ответов
        Collections.shuffle(optionQAs);

        //Номер варианта ответа
        int i=1;

        StringBuilder s = new StringBuilder();

        for(OptionQA optionQA: optionQAs){
            s.append(i);
            s.append(")\t");
            s.append(optionQA);
            s.append("\n");
            i++;
        }
        return String.valueOf(s);
    }

    @Override
    public String toString() {
        return question + "\n" + addons.toString() + "\n" + getStringOptionQAs();
    }
}

