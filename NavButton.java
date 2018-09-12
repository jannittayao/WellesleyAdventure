import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * NavButton is a button for the NavBar panel.
 *
 * @Silvia
 * @5-15-18
 */
public class NavButton extends JButton
{
    /**
     * Constructor for NavButton calls initNavButton helper
     */
    public NavButton(String title){
        super(title); //inherits JButton constructor
        initNavButton();
    }
    
    /**
     * Helper for NavButton to style the button
     */
    public void initNavButton(){
        //style the button
        setForeground(Color.WHITE);
        setFont(new Font("Helvetica",Font.PLAIN, 50));
        setBackground(new Color(19,63,132));
    }
}
