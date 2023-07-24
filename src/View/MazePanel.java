package View;

import javax.swing.*;
import java.awt.Color;

//DISPLAY THE MAZE
//ADD DOORS LOCKED AND UNLOCKED (THIS WILL BE UPDATED)
//ADD A CHARACTER?
//ADD A WAY TO MARK THE USERS PATH
public class MazePanel extends JPanel {

    public MazePanel() {
        //size is set by default layout in TriviaMazeGUI

        super();
        //setSize(200,200);
        setBackground(Color.MAGENTA);
        setVisible(true);
    }
}
