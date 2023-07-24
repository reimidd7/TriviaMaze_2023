package View;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class TriviaMazeGUI {
    /*** FOR TESTING ONLY ***/
    public static void main (String [] args) {
        new TriviaMazeGUI();

    }
    /***---------------***/

    public TriviaMazeGUI() {
        TriviaMazeFrame frame = new TriviaMazeFrame();
        QuestionDisplayPanel questionPanel = new QuestionDisplayPanel();
        MazePanel mazePanel = new MazePanel();
        UserControlsPanel controlsPanel = new UserControlsPanel();
        JPanel eastInfo = new JPanel();

        eastInfo.setLayout(new GridLayout(2,1, 0, 16));
        eastInfo.add(controlsPanel);
        eastInfo.add(questionPanel);

        frame.setLayout(new GridLayout(1,2,16,0));
        frame.add(mazePanel);
        frame.add(eastInfo);

        frame.setVisible(true);

    }
}
