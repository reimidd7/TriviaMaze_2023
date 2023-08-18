package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit Tests for the Question class.
 *
 * @author Kevin Than
 * @version Summer 2023
 */
public class QuestionTest {
    public class TesterQuestion extends Question {
        public TesterQuestion(final int theQuestionID, final String theQuestionType,
                              final String theQuestion) {
            super(theQuestionID, theQuestionType, theQuestion);
        }

        @Override
        public String getCorrectAnswer() {
            return "Aruarian";
        }
    }

    @Test
    public void testGetQuestion() {
        final TesterQuestion question = new TesterQuestion(1, "TF", "Is the sky blue?");
        assertEquals("Is the sky blue?", question.getQuestion());
    }

    @Test
    public void testSetQuestion() {
        final  TesterQuestion question = new TesterQuestion(1, "shortAnswer", "how do you code in spanish?");
        question.setQuestion("how do you code in spanish?");
        assertEquals("how do you code in spanish?", question.getQuestion());
    }

    @Test
    public void testGetQuestionTypeType() {
        TesterQuestion question = new TesterQuestion(1, "MC", "If I put water in my call as gas would it run?");
        assertEquals("MC", question.getQuestionTypeType());
    }

    @Test
    public void testGetCorrectAnswer() {
        TesterQuestion question = new TesterQuestion(1, "Rick Roll", "Never Gonna Give You Up");
        assertEquals("Aruarian", question.getCorrectAnswer());
    }
}