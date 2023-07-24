package Model;

public class SAnsQuestion extends Question {
    private String correctAnswer;

    public SAnsQuestion(int questionID, String question, String correctAnswer) {
        super(questionID, "SAns", question);
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}