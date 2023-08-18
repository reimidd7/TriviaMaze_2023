package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoorsTest {

    private Doors lockedDoor;

    @BeforeEach
    public void setUp() {
        lockedDoor = new Doors(1);
    }

    @Test
    public void testInitialDoorStatusIsLocked() {
        lockedDoor.setLocked(false);
        assertFalse(lockedDoor.getDoorStatus());
    }

    @Test
    public void testSetQuestionTypeAndGetCurrentQuestion() {
        for (int i = 0; i < 5; i++) {
            lockedDoor.setQuestionType();
            Question question = lockedDoor.getCurrQuestion();
            assertNotNull(question);
            assertNotNull(question.getQuestion());
            assertNotNull(question.getCorrectAnswer());
            assertTrue(lockedDoor.getDoorStatus());
        }
    }

    @Test
    public void testSetQuestionTypeMultipleTimes() {
        lockedDoor.setQuestionType();
        Question firstQuestion = lockedDoor.getCurrQuestion();
        assertNotNull(firstQuestion);
        lockedDoor.setQuestionType();
        Question secondQuestion = lockedDoor.getCurrQuestion();
        assertNotNull(secondQuestion);
        assertNotEquals(firstQuestion, secondQuestion);
    }

    @Test
    public void testGetDoorId() {
        assertEquals(1, lockedDoor.getDoorId());
    }

    @Test
    public void testSetQuestionTypeWithDifferentDoorNumbers() {
        Doors door1 = new Doors(1);
        door1.setQuestionType();
        Question question1 = door1.getCurrQuestion();
        assertNotNull(question1);

        Doors door2 = new Doors(2);
        door2.setQuestionType();
        Question question2 = door2.getCurrQuestion();
        assertNotNull(question2);

        assertNotEquals(question1, question2);
    }

    @Test
    public void testGetCurrentQuestionInitially() {
        assertNotNull(lockedDoor.getCurrQuestion());
    }
}
