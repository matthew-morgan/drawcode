package drawcode.graphics;

import drawcode.model.Graph;
import drawcode.model.ObjectNode;
import drawcode.model.PrimitiveNode;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 11/05/13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public enum ScreenCoordinates implements View{
    INSTANCE;
    private Graph graph;
    private final HashMap<PrimitiveNode, Point> primitiveCoords = new HashMap<PrimitiveNode, Point>();
    private final HashMap<ObjectNode, Point> objectCoords = new HashMap<ObjectNode, Point>();

    public void setGraph(Graph g){
        this.graph = g;
    }

    protected void add(PrimitiveNode n, Point p){
        primitiveCoords.put(n, p);
    }
    public void add(ObjectNode n, Point p){
        objectCoords.put(n, p);
    }
    public HashMap<PrimitiveNode, Point> getPrimitives(){
        return primitiveCoords;
    }
    public HashMap<ObjectNode, Point> getObjects(){
        return objectCoords;
    }

    public void refresh(){

        Iterator<PrimitiveNode> iteratorPrimitive = primitiveCoords.keySet().iterator();
        while (iteratorPrimitive.hasNext()) {
            if (!graph.isMemberNode(iteratorPrimitive.next())){
                iteratorPrimitive.remove();
            }
        }
        Iterator<ObjectNode> iteratorObject = objectCoords.keySet().iterator();
        while (iteratorObject.hasNext()) {
            if (!graph.isMemberNode(iteratorObject.next())){
                iteratorObject.remove();
            }
        }
    }
}
