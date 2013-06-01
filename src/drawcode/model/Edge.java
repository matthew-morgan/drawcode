package drawcode.model;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 08/05/13
 * Time: 13:02
 * To change this template use File | Settings | File Templates.
 */
public class Edge {
    private Node rootNode;
    private Node targetNode;

    public Edge(Node rootNode, Node targetNode){
        this.rootNode = rootNode;
        this.targetNode = targetNode;
    }

    public boolean isMember(Node node){
        return node == rootNode || node == targetNode;
    }

    public boolean areConnected(Node node1, Node node2){
        return (node1 == targetNode && node2 == rootNode); // || (node1 == targetNode && node2 == rootNode);
    }

    public boolean isSubNode(Node node, Node candidateSubNode){
        return (node == rootNode && candidateSubNode == targetNode);
    }
}
