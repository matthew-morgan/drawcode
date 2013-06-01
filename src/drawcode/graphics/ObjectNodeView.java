package drawcode.graphics;

import drawcode.model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 17/05/13
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public class ObjectNodeView extends NodeView implements View {
    private final ObjectNode node;
    private final JTable table;
    private final DrawCode drawCode;
    private final int rowHeight = 16;
    private final Graph graph;
    private final GraphView graphView;
    private ArrayList<Node> connectedNodes;

    public ObjectNodeView(ObjectNode node, DrawCode drawCode, Graph graph, GraphView graphView){
        super();
        this.graph = graph;
        this.drawCode = drawCode;
        this.graphView = graphView;
        connectedNodes = new ArrayList<Node>();
        setLayout(null);
        setSize(100, rowHeight * 2);
        this.node = node;
        this.table = new JTable(new NodeViewModel(node));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(table);
        table.setLocation(0, 0);
        table.setSize(100, rowHeight * 2);
        table.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                int x = me.getX() + getLocation().x;
                int y = me.getY() + getLocation().y;
                ObjectNodeView.this.setLocation(x, y);
                ObjectNodeView.this.drawCode.refreshViews();
            }
        });
        table.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){}
            public void keyPressed(KeyEvent e){}
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode()== KeyEvent.VK_DELETE){
                    ObjectNodeView.this.graph.removeNode(ObjectNodeView.this.node);
                    ObjectNodeView.this.drawCode.refreshViews();
                }
            }
        });

        ListSelectionModel lsm = table.getSelectionModel();
        lsm.addListSelectionListener(new SharedListSelectionHandler());
    }

    private void setSize(){
        table.setSize(100, table.getRowCount() * rowHeight);
        setSize(100, table.getRowCount() * rowHeight);
    }

    @Override
    public void refresh() {
        connectedNodes = graph.getConnectedNodes(node);
        setSize();
    }

    @Override
    public Node getNode() {
        return this.node;
    }

    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()){
                int selectedIndex = e.getLastIndex();
                if(selectedIndex == 0){
                    graphView.selectNodeView(ObjectNodeView.this.node);
                    System.out.println(ObjectNodeView.this.node);
                }
                else if (selectedIndex > 0 && selectedIndex - 1 > connectedNodes.size()){
                    graphView.selectNodeView(ObjectNodeView.this.connectedNodes.get(selectedIndex - 1));
                    System.out.println(ObjectNodeView.this.connectedNodes.get(selectedIndex - 1));
                }
            }
        }
    }

    private class NodeViewModel extends AbstractTableModel {

        int rowCount;

        public NodeViewModel(ObjectNode node){
        }

        @Override
        public  String getColumnName(int col){
            return null;
        }

        @Override
        public int getRowCount() {
            return connectedNodes.size() + 2;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getColumnCount() {
            return 2;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex == 0 && columnIndex == 1){
                return node.getClassName();
            }

            else if(rowIndex == 0 && columnIndex == 0){
                return " ----------";
            }

            else if(columnIndex == 0 && rowIndex != 0 && rowIndex <= connectedNodes.size()){
                return connectedNodes.get(rowIndex - 1).getIdentifier();
            }
            else if(columnIndex == 1 && rowIndex != 0 && rowIndex <= connectedNodes.size()){
                return connectedNodes.get(rowIndex - 1).getValue();
            }


            else return null;
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            //The top-leftmost should not be editable
            return !(row == 0 && col == 0);
        }

        public void setValueAt(Object value, int row, int col) {

            if(row == 0 && col == 1){
                node.setClassName((String) value);
            }
            else{
                if(col == 0 && row != 0){
                    if(row - 1 >= connectedNodes.size()){
                        PrimitiveNode newNode = new PrimitiveNode((String) value);
                        graph.connect(node, newNode);
                        graph.addNode(newNode);
                    }
                    else{
                        connectedNodes.get(row - 1).setIdentifier((String) value);
                    }

                }
                if(col == 1 && row != 0 && (row - 1) < connectedNodes.size()){ //To avoid out-of-bounds on the node's ArrayList
                    connectedNodes.get(row - 1).setValue((String) value);
                }
            }
            drawCode.refreshViews();
        }


    }

}

