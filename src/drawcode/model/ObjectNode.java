package drawcode.model;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 08/05/13
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
public class ObjectNode extends Node {
    private String className = "";
    private Nodes nodes = new Nodes();

    public ObjectNode(String className){
        this.className = className;
    }

    public ObjectNode(){

    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getClassName(){
        return className;
    }

    public void addNode(PrimitiveNode node){
        this.nodes.addNode(node);
    }

    public Nodes getNodes(){
        return this.nodes;
    }

    public int getLength(){
        return this.nodes.getLength();
    }

    @Override
    public String getIdentifier() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String value() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setIdentifier(String identifier) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setValue(String value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString(){
        return "Object :" + getClassName();
    }
}
