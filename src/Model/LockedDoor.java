package Model;

public class LockedDoor implements Door {
    private boolean isUnlocked;
    private Question currQuestion;
    private QuestionData questionData;

    public LockedDoor() {
        this.isUnlocked = false;
        this.currQuestion = null;
        this.questionData = new QuestionData();
    }

    @Override
    public boolean getDoorStatus() {
        return isUnlocked;
    }

    @Override
    public void setQuestionType() {
        // Retrieve a random question
        currQuestion = questionData.retrieveQuestion();
    }

    @Override
    public Question getCurrQuestion() {
        return currQuestion;
    }
}
