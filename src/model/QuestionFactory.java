package model;

public abstract class QuestionFactory {
    public abstract Question createQuestion(int questionID, String questionText, String correctAnswer);
}

class MCQuestionFactory extends QuestionFactory {
    public Question createQuestion(int questionID, String questionText, String correctAnswer) {
        return new MCQuestion(questionID, questionText, correctAnswer);
    }
}

class TFQuestionFactory extends QuestionFactory {
    public Question createQuestion(int questionID, String questionText, String correctAnswer) {
        return new TFQuestion(questionID, questionText, correctAnswer);
    }
}

class SAnsQuestionFactory extends QuestionFactory {
    public Question createQuestion(int questionID, String questionText, String correctAnswer) {
        return new SAnsQuestion(questionID, questionText, correctAnswer);
    }
}

