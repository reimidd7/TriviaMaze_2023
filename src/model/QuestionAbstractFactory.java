package model;

public interface QuestionAbstractFactory {
    QuestionFactory createQuestionFactory(String type);
}

