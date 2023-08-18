package view;

import controller.TriviaMaze;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import model.Direction;
import model.Doors;
import model.Player;
import model.Question;

/**
 * Displays the Question for user interaction.
 * Updates each time the player faces a new direction.
 * Contains the BoardKeyListener class.
 *
 * @author Reilly Middlebrooks, Kevin Than
 * @version Summer 2023
 */
public class QuestionDisplayPanel extends JPanel implements PropertyChangeListener {
    /**
     * Creates a custom font for the panel.
     */
    private static final Font LARGE_FONT = new Font("SanSerif", Font.BOLD, 14);

    /**
     * Amount of characters the panel can display before a new line.
     */
    private static final int MAX_CHARS = 50;

    /**
     * Creates the text box for the Short Answer Question.
     */
    private static final Dimension TEXTBOX = new Dimension(200, 30);

    /**
     * Trivia Maze object.
     */
    private TriviaMaze myMaze;

    /**
     * A player object for grabbing questions.
     */
    private Player myPlayer;

    /**
     * A new line to organize the display.
     */
    private final JLabel myFiller = new JLabel("\n");


    /**
     * A new line to organize the display.
     */
    private final JLabel myFiller2 = new JLabel("\n");


    /**
     * Constructor for QuestionDisplayPanel.
     */
    public QuestionDisplayPanel(final TriviaMaze theMaze) {
        super();
        this.myMaze = theMaze;
        this.myPlayer = myMaze.getPlayer();


        addKeyListener(new BoardKeyListener());
        setFocusable(true);
        requestFocus();

        updateQuestion(myMaze.getQuestion());

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setVisible(true);
    }

    /**
     * Resets the panel for a new game.
     */
    public void resetQuestionPanel() {
        removeAll();
        myMaze.newGame();
        homeDisplay();
        revalidate();
        repaint();
    }

    /**
     * Updates the state of the panel when loading a game.
     * @param theLoadedMaze the saved maze.
     */
    public void updateStateAfterLoadQuestion(final TriviaMaze theLoadedMaze) {
        removeAll();
        myMaze = theLoadedMaze;
        myPlayer = theLoadedMaze.getPlayer();
        updateQuestion(myMaze.getQuestion());
        revalidate();
        repaint();
    }

    private String determineDoorQuestionType(final Question theQuestion) {
        return theQuestion.getQuestionTypeType();
    }

    private void mcDisplay(final Question theQuestion) {
        final ArrayList<String> split = new ArrayList<>();
        final String qString = theQuestion.getQuestion();
        final String answer = theQuestion.getCorrectAnswer();

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
        add(myFiller);

        // Question String
        questionFormatter(ask);

        add(myFiller2);
        add(cA);
        add(cB);
        add(cC);
        add(cD);
        setBackground(new Color(136, 216, 176)); //lighter green
    }

    private void tfDisplay(final Question theQuestion) {
        final String qString = theQuestion.getQuestion();
        final String answer = theQuestion.getCorrectAnswer();

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
        add(myFiller);

        // Question String
        questionFormatter(qString);

        add(myFiller2);
        add(bTrue);
        add(bFalse);
        setBackground(new Color(173,190,255)); //bluish
    }

    private void sAnsDisplay(final Question theQuestion) {
        final String qString = theQuestion.getQuestion();
        final String answer = theQuestion.getCorrectAnswer();

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
                final Direction d = myMaze.getPlayer().getPlayerDir();
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
                myMaze.updateDoors();
                myPlayer.incrementWrongAnswers();
                removeAll();
                incorrectDisplay();
                checkForGameOver();
                repaint();
                revalidate();
            }
            requestFocusInWindow();
        });

        add(header);
        add(myFiller);

        // Question String
        questionFormatter(qString);

        add(myFiller2);
        add(shortAns);
        add(submit);
        setBackground(new Color(135, 185, 162)); //darker green
    }

    /**
     * Display a default message.
     */
    private void homeDisplay() {
        final JLabel home = new JLabel("Choose a Doorway... Have fun! ");
        final JLabel home2 = new JLabel("3 wrong answers... you're out ");

        home.setAlignmentX(Component.CENTER_ALIGNMENT);
        home.setFont(LARGE_FONT);
        home2.setAlignmentX(Component.CENTER_ALIGNMENT);
        home2.setFont(LARGE_FONT);
        setBackground(new Color(255, 204, 92)); //orange
        add(home);
        add(home2);

    }

    private void correctDisplay() {
        final JLabel home = new JLabel("Correct! Choose new Door");
        home.setAlignmentX(Component.CENTER_ALIGNMENT);
        home.setFont(LARGE_FONT);
        setBackground(new Color(255, 204, 92));
        add(home);
    }

    private void incorrectDisplay() {
        int left = 2 - myMaze.getPlayer().getWrongAnswers();
        final JLabel home = new JLabel("Incorrect :( choose a new door. You have "
                + left + " strike(s) left");
        home.setAlignmentX(Component.CENTER_ALIGNMENT);
        home.setFont(LARGE_FONT);
        setBackground(new Color(255, 204, 92));
        add(home);
    }

    private void lockedDisplay() {
        final JLabel home = new JLabel("Door is locked choose a new Doorway");
        home.setAlignmentX(Component.CENTER_ALIGNMENT);
        home.setFont(LARGE_FONT);
        setBackground(new Color(255, 111, 105));
        add(home);
    }

    private void buttonFunctionality(final JButton theButton, final String theAnswer) {
        final char firstButton = theButton.getText().charAt(0);
        final char firstAns = theAnswer.charAt(0);

        theButton.addActionListener(e -> {
            if (firstButton == firstAns) {
                final Direction d = myMaze.getPlayer().getPlayerDir();
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
                myMaze.updateDoors();
                removeAll();
                incorrectDisplay();
                myPlayer.incrementWrongAnswers();
                checkForGameOver();
                repaint();
                revalidate();

            }
            requestFocusInWindow();
        });
    }

     // Checks if the player has lost the game.
    private void checkForGameOver() {
        if (myMaze.getPlayer().getWrongAnswers() >= 3) {
            final String[] options = {"Exit", "Leave"};
            SwingUtilities.invokeLater(() -> {
                final int choice = JOptionPane.showOptionDialog(this,
                        "OH NO YOU LOST :(", "Lost Game",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    System.exit(0);
                }

            });
        }
    }

    private void questionFormatter(final String theQString) {
        if (theQString.length() > MAX_CHARS) {
            final int h = theQString.lastIndexOf(" ", MAX_CHARS);
            final String one = theQString.substring(0, h);
            final String two = theQString.substring(h);

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

    public void updateQuestion(final Question theQuestion) {
        final Doors door = myMaze.getCurrentDoor();
        removeAll();

        if (theQuestion == null) {
            homeDisplay();

        } else if (!door.getDoorStatus()) {
            lockedDisplay();

        } else if (determineDoorQuestionType(theQuestion).equals("MC")) {
            mcDisplay(theQuestion); //yellow


        } else if (determineDoorQuestionType(theQuestion).equals("TF")) {
            tfDisplay(theQuestion); //red

        } else if (determineDoorQuestionType(theQuestion).equals("SAns")) {
            sAnsDisplay(theQuestion); //gray

        } else {
            homeDisplay();

        }
        revalidate();
        repaint();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {

        if (myMaze.PROPERTY_NEW_QUESTION.equals(theEvt.getPropertyName())) {
            final Question newQuestion = (Question) theEvt.getNewValue();
            updateQuestion(newQuestion);
            repaint();
        }
    }


    private class BoardKeyListener extends KeyAdapter {
        public void keyPressed(final KeyEvent theEvent) {
            switch (theEvent.getKeyCode()) {
                case KeyEvent.VK_UP -> {
                    myMaze.lookUp();
                    updateQuestion(myMaze.getQuestion());
                    System.out.println("up");
                }
                case KeyEvent.VK_DOWN -> {
                    myMaze.lookDown();
                    updateQuestion(myMaze.getQuestion());
                    System.out.println("down");
                }
                case KeyEvent.VK_LEFT -> {
                    myMaze.lookLeft();
                    updateQuestion(myMaze.getQuestion());
                    System.out.println("left");
                }
                case KeyEvent.VK_RIGHT -> {
                    myMaze.lookRight();
                    updateQuestion(myMaze.getQuestion());
                    System.out.println("right");
                }
            }
            repaint();
        }
    }
}

