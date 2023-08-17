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
import java.util.ArrayList;

/**
 * Class for the controls of the user.
 *
 * @author Danie Oum, Reilly Middlebrooks, Kevin Than
 * @version Summer 2023
 */
public class UserControlsPanel extends JPanel implements PropertyChangeListener {
//    private final JButton upButton;
//    private final JButton downButton;
//    private final JButton leftButton;
//    private final JButton rightButton;
//
//    /**
//     * This method creates and shows the GUI. It includes the arrow buttons and key listeners.
//     */
//    public UserControlsPanel() {
//        setLayout(new GridLayout(2, 3, 10, 10));
//
//        JButton blankButton = new JButton("");
//        JButton blankButton2 = new JButton("");
//        blankButton.setEnabled(false);
//        blankButton2.setEnabled(false);
//
//        upButton = new JButton("↑"); // Up arrow
//        leftButton = new JButton("←"); // Left arrow
//        downButton = new JButton("↓"); // Down arrow
//        rightButton = new JButton("→"); // Right arrow
//
//        add(blankButton);
//        add(upButton);
//        add(blankButton2);
//        add(leftButton);
//        add(downButton);
//        add(rightButton);
////
////        setFocusable(true);
////        requestFocus();
//        setVisible(true);
//    }
//
//    public ArrayList<JButton> getButtons() {
//        ArrayList<JButton> but = new ArrayList<>();
//        but.add(upButton);
//        but.add(downButton);
//        but.add(leftButton);
//        but.add(rightButton);
//        return but;
//    }

//    /**
//     * This method is to highlight the direction of the buttons when pressed.
//     *
//     * @param keyCode The key code for the buttons
//     * @param highlight The buttons will highlight green
//     * @param buttons The buttons
//     */
//    public void highlightDirection(int keyCode, boolean highlight, ArrayList<JButton> buttons) {
//        for (JButton button : buttons) {
//            button.setBackground(null);
//        }
//        switch (keyCode) {
//            case KeyEvent.VK_UP:
//                buttons.get(0).setBackground(highlight ? Color.GREEN : null);
//                break;
//            case KeyEvent.VK_DOWN:
//                buttons.get(1).setBackground(highlight ? Color.GREEN : null);
//                break;
//            case KeyEvent.VK_LEFT:
//                buttons.get(2).setBackground(highlight ? Color.GREEN : null);
//                break;
//            case KeyEvent.VK_RIGHT:
//                buttons.get(3).setBackground(highlight ? Color.GREEN : null);
//                break;
//        }
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes here if needed
    }
}
