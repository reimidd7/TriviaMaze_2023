package view;

//DISPLAY THE QUESTION BEING ASKED AT THE MOMENT
//DISPLAY NOTHING/AN IMAGE WHEN THE USER IS IN BETWEEN QUESTIONS
//LOWER RIGHT HAND SIDE OF THE FRAME

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

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

    private Maze myMaze;
    private Player myPlayer;
    private final JLabel filler = new JLabel("\n");
    private final JLabel filler2 = new JLabel("\n");
    //private final Room myRoom;
    private Doors myDoor;

    //private Question myQuestion;

    /**
     * Constructor for QuestionDisplayPanel.
     */
    public QuestionDisplayPanel(final Maze theMaze) {
        super();

        this.myMaze = theMaze;
        this.myPlayer = myMaze.getPlayer();
        addKeyListener(new BoardKeyListener());
        setFocusable(true);
        requestFocus();
//        myQuestion = myMaze.getQuestion();
//
//
//
//
//
//
//        if (myQuestion == null) {
//            homeDisplay();
//        } else if (determineDoorQuestionType().equals("MC")) {
//            System.out.println("QUESTIONDP -----   " + myQuestion.getQuestion());
//            mcDisplay(myPlayer); //yellow
//        } else if (determineDoorQuestionType().equals("TF")) {
//            System.out.println("QUESTIONDP -----   " + myQuestion.getQuestion());
//            tfDisplay(myPlayer); //red
//        } else if (determineDoorQuestionType().equals("SAns")) {
//            System.out.println("QUESTIONDP -----   " + myQuestion.getQuestion());
//            sAnsDisplay(myPlayer); //gray
//        } else {
//            System.out.println("QUESTIONDP -----   " + myQuestion.getQuestion());
//            homeDisplay();
//        }
        //repaint();
        //revalidate();

        updateQuestion(myMaze.getQuestion());

        //setFocusable(true);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setVisible(true);
    }


    private String determineDoorQuestionType(Question myQuestion) {
        //repaint();
        return myQuestion.getQuestionTypeType();
    }

    private void mcDisplay(Question myQuestion) {
        final ArrayList<String> split = new ArrayList<>();
        final String qString = myQuestion.getQuestion();
        final String answer = myQuestion.getCorrectAnswer();

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
        revalidate();
        repaint();

    }

    public void tfDisplay(Question myQuestion) {
        final String qString = myQuestion.getQuestion();
        final String answer = myQuestion.getCorrectAnswer();

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
        revalidate();
        repaint();

    }

    public void sAnsDisplay(Question myQuestion) {
        final String qString = myQuestion.getQuestion();
        final String answer = myQuestion.getCorrectAnswer();

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
        revalidate();
        repaint();

    }

    /**
     * Display a default message.
     */
    public void homeDisplay() {
        final JLabel home = new JLabel("Choose a Doorway... Have fun!");
        home.setAlignmentX(Component.CENTER_ALIGNMENT);
        home.setFont(LARGE_FONT);
        setBackground(Color.ORANGE);
        add(home);
        revalidate();
        repaint();
    }

    private void buttonFunctionality(final JButton theButton, final String theAnswer) {
        final char firstButton = theButton.getText().charAt(0);
        final char firstAns = theAnswer.charAt(0);

        theButton.addActionListener(e -> {
            if (firstButton == firstAns) {
                System.out.println("Correct!");
            } else {
                myMaze.updateDoors(myDoor);
                System.out.println("incorrect!");
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

    public void updateQuestion(Question question) { //ONLY CALLED THE FIRST TIME
        System.out.println("updateQuestion is being called\n\n\n");

        removeAll();

        if (question == null) {
            homeDisplay();

        } else if (determineDoorQuestionType(question).equals("MC")) {
            System.out.println("QUESTIONDP -----   " + question.getQuestion());
            mcDisplay(question); //yellow

        } else if (determineDoorQuestionType(question).equals("TF")) {
            System.out.println("QUESTIONDP -----   " + question.getQuestion());
            tfDisplay(question); //red

        } else if (determineDoorQuestionType(question).equals("SAns")) {
            System.out.println("QUESTIONDP -----   " + question.getQuestion());
            sAnsDisplay(question); //gray

        } else {
            System.out.println("QUESTIONDP -----   " + question.getQuestion());
            homeDisplay();

        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
//        if (myMaze.PROPERTY_DOOR_STATUS.equals(theEvt.getPropertyName())) {
//            myDoor = (Doors) theEvt.getNewValue();
//            repaint();
//        }
//        if (theEvt.getPropertyName().equals(myMaze.PROPERTY_LOCATION_CHANGE)) {
//            Player newPlayer = (Player) theEvt.getNewValue();
//            myPlayer = new Player(newPlayer.getPlayerLoc(), newPlayer.getPlayerDir());
//            repaint();
//        }

        System.out.println("is this being called");
        if (myMaze.PROPERTY_NEW_QUESTION.equals(theEvt.getPropertyName())) {
            Question newQuestion = (Question) theEvt.getNewValue();
            updateQuestion(newQuestion);
            repaint();
        }
    }


    private class BoardKeyListener extends KeyAdapter {
        /*TODO: When a key is pressed we want to
        grab the question in that direction
        Ask the question
        if the user is correct move the player in that direction
        if the user is incorrect change the door color (user remains in the same room)
         */
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    myMaze.getQuestion();
                    myMaze.up();
                    System.out.println("up");
                    break;
                case KeyEvent.VK_DOWN:
                    myMaze.getQuestion();

                    myMaze.down();

                    System.out.println("down");
                    break;
                case KeyEvent.VK_LEFT:
                    myMaze.getQuestion();

                    myMaze.left();

                    System.out.println("left");
                    break;
                case KeyEvent.VK_RIGHT:
                    myMaze.getQuestion();

                    myMaze.right();

                    System.out.println("right");
                    break;
            }
            updateQuestion(myMaze.getQuestion());

            repaint();
        }
    }
}

