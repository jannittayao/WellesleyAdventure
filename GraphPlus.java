import java.util.LinkedList;
import javafoundations.*;

/********************************************************************
  *  DO NOT CHANGE THIS FILE.
  *  GraphPlus.java
  *  Extends the Graph interface 
  *  @author CS230 Staff
  *  @version 2018.4.19
  *********************************************************************/
public interface GraphPlus<T> extends Graph<T> {    
  
  /******************************************************************
    * Creates a new graph that has all the same vertices
    * and arcs as the original.
    * 
    * @return the new graph.
    *****************************************************************/
  public GraphPlus<T> clone();
  
  
  /******************************************************************
    * Checks if a vertex is a sink (points to no other vertex)
    * 
    * @param vertex: the potential sink vertex
    * @return true if the vertex is a sink, false if it is not.
    * @throws IllegalArgumentException if given vertex is not in graph
    ******************************************************************/
  public boolean isSink(T vertex);
  
  /******************************************************************
    * Checks if a vertex is a source (no vertex points to it)
    * 
    * @param vertex: the potential source vertex
    * @return true if the vertex is a source, false if it is not
    * @throws IllegalArgumentException if given vertex is not in graph
    ******************************************************************/
  public boolean isSource(T vertex);
  
  /******************************************************************
    * Retrieves all vertices that are sinks. 
    * 
    * @return all the sinks in a linked list
    ******************************************************************/
  public LinkedList<T> allSinks();
  
  /******************************************************************
    * Retrieves all vertices that are sources. 
    * 
    * @return all the sources in a linked list
    ******************************************************************/
  public LinkedList<T> allSources();
  
  /******************************************************************
    * Checks if a vertex is isolated, i.e., no vertices
    * point to it and it points to no vertices.
    * 
    * @param vertex: the vertex to check for isolation
    * @return true if the vertex is isolated, false if it is not
    * @throws IllegalArgumentException if given vertex is not in graph
    ******************************************************************/
  public boolean isIsolated(T vertex);
  
  
  /******************************************************************
    * Returns a LinkedList containing a DEPTH-first search
    * traversal of the graph starting at the given vertex. The result
    * list should contain all vertices visited during the traversal in
    * the order they were visited.
    * You can use pseudocode from class materials as a starting point.
    * 
    * @param vertex: the starting vertex for the traversal
    * @return a linked list with the vertices in depth-first order
    * @throws IllegalArgumentException if given vertex is not in graph
    *****************************************************************/
  public LinkedList<T> dfsTraversal(T startVertex);

   /******************************************************************
    * Returns an ArrayIterator containing a DEPTH-first search
    * traversal of the graph starting at the given vertex. The result
    * iterator should contain all vertices visited during the traversal in
    * the order they were visited.
    * You can use pseudocode from class materials as a starting point.
    * 
    * @param vertex: the starting vertex for the traversal
    * @return an ArrayIterator with the vertices in depth-first order
    * @throws IllegalArgumentException if given vertex is not in graph
    *****************************************************************/
   public ArrayIterator<T> dfsTraversalIter(T startVertex);
  
  /******************************************************************
    * Returns a LinkedList containing a BREADTH-first search
    * traversal of a graph starting at the given vertex.  The result
    * list should contain all vertices visited during the traversal in
    * the order they were visited.
    * 
    * @param vertex: the starting vertex for the traversal
    * @return a linked list with the vertices in breadth-first order
    * @throws IllegalArgumentException if given vertex is not in graph
    *****************************************************************/
  public LinkedList<T> bfsTraversal(T startVertex);
  
/******************************************************************
    * Returns an ArrayIterator containing a BREADTH-first search
    * traversal of the graph starting at the given vertex. The result
    * iterator should contain all vertices visited during the traversal in
    * the order they were visited.
    * 
    * @param vertex: the starting vertex for the traversal
    * @return an ArrayIterator with the vertices in breadth-first order
    * @throws IllegalArgumentException if given vertex is not in graph
    *****************************************************************/
  public ArrayIterator<T> bfsTraversalIter(T vertex);
}
