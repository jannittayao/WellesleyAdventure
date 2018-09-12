import javafoundations.*;

/**
 * ChooseYourOwnAdventure creates a binary tree that allows users to 
 * creae their own unique ~Wellesley experience~.
 *
 * @author Jannitta Yao
 * @version 5/16/18
 */
public class ChooseYourOwnAdventure
{
    //private 
    LinkedBinaryTree<Question> adventure; //initialize instance variables 
    Question currentQ;
    LinkedBinaryTree<Question> currentTree;
    ArrayQueue<Place> places; 
    
    /**
     * Constructor method; sets up adventure database
     */
    public ChooseYourOwnAdventure(){
        places = new ArrayQueue<Place>();
        
        WellesleyMap map = new WellesleyMap();
        Question q1 = new Question("It's 8 am and you just woke up. How do you start your day?",
                                    "Go get breakfast and then go to class in SCI",
                                    "Go back to bed",
                                    map.getVertex("Science Center"),
                                    map.getVertex("Your Dorm (Stone D)"));
        Question q2 = new Question("You got breakfast and you made it to class. What are you doing after?",
                                    "Go get lunch with your friends in Lulu",
                                    "Attend a lecture in the academic quad",
                                    map.getVertex("Lulu Chow Wang Campus Center"),
                                    map.getVertex("Academic Quad"));                            
        Question q3 = new Question("Oh no! You hit snooze too many times and you woke up 5 minutes before class." 
                                    + " What do you do?",
                                    "Run to your class in SCI",
                                    "Go back to bed",
                                    map.getVertex("Science Center"),
                                    map.getVertex("Your Dorm (Stone D)"));    
        Question q4 = new Question("Lunch with your friends was a lot of fun. What are your plans for the afternoon?",
                                    "Study in Clapp Library",
                                    "Hang out with your friends in Lulu",
                                    map.getVertex("Clapp"),
                                    map.getVertex("Lulu Chow Wang Campus Center"));        
        Question q5 = new Question("You learned a lot from the lecture! What are you doing for the rest of the afternoon?",
                                    "Take a walk in the arboretum",
                                    "Study in SCI",
                                    map.getVertex("Arboretum"),
                                    map.getVertex("Science Center"));  
        Question q6 = new Question("You made it to class with 30 seconds to spare! What are you doing after class?",
                                    "Go on a walk around Lake Waban",
                                    "Relax in the academic quad with your friends",
                                    map.getVertex("Lake Waban"),
                                    map.getVertex("Academic Quad"));                             
        Question q7 = new Question("You went back to sleep and woke up at 12 pm. What are you doing after you get up?",
                                    "Work out at the KSC",
                                    "Visit the Davis Museum for your Art History essay",
                                    map.getVertex("KSC"),
                                    map.getVertex("Davis Museum"));   
        Question q8 = new Question("Studying in Clapp was very productive and you got a lot of work done." +
                                    " What are your evening plans?",
                                    "Finish up your work and go to bed early",
                                    "Make ramen with your roommate",
                                    map.getVertex("Your Dorm (Stone D)"),
                                    map.getVertex("Your Dorm (Stone D)"));
        Question q9 = new Question("You bumped into a friend that you hadn't seen in weeks at Lulu, and had a great " 
                                    + "time hanging out with them. What are your evening plans?",
                                    "Study in SCI",
                                    "Stay up late looking at memes",
                                    map.getVertex("Science Center"),
                                    map.getVertex("Your Dorm (Stone D)"));  
        Question q10 = new Question("The weather was beautiful today, and the arboretum even more so. "
                                    + "You saw so many cool plants and birds. What are your evening plans?",
                                    "Study for your midterm in Clapp",
                                    "Go to an org's performance in Alumnae Hall",
                                    map.getVertex("Clapp"),
                                    map.getVertex("Alumnae Hall"));   
        Question q11 = new Question("Studying in SCI was very productive and you got a lot of work done." +
                                    " What are your evening plans?",
                                    "Go to bed early",
                                    "Keep studying in SCI",
                                    map.getVertex("Your Dorm (Stone D)"),
                                    map.getVertex("Science Center"));   
        Question q12 = new Question("Lake Waban was so beautiful today, and you got to see so many dogs on your " +
                                    "walk around the lake. What are your evening plans?",
                                    "Go to Cafe Hoop for some nachos",
                                    "Look at the stars in the Observatory",
                                    map.getVertex("Lulu Chow Wang Campus Center"),
                                    map.getVertex("Observatory"));                              
        Question q13 = new Question("You managed to get a good tan while relaxing in the academic quad with your friends." + 
                                    " What are your evening plans?",
                                    "Study for your midterm in Lulu",
                                    "Go to bed early",
                                    map.getVertex("Lulu Chow Wang Campus Center"),
                                    map.getVertex("Your Dorm (Stone D)"));     
        Question q14 = new Question("You got a good workout in at the KSC. What are your evening plans?",
                                    "Finish your homework in the Observatory library",
                                    "Stay up until 3 am watching Vine compilations",
                                    map.getVertex("Observatory"),
                                    map.getVertex("Your Dorm (Stone D)")); 
        Question q15 = new Question("There were a lot of cool pieces at the Davis, and you found a lot of inspiration " +
                                    "just walking around the museum. What are your evening plans?",
                                    "Start drafting your Art History essay in Clapp",
                                    "Go to the roof of SCI to watch the sunset",
                                    map.getVertex("Clapp"),
                                    map.getVertex("Science Center"));   
        Question l1 = new Question("The day is over! You spent your day on " 
                                    + "75% productive classwork & 25% self-care", 
                                     "", "", map.getVertex(""), map.getVertex(""));
        Question l2 = new Question("The day is over! You spent your day on " 
                                    + "50% productive classwork & 25% activities with friends & 25% self-care", 
                                     "", "", map.getVertex(""), map.getVertex(""));                            
        Question l3 = new Question("The day is over! You spent your day on " 
                                    + "25% productive classwork & 25% activities with friends & 50% self-care", 
                                     "", "", map.getVertex(""), map.getVertex(""));
        Question l4 = new Question("The day is over! You spent your day on " 
                                    + "50% productive classwork & 50% self-care", 
                                     "", "", map.getVertex(""), map.getVertex("")); 
        Question l5 = new Question("The day is over! You spent your day on " 
                                    + "100% productive classwork", 
                                     "", "", map.getVertex(""), map.getVertex(""));   
        Question l6 = new Question("The day is over! You spent your day on " 
                                    + "25% productive classwork & 75% self-care", 
                                     "", "", map.getVertex(""), map.getVertex(""));  
        Question l7 = new Question("The day is over! You spent your day on " 
                                    + "100% self-care", 
                                     "", "", map.getVertex(""), map.getVertex(""));                             
        
        //creates subtrees of adventure
        LinkedBinaryTree<Question> n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14;
        n1 = new LinkedBinaryTree<Question> (q8,
                                           new LinkedBinaryTree<Question>(l1),
                                           new LinkedBinaryTree<Question>(l2)); 
        n2 = new LinkedBinaryTree<Question> (q9,
                                           new LinkedBinaryTree<Question>(l2),
                                           new LinkedBinaryTree<Question>(l3));                 
        n3 = new LinkedBinaryTree<Question> (q10,
                                           new LinkedBinaryTree<Question>(l1),
                                           new LinkedBinaryTree<Question>(l4)); 
        n4 = new LinkedBinaryTree<Question> (q11,
                                           new LinkedBinaryTree<Question>(l1),
                                           new LinkedBinaryTree<Question>(l5));
        n5 = new LinkedBinaryTree<Question> (q12,
                                           new LinkedBinaryTree<Question>(l1),
                                           new LinkedBinaryTree<Question>(l2));
        n6 = new LinkedBinaryTree<Question> (q13,
                                           new LinkedBinaryTree<Question>(l2),
                                           new LinkedBinaryTree<Question>(l3)); 
        n7 = new LinkedBinaryTree<Question> (q14,
                                           new LinkedBinaryTree<Question>(l6),
                                           new LinkedBinaryTree<Question>(l7));     
        n8 = new LinkedBinaryTree<Question> (q15,
                                           new LinkedBinaryTree<Question>(l4),
                                           new LinkedBinaryTree<Question>(l6));      
        n9 = new LinkedBinaryTree<Question> (q4, n1, n2);     
        n10 = new LinkedBinaryTree<Question> (q5, n3, n4);    
        n11 = new LinkedBinaryTree<Question> (q6, n5, n6);    
        n12 = new LinkedBinaryTree<Question> (q7, n7, n8);  
        n13 = new LinkedBinaryTree<Question> (q2, n9, n10);
        n14 = new LinkedBinaryTree<Question> (q3, n11, n12);
        
        adventure = new LinkedBinaryTree<Question> (q1, n13, n14); 
        
        currentQ = q1;
        currentTree = adventure;
    }
    
    /**
     * addPlace adds a Place to the ArrayQueue of Places
     * 
     * @param Place p Place to be added to Places 
     */
    public void addPlace(Place p){
        places.enqueue(p);
    }
    
    /**
     * containsPlace is a helper method that checks to see if a Place is in 
     * the ArrayQueue of Places 
     * 
     * @return boolean of whether Place is in Array of Places 
     */
    public boolean containsPlace(Place pl){
        ArrayQueue<Place> temp = new ArrayQueue<Place>();
        boolean contains = false;
        while (!places.isEmpty()){
            Place i = places.dequeue();
            if (i.equals(pl)) {
                temp.enqueue(i);
                contains = true;
                
            }
            else
                temp.enqueue(i);
        }
        while (!temp.isEmpty()){
            places.enqueue(temp.dequeue());
        }
        return contains;
    }
    
    /**
     * getQueue() returns the ArrayQueue of Places 
     * 
     * @return ArrayQueue of Places
     */
    public ArrayQueue<Place> getQueue(){
        return places;
    }
    
    //by Silvia
    /**
     * getCurrentQuestion() returns the current Question 
     * 
     * @return Question the currentQuestion asked
     */
    public Question getCurrentQuestion(){
        return currentQ;
    }
    
    //by Silvia
    /**
     * Connects the GUI to the BinaryTree and allows the user to play 
     * the Adventure mode of the WellesleyGUI
     */
    public void answerQuestion(String dir){
        if(dir == "l"){
            currentQ = currentTree.getLeft().getRootElement();
            currentTree = currentTree.getLeft();
            if(!currentQ.isLeaf()){
                places.enqueue(currentQ.getLeftPlace());
            }
        }
        else if(dir == "r"){
            currentQ = currentTree.getRight().getRootElement();
            currentTree = currentTree.getRight();
            if(!currentQ.isLeaf()){
                places.enqueue(currentQ.getRightPlace());
            }
        }
        
    }
    
    /**
     * Main method; tests methods 
     */
    public static void main (String[] args){
        //Testing for addPlace and containsPlace
        ChooseYourOwnAdventure test = new ChooseYourOwnAdventure();
        WellesleyMap map = new WellesleyMap();
        ArrayQueue<Place> q = test.getQueue();
        test.addPlace(map.getVertex("Clapp"));
        test.addPlace(map.getVertex("Science Center"));
        test.addPlace(map.getVertex("Lake Waban"));
        
        // System.out.println(q);
        // System.out.println("TESTING containsPlace() method\nIs SCI in the "+
                            // "Queue of Places?\nExpected: true Actual: " +
                            // test.containsPlace(map.getVertex("Science Center")));
        
        // System.out.println(test.getQueue());
    }
}