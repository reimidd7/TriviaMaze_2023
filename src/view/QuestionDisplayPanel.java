package view;

//DISPLAY THE QUESTION BEING ASKED AT THE MOMENT
//DISPLAY NOTHING/AN IMAGE WHEN THE USER IS IN BETWEEN QUESTIONS
//LOWER RIGHT HAND SIDE OF THE FRAME

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import model.Direction;
import model.Doors;
import model.Maze;
import model.Player;
import model.Question;
import model.Room;

public class QuestionDisplayPanel extends JPanel {
    private static final Font LARGE_FONT = new Font("SanSerif", Font.BOLD, 14);

    private static final int MAX_CHARS = 50;

    private static final Dimension TEXTBOX = new Dimension(200, 30);

    private final Maze myMaze;
    private final Player myPlayer;
    private final JLabel filler = new JLabel("\n");
    private final JLabel filler2 = new JLabel("\n");

    /**
     * Constructor for QuestionDisplayPanel.
     */
    public QuestionDisplayPanel(final Maze theMaze) {
        this.myMaze = theMaze;
        myPlayer = myMaze.getPlayer();

        if (determineDoorQuestionType(myPlayer).equals("MC")) {
            mcDisplay(myPlayer); //yellow
        } else if (determineDoorQuestionType(myPlayer).equals("TF")) {
            tfDisplay(myPlayer); //red
        } else if (determineDoorQuestionType(myPlayer).equals("SAns")) {
            sAnsDisplay(myPlayer); //gray
        } else {
            homeDisplay();
        }

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setVisible(true);
    }

    private String determineDoorQuestionType(final Player thePlayer) {
        final Direction playerDir = thePlayer.getPlayerDir();
        final Point playerLoc = thePlayer.getPlayerLoc();
        final Room room = myMaze.getRoom(playerLoc);
        final Doors door = room.getDoorByDirection(playerDir);
        final Question q = door.getCurrQuestion();

        return q.getQuestionTypeType();

    }

    private void mcDisplay(final Player thePlayer) {
        final ArrayList<String> split = new ArrayList<>();
        final Room room = myMaze.getRoom(thePlayer.getPlayerLoc());
        final Doors door = room.getDoorByDirection(thePlayer.getPlayerDir());
        final Question ques = door.getCurrQuestion();
        final String qString = ques.getQuestion();
        String answer = ques.getCorrectAnswer();

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
        final JRadioButton cA = new JRadioButton(choiceA);
        final JRadioButton cB = new JRadioButton(choiceB);
        final JRadioButton cC = new JRadioButton(choiceC);
        final JRadioButton cD = new JRadioButton(choiceD);

        cA.setAlignmentX(Component.CENTER_ALIGNMENT);
        cB.setAlignmentX(Component.CENTER_ALIGNMENT);
        cC.setAlignmentX(Component.CENTER_ALIGNMENT);
        cD.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(header);
        add(filler);

        // Question String
        if (ask.length() > MAX_CHARS) {
            final int h = ask.lastIndexOf(" ", MAX_CHARS);
            final String one = ask.substring(0, h);
            final String two = ask.substring(h, ask.length());

            final JLabel question1 = new JLabel(one);
            final JLabel question2 = new JLabel(two);

            question1.setAlignmentX(Component.CENTER_ALIGNMENT);
            question1.setFont(LARGE_FONT);
            question2.setAlignmentX(Component.CENTER_ALIGNMENT);
            question2.setFont(LARGE_FONT);

            add(question1);
            add(question2);

        } else {
            final JLabel question = new JLabel(ask);

            question.setAlignmentX(Component.CENTER_ALIGNMENT);
            question.setFont(LARGE_FONT);

            add(question);
        }

        add(filler2);
        add(cA);
        add(cB);
        add(cC);
        add(cD);
        setBackground(Color.yellow);
    }

    public void tfDisplay(final Player thePlayer) {
        final Room room = myMaze.getRoom(thePlayer.getPlayerLoc());
        final Doors door = room.getDoorByDirection(thePlayer.getPlayerDir());
        final Question ques = door.getCurrQuestion();
        final String qString = ques.getQuestion();
        String answer = ques.getCorrectAnswer();

        // Question Type
        final JLabel header = new JLabel("True or False");
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Answers
        final JButton bTrue = new JButton("True");
        final JButton bFalse = new JButton("False");
        bTrue.setAlignmentX(Component.CENTER_ALIGNMENT);
        bFalse.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(header);
        add(filler);

        // Question String
        if (qString.length() > MAX_CHARS) {
            final int h = qString.lastIndexOf(" ", MAX_CHARS);
            final String one = qString.substring(0, h);
            final String two = qString.substring(h, qString.length());

            final JLabel question1 = new JLabel(one);
            question1.setFont(LARGE_FONT);
            final JLabel question2 = new JLabel(two);
            question2.setFont(LARGE_FONT);

            question1.setAlignmentX(Component.CENTER_ALIGNMENT);
            question2.setAlignmentX(Component.CENTER_ALIGNMENT);

            add(question1);
            add(question2);

        } else {
            final JLabel question = new JLabel(qString);

            question.setAlignmentX(Component.CENTER_ALIGNMENT);
            question.setFont(LARGE_FONT);

            add(question);
        }

        add(filler2);
        add(bTrue);
        add(bFalse);
        setBackground(Color.pink);
    }

    public void sAnsDisplay(final Player thePlayer) {
        final Room room = myMaze.getRoom(thePlayer.getPlayerLoc());
        final Doors door = room.getDoorByDirection(thePlayer.getPlayerDir());
        final Question ques = door.getCurrQuestion();
        final String qString = ques.getQuestion();
        String answer = ques.getCorrectAnswer();

        // Question Type
        final JLabel header = new JLabel("Short Answer");
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Answers
        final JTextField shortAns = new JTextField();
        shortAns.setMaximumSize(TEXTBOX);
        shortAns.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(header);
        add(filler);

        // Question String
        if (qString.length() > MAX_CHARS) {
            final int h = qString.lastIndexOf(" ", MAX_CHARS);
            final String one = qString.substring(0, h);
            final String two = qString.substring(h, qString.length());

            final JLabel question1 = new JLabel(one);
            final JLabel question2 = new JLabel(two);

            question1.setAlignmentX(Component.CENTER_ALIGNMENT);
            question1.setFont(LARGE_FONT);
            question2.setAlignmentX(Component.CENTER_ALIGNMENT);
            question2.setFont(LARGE_FONT);


            add(question1);
            add(question2);

        } else {
            final JLabel question = new JLabel(qString);

            question.setAlignmentX(Component.CENTER_ALIGNMENT);
            question.setFont(LARGE_FONT);

            add(question);
        }

        add(filler2);
        add(shortAns);
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
}

