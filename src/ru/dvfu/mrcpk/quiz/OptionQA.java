package ru.dvfu.mrcpk.quiz;

/**
 * Created by gorynych on 11.11.16.
 */ // Класс для одного ответа
class OptionQA{
    // Вариант ответа
    private String option;

    // Корректность 0 или 1
    private byte correctA;

    // Correct of answer: true or false
    private boolean correctOpt;

    public OptionQA(String option, byte correctA, boolean correctOpt) {
        this.option = option;
        this.correctA = correctA;
        this.correctOpt = correctOpt;
    }

    //User answer
    private byte userAnswer;

    //User answer boolean
    private boolean userAnswerB;

    public boolean isCorrectOpt() {
        return correctOpt;
    }

    public void setCorrectOpt(boolean correctOpt) {
        this.correctOpt = correctOpt;
    }

    public byte getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(byte userAnswer) {
        this.userAnswer = userAnswer;
    }

    /* Результат ответа на вопрос (последовательно в тернарном операторе):
       a) Если выбран правильный вариант ответа, тогда возвращается '1';
       b) Неверные варианты так и остаются '0';
       c) Если выбран неверный вариант ответа, то '-1';
       d) Если не выбран правильный вариант, тогда 0.
     */
//    public byte defineResult(){
//        return (byte) ((correctA == userAnswer)? ((correctA == 0)? 0 : 1) : ((correctA == 0)? -1 : 0));
//    }

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
//        return option + "\t = " + correctA + "\t = " + correctOpt + "\tuserAnswer = " + userAnswer + "\t";
    }

    public boolean isUserAnswerB() {
        return userAnswerB;
    }

    public void setUserAnswerB(boolean userAnswerB) {
        this.userAnswerB = userAnswerB;
    }
}
