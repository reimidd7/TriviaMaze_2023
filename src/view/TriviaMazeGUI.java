package view;

import model.Maze;

import javax.swing.*;
import java.awt.GridLayout;

public class TriviaMazeGUI {
    /*** FOR TESTING ONLY ***/
    public static void main (String [] args) {
        new TriviaMazeGUI();
    }
    /***---------------***/

    public TriviaMazeGUI() {
        // Create the Maze object here
        Maze maze = new Maze(5, 5);

        final TriviaMazeFrame frame = new TriviaMazeFrame();
        final JLabel questionLabel = new JLabel();
        final JLabel questionTypeLabel = new JLabel();
        final QuestionDisplayPanel questionPanel = new QuestionDisplayPanel(questionLabel, questionTypeLabel);

        // Pass the Maze object to the MazePanel constructor
        final MazePanel mazePanel = new MazePanel(maze);

        final UserControlsPanel controlsPanel = new UserControlsPanel();
        final JPanel eastInfo = new JPanel();

        eastInfo.setLayout(new GridLayout(2,1, 0, 16));
        eastInfo.add(controlsPanel);
        eastInfo.add(questionPanel);

        frame.setLayout(new GridLayout(1,2,16,0));
        frame.add(mazePanel);
        frame.add(eastInfo);

        frame.setVisible(true);
        questionPanel.homeDisplay();
    }
}
