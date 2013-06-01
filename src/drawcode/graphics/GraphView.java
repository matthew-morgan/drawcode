package drawcode.graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import com.vldocking.swing.docking.*;
import drawcode.model.*;
import drawcode.utils.TwoItemRing;

import javax.swing.*;


public class GraphView extends JPanel implements Dockable, View {

    private final DockKey key = new DockKey("graphView");
    private final Graph graph;
    private final DrawCode drawCode;
    private final TwoItemRing<Node> ring;   //This is used to keep track of the last two things selected, in order to draw arrows between them

    public GraphView(Graph graph,  DrawCode drawCode){
        super();
        ring = new TwoItemRing<Node>();
        key.setFloatEnabled(true);
        setLayout(null);
        this.graph = graph;
        this.drawCode = drawCode;
        graphViewMouseListener gvml = new graphViewMouseListener();
        addMouseListener(gvml);

    }

    public TwoItemRing<Node> getSelectedNodes(){
        return this.ring;
    }

    public void selectNodeView(Node selectedNode){
        ring.add(selectedNode);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);

        for (Component component1 : getComponents()) {
            Node node1 = ((NodeView) component1).getNode();
            for(Component component2 : getComponents()){
                Node node2 = ((NodeView) component2).getNode();
                if(graph.areConnected(node1, node2)){
                    int component1x = component1.getX();
                    int component1y = component1.getY();
                    int component2x = component2.getX();
                    int component2y = component2.getY();
                    g2.drawLine(component1x,component1y, component2x, component2y);
                }
            }
        }
        //;
        //g2.draw(arrowHead);
        /*
        tx.setToIdentity();
        double angle = Math.atan2(line.y2-line.y1, line.x2-line.x1);
        tx.translate(line.x2, line.y2);
        tx.rotate((angle-Math.PI/2d));

        g2.setTransform(tx);
        g2.fill(arrowHead);
        */
    }


    public void addNode(PrimitiveNodeView n, Point p){
        this.add(n);
        n.setLocation(p.x, p.y);
    }

    public void addNode(ObjectNodeView n, Point p){
        this.add(n);
        n.setLocation(p.x, p.y);
        drawCode.registerListener(n);
    }

    @Override
    public DockKey getDockKey() {
        return key;
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public void refresh() {
        for (Component component : getComponents()) {
            Node n = ((NodeView) component).getNode();
            if(!graph.isMemberNode(n)){
                remove(component);
            }
        }
        revalidate();
        repaint();
    }

    private class graphViewMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            Point p = e.getPoint();
            if(SwingUtilities.isRightMouseButton(e)){
                ObjectNode n = new ObjectNode();
                graph.addNode(n);
                GraphView.this.addNode(new ObjectNodeView(n, drawCode, graph, GraphView.this), p);
                drawCode.refreshViews();
            }
            if(SwingUtilities.isLeftMouseButton(e)){
                PrimitiveNode n = new PrimitiveNode();
                graph.addNode(n);
                GraphView.this.addNode(new PrimitiveNodeView(n, drawCode, graph, GraphView.this), p);
                drawCode.refreshViews();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
