package model;

import java.io.Serializable;

public class QuestionFactoryProducer implements QuestionAbstractFactory, Serializable {
    public QuestionFactory createQuestionFactory(String type) {
        if (type == null) {
            return null;
        } else if ("MC".equals(type)) {
            return new MCQuestionFactory();
        } else if ("SAns".equals(type)) {
            return new SAnsQuestionFactory();
        } else if ("TF".equals(type)) {
            return new TFQuestionFactory();
        }
        return null;
    }
}
