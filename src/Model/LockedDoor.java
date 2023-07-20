package Model;

public class LockedDoor implements Door {
    Question currQuestion;

    private LockedDoor() {
        this.currQuestion = getCurrQuestion();
    }

    @Override
    public boolean getDoorStatus() {
        return false;
    }

    @Override
    public void setQuestionType() {
        currQuestion = null;
    }

    @Override
    public Question getCurrQuestion() {
        return null;
    }
}
