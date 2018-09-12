import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.util.*;
/**
 * Class representing a panel in the GUI with a photograph of a place and the 
 * vertices it connects to.
 *
 * @Silvia Zeamer
 * @5-15-2018
 */
public class PlacePanel extends JPanel
{
    InfoBar info;
    JPanel img;
    
    /**
     * Constructor for the PlacePanel which takes a String with the name
     * 
     * @params
     */
    public PlacePanel(String placePic, LinkedList<String> links, String title){
        initPlace(placePic, links, title);
    }
    
    /**
     * Helper method to initialize the PlacePanel with an ImagePanel
     */
    private void initPlace(String placePic, LinkedList<String> links, String title){
        
        //add image to top
        img = new ImagePanel(placePic);
        add(img);
        
        //add info and navigation to bottom
        info = new InfoBar(links, title);
        add(info);
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
    
    /**
     * Returns a list of buttons in the place panel
     * 
     * @params none
     * @returns LinkedList<JButtons>
     */
    public LinkedList<JButton> getButtons(){
        return info.getButtons();
    }
    
    /**
     * Returns the place panel's info bar
     * 
     * @params none
     * @return InfoBar
     */
    public InfoBar getInfoBar(){
        return info;
    }
}