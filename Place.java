/**
 * Place class for place vertices on the Map graph
 * CS 230 Final Project: Choose Your Own Wellesley Adventure
 *
 * @Isabelle Chun
 * @5-7-18
 */
public class Place
{
    // instance variables
    private String name, descrip, photoFileName;
    
    /**
     * Constructor for objects of class Place
     * @param name is the name of the Place
     * @param descrip is a one-sentence fun fact for this place
     * @param photoFileName is a jpg from the Photographs folder. photo of this place.
     */
    public Place(String name, String descrip, String photoFileName)
    {
        this.name = name;
        this.descrip = descrip;
        this.photoFileName = photoFileName;
    }
    
    //GETTERS---------------------------------
    /**
     * @returns fun fact string for this place
     */
    public String getDescrip() {
        return descrip; }
    
    /**
     * 
     * @returns string of file of photo of this place
     */
    public String getPhoto() {
        return "Photographs/" + photoFileName; }
    
    /**
     * compares 2 places to see if they are the same
     * @returns true if places have same name
     * @returns false if places do not have same name
     */    
    public boolean samePlace(Place p) {
        return name.equals(p.name);
    }
    
    /**
     * @returns name of place
     */
    public String toString() {
        return name;
    }
    
}