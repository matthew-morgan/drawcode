package drawcode.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 08/05/13
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
public class Nodes implements Iterable<Node>{

    private ArrayList<Node> nodes;

    public Nodes(){
        this.nodes = new ArrayList<Node>();
    }

    public void addNode(Node node){
        this.nodes.add(node);
    }

    public void removeNode(Node node){
        this.nodes.remove(node);
    }

    public void addNode(int index, Node node){
        this.nodes.add(index, node);
    }

    public Node getNode(int index){
        return this.nodes.get(index);
    }

    public Iterator<Node> iterator(){
        return this.nodes.iterator();
    }

    public boolean isMemberNode(Node n){
        return nodes.contains(n);
    }

    public void clear(){
        this.nodes.clear();
    }

    public int getLength(){
        return this.nodes.size();
    }
}
