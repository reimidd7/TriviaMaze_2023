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



        updateQuestion(myMaze.getQuestion());

//
//        if (myMaze.getPlayer().getPlayerLoc().equals(myMaze.getExit())) {
//            removeAll();
//            JOptionPane.showMessageDialog(QuestionDisplayPanel.this, "YOU WON!!!!!");
//        } else {
//
//        }
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setVisible(true);
    }


    private String determineDoorQuestionType(Question myQuestion) {
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

            if (userAns.equals(ans)) {
                myMaze.setCanGetThrough(true);
                Direction d = myMaze.getPlayer().getPlayerDir();
                if (d.equals(Direction.SOUTH)) {
                    myMaze.down();
                } else if (d.equals(Direction.NORTH)) {
                    myMaze.up();
                } else if (d.equals(Direction.EAST)) {
                    myMaze.right();
                } else if (d.equals(Direction.WEST)) {
                    myMaze.left();
                }
                removeAll();
                correctDisplay();
                repaint();
                revalidate();
            } else {
                myMaze.setCanGetThrough(false);
                myMaze.updateDoors();
                removeAll();
                incorrectDisplay();
                repaint();
                revalidate();
            }

            requestFocusInWindow();
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
        setBackground(Color.ORANGE);
        add(home);
    }

    public void correctDisplay() {
        final JLabel home = new JLabel("Correct! Choose new Door");
        home.setAlignmentX(Component.CENTER_ALIGNMENT);
        home.setFont(LARGE_FONT);
        setBackground(Color.ORANGE);
        add(home);
    }

    public void incorrectDisplay() {
        final JLabel home = new JLabel("Incorrect :( choose a new door");
        home.setAlignmentX(Component.CENTER_ALIGNMENT);
        home.setFont(LARGE_FONT);
        setBackground(Color.ORANGE);
        add(home);
    }

    private void buttonFunctionality(final JButton theButton, final String theAnswer) {
        final char firstButton = theButton.getText().charAt(0);
        final char firstAns = theAnswer.charAt(0);

        theButton.addActionListener(e -> {
            if (firstButton == firstAns) {
                myMaze.setCanGetThrough(true);
                Direction d = myMaze.getPlayer().getPlayerDir();
                if (d.equals(Direction.SOUTH)) {
                    myMaze.down();
                } else if (d.equals(Direction.NORTH)) {
                    myMaze.up();
                } else if (d.equals(Direction.EAST)) {
                    myMaze.right();
                } else if (d.equals(Direction.WEST)) {
                    myMaze.left();
                }
                removeAll();
                correctDisplay();
                repaint();
                revalidate();
            } else {
                myMaze.setCanGetThrough(false);
                myMaze.updateDoors();
                removeAll();
                incorrectDisplay();
                repaint();
                revalidate();
            }

            requestFocusInWindow();

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

    public void winDisplay() {
        final JLabel home = new JLabel("You Won!!!!");
        home.setAlignmentX(Component.CENTER_ALIGNMENT);
        home.setFont(LARGE_FONT);
        setBackground(Color.CYAN);
        add(home);
    }

    public void updateQuestion(Question question) { //ONLY CALLED THE FIRST TIME
        removeAll();

        if (question == null) {
            homeDisplay();

        } else if (determineDoorQuestionType(question).equals("MC")) {
            mcDisplay(question); //yellow


        } else if (determineDoorQuestionType(question).equals("TF")) {
            tfDisplay(question); //red

        } else if (determineDoorQuestionType(question).equals("SAns")) {
            sAnsDisplay(question); //gray

        } else {
            homeDisplay();

        }
        revalidate();
        repaint();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (myMaze.PROPERTY_DOOR_STATUS.equals(theEvt.getPropertyName())) {
            myDoor = (Doors) theEvt.getNewValue();
            repaint();
        }
//        if (theEvt.getPropertyName().equals(myMaze.PROPERTY_LOCATION_CHANGE)) {
//            Player newPlayer = (Player) theEvt.getNewValue();
//            myPlayer = new Player(newPlayer.getPlayerLoc(), newPlayer.getPlayerDir());
//            repaint();
//        }

        //System.out.println("is this being called");
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
        public void keyPressed(KeyEvent theEvent) {
            switch (theEvent.getKeyCode()) {
                case KeyEvent.VK_UP:
                    myMaze.lookUp();
                    updateQuestion(myMaze.getQuestion());
                    System.out.println("up");
                    break;

                case KeyEvent.VK_DOWN:
                    myMaze.lookDown();
                    updateQuestion(myMaze.getQuestion());
                    System.out.println("down");
                    break;

                case KeyEvent.VK_LEFT:
                    myMaze.lookLeft();
                    updateQuestion(myMaze.getQuestion());
                    System.out.println("left");
                    break;

                case KeyEvent.VK_RIGHT:
                    myMaze.lookRight();
                    updateQuestion(myMaze.getQuestion());
                    System.out.println("right");
                    break;
            }
            repaint();
        }

    }

}

