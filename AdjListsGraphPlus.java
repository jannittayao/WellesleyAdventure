import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;
import javafoundations.ArrayIterator;
import javafoundations.LinkedQueue;
import javafoundations.LinkedStack;

/**
 * CS 230 Assignment 7
 * @author Isabelle Chun (no partner)
 * @4-25-18
 */


public class AdjListsGraphPlus<T> extends AdjListsGraph<T> 
implements GraphPlus<T> {

    /** DO NOT MODIFY THE CONSTRUCTORS AND THE FIRST METHOD ******************
     * The methods you will implement follow below.
     *  @author CS230 Staff (of the three first methods)
     *  @version 2018.04.19
     */

    /**
     * Construct an empty graph.
     */
    public AdjListsGraphPlus() {
        super();
    }

    /**
     * Construct a graph with the same vertices and edges as the given original.
     * @param original
     */
    public AdjListsGraphPlus(AdjListsGraphPlus<T> original) {
        super(original);
    }

    public AdjListsGraphPlus(AdjListsGraph<T> original) {
        super(original);
    }

    /**
     * DO NOT MODIFY!
     * Read a TGF file and create an AdjListsGraphPlus<String> from it.
     * @param tgfFile - the TGF file to read
     * @return a graph created from the TGF file
     * @throws FileNotFoundException if TGF file is not found.
     */
    public static AdjListsGraphPlus<String> fromTGF(String tgfFile) throws FileNotFoundException {
        AdjListsGraph<String> g = AdjListsGraph.AdjListsGraphFromFile(tgfFile);
        AdjListsGraphPlus<String> gPlus = new AdjListsGraphPlus(g);
        return gPlus;
    }

    /**** IMPLEMENT THE METHODS BELOW *********************************
     * Replace "throw new UnsupportedOperationException();" with
     * a working implementation.
     ******************************************************************/
    public GraphPlus<T> clone() {
        GraphPlus<T> newGraph = new AdjListsGraphPlus();
        //clone every vertex
        for (int i = 0; i < vertices.size(); i++) {
            newGraph.addVertex(vertices.elementAt(i));
        }

        //clone every arc
        for (int j = 0; j < arcs.size(); j++) {
            LinkedList<T> arcsToAdd = arcs.elementAt(j);
            for (int k = 0; k < arcsToAdd.size(); k++) {
                newGraph.addArc(getVertex(j), arcsToAdd.get(k)); }
        }
        return newGraph;
    }

    /******************************************************************
     * Checks if a vertex is a sink (points to no other vertex)
     * 
     * @param vertex: the potential sink vertex
     * @return true if the vertex is a sink, false if it is not.
     * @throws IllegalArgumentException if given vertex is not in graph
     ******************************************************************/
    public boolean isSink(T vertex) {
        LinkedList<T> successors = getSuccessors(vertex);
        return (successors.size() < 1);
    }

    /******************************************************************
     * Retrieves the vertices that are sinks and 
     * @return all the sinks in a linked list
     ******************************************************************/
    public LinkedList<T> allSinks() {
        LinkedList<T> sinks = new LinkedList<T>();
        for (int i = 0; i < vertices.size(); i++) { //filter from all vertices
            if (isSink(vertices.elementAt(i)))
                sinks.add(vertices.elementAt(i));
        }
        return sinks;
    }

    /******************************************************************
     * Checks if a vertex is a source (no vertex points to it)
     * 
     * @param vertex: the potential source vertex
     * @return true if the vertex is a source, false if it is not
     * @throws IllegalArgumentException if given vertex is not in graph
     ******************************************************************/
    public boolean isSource(T vertex) {
        LinkedList<T> predecessors = getPredecessors(vertex);
        return (predecessors.size() < 1) ;
    }

    /******************************************************************
     * Retrieves the vertices that are sources and 
     * @return all the sources in a linked list
     ******************************************************************/
    public LinkedList<T> allSources() {
        LinkedList<T> sources = new LinkedList<T>();
        for (int i = 0; i < vertices.size(); i++) { //filter from all vertices
            if (isSource(vertices.elementAt(i)))
                sources.add(vertices.elementAt(i));
        }
        return sources;
    }

    /******************************************************************
     * Checks if a vertex is a isolated, b/c it's source and sink
     * @return true if the vertex is isolated, false if it is not
     ******************************************************************/
    public boolean isIsolated(T vertex) {
        return (isSink(vertex) && isSource(vertex));
    }

    /******************************************************************
     * Returns a LinkedList contining a DEPTH first search traversal 
     * starting at the given vertex. If the vertex is not valid, 
     * it returns an empty list
     * @return a linked list with the vertices in depth-first order
     *****************************************************************/
    public LinkedList<T> dfsTraversal(T vertex) {
        T currentVertex, nextVertex;
        LinkedStack<T> traversalStack = new LinkedStack<T>();
        LinkedList<T> traversalList = new LinkedList<T>();
        Vector<T> visited = new Vector<T>();
        boolean found;
        
        if (! containsVertex(vertex)) //if vertex given is not in graph, return empty LinkedList
            return traversalList;

        traversalStack.push(vertex);
        traversalList.add (vertex);
        visited.add(vertex);
        
        while (!traversalStack.isEmpty())
        {
            currentVertex = traversalStack.peek();
            LinkedList<T> successors = getSuccessors(currentVertex);
            found = false; //boolean used for when to pop from stack
            
            for (int i = 0; i < successors.size(); i++)
            {
                nextVertex = successors.get(i);
                if (!visited.contains(nextVertex)) {
                    traversalStack.push(nextVertex);
                    traversalList.add(nextVertex);
                    visited.add(nextVertex);
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty())
                traversalStack.pop();
        }
        return traversalList;
    }


    /******************************************************************
     * Returns an iterator contining a DEPTH first search traversal 
     * starting at the given vertex. If the vertex is not valid, 
     * it returns an empty iterator.
     * @return an ArrayIterator<T> with the vertices in depth-first order
     *****************************************************************/
    public ArrayIterator<T> dfsTraversalIter(T vertex) {
        T currentVertex, nextVertex;
        LinkedStack<T> traversalStack = new LinkedStack<T>();
        ArrayIterator<T> iter = new ArrayIterator<T>();
        Vector<T> visited = new Vector<T>();
        //visited is used to check whether a vertex is already in array iterator
        //since vertices are added to both at the same time
        boolean found;
        if (! containsVertex(vertex)) //if vertex given is not in graph, return empty iterator
            return iter;

        traversalStack.push(vertex); //stack contains vertices whose successors have yet to be checked
        iter.add (vertex);
        visited.add(vertex);
        
        while (!traversalStack.isEmpty())
        {
            currentVertex = traversalStack.peek();
            LinkedList<T> successors = getSuccessors(currentVertex);
            found = false; //boolean used for when to pop from stack
            
            for (int i = 0; i < successors.size(); i++) //check every successor of currentVertex to see if traversed yet
            {
                nextVertex = successors.get(i);
                if (!visited.contains(nextVertex)) { //if successor vertex (nextVertex) has not been visited, add it to
                    traversalStack.push(nextVertex);
                    iter.add(nextVertex);
                    visited.add(nextVertex);
                    found = true;
                }
            }
            
            if (!found && !traversalStack.isEmpty())
                traversalStack.pop();
        }
        return iter;
    }

    /******************************************************************
     * Returns a LinkedList contining a BREADTH first search traversal 
     * starting at the given vertex. If the vertex is not valid, 
     * it returns an empty list
     * @return a linked list with the vertices in breadth-first order
     *****************************************************************/
    public LinkedList<T> bfsTraversal(T vertex) {
        T currentVertex, nextVertex;
        LinkedQueue<T> traversalQueue = new LinkedQueue<T>();
        LinkedList<T> traversalList = new LinkedList<T>();
        Vector<T> visited = new Vector<T>();
        if (! containsVertex(vertex)) //if vertex given is not in graph, return empty LinkedList
            return traversalList;

        traversalQueue.enqueue(vertex);
        while (!traversalQueue.isEmpty())  {
            currentVertex = traversalQueue.dequeue();
            traversalList.add(currentVertex);
            for (int vertexIndex = 0; vertexIndex < getNumVertices(); vertexIndex++) {
                nextVertex = getVertex(vertexIndex);
                if (isArc(currentVertex, nextVertex) &&  !visited.contains(nextVertex))
                { //if arc exists and nextVertex is not yet in traversalList, add it
                    traversalQueue.enqueue(nextVertex);
                    visited.add(nextVertex); 
                }
            }
        }
        return traversalList;
    }

    /******************************************************************
     * Returns an iterator contining a BREADTH first search traversal 
     * starting at the given vertes. If the vertex is not valid, 
     * it returns an empty iterator.
     * @return an ArrayIterator<T> with the vertices in breadth-first order
     *****************************************************************/
    public ArrayIterator<T> bfsTraversalIter(T vertex) {
        T currentVertex, nextVertex;
        LinkedQueue<T> traversalQueue = new LinkedQueue<T>();
        ArrayIterator<T> iter = new ArrayIterator<T>();
        Vector<T> visited = new Vector<T>();
        if (! containsVertex(vertex)) //if vertex given is not in graph, return empty iterator
            return iter;

        traversalQueue.enqueue(vertex);
        while (!traversalQueue.isEmpty())  {
            currentVertex = traversalQueue.dequeue();
            iter.add(currentVertex);
            for (int vertexIndex = 0; vertexIndex < getNumVertices(); vertexIndex++) {
                nextVertex = getVertex(vertexIndex);
                if (isArc(currentVertex, nextVertex) &&  !visited.contains(nextVertex))
                { //if arc exists and nextVertex is not yet in traversalList, add it
                    traversalQueue.enqueue(nextVertex);
                    visited.add(nextVertex);
                }
            }
        }
        return iter;
    }
}
