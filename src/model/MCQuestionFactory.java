package model;

public class MCQuestionFactory extends QuestionFactory {
    public Question createQuestion(int questionID, String questionText, String correctAnswer) {
        return new MCQuestion(questionID, questionText, correctAnswer);
    }
}
