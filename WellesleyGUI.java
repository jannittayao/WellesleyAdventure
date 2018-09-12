 

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * WellesleyGUI implements the GUI and allows the user to explore Wellesley
 *
 * @author Silvia Zeamer
 */
public class WellesleyGUI
{
    /**
     * Main method which creates a new instance of WellesleyAdventure and displays
     * it.
     */
        public static void main(String[] args) {
        //start a new window
        EventQueue.invokeLater(() -> {
            
            WellesleyAdventure w = new WellesleyAdventure();
            w.setVisible(true);
        });
    }
}
