import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 * Extension to JFrame which defines the navigation bar which is
 * visible throughout the userinterface of WellesleyAdventure
 *
 * @Silvia Zeamer
 * @5-15-2017
 */
public class WellesleyAdventure extends JFrame
{
    JPanel content;
    CardLayout cardLayout;
    JPanel currentPlace;
    JPanel currentQuestion;
    NavBar nav;
    WellesleyMap mapGraph;
    ChooseYourOwnAdventure adventure;
    QuestionPanel quiz;
    JPanel map;
    JPanel home;
    JPanel about;
    
    /**
     * Constructor for the WellesleyAdventure class; sets up the 
     * UI for the whole program
     * 
     * @params none
     */
    public WellesleyAdventure(){
        initUI();
    }
    
    /**
     * Private class which sets up the elements of the UI and adds
     * action listeners connecting buttons to the classes which
     * define their behaviors
     * 
     * @params none
     * @return void
     */
    private void initUI() {
        //set up the underlying graph
        mapGraph = new WellesleyMap();
        
        //set up the window
        setTitle("Choose Your Wellesley Adventure");
        setSize(2000, 1400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(19,63,132));
  
        //add the navigation bar
        nav = new NavBar();
        add(nav, BorderLayout.LINE_START);
        
        //set up the content
        content = new JPanel();
        cardLayout = new CardLayout();
        content.setLayout(cardLayout);
        add(content);
        
        //set up the home panel
        LinkedList<String> links = new LinkedList<String>(Arrays.asList("Explore", "Adventure"));
        home = new PlacePanel("Photographs/home.jpg", links, "");
        addPlaceListeners((PlacePanel)home);
        
        //set up the map panel
        map = new MapPanel();
        for(JButton b: ((MapPanel)map).getLinks()){
            b.addActionListener(new PlaceListener());
        }
        
        //set up the current location panel
        update();
        
        //set up the info panel
       String info = "Choose Explore mode to learn about buildings and fun places on Wellesley’s campus. " +
       "Click on buildings listed in the legend at the bottom left corner of the map to learn about them. "+
       "You’ll find a fun fact and a list of other places they’re connected to! "+
       "Choose quiz mode to take a short quiz that takes you through a day of activities at Wellesley! "+
       "Click on your answer to advance to the next question. "+
       "Once you’re done, you’ll find out what portion of your day was spent on academics, having fun with friends, and on self-care. "
       + "You’ll also get a list of places you visited during your day at Wellesley. "
       + "Created by: Isabelle Chun, Jannitta Yao, Silvia Zeamer";
       
       //set up the choose your own adventure panel
       adventure = new ChooseYourOwnAdventure();
       quiz = new QuestionPanel(adventure.getCurrentQuestion(), adventure);
       for(JButton b: quiz.getButtons()){
           b.addActionListener(new QuizListener());
        }
       
       // try{
            // Scanner scan = new Scanner(new File("about.txt"));
            // while(scan.hasNextLine()){
                // System.out.println(scan.nextLine());
                // //info = info + scan.next();
            // }
            // scan.close();
       // }
       // catch(FileNotFoundException e){
           // System.out.println("File not found.");
        // }
        
       about = new InfoBar("About",info);
        
        
        //add elements to the card layout
        content.add(home, "HOME");
        content.add(map, "MAP");
        content.add(currentPlace, "CPLACE");
        content.add(about, "ABOUT");
        content.add(quiz, "QUIZ");
        
        //start with the home page
        goHome();
        
        //set up NavBar action listeners
        nav.getHome().addActionListener(new NavListener());
        nav.getMap().addActionListener(new NavListener());
        nav.getEnter().addActionListener(new NavListener());
        nav.getAbout().addActionListener(new NavListener());
    }
    
    /**
     * Updates the current location displayed in explore mode by 
     * checking the current location on the underlying graph of
     * place objects and making a new placepanel using the information
     * it contains.
     * 
     * @params none
     * @return void
     */
    public void update(){
        Place cp = mapGraph.getCurrentPlace();
        
        //make a LinkedList of attached places
        LinkedList<String> linkedNames = new LinkedList<String>();
        for(Place p: mapGraph.requestNeighbors(cp)){
            linkedNames.add(p.toString());
        }
        
        //System.out.println(linkedNames);
        
        //make a PlacePanel
        currentPlace = new PlacePanel(cp.getPhoto(), linkedNames, cp.toString());
        //System.out.println(cp.getPhoto());
        //add actionlisteners to all the buttons
        addPlaceListeners((PlacePanel)currentPlace);
    }
    
    /**
     * Helper method to add listeners to the buttons in a PlacePanel
     * 
     * @params PlacePanel p a PlacePanel to which to add listeners
     * @return void
     */
    public void addPlaceListeners(PlacePanel p){
        for(JButton j: ((PlacePanel)p).getButtons()){
            j.addActionListener(new PlaceListener());
        }
    }
    
    /**
     * Helper method which returns the navBar instance used in the GUI
     * 
     * @params none
     * @return NavBar the navigation bar used
     */
    public NavBar getNav(){
        return nav;
    }
    
    /**
     * Sets the contents of the right side of the screen to the home
     * panel; called during setup and in response to the home 
     * button in the navbar
     * 
     * @params none
     * @return void
     */
    public void goHome(){
        content.removeAll();
        content.add(home, "HOME");
        cardLayout.show(content, "HOME");
        content.repaint();
        content.revalidate();
    }
    
    /**
     * Sets the contents of the right side of the screen to the map
     * panel, called in response to the map button in the navbar
     * 
     * @params none
     * @return void
     */
    public void goToMap(){
        content.removeAll();
        content.add(map, "MAP");
        cardLayout.show(content, "MAP");
        content.repaint();
        content.revalidate();
    }
    
    /**
     * Sets the contents of the right side of the screen to the current
     * place on the map
     * 
     * @params none
     * @return void
     */
    public void returnToPlace(){
        content.removeAll();
        content.add(currentPlace, "CPLACE");
        cardLayout.show(content, "CPLACE");
        content.repaint();
        content.revalidate();
    }
    
    /**
     * Sets the contents of the right side of the screen to the about
     * panel with instructions for running the program
     * 
     * @params none
     * @return void
     */
    public void goToAbout(){
        content.removeAll();
        content.add(about, "ABOUT");
        cardLayout.show(content, "ABOUT");
        content.repaint();
        content.revalidate();
    }
    
    /**
     * Sets the contents of the right side fo the screen to the 
     * current QuestionPanel, allowing the user to go through a
     * series of decisions in a day at Wellesley
     * 
     * @params none
     * @return void
     */
    public void goToQuiz(){
        content.removeAll();
        quiz = new QuestionPanel(adventure.getCurrentQuestion(),adventure);
        
        if(!adventure.getCurrentQuestion().isLeaf()){
            for(JButton b: quiz.getButtons()){
               b.addActionListener(new QuizListener());
            }
        }
        content.add(quiz);
        cardLayout.show(content, "QUIZ");
        content.repaint();
        content.revalidate();
    }
    
    /**
     * Class representing an action listener which responds to input
     * from the user via the navigation bar; calls the methods which
     * set the contents of the right side of the screen
     */
    public class NavListener implements ActionListener{
        /**
         * Called when actionEvents are generated by the buttons
         * in the navigation bar; sets the contents of the 
         * cardLayout containing the elements of the screenś right
         * side
         * 
         * @params ActionEvent e an ActionEvent generated by user interaction
         * with a button
         * @return void
         */
        public void actionPerformed(ActionEvent e){
            returnToPlace();
            //handle actionsevents from the nav bar
            if(e.getSource() == nav.getHome()){
                goHome();
            }
            else if(e.getSource() == nav.getMap()){
                goToMap();
            }
            else if(e.getSource() == nav.getEnter()){
                returnToPlace();
            }
            else{
                goToAbout();
            }
        }
    }
    
    /**
     * Class representing an action listener which responds to
     * user input from buttons which lead to places as well as 
     * to the two buttons leading to explore mode and to adventure
     * mode in the PLacePanel used in the home panel
     */
    public class PlaceListener implements ActionListener{
        /**
         *Called when actionEvents are generated by the buttons
         *in the  placePanel and mapPanel class; sets the contents of the 
         *cardLayout containing the elements of the screenś right
         *side. Goes to places, or enters explore or adventure mode
         *
         *@params actionEvent e the actonEvent triggerred by interaction 
         *with a button
         *@return void
         */
        public void actionPerformed(ActionEvent e){
            String action = e.getActionCommand();
            if(action=="Explore"){
                   update();
                   returnToPlace();
                } 
            else if(action=="Adventure"){
                    goToQuiz();
            }
            else{
                mapGraph.setCurrentPlace(action);
                // System.out.println(mapGraph.getCurrentPlace());
                update();
                returnToPlace();
            }
        }
    }

    /**
     * Class representing a listener which responds to input from
     * Quiz questions and traverses the binary tree of choices in addition
     * updating the current QuizPanel accordingly
     */
    public class QuizListener implements ActionListener{
        /**
         * Updates the userś position in the binary tree of choices
         * and updates the GUI to correspond with this position
         * 
         * @params ActionAvent e the action event triggered by a button
         * @return void
         */
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand() == "l"){
                adventure.answerQuestion("l");
                goToQuiz();
            }
            else if(e.getActionCommand() == "r"){
                adventure.answerQuestion("r");
                goToQuiz();
            }
        }
    }
}

