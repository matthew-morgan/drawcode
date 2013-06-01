package drawcode.graphics;
import com.vldocking.swing.docking.DockKey;
import com.vldocking.swing.docking.Dockable;
import drawcode.model.Graph;

import javax.swing.*;
import java.awt.*;

class ElementListView extends JPanel implements Dockable, View {

    private final JList elementList;
    private final DockKey key = new DockKey("Element list");
    private final Graph graph;
    private final ElementListModel elm;

    public ElementListView(Graph graph) {
        this.graph = graph;
        setLayout(new BorderLayout());
        elm = new ElementListModel();
        elementList = new JList(elm);
        add(elementList);
        setVisible(true);
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
       elementList.setModel(new ElementListModel());
       repaint();
       invalidate();
    }

    private class ElementListModel extends AbstractListModel {
        @Override
        public int getSize() {
            return graph.toVector().size();
        }

        @Override
        public Object getElementAt(int index) {
            return graph.toVector().toArray()[index].toString();
        }

    }
}