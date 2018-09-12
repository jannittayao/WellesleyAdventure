import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * NavBar is a panel that remains on the left of the GUI throughout our program.
 * It contains NavButtons Home, Map, Enter, and About
 *
 * @Silvia
 * @5-15-18
 */
public class NavBar extends JPanel
{
    JButton home, map, enter, about;
    
    /**
     * Constructor for NavBar calls helper method initNavBar
     */
    public NavBar(){
        initNavBar();
    }
    
    /**
     * creates buttons and adds to NavBar panel
     * sets layout of NavBar
     */
    public void initNavBar(){
        //create buttons
        home = new NavButton("Home");
        map = new NavButton("Map");
        enter = new NavButton("Return");
        about = new NavButton("About");
        
        //add the buttons
        add(home);add(map);add(enter);add(about);
        
        //set the layout
        setLayout(new GridLayout(4,1));
        setPreferredSize(new Dimension(500,250));
        setVisible(true);
        setBackground(new Color(19,63,132));
    }
    
    //GETTERS ---------------
    /**
     * @return NavButton with title Home
     */
    public JButton getHome(){
        return home; }
    
    /**
     * @return NavButton with title Map
     */
    public JButton getMap(){
        return map; }
    
    /**
     * @return NavButton with title Enter
     */    
    public JButton getEnter(){
        return enter; }
    
    /**
     * @return NavButton with title About
     */
    public JButton getAbout(){
        return about;}
}
