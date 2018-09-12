import java.util.Scanner;
import java.util.LinkedList;
import java.util.Vector;
import java.io.*;
/**
 * Map of Wellesley with place vertices and arcs between connected places.
 *
 * @Isabelle Chun
 * @5-8-18
 */
public class WellesleyMap
{
    private AdjListsGraphPlus<Place> map;
    private Place currentPlace;
    private LinkedList<Place> placeList;

    /**
     * Constructor for objects of class Map, adds all vertices and arcs by scanning 2 files.
     * place_list.txt is a text file with information for each new vertex to add to map.
     * place_list.txt is in alphabetical order by name of Place.
     * arcs_list.txt is a text file with a line for each arc, as 2 ints.
     * Ints in arcs_list.txt are based on order the places were entered, alphabetical order.
     */
    public WellesleyMap()
    {
        placeList = new LinkedList<Place>();
        map = new AdjListsGraphPlus<Place>();
        try { //add vertices
            Scanner scan = new Scanner (new File("place_list.txt"));
            while (scan.hasNext()){
                String placeLine = scan.nextLine();
                String[] placeLineSplit = placeLine.split(";");

                Place newPlace = new Place(placeLineSplit[0], placeLineSplit[1], placeLineSplit[2]);
                map.addVertex(newPlace);
                //Silvia
                placeList.add(newPlace);
            }
            scan.close();
            
            //add edges
            Scanner scanArcs = new Scanner(new File("arcs_list.txt"));
            while (scanArcs.hasNext()){
                int from = scanArcs.nextInt();
                int to = scanArcs.nextInt();
                map.addArc(from, to);
            }
            scanArcs.close();  
            
            currentPlace = getVertex("Your Dorm (Stone D)");
        }
        catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        
        
    }
    
    /**
     * getVertex searches for Place with same name as argument passed,
     * and returns that Place or null if not found in vertices.
     * 
     * @returns Place of same name as parameter
     */

    public Place getVertex(String placeName) {
        for (Place vertex: map.vertices) {
            if (vertex.toString().equals(placeName)) {
                return vertex;
            }
        }
        return null;
    }
    
    /**
     * updates currentPlace instance var
     * @params placeName is a placeName of a Place vertex
     */
    public void setCurrentPlace(String placeName) {
        currentPlace = getVertex(placeName);
        //could also be implemented to take in a Place placeName as param
        //currentPlace = placeName;
    }
    
    //by Silvia
    public Place getCurrentPlace(){
        return currentPlace;
    }
    
    public LinkedList<Place> getPlaces(){
        return placeList;
    }
    
    /**
     * returns string representation of Wellesley map, calls AdjListsGraphPlus' toString().
     */
    public String toString() {
        return map.toString() + "\nCurrent Place: " + currentPlace;
    }
    
    /**
     * returns a LinkedList<Place> of all vertices adjacent to the vertex given.
     */
    public  LinkedList<Place> requestNeighbors(Place vertex) {
        return map.getSuccessors(vertex);
    }
    

    public static void main(String[] args){
        WellesleyMap test = new WellesleyMap();
        System.out.println("\n" + test + "\n");
        Place lulu = test.getVertex("Lulu Chow Wang Campus Center");
        System.out.println(test.requestNeighbors(lulu));
        

    }
}