import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 * Class which displays a location in the binary decision tree which
 * the user follows through a day at Wellesley. Appears differently 
 * depending on wether the current location is a leaf.
 *
 * @Silvia Zeamer
 * @5-16-2017
 */
public class QuestionPanel extends JPanel
{
    LinkedList<String> buttons;
    LinkedList<JButton> JButtons;
    String lAnswer;
    String rAnswer;
    String rQ;
    String lQ;
    ChooseYourOwnAdventure adventure;
    
    /**
     * Constructor which takes a Question q and a ChooseYourOwnAdventure
     * object a and displays either a question with two answer options
     * or ana analysis of the userś decisions depending on the location
     * in the ChooseYourOwnAdventure binary tree.
     * 
     * @params Question q, ChooseYourOwnAdventure a
     */
    public QuestionPanel(Question q, ChooseYourOwnAdventure a){
        //create a ChooseYourOwnAdventure Instance to get info from
        adventure = a;
        
        //create a LinkedList of the button names
        buttons = new LinkedList<String>();
        buttons.add(q.getLeftAnswer());
        buttons.add(q.getRightAnswer());
        
        //access the place associated with this question
        
        //add the picture associated with this questionś location
        
        
        //make an InfoBar with this question
        if(q.isLeaf()){
            InfoBar contents = new InfoBar(adventure.getQueue(), q.getQuestion(),"Analysis");
            add(contents);
        }
        else{
            InfoBar contents = new InfoBar(buttons, q.getQuestion());
            contents.getButtons().get(0).setActionCommand("l");
            contents.getButtons().get(1).setActionCommand("r");
            JButtons = contents.getButtons();
            add(contents);
       }
       setLayout(new GridLayout(1,1));
    }
    
    /**
     * Returns a LinkedList of JButtons contained in the Question
     * Panel
     * 
     * @params none
     * @return LinkedList<JButton>
     */
    public LinkedList<JButton> getButtons(){
        return JButtons;
    }
}
