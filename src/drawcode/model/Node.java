package drawcode.model;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 08/05/13
 * Time: 13:02
 * To change this template use File | Settings | File Templates.
 */
public abstract class Node {

    public abstract String getIdentifier();


    public abstract String value();


    public abstract void setIdentifier(String identifier);

    public abstract void setValue(String value);

    public abstract String getValue();
}
