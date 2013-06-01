package drawcode.graphics;

import drawcode.model.Node;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 17/05/13
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public abstract class NodeView extends JPanel implements View {
    public abstract Node getNode();
}
