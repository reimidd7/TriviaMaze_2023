package model;

public class TFQuestionFactory extends QuestionFactory {
    public Question createQuestion(int questionID, String questionText, String correctAnswer) {
        return new TFQuestion(questionID, questionText, correctAnswer);
    }
}
