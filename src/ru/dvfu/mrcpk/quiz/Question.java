package ru.dvfu.mrcpk.quiz;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Class Question
 */
public class Question implements Serializable{

    // Вопрос и подробности
    private String questions;

    private StringBuilder addons;

    // Варианты ответа
    private List<OptionQA> optionQAs;

    public Question(String questions, StringBuilder addons, List<OptionQA> optionQAs) {
        this.questions = questions;
        this.addons = addons;
        this.optionQAs = optionQAs;
    }

    public StringBuilder getAddons() {
        return addons;
    }

    public void setAddons(StringBuilder addons) {
        this.addons = addons;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public List<OptionQA> getOptionQAs() {
        return optionQAs;
    }

    public void setOptionQA(List<OptionQA> optionQAs) {
        this.optionQAs = optionQAs;
    }

    public String getStringOptionQAs(){
        String s = null;
        for(OptionQA optionQA: optionQAs){
            s +=optionQA;
            s += "\n";
        }
        return s;
    }


    @Override
    public String toString() {
        return questions + "\n" + addons.toString() + "\n" + getStringOptionQAs();
    }
}

class OptionQA{
    // Вариант ответа
    private String option;

    // Корректность 0 или 1
    private byte correctA;

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
        return option + "\t" + correctA + "\t";
    }
}