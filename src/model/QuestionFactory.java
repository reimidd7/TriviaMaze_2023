package model;

public abstract class QuestionFactory {
    public abstract Question createQuestion(int questionID, String questionText, String correctAnswer);
}

