package model;

/**
 * Interface for the question factory.
 *
 * @author Kevin Than, Reilly Middlebrooks
 * @version Summer 2023
 */
public interface QuestionFactory {
    AbstractQuestionFactory createQuestionFactory(String theType);
}

