package model;

public class SAnsQuestionFactory extends QuestionFactory {
    public Question createQuestion(int questionID, String questionText, String correctAnswer) {
        return new SAnsQuestion(questionID, questionText, correctAnswer);
    }
}
