import java.awt.*;
import javax.swing.*;
import java.util.*;
import javafoundations.*;
/**
 * Write a description of class InfoBar here.
 *
 * @ Silvia Zeamer
 * @ 5-15-18
 */
public class InfoBar extends JPanel
{
    LinkedList<JButton> buttonList;
    
    /**
     * InfoBar constructor
     * @param buttons is a list of every button that an InfoBar will contain
     * @param title is the title of this InfoBar panel
     */
    public InfoBar(LinkedList<String> buttons, String title){
        buttonList = new LinkedList<JButton>();
        initInfo(buttons, title);
    }
    
    /**
     * initInfo is a helper method for InfoBar constructor.
     * updates the contents of an InfoBar panel.
     * 
     * @params buttons is a list of 
     * @params title is the main header of this panel.
     */
    private void initInfo(LinkedList<String> buttons, String title){

        //add title
        JLabel t = new JLabel(title, SwingConstants.CENTER); 
        t.setFont(new Font("Helvetica",Font.PLAIN, 45));
        t.setForeground(Color.WHITE);
        add(t);
        
        try { //if title is a Place vertex
        WellesleyMap tempMap = new WellesleyMap();
        String descrip = tempMap.getVertex(title).getDescrip();
        JLabel descripLabel = new JLabel(descrip, SwingConstants.CENTER); 
        descripLabel.setFont(new Font("Helvetica",Font.PLAIN, 25));
        descripLabel.setForeground(Color.WHITE);
        add(descripLabel); }
        catch (NullPointerException e) {}
        
        
        
        // JLabel connectionsLabel = new JLabel("Connections:", SwingConstants.CENTER);
        // connectionsLabel.setFont(new Font("Helvetica",Font.PLAIN, 25));
        // connectionsLabel.setForeground(Color.WHITE);
        // add(connectionsLabel);
        
        JPanel buttonsContainer = new JPanel(); //initialize new sub-panel for each place button
        buttonsContainer.setBackground(new java.awt.Color(19,63,132));
        //add each button from LinkedList to buttonsContainer sub-panel
        for(String s: buttons){ 
            JButton b = new JButton(s);
            
            //style buttons
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Helvetica",Font.PLAIN, 25));
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            
            //add buttons
            buttonsContainer.add(b);
            buttonList.add(b);
        }
        add(buttonsContainer);
        
        setLayout(new GridLayout(3,0));
        setPreferredSize(new Dimension(150, 250));
        setBackground(new java.awt.Color(19,63,132));
    }
    
    public InfoBar(String title, String info){
        buttonList = new LinkedList<JButton>();
        
        
        JLabel t = new JLabel(title, SwingConstants.CENTER);
        t.setFont(new Font("Helvetica",Font.PLAIN, 50));
        t.setForeground(Color.WHITE);
        add(t);
        
        JTextArea i = new JTextArea(info);
        i.setEditable(false);
        i.setFont(new Font("Helvetica",Font.PLAIN, 25));
        i.setForeground(Color.WHITE);
        i.setBackground(new Color(19,63,132));
        i.setLineWrap(true);
        i.setWrapStyleWord(true);
        i.setPreferredSize(new Dimension(1000, 500));

        add(i);
        
        setLayout(new GridLayout(2,1));
        setPreferredSize(new Dimension(1000, 500));
        setBackground(new java.awt.Color(19,63,132));
    }
    
    public InfoBar(ArrayQueue<Place> queue, String info, String title){
        buttonList = new LinkedList<JButton>();
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Helvetica",Font.PLAIN, 50));
        titleLabel.setPreferredSize(new Dimension(1000, 500));
        add(titleLabel);
        
        
        //container for analysis
        JPanel infoContainer = new JPanel();
        add(infoContainer);
        
        JPanel placeContainer = new JPanel();
        
        //add a label to the list of places visited
        JLabel places = new JLabel("Places Visited", SwingConstants.CENTER);
        places.setForeground(Color.WHITE);
        places.setFont(new Font("Helvetica", Font.BOLD, 25));
        placeContainer.add(places);
        
        placeContainer.setLayout(new GridLayout(0,1));
        //System.out.println(queue);
        ArrayQueue<Place> placeStore = new ArrayQueue<Place>();
        while(!queue.isEmpty()){
            Place p = queue.dequeue();
            //System.out.println(p);
            JLabel pLabel = new JLabel(p.toString(), SwingConstants.CENTER);
            pLabel.setForeground(Color.WHITE);
            pLabel.setFont(new Font("Helvetica", Font.PLAIN, 25));
            placeContainer.add(pLabel);
            placeStore.enqueue(p);
        }
        
        while(!placeStore.isEmpty()){
            queue.enqueue(placeStore.dequeue());
        }
        infoContainer.add(placeContainer);
        
        //add info text
        JTextArea i = new JTextArea(info);
        i.setEditable(false);
        i.setFont(new Font("Helvetica",Font.PLAIN, 25));
        i.setForeground(Color.WHITE);
        i.setBackground(new Color(19,63,132));
        i.setLineWrap(true);
        i.setWrapStyleWord(true);
        i.setPreferredSize(new Dimension(500, 500));
        infoContainer.add(i);
        
        //set up appearance
        placeContainer.setBackground(new java.awt.Color(19,63,132));
        infoContainer.setLayout(new GridLayout(1,2));
        infoContainer.setBackground(new java.awt.Color(19,63,132));
        setPreferredSize(new Dimension(1000, 500));
        setBackground(new java.awt.Color(19,63,132));
    }
    
    public LinkedList<JButton> getButtons(){
        return buttonList;
    }
}
