package drawcode.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 01/05/13
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
public class Graph implements Serializable {

    private final Edges edges;
    private final Nodes primitiveNodes;
    private final Nodes objectNodes;

    public Graph(){
        this.edges = new Edges();
        this.primitiveNodes = new Nodes(); //TODO: This constructor might need to be changed to make Graph serializable
        this.objectNodes = new Nodes(); //TODO: This constructor might need to be changed to make Graph serializable
    }

    public void addNode(PrimitiveNode node){
        this.primitiveNodes.addNode(node);
    }

    public void addNode(ObjectNode node){
        this.objectNodes.addNode(node);
    }

    public void removeNode(PrimitiveNode node){
        this.primitiveNodes.removeNode(node);
        this.edges.removeEdgesFromNode(node);
    }

    public void removeNode(ObjectNode node){
        this.objectNodes.removeNode(node);
        this.edges.removeEdgesFromNode(node);
    }

    public void addEdge(Node rootNode, Node targetNode){
        this.edges.addEdge(new Edge(rootNode, targetNode));
    }

    public void removeEdge(Edge edge){
        this.edges.removeEdge(edge);
    }

    public boolean isMemberNode(Node n){
        return primitiveNodes.isMemberNode(n) || objectNodes.isMemberNode(n);
    }

    public Iterable<Node> getPrimitiveNodes(){
        return this.primitiveNodes;
    }

    public Iterable<Node> getObjectNodes(){
        return this.objectNodes;
    }

    public void clearNodes(){
        primitiveNodes.clear();
        objectNodes.clear();
    }

    public ArrayList<Node> toVector(){
        ArrayList<Node> alist = new ArrayList<Node>();
        for(Node n: getPrimitiveNodes()){
            alist.add(n);
        }
        for(Node n: getObjectNodes()){
            alist.add(n);
        }
        return alist;
    }

    public boolean areConnected(Node node1, Node node2){
        return this.edges.areConnected(node1, node2);
    }

    public ArrayList<Node> getConnectedNodes(Node nodeInQuestion){
        ArrayList<Node> flattenedNodes = new ArrayList<Node>();
        for(Node candidateNode: primitiveNodes){
            if(areConnected(nodeInQuestion, candidateNode)){
                flattenedNodes.add(candidateNode);
            }
        }
        for(Node candidateNode: objectNodes){
            if(areConnected(nodeInQuestion, candidateNode)){
                flattenedNodes.add(candidateNode);
            }
        }
        return flattenedNodes;
    }

    public void connect(Node origin, Node target){
        edges.addEdge(new Edge(origin, target));
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Node n: toVector()){
            s.append(n);
        }
        return s.toString();
    }
}
