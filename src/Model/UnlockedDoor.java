package Model;

import java.util.Random;

public class UnlockedDoor implements Door {
    Question currQuestion;

    @Override
    public boolean getDoorStatus() {
        return true;
    }

    @Override
    public void setQuestion() {
        Random ran = new Random();
        int choice = ran.nextInt(1,3);
        switch (choice) {
            case 1 -> currQuestion = new MCQuestion();
            case 2 -> currQuestion = new TFQuestion();
            case 3 -> currQuestion = new SAnsQuestion();
        }
    }

    @Override
    public Question getCurrQuestion() {
        return currQuestion;
    }
}
