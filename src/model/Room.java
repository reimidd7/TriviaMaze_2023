package model;

// Separate Room into different class?
public class Room {
    private boolean isLocked;


    public Room() {
        isLocked = true;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unlock() {
        isLocked = false;
    }

    //public QuestionAnswer getQuestionAnswer() {
//        return questionAnswer;
//    }
}
