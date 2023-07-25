package Model;

public class LockedDoorTest {
    public static void main(String[] args) {
        Door lockedDoor = new LockedDoor();

        for (int i = 0; i < 5; i++) {
            System.out.println("Round " + (i + 1) + ":");
            System.out.println("Door Status: " + (lockedDoor.getDoorStatus() ? "Unlocked" : "Locked"));

            lockedDoor.setQuestionType();
            Question question = lockedDoor.getCurrQuestion();

            if (question != null) {
                System.out.println("Question: " + question.getQuestion());
                System.out.println("Correct Answer: " + question.getCorrectAnswer());
            } else {
                System.out.println("No question available.");
            }
            System.out.println();
        }
    }
}

