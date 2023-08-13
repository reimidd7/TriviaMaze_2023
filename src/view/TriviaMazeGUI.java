package view;

import model.Maze;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TriviaMazeGUI implements PropertyChangeListener {
    /*** FOR TESTING ONLY ***/
    public static void main (String [] args) {
        new TriviaMazeGUI();
    }
    /***---------------***/

    public TriviaMazeGUI() {
        // Create the Maze object here
        Maze maze = new Maze(5, 5);

        final TriviaMazeFrame frame = new TriviaMazeFrame(maze);
        final JLabel questionLabel = new JLabel();
        final JLabel questionTypeLabel = new JLabel();
        final QuestionDisplayPanel questionPanel = new QuestionDisplayPanel(maze);

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

        maze.addPropertyChangeListener(frame);

        maze.addPropertyChangeListener(mazePanel);


        frame.setVisible(true);





    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
