package drawcode.model;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 08/05/13
 * Time: 13:02
 * To change this template use File | Settings | File Templates.
 */
public class PrimitiveNode extends Node {

    String identifier;
    String value;

    public PrimitiveNode(String identifier, String value){
        this.identifier = identifier;
        this.value = value;
    }

    public PrimitiveNode(String identifier){  //These Constructors might be useless
        this.identifier = identifier;
        this.value = null;
    }

    public PrimitiveNode(){
        this.identifier = null;
        this.value = null;
    }

    public String getIdentifier(){
        return identifier;
    }

    public String value(){
        return value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String toString(){
        return "Identifier: " + getIdentifier() + ", Value: " + getValue();
    }
}
