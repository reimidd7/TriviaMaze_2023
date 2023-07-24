package Model;

public class MCQuestion extends Question {
    private String correctAnswer;

    public MCQuestion(int questionID, String question, String correctAnswer) {
        super(questionID, "MC", question);
        this.correctAnswer = correctAnswer;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}