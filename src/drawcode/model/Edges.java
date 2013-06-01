package drawcode.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 08/05/13
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
public class Edges implements Iterable<Edge> {
    private ArrayList<Edge> edges;

    public Edges(){
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public void removeEdge(Edge edge){
        this.edges.remove(edge);
    }

    public void removeEdgesFromNode(Node node){
        Iterator<Edge> iter = edges.iterator();
        while(iter.hasNext()){
            Edge edge = iter.next();
            if(edge.isMember(node)){
                iter.remove();
            }
        }
    }

    public Iterator<Edge> iterator(){
        return this.edges.iterator();
    }

    public boolean areConnected(Node node1, Node node2){
        for(Edge edge: edges) {
            if(edge.areConnected(node1, node2)){
                return true;
            }
        }
        return false;
    }

}
