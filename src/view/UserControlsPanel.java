package view;

//NOT COMPLETE - need to make sure the buttons enable and disable correctly
//ARROWS LIKE A CONTROLLER
//ONLY HIGHLIGHT THE DIRECTIONS AVAILABLE
//ADD ARROW KEYS LABELS
//CONNECT TO GAME MAZE CONTROLS

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserControlsPanel extends JPanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserControlsPanel::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Control Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3, 10, 10));

        JButton blankButton = new JButton("");
        JButton upButton = new JButton("↑"); // Up arrow
        blankButton.setEnabled(false);
        JButton blankButton2 = new JButton("");
        blankButton2.setEnabled(false);

        JButton leftButton = new JButton("←"); // Left arrow
        JButton downButton = new JButton("↓"); // Down arrow
        JButton rightButton = new JButton("→"); // Right arrow

        panel.add(blankButton);
        panel.add(upButton);
        panel.add(blankButton2);
        panel.add(leftButton);
        panel.add(downButton);
        panel.add(rightButton);

        frame.add(panel);

        // Attach a KeyAdapter to the frame to listen for arrow key events
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                highlightDirection(keyCode, true, upButton, downButton, leftButton, rightButton);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                highlightDirection(keyCode, false, upButton, downButton, leftButton, rightButton);
            }
        });

        frame.setFocusable(true);
        frame.requestFocus();
        frame.setVisible(true);
    }

    private static void highlightDirection(int keyCode, boolean highlight, JButton... buttons) {
        for (JButton button : buttons) {
            button.setBackground(null);
        }

        switch (keyCode) {
            case KeyEvent.VK_UP:
                buttons[0].setBackground(highlight ? Color.GREEN : null);
                break;
            case KeyEvent.VK_DOWN:
                buttons[1].setBackground(highlight ? Color.GREEN : null);
                break;
            case KeyEvent.VK_LEFT:
                buttons[2].setBackground(highlight ? Color.GREEN : null);
                break;
            case KeyEvent.VK_RIGHT:
                buttons[3].setBackground(highlight ? Color.GREEN : null);
                break;
        }
    }
}
