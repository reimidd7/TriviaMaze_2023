package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Class for the controls of the user.
 *
 * @author Danie Oum, Reilly Middlebrooks, Kevin Than
 * @version Summer 2023
 */
public class UserControlsPanel extends JPanel implements PropertyChangeListener {
    private final JButton upButton;
    private final JButton downButton;
    private final JButton leftButton;
    private final JButton rightButton;

    public UserControlsPanel() {
        setLayout(new GridLayout(2, 3, 10, 10));

        JButton blankButton = new JButton("");
        JButton blankButton2 = new JButton("");
        blankButton.setEnabled(false);
        blankButton2.setEnabled(false);

        upButton = new JButton("↑"); // Up arrow
        leftButton = new JButton("←"); // Left arrow
        downButton = new JButton("↓"); // Down arrow
        rightButton = new JButton("→"); // Right arrow

        add(blankButton);
        add(upButton);
        add(blankButton2);
        add(leftButton);
        add(downButton);
        add(rightButton);

        // Attach a KeyAdapter to the panel to listen for arrow key events
        addKeyListener(new KeyAdapter() {
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

        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    private void highlightDirection(int keyCode, boolean highlight, JButton... buttons) {
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes here if needed
    }
}
