package model;

public class QuestionFactoryProducer implements QuestionAbstractFactory {
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
