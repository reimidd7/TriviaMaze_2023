package model;

import java.io.Serializable;

/**
 * Calls upon the Question Factory class to create the proper Questions.
 *
 * @author Kevin Than
 * @version Summer 2023
 */
public class QuestionFactoryProducer implements QuestionFactory, Serializable {
    public AbstractQuestionFactory createQuestionFactory(final String theType) {
        if (theType == null) {
            return null;
        } else if ("MC".equals(theType)) {
            return new MCQuestionFactory();
        } else if ("SAns".equals(theType)) {
            return new SAnsQuestionFactory();
        } else if ("TF".equals(theType)) {
            return new TFQuestionFactory();
        }
        return null;
    }
}
