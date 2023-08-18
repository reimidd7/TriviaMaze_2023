package model;

/**
 * Abstract classes for the Question Factory.
 *
 * @author Kevin Than, Reilly Middlebrooks
 * @version Summer 2023
 */
public abstract class AbstractQuestionFactory {
    public abstract Question createQuestion(int theQuestionID,
                                            String theQuestionText, String theCorrectAnswer);
}

class MCQuestionFactory extends AbstractQuestionFactory {
    public Question createQuestion(final int theQuestionID, final String theQuestionText,
                                   final String theCorrectAnswer) {
        return new MCQuestion(theQuestionID, theQuestionText, theCorrectAnswer);
    }
}

class TFQuestionFactory extends AbstractQuestionFactory {
    public Question createQuestion(final int theQuestionID, final String theQuestionText,
                                   final String theCorrectAnswer) {
        return new TFQuestion(theQuestionID, theQuestionText, theCorrectAnswer);
    }
}

class SAnsQuestionFactory extends AbstractQuestionFactory {
    public Question createQuestion(final int theQuestionID, final String theQuestionText,
                                   final String theCorrectAnswer) {
        return new SAnsQuestion(theQuestionID, theQuestionText, theCorrectAnswer);
    }
}

