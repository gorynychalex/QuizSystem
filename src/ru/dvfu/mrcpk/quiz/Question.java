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
    private byte getResultQ;

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

//            System.out.print("optionQAs.get(" + i + ") = " + optionQAs.get(Integer.parseInt(answers.split("(\\p{Punct}|\\p{Blank})")[i])-1).getOption() + "\t");
        }
//        System.out.println();
    }

    public byte markEqual(){
        //Расчет оценки
        float mark = (float) getCorrectUserAnswers()/(float) getSumCorrectOptionsQA()/((float) getInCorrectUserAnswers()+1);
        return (byte)(mark*100);
    }

    // Метод вывода результата пользователя
    public byte getResult(){

        //Объявление переменной счетчика вариантов ответа
//        int i = 0;

        // Переменная для подсчета суммы ответов пользователя
//        int sumUserAnswers = 0;

        //Перебор всех вариантов ответа
//        for(OptionQA optionQA: optionQAs){
            //Строка разделения ответов и преобразование в тип byte
//            optionQA.setUserAnswer(Byte.parseByte(answers.split(" ")[i]));
//            System.out.print(optionQA.defineResult() + "\t");

            //Счетчик правильных ответов
//            i++;

//            sumOptions += optionQA.getCorrectA();

//            sumUserAnswers += optionQA.defineResult();
//        }


//        System.out.println("Всего ответов " + i);
        System.out.println("Сумма правильных ответов " + getSumCorrectOptionsQA());
        System.out.println("Сумма ответов пользователя " + getSumUserAnswers());
        try {
//            int getRes = sumUserAnswers*10/i;
            int getRes = getSumUserAnswers()*10/ getSumCorrectOptionsQA();
            getResultQ = (byte) (getRes > 0 ? getRes : 0);
        } catch (ArithmeticException e){
            System.out.println("Сумма правильных ответов - ноль! Некорректный вопрос!");
            getResultQ = 0;
        }
        System.out.println("Баллов за ответ на вопрос: " + getResultQ);
        System.out.println("Оценка за ответ на вопрос: " + getResultQ/2);
        System.out.println();
        return getResultQ;
    }

    // Метод получения суммы верных ответов
    public int getSumCorrectOptionsQA(){

        int sum=0;
        for(OptionQA o: optionQAs)
            sum += o.getCorrectA();

        return sum;
    }

    // Метод получения суммы ответов пользователя
    public int getSumUserAnswers(){

        int sum=0;
        for(OptionQA o: optionQAs)
            sum += o.defineResult();

        return sum;
    }

    // Метод получения суммы ВЕРНЫХ ответов пользователя
    public int getCorrectUserAnswers(){

        int sum=0;
        for(OptionQA o: optionQAs) {
            if (o.equals(1)) {
                sum += o.defineResult();
            }
        }
        return sum;
    }

    // Метод получения суммы НЕВЕРНЫХ ответов пользователя
    public int getInCorrectUserAnswers(){

        int sum=0;
        for(OptionQA o: optionQAs) {
            if (o.equals(-1)) {
                sum += o.defineResult();
            }
        }
        return -sum;
    }

    // Метод вывода вариантов ответов
    public String getStringOptionQAs(){

        Collections.shuffle(optionQAs);

        StringBuilder s = new StringBuilder();
        int i=1;

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

// Класс для одного ответа
class OptionQA{
    // Вариант ответа
    private String option;

    // Корректность 0 или 1 (false или true)
    private byte correctA;

    //User answer
    private byte userAnswer;


    public byte getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(byte userAnswer) {
        this.userAnswer = userAnswer;
    }

    /* Результат ответа на вопрос (последовательно в тернарном операторе):
       a) Если пользователь выбрал правильный вариант ответа, тогда возвращается '1';
       b) Неверные варианты так и остаются '0';
       c) Если выбран неверный вариант ответа, то '-1';
       d) Если не выбран правильный вариант, тогда 0.
     */
    public byte defineResult(){
//        return (byte) ((correctA == userAnswer)? 1 : -1);
        return (byte) ((correctA == userAnswer)? ((correctA == 0)? 0 : 1) : ((correctA == 0)? -1 : 0));
    }

    public OptionQA(String option, byte correctA) {
        this.option = option;
        this.correctA = correctA;
    }

    public byte getCorrectA() {
        return correctA;
    }

    public void setCorrectA(byte correctA) {
        this.correctA = correctA;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {

        return option;

        //Для тестирования выводится вариант с правильным ответом и ответом пользователя
//        return option + "\t = " + correctA + "\tuserAnswer = " + userAnswer;
    }
}