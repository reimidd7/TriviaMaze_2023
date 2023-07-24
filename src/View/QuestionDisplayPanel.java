package View;

//DISPLAY THE QUESTION BEING ASKED AT THE MOMENT
//DISPLAY NOTHING/AN IMAGE WHEN THE USER IS IN BETWEEN QUESTIONS
//LOWER RIGHT HAND SIDE OF THE FRAME

import javax.swing.*;
import java.awt.Color;

public class QuestionDisplayPanel extends JPanel {
    public QuestionDisplayPanel() {
        //size is set by default layout in TriviaMazeGUI
        super();
        basePanel();

    }

    private void basePanel() {
        //the base panel that will contain the questions and waiting screen
        setBackground(Color.BLACK);
        setVisible(true);

    }

    private void displayQuestion() {
        //will display the question
        //will this handle the answer clicks?

    }

    private void homeDisplay() {
        //will display "Choose a doorway"
        //"question incoming...."
    }
}
