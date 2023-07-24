package Model;

public class TFQuestion extends Question {
    private String correctAnswer;

    public TFQuestion(int questionID, String question, String correctAnswer) {
        super(questionID, "TF", question);
        this.correctAnswer = correctAnswer;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}