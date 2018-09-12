
/**
 * Question class creates Question objects that have a question and left and right Place and questions associated with them
 *
 * @author Jannitta Yao
 * @version 5/15/18
 */
public class Question
{
    String question, Lanswer, Ranswer;
    Place Lplace, Rplace;
    
    /**
     * Constructor method, creates Question objects 
     * 
     * @param String q question associated with Question object
     * @param String a1 answer associated with the left branch of the tree
     * @param String a2 answer associated with the right branch of the tree
     * @param Place p1 Place object associated with the left branch of the tree
     * @param Place p2 Place object associated with the right branch of the tree
     */
    public Question(String q, String a1, String a2, Place p1, Place p2){
        question = q;
        Lplace = p1;
        Rplace = p2;
        Lanswer = a1;
        Ranswer = a2;
    }
    
    /**
     * getQuestion returns the question associated with the Question class
     * 
     * @return String question
     */
    public String getQuestion(){
        return question;
    }
    
    /**
     * getLeftPlace returns the Place object associated with the left branch of the subtree
     * 
     * @return Place object associated with the left branch of the subtree
     */
    public Place getLeftPlace(){
        return Lplace;
    }
    
    /**
     * getRightPlace returns the Place object associated with the right branch of the subtree
     * 
     * @return Place object associated with the right branch of the subtree
     */
    public Place getRightPlace(){
        return Rplace;
    }
    
    /**
     * getLeftAnswer returns the answer associated with the left branch of the subtree
     */
    public String getLeftAnswer(){
        return Lanswer;
    }
    
    /**
     * getRightAnswer returns the answer associated with the right branch of the subtree
     */
    public String getRightAnswer(){
        return Ranswer;
    }
    
    /**
     * isLeaf checks to see if the Question object is a leaf of a tree
     * 
     * @return boolean whether the Question object is the leaf of a tree
     */
    public boolean isLeaf(){
        return (Lanswer.equals("") && Ranswer.equals(""));
    }
    
    /**
     * toString method returns a nicely formatted String of the Question object
     * 
     * @return String of Question object
     */
    public String toString() {
        String s = ""; 
        s += "Question: " + question;
        
        return s;
    }
    
    /**
     * Main method; test methods associated with the Question class
     */
    public static void main (String[] args){
        WellesleyMap map = new WellesleyMap(); //create new map for testing 
        Question q1 = new Question("Where do you want to study?", "Go to Clapp!", "Go to Science Center!", map.getVertex("Clapp"), map.getVertex("Science Center"));
        System.out.println("Testing getRightPlace()\nExpected: Clapp Actual: " + q1.getRightPlace());
        System.out.println("Testing getLeftPlace()\nExpected: Science Center Actual: " + q1.getLeftPlace());
        System.out.println("Testing getRightAnswer()\nExpected: Go to Clapp! Actual: " + q1.getRightAnswer());
        System.out.println("Testing getLeftAnswer()\nExpected: Go to Science Center! Actual: " + q1.getLeftAnswer());
        
        //Creates a Question that is a leaf of the subtree
        Question q2 = new Question("Hello", "", "", map.getVertex(""), map.getVertex(""));
        System.out.println("Testing isLeaf: Expected: true Actual: " + q2.isLeaf());
    }
}