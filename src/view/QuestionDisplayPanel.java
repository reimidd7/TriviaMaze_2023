package view;

//DISPLAY THE QUESTION BEING ASKED AT THE MOMENT
//DISPLAY NOTHING/AN IMAGE WHEN THE USER IS IN BETWEEN QUESTIONS
//LOWER RIGHT HAND SIDE OF THE FRAME

import model.Doors;
import model.Question;

import javax.swing.*;
import java.awt.*;

public class QuestionDisplayPanel extends JPanel {
    private JLabel questionLabel;
    private JLabel questionTypeLabel;

    /**
     * Constructor for QuestionDisplayPanel.
     * @param questionLabel Display question text.
     * @param questionTypeLabel Display question type.
     */

    public QuestionDisplayPanel(JLabel questionLabel, JLabel questionTypeLabel) {
        this.questionLabel = questionLabel;
        this.questionTypeLabel = questionTypeLabel;

        basePanel();
    }

    /**
     * Sets the base appearance and layout.
     */
    private void basePanel() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        questionLabel.setForeground(Color.WHITE);
        questionTypeLabel.setForeground(Color.WHITE);

        add(questionLabel, BorderLayout.CENTER);
        add(questionTypeLabel, BorderLayout.SOUTH);
    }

    /**
     * Displays the door base on the provided door.
     * @param door
     */
    private void displayQuestion(Doors door) {
        if (door != null) {
            Question currentQuestion = door.getCurrQuestion();
            if (currentQuestion != null) {
                questionLabel.setText("Question: " + currentQuestion.getQuestion());
                questionTypeLabel.setText("Type: " + currentQuestion.getQuestionTypeType());
            } else {
                questionLabel.setText("No question available.");
                questionTypeLabel.setText("");
            }
        } else {
            questionLabel.setText("Invalid door.");
            questionTypeLabel.setText("");
        }
    }

    /**
     * Display a default message.
     */
    public void homeDisplay() {
        questionLabel.setText("Choose a doorway");
        questionTypeLabel.setText("Question incoming....");
    }
}

