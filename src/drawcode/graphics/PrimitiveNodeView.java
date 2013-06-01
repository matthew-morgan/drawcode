package drawcode.graphics;

import drawcode.model.Graph;
import drawcode.model.Node;
import drawcode.model.PrimitiveNode;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 17/05/13
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public class PrimitiveNodeView extends NodeView implements View {
    private final PrimitiveNode node;
    private final JTable table;
    private final DrawCode drawCode;
    private final Graph graph;
    private final GraphView graphView;

    public PrimitiveNodeView(PrimitiveNode node, DrawCode dc, Graph graph, GraphView graphView){
        super();
        this.graph = graph;
        this.drawCode = dc;
        this.graphView = graphView;
        setLayout(null);
        setSize(100, 25);
        this.node = node;
        this.table = new JTable(new NodeViewModel());
        add(table);

        ListSelectionModel lsm = table.getSelectionModel();
        lsm.addListSelectionListener(new SharedListSelectionHandler());

        table.setLocation(0, 0);
        table.setRowHeight(25);
        table.setSize(100, 25);
        table.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent me) {
                int x = me.getX() + getLocation().x;
                int y = me.getY() + getLocation().y;
                PrimitiveNodeView.this.setLocation(x, y);
                PrimitiveNodeView.this.drawCode.refreshViews();
            }

        });

        table.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){}
            public void keyPressed(KeyEvent e){}
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode()== KeyEvent.VK_DELETE){
                    PrimitiveNodeView.this.graph.removeNode(PrimitiveNodeView.this.node);
                    PrimitiveNodeView.this.drawCode.refreshViews();
                }
            }
        });
    }

    public Node getNode(){
        return this.node;
    }
    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()){
                graphView.selectNodeView(PrimitiveNodeView.this.node);
            }
        }
    }

    @Override
    public void refresh() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private class NodeViewModel extends AbstractTableModel {

        public NodeViewModel(){
           // addTableModelListener(new primitiveListener());
        }
        int rowCount = 1;
        int columnCount = 2;
        @Override
        public  String getColumnName(int col){
            return null;
        }

        @Override
        public int getRowCount() {
            return rowCount;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getColumnCount() {
            return columnCount;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(rowIndex == 0 && columnIndex == 0){
                return node.getIdentifier();
            }
            else if(rowIndex == 0 & columnIndex == 1){
                return node.getValue();
            }

            else return null;
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return true;
        }

        public void setValueAt(Object value, int row, int col) {
            if(row == 0 && col == 0){
                node.setIdentifier((String) value);
            }
            else if(row == 0 & col == 1){
                node.setValue((String) value);
            }
            fireTableCellUpdated(row, col);
            drawCode.refreshViews();
        }
    }

    /*
    private class primitiveListener implements TableModelListener{
        @Override
        public void tableChanged(TableModelEvent e) {
            int column = e.getColumn();
            int row = e.getFirstRow();
            if(column == 0 && row == 0){
                node.setIdentifier("foo");
            }
        }
    }
    */
}
