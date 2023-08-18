package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit Tests for the Doors class.
 *
 * @author Kevin Than
 * @version Summer 2023
 */
public class DoorsTest {

    /**
     * Door for testing.
     */
    private Doors myLockedDoor;

    @BeforeEach
    public void setUp() {
        myLockedDoor = new Doors(1);
    }

    @Test
    public void testInitialDoorStatusIsLocked() {
        myLockedDoor.setLocked(false);
        assertFalse(myLockedDoor.getDoorStatus());
    }

    @Test
    public void testSetQuestionTypeAndGetCurrentQuestion() {
        for (int i = 0; i < 5; i++) {
            myLockedDoor.setQuestionType();
            final Question question = myLockedDoor.getCurrQuestion();
            assertNotNull(question);
            assertNotNull(question.getQuestion());
            assertNotNull(question.getCorrectAnswer());
            assertTrue(myLockedDoor.getDoorStatus());
        }
    }

    @Test
    public void testSetQuestionTypeMultipleTimes() {
        myLockedDoor.setQuestionType();
        final Question firstQuestion = myLockedDoor.getCurrQuestion();
        assertNotNull(firstQuestion);
        myLockedDoor.setQuestionType();
        final Question secondQuestion = myLockedDoor.getCurrQuestion();
        assertNotNull(secondQuestion);
        assertNotEquals(firstQuestion, secondQuestion);
    }

    @Test
    public void testGetDoorId() {
        assertEquals(1, myLockedDoor.getDoorId());
    }

    @Test
    public void testSetQuestionTypeWithDifferentDoorNumbers() {
        final Doors door1 = new Doors(1);
        door1.setQuestionType();
        final Question question1 = door1.getCurrQuestion();
        assertNotNull(question1);

        final Doors door2 = new Doors(2);
        door2.setQuestionType();
        final Question question2 = door2.getCurrQuestion();
        assertNotNull(question2);

        assertNotEquals(question1, question2);
    }

    @Test
    public void testGetCurrentQuestionInitially() {
        assertNotNull(myLockedDoor.getCurrQuestion());
    }
}
