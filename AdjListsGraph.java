/********************************************************************
  * AdjListsGraph.java
  * Implementation of the Graph.java interface using adjacency lists. 
  * KNOWN FEATURES/BUGS: 
  * It handles unweighted graphs only, but it can be extended.
  * It does not handle operations involving non-existing vertices
  ********************************************************************/

import java.util.*;
import java.io.*;

public class AdjListsGraph<T> implements Graph<T>{
  private final int NOT_FOUND = -1;
  
  protected Vector<LinkedList<T>> arcs;   // adjacency matrices of arcs
  protected Vector<T> vertices;   // values of vertices
  
  /******************************************************************
    * Constructor. Creates an empty graph.
    ******************************************************************/
  public AdjListsGraph() {
    this.arcs = new Vector<LinkedList<T>>();
    this.vertices = new Vector<T>();
  }
  
  /******************************************************************
    * Constructor 2. Creates a new graph with same vertices and arcs as input.
    ******************************************************************/
  public AdjListsGraph(AdjListsGraph g) {
    this.vertices = (Vector<T>)g.vertices.clone();
    this.arcs = new Vector<LinkedList<T>>();
    for(int i=0; i<g.vertices.size(); i++){
      this.arcs.addElement((LinkedList<T>)((LinkedList<T>)g.arcs.get(i)).clone());
    } 
  }
  
  /*****************************************************************
    * Creates and returns a new graph using the data found in the input file.
    * If the file does not exist, a message is printed. 
    *****************************************************************/
  public static AdjListsGraph<String> AdjListsGraphFromFile(String tgf_file_name) {
    AdjListsGraph<String> g = new AdjListsGraph<String>();
    try{ // to read from the tgf file
      Scanner scanner = new Scanner(new File(tgf_file_name));
      //read vertices
      while (!scanner.next().equals("#")){
        String token = "";
        token = scanner.next();
        g.addVertex(token); 
      }
      //read arcs
      while (scanner.hasNext()){
        int from = scanner.nextInt();
        int to = scanner.nextInt();
        g.addArc(from, to);
      }
      scanner.close();
    } catch (IOException ex) {
      System.out.println(" ***(T)ERROR*** The file was not found: " + ex);
    }
    return g;
  }
  
  
  /******************************************************************
    * Returns true if the graph is empty and false otherwise. 
    ******************************************************************/
  public boolean isEmpty() {
    return vertices.size() == 0;
  }
  
  /******************************************************************
    * Returns the number of vertices in the graph.
    ******************************************************************/
  public int getNumVertices() { 
    return vertices.size(); 
  }
  
  /******************************************************************
    * Returns the number of arcs in the graph by counting them.
    ******************************************************************/
  public int getNumArcs() {
    int totalArcs = 0;
    for (int i = 0; i < vertices.size(); i++) //for each vertex
      //add the number of its connections
      totalArcs = totalArcs + arcs.get(i).size(); 
    
    return totalArcs; 
  }
  
  
   /** Returns true if this graph contains the vertex, false otherwise. */
  public boolean containsVertex(T vertex){
      return vertices.contains(vertex);
  }
  
  
  /******************************************************************
    * Returns true iff a directed edge exists from v1 to v2.
    ******************************************************************/
  public boolean isArc (T vertex1, T vertex2){ 
    try {
      int index = vertices.indexOf(vertex1);
      LinkedList<T> l = arcs.get(index);
      return (l.indexOf(vertex2) != -1);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println(vertex1 + " vertex does not belong in the graph");
      return false;
    }
  }
  

  /******************************************************************
    Returns true iff an edge exists between two given vertices
    which means that two corresponding arcs exist in the graph
    ******************************************************************/
  public boolean isEdge (T vertex1, T vertex2) {
    return (isArc(vertex1, vertex2) && isArc(vertex2, vertex1)); 
  }
  
  /******************************************************************
   * Returns true IFF the graph is undirected, that is, for every 
   *    pair of nodes i,j for which there is an arc, the opposite arc
   *    is also present in the graph.  
   * An empty graph is undirected ####is that all right?????
   ******************************************************************/
  public boolean isUndirected() {
    for (int i=0; i<arcs.size(); i++) {
      for (T vertex : arcs.get(i)) {
        int index = vertices.indexOf(vertex);
        LinkedList<T> l = arcs.get(index);
        if (l.indexOf(vertices.get(i)) == -1) {
          return false;
        }
      }
    }
    return true;
  }
  
//  /******************************************************************
//    Adds a vertex to the graph, expanding the capacity of the graph
//    if necessary.  If the vertex already exists, it does not add it.
//    ******************************************************************/
  public void addVertex (T vertex) {
    if (vertices.indexOf(vertex) == -1) { //the vertex is not already there
      // add it to the vertices vector
      vertices.add(vertex); 
       
      //indicate that the new vertex has no arcs to other vertices yet
      arcs.add(new LinkedList<T>()); 
    }
  }
  
    /** Returns the object associated with a vertex index.
    * If the vertex does not exist, a null is returned. */
  public T getVertex (int index){
      if (index<0 || index>=vertices.size())  return null;
      return vertices.get(index);
  }

  /******************************************************************
    * Removes a single vertex with the given value from the graph.  
    * Uses equals() for testing equality
    ******************************************************************/
  public void removeVertex (T vertex) {
    int index = vertices.indexOf(vertex);
    this.removeVertex(index);
  }
  
  /******************************************************************
    Helper. Removes a vertex at the given index from the graph.   
    Note that this may affect the index values of other vertices.
    ******************************************************************/
  private void removeVertex (int index) {
    T vertex = vertices.get(index);
    vertices.remove(index); //remove vertex from vertices vector
    arcs.remove(index); //remove its list of adjacent vertices vector
    //remove it from the other lists, wherever it was found
    for (int i = 0; i < arcs.size(); i++) {
      for (T otherVertex : arcs.get(i)) {
        if (otherVertex.equals(vertex))
          arcs.get(i).remove(vertex);
      }
    }
  }
  
  /******************************************************************
    * Inserts an edge between two vertices of the graph.
    * If one or both vertices do not exist, ignores the addition.
    ******************************************************************/
  public void addEdge (T vertex1, T vertex2) {
    // getIndex will return NOT_FOUND if a vertex does not exist,
    // and the addArc() will not insert it
    this.addArc (vertex1, vertex2);
    addArc (vertex2, vertex1);
  }
  
  /******************************************************************
    * Inserts an arc from v1 to v2.
    * If the vertices exist, else does not change the graph. 
   ******************************************************************/
  public void addArc (T source, T destination){
    int sourceIndex = vertices.indexOf(source);
    int destinationIndex = vertices.indexOf(destination);
    
    //if source and destination exist, add the arc. do nothing otherwise
    if ((sourceIndex != -1) && (destinationIndex != -1)){
      LinkedList<T> l = arcs.get(sourceIndex);
      l.add(destination);
      }
    }
  
//  /******************************************************************
//    Helper. Inserts an edge between two vertices of the graph.
//    ******************************************************************/
  protected void addArc (int index1, int index2) {
    //if (indexIsValid(index1) && indexIsValid(index2))
      //vertices.get(index1).add(v2);
    LinkedList<T> l = arcs.get(index1-1);
    T v = vertices.elementAt(index2-1);
    l.add(v);
  }
  
  
  /******************************************************************
    * Removes an edge between two vertices of the graph.
    * If one or both vertices do not exist, ignores the removal.
    ******************************************************************/
  public void removeEdge (T vertex1, T vertex2) {
    removeArc (vertex1, vertex2);
    removeArc (vertex2, vertex1);
  }
  
  
  /******************************************************************
    * Removes an arc from vertex v1 to vertex v2,
    * if the vertices exist, else does not change the graph. 
   ******************************************************************/
  public void removeArc (T vertex1, T vertex2) {
    int index1 = vertices.indexOf(vertex1);
    int index2 = vertices.indexOf(vertex2);
    removeArc (index1, index2);
  }
  
  /******************************************************************
    * Helper. Removes an arc from index v1 to index v2.
    ******************************************************************/
  private void removeArc (int index1, int index2) {
    //if (indexIsValid(index1) && indexIsValid(index2))
    T to = vertices.get(index2);
    LinkedList<T> connections = arcs.get(index1);
    connections.remove(to);
  }
  /******************************************************************
    * Retrieve from a graph the vertices x pointing to vertex v (x->v)
    * and returns them onto a linked list
    ******************************************************************/
  public LinkedList<T> getPredecessors(T vertex){
    LinkedList<T> previous = new LinkedList<T>(); // to be returned
    //for each linked list in the arcs vector
    for (int i = 0; i < arcs.size(); i++) {
      //search for input vertex
      int index = arcs.get(i).indexOf(vertex); 
      //if found, then add predecessor to the previous list
      if (index != -1)
        previous.add(vertices.get(i)); 
    }    
    return previous;    
  }
  
  /******************************************************************
   * Retrieve from a graph the vertices x following vertex v (v->x)
   * and returns them onto a linked list
    ******************************************************************/
  public LinkedList<T> getSuccessors(T vertex) {
    int index = vertices.indexOf(vertex);
    //if vertex does not exist, return null
    if (index<0 || index>=vertices.size()) 
       return null;
    
    return arcs.get(index);
  }
//  
//  /******************************************************************
//    Returns a string representation of the graph. 
//    ******************************************************************/
  public String toString() {
    if (vertices.size() == 0) return "Graph is empty";
    
    String result = "Vertices: \n";
    result = result + vertices;
    
    result = result + "\n\nEdges: \n";
    for (int i=0; i< vertices.size(); i++)
      result = result + "from " + vertices.get(i) + ": "  + arcs.get(i) + "\n";
   
    return result;
  }
  
  /******************************************************************
    * Saves the current graph into a .tgf file.
    * If it cannot save the file, a message is printed. 
    *****************************************************************/
  public void saveToTGF(String fName) {
    try {
      PrintWriter writer = new PrintWriter(new File(fName));
      
      //write vertices by iterating through vector "vertices"
      for (int i = 0; i < vertices.size(); i++) {
          writer.print((i+1) + " " + vertices.get(i));
          writer.println("");
      }
      writer.print("#"); // Prepare to print the edges
      writer.println("");
      
      //write arcs by iterating through arcs vector
      for (int i = 0; i < arcs.size(); i++){ //for each linked list in arcs
        for (T vertex :arcs.get(i)) {
          int index2 = vertices.indexOf(vertex);
          writer.print((i+1) + " " + (index2+1));
          writer.println("");
        }
      }
      writer.close();
    } catch (IOException ex) {
      System.out.println("***ERROR***" +  fName + " could not be written: " + ex);
    }
  }
  
  
//  /******************************************************************
//    Very Basic Driver program. 
//    ******************************************************************/
//  
  public static void main (String args[]){
    //System.out.println("TESTING METHODS"); System.out.println("_________________");
    AdjListsGraph<String> g = new AdjListsGraph<String>();
    System.out.println(g.isUndirected());
    System.out.println("New graph created.");
    System.out.println("isEmpty is TRUE: \t" + g.isEmpty());
    
    System.out.println("Adding 4 vertices: A, B, C, D");
    g.addVertex("A"); g.addVertex("B"); g.addVertex("C"); g.addVertex("D");
   //add some edges
    g.addArc("A", "B");
    g.addArc("B", "A");
    g.addArc("A", "C");
    g.addArc("C", "B");
    g.removeVertex("D");
    g.removeArc("A","B");
    g.removeEdge("A", "B");
    System.out.println("D predecessors" + g.getPredecessors("D"));
        System.out.println("A predecessors" + g.getPredecessors("A"));
    System.out.println(g);
    g.saveToTGF("out.tgf");
    System.out.println("D successors" + g.getSuccessors("D"));
        System.out.println("A successors" + g.getSuccessors("A"));
    
   
  }
  
}
