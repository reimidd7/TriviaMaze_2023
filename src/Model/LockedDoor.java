package Model;

public class LockedDoor implements Door {
    Question currQuestion;
    @Override
    public boolean getDoorStatus() {
        return false;
    }

    @Override
    public void setQuestion() {
        currQuestion = null;
    }

    @Override
    public Question getCurrQuestion() {
        return null;
    }
}
