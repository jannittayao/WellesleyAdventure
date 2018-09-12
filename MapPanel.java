import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 * MapPanel is used in WellesleyGUI to display map of Wellesley campus with buttons
 * for places in WellesleyMap graph
 *
 * @Silvia
 * @5-16-18
 */
public class MapPanel extends JPanel
{
    WellesleyMap mapGraph;
    LinkedList<JButton> buttonList;
    
    /**
     * Constructor of MapPanel objects, calls initMap helper method
     */
    public MapPanel(){
        initMap(); 
    }
    
    /**
     * Helper method for MapPanel constructor
     * 
     */
    private void initMap(){
        mapGraph = new WellesleyMap();
        buttonList = new LinkedList<JButton>(); //list of all buttons on MapPanel
        
        setBackground(new Color(19,63,132));
        
        //set up the image of the map
        JPanel map = new ImagePanel("Map.jpg");
        map.setOpaque(false);
        add(map);
        
        //set up the buttons
        JPanel links = new JPanel(); //subpanel of place buttons
        links.setBackground(new Color(19,63,132));
        links.setLayout(new GridLayout(5, 10));
        for(Place p: mapGraph.getPlaces()){ //for every place in graph, set up new button
            JButton b = new JButton(p.toString());
            
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Helvetica",Font.PLAIN, 25));
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            
            buttonList.add(b); //update list of MapPanel's buttons
            links.add(b);
        }
        
        add(links);
    }
    
    /**
     * @returns list of all buttons on MapPanel
     */
    public LinkedList<JButton> getLinks(){
        return buttonList;
    }
}
