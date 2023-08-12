package view;

//DISPLAY THE QUESTION BEING ASKED AT THE MOMENT
//DISPLAY NOTHING/AN IMAGE WHEN THE USER IS IN BETWEEN QUESTIONS
//LOWER RIGHT HAND SIDE OF THE FRAME

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Direction;
import model.Doors;
import model.Maze;
import model.Player;
import model.Question;
import model.Room;

public class QuestionDisplayPanel extends JPanel implements PropertyChangeListener {
    private static final Font LARGE_FONT = new Font("SanSerif", Font.BOLD, 14);

    private static final int MAX_CHARS = 50;

    private static final Dimension TEXTBOX = new Dimension(200, 30);

    private final Maze myMaze;
    private final Player myPlayer;
    private final JLabel filler = new JLabel("\n");
    private final JLabel filler2 = new JLabel("\n");
    private final Room myRoom;
    private Doors myDoor;

    /**
     * Constructor for QuestionDisplayPanel.
     */
    public QuestionDisplayPanel(final Maze theMaze) {
        super();
        this.myMaze = theMaze;
        this.myPlayer = myMaze.getPlayer();
        this.myRoom = myMaze.getRoom(myPlayer.getPlayerLoc());
        this.myDoor = myRoom.getDoorByDirection(myPlayer.getPlayerDir());

        if (determineDoorQuestionType(myPlayer).equals("MC")) {
            mcDisplay(myPlayer); //yellow
        } else if (determineDoorQuestionType(myPlayer).equals("TF")) {
            tfDisplay(myPlayer); //red
        } else if (determineDoorQuestionType(myPlayer).equals("SAns")) {
            sAnsDisplay(myPlayer); //gray
        } else {
            homeDisplay();
        }

        setFocusable(true);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setVisible(true);
    }

    private String determineDoorQuestionType(final Player thePlayer) {
        final Question q = myDoor.getCurrQuestion();

        return q.getQuestionTypeType();

    }

    private void mcDisplay(final Player thePlayer) {
        final ArrayList<String> split = new ArrayList<>();
        final Question ques = myDoor.getCurrQuestion();
        final String qString = ques.getQuestion();
        final String answer = ques.getCorrectAnswer();

        final Scanner s = new Scanner(qString);
        while (s.hasNextLine()) {
            split.add(s.nextLine());
        }

        final String ask = split.get(0);
        final String choiceA = split.get(1);
        final String choiceB = split.get(2);
        final String choiceC = split.get(3);
        final String choiceD = split.get(4);

        // Question Type
        final JLabel header = new JLabel("Multiple Choice");
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Answers
        final JButton cA = new JButton(choiceA);
        buttonFunctionality(cA,  answer);
        final JButton cB = new JButton(choiceB);
        buttonFunctionality(cB, answer);
        final JButton cC = new JButton(choiceC);
        buttonFunctionality(cC, answer);
        final JButton cD = new JButton(choiceD);
        buttonFunctionality(cD, answer);

        cA.setAlignmentX(Component.CENTER_ALIGNMENT);
        cB.setAlignmentX(Component.CENTER_ALIGNMENT);
        cC.setAlignmentX(Component.CENTER_ALIGNMENT);
        cD.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(header);
        add(filler);

        // Question String
        questionFormatter(ask);

        add(filler2);
        add(cA);
        add(cB);
        add(cC);
        add(cD);
        setBackground(Color.yellow);
    }

    public void tfDisplay(final Player thePlayer) {
        final Question ques = myDoor.getCurrQuestion();
        final String qString = ques.getQuestion();
        final String answer = ques.getCorrectAnswer();

        // Question Type
        final JLabel header = new JLabel("True or False");
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Answers
        final JButton bTrue = new JButton("True");
        buttonFunctionality(bTrue, answer);
        final JButton bFalse = new JButton("False");
        buttonFunctionality(bFalse, answer);
        bTrue.setAlignmentX(Component.CENTER_ALIGNMENT);
        bFalse.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(header);
        add(filler);

        // Question String
        questionFormatter(qString);

        add(filler2);
        add(bTrue);
        add(bFalse);
        setBackground(Color.pink);
    }

    public void sAnsDisplay(final Player thePlayer) {
        final Question ques = myDoor.getCurrQuestion();
        final String qString = ques.getQuestion();
        final String answer = ques.getCorrectAnswer();

        // Question Type
        final JLabel header = new JLabel("Short Answer");
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Answers
        final JTextField shortAns = new JTextField();
        shortAns.setMaximumSize(TEXTBOX);
        shortAns.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Submit button
        final JButton submit = new JButton("Submit");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);

        // read input
        submit.addActionListener(e -> {
            final String userAns = shortAns.getText().toLowerCase().replaceAll("\\s", "");
            final String ans = answer.toLowerCase().replaceAll("\\s", "");
            System.out.println(userAns + "   real:   " + ans);
            if (userAns.equals(ans)) {
                System.out.println("Correct!" + myDoor.getDoorStatus());
            } else {
                myMaze.updateDoors(myDoor);
                System.out.println("incorrect!" + myDoor.getDoorStatus());
            }
        });

        add(header);
        add(filler);

        // Question String
        questionFormatter(qString);

        add(filler2);
        add(shortAns);
        add(submit);
        setBackground(Color.GRAY);

    }

    /**
     * Display a default message.
     */
    public void homeDisplay() {
        final JLabel home = new JLabel("Choose a Doorway... Have fun!");
        home.setAlignmentX(Component.CENTER_ALIGNMENT);
        home.setFont(LARGE_FONT);
    }

    private void buttonFunctionality(final JButton theButton, final String theAnswer) {
        final char firstButton = theButton.getText().charAt(0);
        final char firstAns = theAnswer.charAt(0);

        theButton.addActionListener(e -> {
            if (firstButton == firstAns) {
                System.out.println("Correct!" + myDoor.getDoorStatus());
            } else {
                myMaze.updateDoors(myDoor);
                System.out.println("incorrect!" + myDoor.getDoorStatus());
            }

        });

    }


    private void questionFormatter(final String theQString) {
        if (theQString.length() > MAX_CHARS) {
            final int h = theQString.lastIndexOf(" ", MAX_CHARS);
            final String one = theQString.substring(0, h);
            final String two = theQString.substring(h, theQString.length());

            final JLabel question1 = new JLabel(one);
            final JLabel question2 = new JLabel(two);

            question1.setAlignmentX(Component.CENTER_ALIGNMENT);
            question1.setFont(LARGE_FONT);
            question2.setAlignmentX(Component.CENTER_ALIGNMENT);
            question2.setFont(LARGE_FONT);


            add(question1);
            add(question2);

        } else {
            final JLabel question = new JLabel(theQString);

            question.setAlignmentX(Component.CENTER_ALIGNMENT);
            question.setFont(LARGE_FONT);

            add(question);
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (myMaze.PROPERTY_DOOR_STATUS.equals(theEvt.getPropertyName())) {
            myDoor = (Doors) theEvt.getNewValue();
            repaint();
        }
    }
}

